package com.example.getallimage.Class;

import java.util.Date;

public class AlbumItem {
    String title;
    String path;
    String dateTaken;
    String folderName;
    ItemInfo itemInfo;
    public AlbumItem() {
    }

    public AlbumItem(String title, String path, String dateTaken, String folderName, ItemInfo itemInfo) {
        this.title = title;
        this.path = path;
        this.dateTaken = dateTaken;
        this.folderName = folderName;
        this.itemInfo = itemInfo;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getDateTaken() {
        return dateTaken;
    }

    public void setDateTaken(String dateTaken) {
        this.dateTaken = dateTaken;
    }

    public String getFolderName() {
        return folderName;
    }

    public void setFolderName(String folderName) {
        this.folderName = folderName;
    }

    public ItemInfo getItemInfo() {
        return itemInfo;
    }

    public void setItemInfo(ItemInfo itemInfo) {
        this.itemInfo = itemInfo;
    }
}
