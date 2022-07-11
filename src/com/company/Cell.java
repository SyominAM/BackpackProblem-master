package com.company;

public class Cell {

    private String direction;
    private int resultValue;

    public Cell(String direction, int resultValue) {
        this.direction = direction;
        this.resultValue = resultValue;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public int getResultValue() {
        return resultValue;
    }

    public String printCell () {
        return this.direction +  "|" + this.resultValue;
    }
}
