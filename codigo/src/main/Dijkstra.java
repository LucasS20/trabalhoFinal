package main;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

public class Dijkstra {

    public static Grafo caminhosMaisCurtos(Grafo graph, Vertice raiz) {
        raiz.setDistance(0);

        Set<Vertice> settledVertice = new HashSet<>();
        Set<Vertice> unsettledVertice = new HashSet<>();

        unsettledVertice.add(raiz);

        while (unsettledVertice.size() != 0) {
            Vertice currentVertice = getLowestDistanceNode(unsettledVertice);
            unsettledVertice.remove(currentVertice);
            for (Map.Entry<Vertice, Integer> adjacencyPair : currentVertice.getAdjascentes().entrySet()) {
                Vertice adjacentNode = adjacencyPair.getKey();
                Integer edgeWeight = adjacencyPair.getValue();
                if (!settledVertice.contains(adjacentNode)) {
                    CalculateMinimumDistance(adjacentNode, edgeWeight, currentVertice);
                    unsettledVertice.add(adjacentNode);
                }
            }
            settledVertice.add(currentVertice);
        }
        return graph;
    }

    /**
     * Método privado que calcula a distancia minima a partir do nó raiz
     * @param evaluationNode
     * @param edgeWeigh peso da aresta
     * @param sourceNode Vertice de inicio
     */
    private static void CalculateMinimumDistance(Vertice evaluationNode, Integer edgeWeigh, Vertice sourceNode) {
        Integer sourceDistance = sourceNode.getDistance();
        if (sourceDistance + edgeWeigh < evaluationNode.getDistance()) {
            evaluationNode.setDistance(sourceDistance + edgeWeigh);
            LinkedList<Vertice> shortestPath = new LinkedList<>(sourceNode.getCaminhos());
            shortestPath.add(sourceNode);
            evaluationNode.setCaminhos(shortestPath);
        }
    }

    /**
     * Método privado que retorna qual o vertice que está mais perto entre as possiveis escolhas
     * @param unsettledNodes
     * @return
     */
    private static Vertice getLowestDistanceNode(Set<Vertice> unsettledNodes) {
        Vertice lowestDistanceNode = null;
        int lowestDistance = Integer.MAX_VALUE;
        for (Vertice vertice : unsettledNodes) {
            int verticeDistance = vertice.getDistance();
            if (verticeDistance < lowestDistance) {
                lowestDistance = verticeDistance;
                lowestDistanceNode = vertice;
            }
        }
        return lowestDistanceNode;
    }
}
