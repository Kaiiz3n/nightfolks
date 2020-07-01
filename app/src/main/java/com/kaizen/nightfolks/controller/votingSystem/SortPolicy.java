package com.kaizen.nightfolks.controller.votingSystem;

public interface SortPolicy<ValueType> {
    /**
     * Compare between two objects.
     * Defined by the interface user
     *
     * @return result used in comparison
     */
    int apply(ValueType o1, ValueType o2);
}
