package main;

import java.util.*;


public class App {

    public static void main(String[] args) throws Exception {
        Scanner teclado = new Scanner(System.in);
        String opcao;
        GrafoMutavel grafoCidades = gerarGrafo();

        do {
            System.out.println("Escolha o qual das opções você quer executar");
            System.out.println("1. Grau e Vizinhos\n2.delVerticeOudelAresta\n3.Componentes\n4.CaminhoMinimo");
            opcao = teclado.nextLine();

            switch (opcao.toLowerCase()) {
                case "1" -> {
                    System.out.println("Qual cidade você deseja consultar o Grau e os Vizinhos " +
                            "(Modelo: nome_cidade, nome_estado): ");
                    String cidade = teclado.nextLine();
                    grafoCidades.grauEVizinhos(cidade);
                }

                case "2" -> delVerticeOuAresta(grafoCidades);

                case "3" -> System.out.println("Incompleto");

                case "4" -> {
                    System.out.println("Digite a cidade e estado de origem da Aresta que quer deletar " +
                            "(Modelo: nome_cidade, nome_estado):");
                    String origem = teclado.nextLine();
                    System.out.println("Digite a cidade e estado destino que quer deletar " +
                            "(Modelo: nome_cidade, nome_estado):");
                    String destino = teclado.nextLine();
                    System.out.println(
                            grafoCidades.caminhoMinimo(
                                    grafoCidades.vertices.find(origem),
                                    grafoCidades.vertices.find(destino)
                            ));

                    grafoCidades.clearVisitas();
                }

                case "fim" -> System.out.println("Sistema encerrado");

                default -> System.out.println("Opção invalida");

            }
        } while(!opcao.equals("FIM"));
    }

    private static void delVerticeOuAresta(GrafoMutavel grafoCidades) throws Exception {
        Scanner teclado = new Scanner(System.in);
        String opcao;

        System.out.println("Deseja deletar Vertice ou Aresta");
        opcao = teclado.nextLine();

        switch (opcao.toLowerCase()) {
            case "vertice" -> {
                System.out.println("Digite a cidade e estado que quer deletar " +
                        "(Modelo: nome_cidade, nome_estado):");
                String cidade = teclado.nextLine();
                grafoCidades.removeVertice(cidade);
            }
            case "aresta" -> {
                System.out.println("Digite a cidade e estado de origem da Aresta que quer deletar " +
                        "(Modelo: nome_cidade, nome_estado):");
                String origem = teclado.nextLine();
                    System.out.println("Digite a cidade e estado destino que quer deletar " +
                            "(Modelo: nome_cidade, nome_estado):");
                String destino = teclado.nextLine();
                grafoCidades.delAresta(origem, destino);
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
