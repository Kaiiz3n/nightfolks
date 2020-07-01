package com.kaizen.nightfolks.controller.votingSystem.voteObjects;

import com.kaizen.nightfolks.controller.votingSystem.IVoteObject;
import com.kaizen.nightfolks.controller.votingSystem.SortPolicy;

public class VoteInteger implements IVoteObject<Integer> {
    int votes;
    Integer value;


    public VoteInteger(int value) {
        this.votes = 0;
        this.value = value;
    }

    /**
     * Be careful, only for testing purposes , votes should start always at 0
     *
     * @param value
     * @param votes
     */
    public VoteInteger(int value, int votes) {
        this.votes = votes;
        this.value = value;
    }

    @Override
    public Integer getValue() {
        return this.value;
    }

    @Override
    public Integer getVotes() {
        return this.votes;
    }

    @Override
    public void vote() {
        this.votes = votes + 1;
    }


    public static class IntegerComparisonPolicy implements SortPolicy<VoteInteger> {
        @Override
        public int apply(VoteInteger o1, VoteInteger o2) {
            return Integer.compare(o1.getVotes(), o2.getVotes());
        }
    }
}