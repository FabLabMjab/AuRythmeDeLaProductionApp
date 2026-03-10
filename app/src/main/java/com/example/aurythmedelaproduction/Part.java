package com.example.aurythmedelaproduction;

public class Part {
    public String id;
    public int imageRes;

    public Part(String id, int imageRes) {
       setId(id);
        setImageRes(imageRes);
    }

    // Getter ID
    public String getId() {
        return id;
    }

    // Setter ID
    public void setId(String id) {
        this.id = id;
    }

    // Getter image
    public int getImageRes() {
        return imageRes;
    }

    // Setter image
    public void setImageRes(int imageRes) {
        this.imageRes = imageRes;
    }
}
