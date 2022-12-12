package main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class GrafoMutavel extends Grafo {
    protected final String PATH = "docs/grafos/";

    public GrafoMutavel(String nome) {
        super(nome);
    }

    public ArrayList<Vertice> getVizinhos(Vertice vertice) {
        return vertice.getVizinhos();
    }

    public void carregar(String nome) throws IOException {
//        String path = PATH + nome + ".csv";
//        File arquivo = new File(path);
//
//        FileReader file = new FileReader(arquivo);
//        BufferedReader reader = new BufferedReader(file);
//
//
//        String line;
//        line = reader.readLine();
//        int id;
//        int idOrigem = 0;
//        boolean firstLine = true;
//        String[] splitMatrix;
//
//        while(line != null) {
//            splitMatrix = line.split(";");
//            boolean firstValue = true;
//
//            if (firstLine) {
//                for (String matrix : splitMatrix) {
//                    if(!matrix.equals(" ")) {
//                        id = Integer.parseInt(matrix);
//                        this.addVertice(id);
//                    }
//                }
//                firstLine = false;
//            } else {
//                for (String matrix : splitMatrix) {
//                    if (firstValue) {
//                        if(!matrix.equals(" ")) {
//                            idOrigem = Integer.parseInt(matrix);
//                            firstValue = false;
//                        }
//                    } else if (matrix.equals("1")) {
//                        id = Integer.parseInt(matrix);
//                        this.addAresta(idOrigem, id);
//                    }
//                }
//
//                line = reader.readLine();
//            }
//        }
//        reader.close();
//        file.close();
    }


    public void salvar(String nome) throws IOException {
//        String path = PATH + nome + ".csv";
//
//        FileWriter writer = new FileWriter(path);
//        BufferedWriter bfWriter = new BufferedWriter(writer);
//        int cont = 0;
//        Vertice[] vertices = new Vertice[this.ordem()];
//        vertices = this.vertices.allElements(vertices);
//        String[] keys = this.vertices.allKeys();
//        String keyString;
//
//        bfWriter.append(" ;");
//
//        for(Vertice vertice : vertices) {
//
//            if(cont == keys.length - 1) {
//                bfWriter.append((char) vertice.getId()).append("\n");
//            } else {
//                bfWriter.append((char) vertice.getId()).append(";");
//            }
//            cont++;
//        }
//
//        cont = 0;
//
//        for(String keyOrigem : keys) {
//            keyString = keyOrigem.toString();
//            bfWriter.append(keyString).append(";");
//            for(Integer keyDestino : keys) {
//                if((this.existeAresta(keyOrigem, keyDestino)) != null) {
//                    if (cont == keys.length - 1)
//                        bfWriter.append("1\n");
//                    else
//                        bfWriter.append("1;");
//                } else {
//                    if (cont == keys.length - 1)
//                        bfWriter.append("0\n");
//
//                    else
//                        bfWriter.append("0;");
//                }
//                cont++;
//            }
//            cont = 0;
//        }
//        bfWriter.close();
//        writer.close();
    }

    /**
     * Adiciona, se possível, um vértice ao grafo. O vértice é auto-nomeado com o próximo id disponível.
     * @param nome do vertice a ser adicionado.
     * @return true se adicionou e false se não
     */
    public boolean addVertice(String nome){
        Vertice novo = new Vertice(nome);
        return this.vertices.add(nome, novo);
    }

    public boolean addVertice(Vertice vertice) {
        return this.vertices.add(vertice.getId(), vertice);
    }

    public boolean removeVertice(String id) {
        return this.vertices.del(id);
    }

    /**
     * Adiciona uma aresta entre dois vértices do grafo. 
     * Não verifica se os vértices pertencem ao grafo.
     * @param origem Vértice de origem
     * @param destino Vértice de destino
     * @return true se adicionou e false se não
     */
    public boolean addAresta(String origem, String destino) throws Exception {
        boolean adicionou = false;
        Vertice saida = this.existeVertice(origem);
        Vertice chegada = this.existeVertice(destino);

        if(saida != null && chegada != null) {
            adicionou = saida.addAresta(1, chegada);
        }

        return adicionou;
    }
    
    public boolean delAresta(String origem, String destino) throws Exception {
        Vertice saida = this.existeVertice(origem);
        Vertice chegada = this.existeVertice(destino);
        return saida.delAresta(origem) && chegada.delAresta(destino);
    }

    private int getVerticesSize() {
        return this.vertices.size();
    }
    public Vertice getVerticeByName(String name) {
        Vertice[] vertices = new Vertice[this.ordem()];
        return Arrays.stream(this.vertices.allElements(vertices))
                .filter(v -> v.getId().equals(name))
                .findFirst()
                .orElse(null);
    }
}
