package it.unibo.generics.graph.api;

import java.util.Collection;
import java.util.Collections;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class GraphImpl<N> implements Graph<N>{

    private final Map<N, Set<N>> edges = new LinkedHashMap<>();
    private final FringeAccumulationStrategy<Step<N>> strategy;

    /**
     * Builds a new Graph with the provided strategy.
     * 
     * @param strategy
     *            the strategy to be used to search in the graph
     */
    public GraphImpl(final FringeAccumulationStrategy<Step<N>> strategy) {
        this.strategy = Objects.requireNonNull(strategy);
    }


    public void addNode(N node) {
        edges.putIfAbsent(node, new HashSet<>());
    }

    public void addEdge(N source, N target) {
        if (nodesExist(source, target)) {
            edges.get(source).add(target);
        }
    }

    public Set<N> nodeSet() {
        return new HashSet<>(edges.keySet());
    }

    public Set<N> linkedNodes(N node) {
        return edges.get(node);
    }

    public List<N> getPath(N source, N target) {
        if (nodesExist(source, target)) {
            return graphSearch(source, target);
        } else {
            return Collections.emptyList();
        }
    }

    private List<N> graphSearch(final N source, final N target) {
        final Deque<Step<N>> fringe = new LinkedList<>();
        fringe.add(new Step<>(source));
        final Set<N> alreadyVisited = new HashSet<>();
        while (!fringe.isEmpty() && alreadyVisited.size() < getNodesCount()) {
            final Step<N> lastStep = fringe.removeFirst();
            final N currentNode = lastStep.getPosition();
            if (currentNode.equals(target)) {
                return lastStep.getPath();
            } else if (!alreadyVisited.contains(currentNode)) {
                alreadyVisited.add(currentNode);
                updateFringe(fringe, lastStep);
            }
        }
        return Collections.emptyList();
    }
    
    private int getNodesCount() {
        return edges.keySet().size();
    }

    private boolean nodesExist(final N... nodes) {
        for (final N node : nodes) {
            if (!edges.containsKey(node)) {
                throw new IllegalArgumentException("No such node: " + node);
            }
        }
        return true;
    }

    private void updateFringe(final Deque<Step<N>> fringe, final Step<N> lastStep) {
        final N currentNode = lastStep.getPosition();
        for (final N reachableNode : linkedNodes(currentNode)) {
            strategy.addToFringe(fringe, new Step<>(lastStep, reachableNode));
        }
    }
}