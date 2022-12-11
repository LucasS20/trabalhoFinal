package main;

import java.lang.management.GarbageCollectorMXBean;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class App {

    public static void main(String[] args) throws Exception {
        GrafoMutavel grafoCidades = gerarGrafo();

//       grafoCidades.grauEVizinhos("São Paulo, São Paulo");
//
//       delVerticeOuAresta(grafoCidades);


        components(grafoCidades);

//        getCaminhoMinimo("Belo Horizonte, Minas Gerais", "Abaeté, Minas Gerais", grafoCidades);


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

    private static void getCaminhoMinimo(String origem, String destino, GrafoMutavel grafoCidades) {
        Grafo caminhos = Dijkstra.caminhosMaisCurtos(grafoCidades, grafoCidades.existeVertice(origem));
        List<Vertice> vertice = Arrays.stream(caminhos.getVertices()).filter(v -> v.getDistance() != Integer.MAX_VALUE).toList();

        System.out.println(vertice.stream().filter(v -> v.getId().equals(destino)).findFirst().get().getDistance());
    }

    private static void components(Grafo grafoCidades) {
        List<Vertice> vertices = new ArrayList<>();
        for (Vertice cidade : grafoCidades.getVertices()) {
            Grafo caminhos = grafoCidades.clone("Grafocaminhos");
            if(!vertices.contains(cidade)) {
                caminhos = Dijkstra.caminhosMaisCurtos(caminhos, caminhos.existeVertice(cidade.getId()));
                vertices = Arrays.stream(caminhos.getVertices()).filter(v -> v.getDistance() != Integer.MAX_VALUE).toList();
            }

            System.out.println(vertices);
        }
    }
}
