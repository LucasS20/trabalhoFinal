package main;

import java.io.*;
import java.util.Collection;

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

    /**
     * Construtor. Cria um grafo vazio com capacidade para MAX_VERTICES
     */
    public Grafo(String nome){
        this.nome = nome;
        this.vertices = new ABB<>();
    }
    public void carregar() {}

    public void salvar(String path) throws IOException {
        File arquivo = new File(path);

        FileReader file = new FileReader(arquivo);
        BufferedReader reader = new BufferedReader(file);

        String line;
        line = reader.readLine();

        int id;
        int idOrigem = 0;
        String[] splitMatrix = line.split(";");
        boolean firstLine = true;

        while(line != null) {
            boolean firstValue = true;

            if (firstLine) {
                for (String matrix : splitMatrix) {
                    if(!matrix.equals("")) {
                        id = Integer.parseInt(matrix);
                        this.addVertice(id);
                    }
                }
                firstLine = false;
            } else {
                for (String matrix : splitMatrix) {
                    if (firstValue) {
                            idOrigem = Integer.parseInt(matrix);
                        firstValue = false;
                    } else if (matrix.equals("1")) {
                        id = Integer.parseInt(matrix);
                        this.addAresta(idOrigem, id);
                    }
                }

                line = reader.readLine();
            }
        }
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
//    public boolean completo(){
//        Vertice[] verticesArray = vertices.allElements(vertices);
//        for(int i=0;i < vertices.size(); i++) {
//            for(int j=0;j < vertices.size(); j++) {
//                Aresta existeAreta = this.existeAresta(verticesArray[i], verticesArray[j]);
//                if(existeAreta != null && i == j) {
//                  return false;
//                } else if(existeAreta == null) {
//                    addAresta(verticesArray[i], verticesArray[j]);
//                }
//            }
//        }
//
//       return true;
//    }

//    public Grafo subGrafo(Lista<Vertice> vertices){
//        Grafo subgrafo = new Grafo("Subgrafo de "+this.nome);
//        boolean adiciounou;
//        for(Vertice vertice : vertices) {
//            for (Vertice vertice2 : vertices) {
//                subgrafo.addVertice(vertice);
//                int indexVertice = vertices.indexOf(vertice);
//                int indexVertice2 = vertices.indexOf(vertice2);
//                Aresta existeAreta = this.existeAresta(indexVertice, indexVertice2);
//                Aresta existeAretaSubgrafo = subgrafo.existeAresta(indexVertice, indexVertice2);
//                if (existeAreta != null && existeAretaSubgrafo == null) {
//                    subgrafo.addAresta(indexVertice, indexVertice2);
//                }
//            }
//        }
//
//        return subgrafo;
//    }
    
//    public int tamanho(){
//        Vertice[] verticesArray = vertices.allElements(this.vertices);
//        int numArestar = contarArestar(verticesArray);
//        int numVertice = this.vertices.size();
//
//    }

//    private int contarArestar(Vertice[] verticesArray) {
//        int contador = 0;
//        for(int i=0;i < verticesArray.length; i++) {
//            for (int j=i; j < verticesArray.length; j++) {
//                Aresta existeAreta = this.existeAresta(verticesArray[i], verticesArray[j]);
//                if (existeAreta != null) {
//                    contador++;
//                }
//            }
//        }
//
//        return contador;
//    }

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
        Integer[] keys = this.vertices.allKeys();
        Integer k = keys[0];

        Vertice vertice;

        for(Integer key : keys) {
            if(key != null) {
                vertice = this.existeVertice(key);
                tamanho += vertice.grau();
            }
        }

        return tamanho/2 + this.ordem();
    }

}
