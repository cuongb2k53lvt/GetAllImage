package com.example.getallimage.Class;

public class ItemInfo {
    String orientation;
    String width;
    String height;
    String size;

    public ItemInfo(String orientation, String width, String height, String size) {
        this.orientation = orientation;
        this.width = width;
        this.height = height;
        this.size = size;
    }

    public String getOrientation() {
        return orientation;
    }

    public void setOrientation(String orientation) {
        this.orientation = orientation;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}
