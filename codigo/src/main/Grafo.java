package main;

/**
 * MIT License
 * <p>
 * Copyright(c) 2021 João Caram <caram@pucminas.br>
 * <p>
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * <p>
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * <p>
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

import java.util.*;

/**
 * Classe básica para um Grafo simples
 */
public class Grafo {
    public final String nome;
    protected ABB<Vertice> vertices;
    /**
     * Construtor. Cria um grafo vazio com capacidade para MAX_VERTICES
     */
    public Grafo(String nome) {
        this.nome = nome;
        this.vertices = new ABB<>();
    }

    /**
     *  Verifica existencia do vertice
     * @param id do Vertice procurado
     * @return returna o Vertice ou null caso não exista
     */
    public Vertice existeVertice(String id) throws Exception {
        try {
            return this.vertices.find(id);
        } catch (Exception e) {
            throw new Exception("Vertice não existe");
        }

    }

    public void grauEVizinhos(String id) throws Exception {
        Vertice vertice = this.existeVertice(id);

        System.out.println("Grau: " + vertice.grau());

        vertice.getVizinhos().forEach(v -> {
            try {
                System.out.println("Cidades vizinhas: " + v.getId());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        });
    }

    /**
     * Verifica existencia de aresta entre vertices
     * @param origem Vértice de origem
     * @param destino Vértice de destino
     * @return null se a aresta não existe
     */
    public Aresta existeAresta(String origem, String destino) throws Exception {
        Vertice saida = this.existeVertice(origem);
        Vertice chegada = this.existeVertice(destino);
        if (saida != null && chegada != null) {
            return saida.existeAresta(destino);
        }

        return null;
    }

    /**
     * Gera grafo completo.
     * @return TRUE para grafo completo, FALSE caso contrário
     */
    public boolean completo() {
        int numVertices = this.ordem();
        int numArestas = this.tamanho();

        return numVertices * (numVertices - 1) / 2 == numArestas;
    }


    /**
     *  A ordem de um grafo se da pelo numero de vertices que ele possui
     * @return numero de vertices
     */
    public int ordem() {
        return this.vertices.size();
    }

    /**
     * O tamanho de um grafo se da pelo soma do numero de arestas e do numero de vertices
     * @return a soma do numero de arestar e do numero de vertices do grafo
     */
    public int tamanho() {
        int tamanho = 0;

        Vertice[] vertices = new Vertice[this.ordem()];
        vertices = this.vertices.allElements(vertices);

        for (Vertice vertice : vertices) {
            tamanho += vertice.grau();
        }

        return tamanho / 2 + this.ordem();
    }

    public Vertice[] getVertices() {
        Vertice[] vertices1 = new Vertice[this.vertices.size()];
        return this.vertices.allElements(vertices1);
    }

    private TreeMap<String, Vertice> getVerticesAbb() {
        return this.vertices.clone();
    }

    public Grafo clone(String nome) {
        Grafo grafoClone = new Grafo(nome);
        grafoClone.vertices.addAll(getVerticesAbb());

        return grafoClone;
    }

    public double caminhoMinimo(Vertice origem, Vertice destino) {
        origem.setDistance(0);
        origem.visitar();
        double distancia = destino.getDistance();

        if(origem.getVizinhos().contains(destino)) {
            destino.setDistance((origem.getDistance() + origem.existeAresta(destino.getId()).peso()));
            destino.setAnterior(origem);

            return destino.getDistance();
        }

        for(Vertice vizinho : origem.getVizinhos()) {
            if(!vizinho.visitado()) {
                vizinho.setDistance(origem.getDistance() + origem.existeAresta(vizinho.getId()).peso());
                double novaDistancia = caminhoMinimo(vizinho, destino);
                if (distancia > novaDistancia) {
                    distancia = novaDistancia;
                }
            }
        }

        return distancia;
    }

    public void clearVisitas() {
        Vertice[] v = new Vertice[this.vertices.size()];
        v = this.vertices.allElements(v);

        for (Vertice vertice : v)
            vertice.limparVisita();
    }
}
    


