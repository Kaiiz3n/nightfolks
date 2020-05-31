package com.kaizen.nightfolks.model;

import com.kaizen.nightfolks.model.entities.Party;

public class PartyFactory {
    public static Party createParty(String name, String type, String host) {
        return new Party(name, type, host);
    }
}
