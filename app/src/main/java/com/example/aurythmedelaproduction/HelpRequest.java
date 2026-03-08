package com.example.aurythmedelaproduction;

public class HelpRequest {

    private String posteId;
    private String line;

    public HelpRequest(String posteId, String line) {
        setPosteId(posteId);
        setLine(line);
    }

    public String getPosteId() {
        return posteId;
    }

    public void setPosteId(String posteId) {
        this.posteId = posteId;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }
}
