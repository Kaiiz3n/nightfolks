package com.kaizen.nightfolks.controller.votingsystem;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class VoteManager implements IVoteManager<IVoteObject> {
    private List<IVoteObject> voteObjects;
    private SortPolicy sortPolicy;

    public VoteManager(List<IVoteObject> voteObjects, SortPolicy sortPolicy) {
        this.voteObjects = voteObjects;
        this.sortPolicy = sortPolicy;
    }

    @Override
    public void sort() {
        Comparator<IVoteObject> comparator = (o1, o2) -> sortPolicy.apply(o1, o2);
        Collections.sort(this.voteObjects, comparator);
    }

    @Override
    public synchronized void vote(IVoteObject vObj) {
        vObj.vote();
        this.sort();
    }
}
