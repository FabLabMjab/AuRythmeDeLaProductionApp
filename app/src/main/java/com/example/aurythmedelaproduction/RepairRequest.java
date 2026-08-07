package com.example.aurythmedelaproduction;

public class RepairRequest {
    private final String mecanicienId;
    private final String line;

    public RepairRequest(String mecanicienId, String line) {
        this.mecanicienId = mecanicienId;
        this.line = line;
    }

    public String getMecanicienId() {
        return mecanicienId;
    }

    public String getLine() {
        return line;
    }
}
