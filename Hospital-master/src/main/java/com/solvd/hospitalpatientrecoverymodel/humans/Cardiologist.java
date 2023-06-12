package com.solvd.hospitalpatientrecoverymodel.humans;

public class Cardiologist extends Employee {
    private boolean heartFailures;
    private boolean heartValveDisease;
    private boolean cardiacArrhytmias;

    public boolean getHeartFailures() {
        return heartFailures;
    }

    public void setHeartFailures(boolean heartFailures) {
        this.heartFailures = heartFailures;
    }

    public boolean getHeartValveDisease() {
        return heartValveDisease;
    }

    public void setHeartValveDisease(boolean heartValveDisease) {
        this.heartValveDisease = heartValveDisease;
    }

    public boolean getCardiacArrhytmias() {
        return cardiacArrhytmias;
    }

    public void setCardiacArrhytmias(boolean cardiacArrhytmias) {
        this.cardiacArrhytmias = cardiacArrhytmias;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
