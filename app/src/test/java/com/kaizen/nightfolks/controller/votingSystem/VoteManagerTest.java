package com.kaizen.nightfolks.controller.votingSystem;

import com.kaizen.nightfolks.controller.votingSystem.voteObjects.VoteInteger;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
class VoteManagerTest {
    private List<IVoteObject> voteObjects;

    @Test
    void sort_aListOfIntegerTest() {
        this.populateWithRandomVotes();
        //SortPolicy defined in VoteInteger(VoteInteger Must implement IVoteObject)
        //Here the normal vote comparison
        SortPolicy sortPolicy = new VoteInteger.IntegerComparisonPolicy();

        //Define voteManager with voteObjects array & sortPolicy
        VoteManager voteManager = new
                VoteManager(this.voteObjects, sortPolicy);
        List unsortedObjects = new ArrayList<Integer>();
        //get List before Sorting
        this.voteObjects.forEach(e -> unsortedObjects.add(e.getVotes()));
        //Sort it with Collections method
        Collections.sort(unsortedObjects, Comparator.naturalOrder());
        //Sort in VoteManager
        voteManager.sort();
        List sortedObjects = new ArrayList<Integer>();
        //get the voteManager List to verify later if its sorted
        this.voteObjects.forEach(e -> sortedObjects.add(e.getVotes()));
        //Verify if its sorted
        assertEquals(sortedObjects, unsortedObjects);
    }

    private void populateWithRandomVotes() {
        voteObjects = new ArrayList<>();
        IntStream randomInts = new Random().ints(10, 0, 100);
        randomInts.forEach(e -> voteObjects.add(new VoteInteger(e * 2, e)));
    }

    @org.junit.jupiter.api.Test
    void vote_shouldChangeVoteValueAndUpdate() {
        this.populateWithRandomVotes();
        SortPolicy sortPolicy = new VoteInteger.IntegerComparisonPolicy();

        VoteManager voteManager = new
                VoteManager(this.voteObjects, sortPolicy);
        //get some element
        IVoteObject objectToVote = voteObjects.get(1);
        //Store old List
        ArrayList<IVoteObject> oldVoteObjects = new ArrayList<>(this.voteObjects);
        //Store old vote integer
        Integer oldVotes = objectToVote.getVotes();
        //Perform a vote action
        voteManager.vote(objectToVote);
        //get the object located where objectToVote was
        IVoteObject objectInOldPosition = voteObjects.get(1);
        //Vote value should increment
        assertEquals(new Integer(oldVotes + 1), objectToVote.getVotes());
        //Positions should be updated based on the recent vote
        assertNotEquals(objectToVote, objectInOldPosition);
        assertNotEquals(voteObjects, oldVoteObjects);

    }
}
