package main;

import java.io.*;
import java.nio.Buffer;
import java.util.List;
import java.util.Vector;

/**
 * MIT License
 *
 * Copyright(c) 2021 João Caram <caram@pucminas.br>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

/** 
 * Classe básica para um Grafo simples
 */
public class Grafo {
    public final String nome;
    private ABB<Vertice> vertices;

    private final String PATH = "docs/grafos/";

    /**
     * Construtor. Cria um grafo vazio com capacidade para MAX_VERTICES
     */
    public Grafo(String nome){
        this.nome = nome;
        this.vertices = new ABB<>();
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

    /**
     *  Verifica existencia do vertice
     * @param idVertice id do Vertice procurado
     * @return returna o Vertice ou null caso não exista
     */
    public Vertice existeVertice(int idVertice){
        return this.vertices.find(idVertice);
    }

    /**
     * Verifica existencia de aresta entre vertices
     * @param origem Vértice de origem
     * @param destino Vértice de destino
     * @return null se a aresta não existe
     */
    public Aresta existeAresta(int origem, int destino){
        Vertice saida = this.existeVertice(origem);
        Vertice chegada = this.existeVertice(destino);
        if(saida != null && chegada != null) {
            return saida.existeAresta(destino);
        }

        return null;
    }
    
    /**
     * Gera grafo completo.
     * @return TRUE para grafo completo, FALSE caso contrário
     */
    public boolean completo(){
        int numVertices = this.ordem();
        int numArestas = this.tamanho();
        if(numVertices*(numVertices-1)/2 == numArestas)
            return true;

        return false;
    }


    /**
     *  A ordem de um grafo se da pelo numero de vertices que ele possui
     * @return numero de vertices
     */
    public int ordem(){
        return this.vertices.size();
    }

    /**
     * O tamanho de um grafo se da pelo soma do numero de arestas e do numero de vertices
     * @return a soma do numero de arestar e do numero de vertices do grafo
     */
    public int tamanho() {
        int tamanho = 0;

        for(Vertice vertice : getVertices()) {
            tamanho += vertice.grau();
        }

        return tamanho/2 + this.ordem();
    }

    private Vertice[] getVertices() {
        Integer[] keys = this.vertices.allKeys();
        Integer tamanho = keys.length;
        Vertice[] vertices = new Vertice[tamanho];

        for(int i = 0; i < tamanho; i++) {
            if(keys[i] != null)
                vertices[i] = this.existeVertice(keys[i]);
        }

        return vertices;
    }

//    public boolean euleriano() {}
//
//    public Lista<Vertice> caminhoEuleriano() {}

}
