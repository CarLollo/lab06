package it.unibo.generics.graph.api;

import java.util.Deque;

public interface FringeAccumulationStrategy<S> {
    /**
     * @param fringe
     *            the queue representing the fringe
     * @param step
     *            the step to add
     */
    void addToFringe(Deque<? super S> fringe, S step);
}
