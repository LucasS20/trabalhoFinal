package main;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

public class Dijkstra {

    public static Grafo caminhosMaisCurtos(Grafo graph, Vertice raiz) {
        raiz.setDistance(0);

        Set<Vertice> settledNodes = new HashSet<>();
        Set<Vertice> unsettledNodes = new HashSet<>();

        unsettledNodes.add(raiz);

        while (unsettledNodes.size() != 0) {
            Vertice currentNode = getLowestDistanceNode(unsettledNodes);
            unsettledNodes.remove(currentNode);
            for (Map.Entry<Vertice, Integer> adjacencyPair :
                    currentNode.getAdjascentes().entrySet()) {
                Vertice adjacentNode = adjacencyPair.getKey();
                Integer edgeWeight = adjacencyPair.getValue();
                if (!settledNodes.contains(adjacentNode)) {
                    CalculateMinimumDistance(adjacentNode, edgeWeight, currentNode);
                    unsettledNodes.add(adjacentNode);
                }
            }
            settledNodes.add(currentNode);
        }
        return graph;
    }

    private static void CalculateMinimumDistance(Vertice evaluationNode, Integer edgeWeigh, Vertice sourceNode) {
        Integer sourceDistance = sourceNode.getDistance();
        if (sourceDistance + edgeWeigh < evaluationNode.getDistance()) {
            evaluationNode.setDistance(sourceDistance + edgeWeigh);
            LinkedList<Vertice> shortestPath = new LinkedList<>(sourceNode.getCaminhos());
            shortestPath.add(sourceNode);
            evaluationNode.setCaminhos(shortestPath);
        }
    }

    private static Vertice getLowestDistanceNode(Set<Vertice> unsettledNodes) {
        Vertice lowestDistanceNode = null;
        int lowestDistance = Integer.MAX_VALUE;
        for (Vertice node : unsettledNodes) {
            int nodeDistance = node.getDistance();
            if (nodeDistance < lowestDistance) {
                lowestDistance = nodeDistance;
                lowestDistanceNode = node;
            }
        }
        return lowestDistanceNode;
    }
}
