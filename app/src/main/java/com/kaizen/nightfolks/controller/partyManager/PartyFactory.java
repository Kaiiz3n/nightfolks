package com.kaizen.nightfolks.controller.partyManager;

import com.kaizen.nightfolks.model.entities.Party;

public class PartyFactory {
    public static Party createParty(String name, String genre, String host) {
        return new Party(name, genre, host);
    }
}
