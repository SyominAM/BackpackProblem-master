package com.company;

public class Item {

    private int itemValue;
    private int itemSize;

    public Item(int value, int itemSize) {
        this.itemValue = value;
        this.itemSize = itemSize;
    }

    public int getItemValue() {
        return itemValue;
    }

    public int getItemSize() {
        return itemSize;
    }
}
