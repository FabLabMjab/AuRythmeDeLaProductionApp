package com.example.aurythmedelaproduction;

public class AssemblyStep {
    private int imageRes;
    private int instructionRes;

    public AssemblyStep(int imageRes, int instruction) {
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
    public int getInstruction() {
        return instructionRes;
    }

    // Setter instructions
    public void setInstruction(int instructions) {
        this.instructionRes = instructions;
    }
}
