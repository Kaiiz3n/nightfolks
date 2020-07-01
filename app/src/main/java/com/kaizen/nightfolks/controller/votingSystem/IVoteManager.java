package com.kaizen.nightfolks.controller.votingSystem;

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
