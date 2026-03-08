package com.example.aurythmedelaproduction;

public class AssemblyStep {
    private int imageRes;
    private String instruction;

    public AssemblyStep(int imageRes, String instruction) {
        setImageRes(imageRes);
        setInstruction(instruction);
    }

    // Getter imageRes
    public int getImageRes() {
        return imageRes;
    }

    // Setter imageRes
    public void setImageRes(int imageRes) {
        this.imageRes = imageRes;
    }

    // Getter instructions
    public String getInstruction() {
        return instruction;
    }

    // Setter instructions
    public void setInstruction(String instructions) {
        this.instruction = instructions;
    }
}
