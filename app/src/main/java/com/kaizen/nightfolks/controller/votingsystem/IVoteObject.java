package com.kaizen.nightfolks.controller.votingsystem;

public interface IVoteObject<ValueType> {
    /**
     * @return value(e.g Song instance)
     */
    ValueType getValue();

    /**
     * @return votes integer(initially at 0)
     */
    Integer getVotes();

    /**
     * Increment votes
     */
    void vote();
}
