package com.example.lista6.model;

public class Exercise {
    private String content;
    private Integer points;

    public Exercise(String content, Integer points) {
        this.content = content;
        this.points = points;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }
}