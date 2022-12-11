package main;

import java.util.*;

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

public class Vertice {

    private ABB<Aresta> arestas;
    private boolean visitado;
    private final String id;
    private Map<Vertice, Integer> adjascentes = new HashMap<>();
    private List<Vertice> caminhos = new LinkedList<>();
    private Integer distancias = Integer.MAX_VALUE;

    /**
     * Construtor para criação de vértice identificado
     *
     * @param id    do vértice a ser criado (atributo final).
     */
    public Vertice(String id) {
        this.id = id;
        this.arestas = new ABB<Aresta>();
        this.visitado = false;
    }

    public Vertice() {
        this.id = "";
    }

    /**
     * Adiciona uma aresta neste vértice para um destino
     *
     * @param peso    Peso da aresta (1 para grafos não ponderados)
     * @param destino Vértice de destino
     */
    public boolean addAresta(double peso, Vertice destino) {
        return this.arestas.add(destino.getId(), new Aresta(peso, destino));
    }

    public boolean delAresta(String destino) {
        return this.arestas.del(destino);
    }

    /**
     * Verifica se já existe aresta entre este vértice e um destino.
     *
     * @param destino Vértice de destino
     * @return TRUE se existe aresta, FALSE se não
     */
    public Aresta existeAresta(String destino) {
        return this.arestas.find(destino);

    }

    /**
     * Retorna o grau do vértice
     *
     * @return Grau (inteiro não negativo)
     */
    public int grau() {
        return this.arestas.size();
    }

    public void visitar() {
        this.visitado = true;
    }

    public void limparVisita() {
        this.visitado = false;
    }

    public boolean visitado() {
        return this.visitado;
    }


    public String getId() {
        return this.id;
    }

    public ArrayList<Vertice> getVizinhos() {
        ArrayList<Vertice> vizinhos = new ArrayList<>();
        Aresta[] arestasArray = new Aresta[this.arestas.size()];
        arestasArray = this.arestas.allElements(arestasArray);

        for (Aresta aresta : arestasArray) {
            vizinhos.add(aresta.getDestino());
        }

        return vizinhos;
    }



    public void setDistance(int i) {
        this.distancias = i;
    }

    public Integer getDistance() {
        return distancias;
    }

    public List<Vertice> getCaminhos() {
        return caminhos;
    }

    public void setCaminhos(List<Vertice> caminhos) {
        this.caminhos = caminhos;
    }

    public Map<Vertice, Integer> getAdjascentes() {
        return this.adjascentes;
    }
}
