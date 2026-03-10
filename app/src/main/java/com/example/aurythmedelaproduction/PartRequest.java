package com.example.aurythmedelaproduction;

public class PartRequest {
    private String assembleurId;
    private String partId;
    private String line;

    public PartRequest(String assembleurId, String partId, String line) {
        setAssembleurId(assembleurId);
        setPartId(partId);
        setLine(line);
    }

    public String getAssembleurId() {
        return assembleurId;
    }

    public void setAssembleurId(String assembleurId) {
        this.assembleurId = assembleurId;
    }

    public String getPartId() {
        return partId;
    }

    public void setPartId(String partId) {
        this.partId = partId;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }
}
