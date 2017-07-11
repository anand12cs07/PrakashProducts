package com.andyapp.prakashproducts.Models;


import java.io.Serializable;

public class ItemModel implements Serializable{
    private static ItemModel itemModel;
    private boolean isLiked;
    private String itemName;
    private String itemImage;
    private String itemWeight;
    private String itemSize;
    private String itemPrice;

    public static ItemModel getInstance(){
        if (itemModel == null)
            itemModel = new ItemModel();
        return itemModel;
    }

    public void setLike(boolean isLiked){
        this.isLiked = isLiked;
    }

    public boolean getisLiked(){
        return isLiked;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemImage() {
        return itemImage;
    }

    public void setItemImage(String itemImage) {
        this.itemImage = itemImage;
    }

    public String getItemWeight() {
        return itemWeight;
    }

    public void setItemWeight(String itemWeight) {
        this.itemWeight = itemWeight;
    }

    public String getItemSize() {
        return itemSize;
    }

    public void setItemSize(String itemSize) {
        this.itemSize = itemSize;
    }

    public String getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(String itemPrice) {
        this.itemPrice = itemPrice;
    }
}
