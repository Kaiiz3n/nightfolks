package com.kaizen.nightfolks.controller.utility;

import com.kaizen.nightfolks.controller.BTNetwork.IParticipant;
import com.kaizen.nightfolks.controller.BTNetwork.Participant;

public class AppData {
    private static AppData appData_instance = null;
    private IParticipant participant;

    private AppData() {
    }

    public static AppData getInstance() {
        if (appData_instance == null) {
            appData_instance = new AppData();
        }
        return appData_instance;
    }

    public void setParticipant(Participant participant) {
        this.participant = participant;
    }

    public IParticipant getParticipant() {
        return this.participant;
    }
}
