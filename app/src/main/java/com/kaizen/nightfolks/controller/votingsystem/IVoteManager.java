package com.kaizen.nightfolks.controller.votingsystem;

public interface IVoteManager<VoteObject> {
    /**
     * Sort the voteObject Set based on the give sortPolicy
     *
     * @return sorted Set
     */
    void sort();

    /**
     * vote the give vote object
     *
     * @param vObj
     */
    void vote(VoteObject vObj);
}
