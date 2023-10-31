package it.unibo.generics.graph.api;

import java.util.LinkedList;
import java.util.List;

public class Step<N> {

    private final Step<N> prevSteps;
    private final N position;

    /**
     * Creates an initial step on node {@code position} with no previous steps.
     * 
     * @param position is the initial node
     */
    Step(final N position) {
        this(null, position);
    }

    /**
     * Creates a further step reaching node {@code currentPosition} following the steps {@code previousSteps}.
     *
     * @param previousSteps is the parent node
     * @param currentPosition is the node reached after performing this step
     */
    Step(final Step<N> previousSteps, final N currentPosition) {
        this.prevSteps = previousSteps;
        this.position = currentPosition;
    }

    /**
     * Flattens the step into a path (i.e. a list of nodes), from the initial one to the last one
     * 
     * @return a list of visited nodes
     */
    public List<N> getPath() {
        final List<N> result = new LinkedList<>();
        Step<N> curr = this;
        do {
            result.add(0, curr.position);
            curr = curr.prevSteps;
        } while (curr != null);
        return result;
    }

    public N getPosition() {
        return this.position;
    }

    @Override
    public String toString() {
        final List<String> elements = new LinkedList<>();
        for (final N node : getPath()) {
            elements.add(node.toString());
        }
        return String.join(" -> ", elements);
    }
}
