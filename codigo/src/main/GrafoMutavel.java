package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class GrafoMutavel extends Grafo {
    protected final String PATH = "docs/grafos/";

    public GrafoMutavel(String nome) {
        super(nome);
    }

    public void carregar(String nome) throws IOException {
        String path = PATH + nome + ".csv";
        File arquivo = new File(path);

        FileReader file = new FileReader(arquivo);
        BufferedReader reader = new BufferedReader(file);


        String line;
        line = reader.readLine();
        int id;
        int idOrigem = 0;
        boolean firstLine = true;
        String[] splitMatrix;

        while(line != null) {
            splitMatrix = line.split(";");
            boolean firstValue = true;

            if (firstLine) {
                for (String matrix : splitMatrix) {
                    if(!matrix.equals(" ")) {
                        id = Integer.parseInt(matrix);
                        this.addVertice(id);
                    }
                }
                firstLine = false;
            } else {
                for (String matrix : splitMatrix) {
                    if (firstValue) {
                        if(!matrix.equals(" ")) {
                            idOrigem = Integer.parseInt(matrix);
                            firstValue = false;
                        }
                    } else if (matrix.equals("1")) {
                        id = Integer.parseInt(matrix);
                        this.addAresta(idOrigem, id);
                    }
                }

                line = reader.readLine();
            }
        }
        reader.close();
        file.close();
    }


    public void salvar(String nome) throws IOException {
        String path = PATH + nome + ".csv";

        FileWriter writer = new FileWriter(path);
        BufferedWriter bfWriter = new BufferedWriter(writer);
        int cont = 0;
        Integer[] keys = this.vertices.allKeys();
        String keyString;

        bfWriter.append(" ;");

        for(Integer key : keys) {
            keyString = key.toString();
            if(cont == keys.length - 1) {
                bfWriter.append(keyString).append("\n");
            } else {
                bfWriter.append(keyString).append(";");
            }
            cont++;
        }

        cont = 0;

        for(Integer keyOrigem : keys) {
            keyString = keyOrigem.toString();
            bfWriter.append(keyString).append(";");
            for(Integer keyDestino : keys) {
                if((this.existeAresta(keyOrigem, keyDestino)) != null) {
                    if (cont == keys.length - 1)
                        bfWriter.append("1\n");
                    else
                        bfWriter.append("1;");
                } else {
                    if (cont == keys.length - 1)
                        bfWriter.append("0\n");

                    else
                        bfWriter.append("0;");
                }
                cont++;
            }
            cont = 0;
        }
        bfWriter.close();
        writer.close();
    }

    /**
     * Adiciona, se possível, um vértice ao grafo. O vértice é auto-nomeado com o próximo id disponível.
     * @param id id do vertice a ser adicionado.
     * @return true se adicionou e false se não
     */
    public boolean addVertice(int id){
        Vertice novo = new Vertice(id);
        return this.vertices.add(id, novo);
    }

    public boolean removeVertice(int id) {
        return this.vertices.del(id);
    }

    /**
     * Adiciona uma aresta entre dois vértices do grafo. 
     * Não verifica se os vértices pertencem ao grafo.
     * @param origem Vértice de origem
     * @param destino Vértice de destino
     * @return true se adicionou e false se não
     */
    public boolean addAresta(int origem, int destino){
        boolean adicionou = false;
        Vertice saida = this.existeVertice(origem);
        Vertice chegada = this.existeVertice(destino);

        if(saida != null && chegada != null) {
            adicionou = saida.addAresta(1, destino);
        }

        return adicionou;
    }
    
    public boolean delAresta(int origem, int destino) {
        Vertice saida = this.existeVertice(origem);
        Vertice chegada = this.existeVertice(destino);
        if(saida.delAresta(destino) && chegada.delAresta(destino))
            return true;

        return false;    
    }
}
