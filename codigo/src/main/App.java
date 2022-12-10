package main;

import java.util.*;
import java.util.stream.Collectors;


public class App {


    public static void main(String[] args) throws Exception {
        GrafoMutavel grafoCidades = gerarGrafo();

//       grafoCidades.grauEVizinhos("São Paulo, São Paulo");
//
//       delVerticeOuAresta(grafoCidades);

        HashMap<Vertice, Integer> relacao = buscarComponentesConexos(grafoCidades);
        relacao.forEach((v, i) -> {
        });

        Map<Integer, ArrayList<Vertice>> reverseMap = new HashMap<>(
                relacao.entrySet().stream()
                        .collect(Collectors.groupingBy(Map.Entry::getValue)).values().stream()
                        .collect(Collectors.toMap(
                                item -> item.get(0).getValue(),
                                item -> new ArrayList<>(
                                        item.stream()
                                                .map(Map.Entry::getKey)
                                                .collect(Collectors.toList())
                                ))
                        ));


        System.out.println(reverseMap);
    }

    public static HashMap<Vertice, Integer> buscarComponentesConexos(GrafoMutavel grafoCidades) {
        HashMap<Vertice, Integer> relacao = new HashMap<>();
        int id = 0;

        for (Vertice v: grafoCidades.getVertices()) {
            if (relacao.get(v) == null) {
                determinaComponenteConexo(v, id, grafoCidades, relacao);
                id++;
            }
        }
        return relacao;
    }

    private static void determinaComponenteConexo(Vertice cidade, int id, GrafoMutavel grafoCidades, Map<Vertice, Integer> relacao) {
        relacao.put(cidade, id);

        grafoCidades.getVizinhos(cidade).forEach(c -> {
            if (relacao.get(c) == null) {
                determinaComponenteConexo(c, id, grafoCidades, relacao);
            }
        });
    }

    private static void delVerticeOuAresta(GrafoMutavel grafoCidades) {
        Scanner teclado = new Scanner(System.in);
        String opcao;

        System.out.println("Vertice ou Aresta");
        opcao = teclado.nextLine();

        switch (opcao) {
            case "vertice" -> {
                System.out.println("Digite a cidade e estado que quer deletar: ");
                String cidade = teclado.nextLine();
                grafoCidades.removeVertice(cidade);
            }
            case "aresta" -> {
                System.out.println("Digite a cidade e estado de origem da Aresta que quer deletar: ");
                String cidade = teclado.nextLine();
                    System.out.println("Digite a cidade e estado destino que quer deletar: ");
                String estado = teclado.nextLine();
                grafoCidades.delAresta(cidade, estado);
            }
        }
    }

    private static GrafoMutavel gerarGrafo() {
        LerCSV arquivo = new LerCSV("docs/grafos/br.csv");
        ArrayList<Cidade> listaCidades = arquivo.lerCidades();
        GrafoPonderado grafoCidades = new GrafoPonderado("GrafoCidades");
        for (Cidade cidade : listaCidades) {
            grafoCidades.addVertice(cidade);

            HashMap<Double, Cidade> cidadeDistancias = new HashMap<>();
            ArrayList<Double> distancias = new ArrayList<>();
            listaCidades.forEach(c -> {
                double distancia = cidade.distanciaEntreCidades(c);
                if(distancia != 0) {
                    cidadeDistancias.put(distancia, c);
                    distancias.add(distancia);
                }
            });


            distancias.stream().sorted().limit(4)
                    .forEach(ds -> {
                        cidade.addAresta(ds, cidadeDistancias.get(ds));
                    });

        }

        return grafoCidades;
    }
}
