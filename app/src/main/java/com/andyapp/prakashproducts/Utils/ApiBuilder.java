package com.andyapp.prakashproducts.Utils;

import android.util.Log;

import com.andyapp.prakashproducts.Models.ItemModel;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class ApiBuilder {

    public String JSON_URL = "https://anandat1195.000webhostapp.com/Prakash%20Product/prakash%20product.json";

    private static ApiBuilder mBuilder;
    public ArrayList<ItemModel> itemBeds;
    public ArrayList<ItemModel> itemChairs;
    public ArrayList<ItemModel> itemCumBeds;
    public ArrayList<ItemModel> itemDinningTables;
    public ArrayList<ItemModel> itemOfficeChairs;
    public ArrayList<ItemModel> itemSofas;
    private String response = "";

    public static ApiBuilder getBuilder() {
        if (mBuilder == null)
            mBuilder = new ApiBuilder();
        return mBuilder;
    }

    public void setResponse(String response) {
        this.response = response;
        getItemBeds();
        getItemChairs();
        getItemCumBeds();
        getItemDinningTables();
        getItemOfficeChairs();
        getItemSofas();
    }

    private void getItemBeds() {
        itemBeds = new ArrayList<ItemModel>();
        try {
            JSONObject object = new JSONObject(response);
            JSONArray array = object.getJSONArray("beds");
            for (int i = 0; i < array.length(); i++) {
                ItemModel itemModel = new ItemModel();
                JSONObject itemObject = array.getJSONObject(i);
                itemModel.setItemName(itemObject.getString("name"));
                itemModel.setItemSize(itemObject.getString("dimension"));
                if (itemObject.has("weight"))
                itemModel.setItemWeight(itemObject.getString("weight"));
                itemModel.setItemPrice(itemObject.getString("price"));
                itemModel.setItemImage(itemObject.getString("large image"));
                itemModel.setItemSmallImage(itemObject.getString("image"));

                itemBeds.add(itemModel);
            }
        } catch (Exception e) {
            Log.e("ApiBuilder", e.toString());
        }
    }

    private void getItemChairs() {
        itemChairs = new ArrayList<ItemModel>();
        try {
            JSONObject object = new JSONObject(response);
            JSONArray array = object.getJSONArray("chairs");
            for (int i = 0; i < array.length(); i++) {
                ItemModel itemModel = new ItemModel();
                JSONObject itemObject = array.getJSONObject(i);
                itemModel.setItemName(itemObject.getString("name"));
                itemModel.setItemSize(itemObject.getString("dimension"));
                if (itemObject.has("weight"))
                    itemModel.setItemWeight(itemObject.getString("weight"));
                itemModel.setItemPrice(itemObject.getString("price"));
                itemModel.setItemImage(itemObject.getString("large image"));
                itemModel.setItemSmallImage(itemObject.getString("image"));
                itemChairs.add(itemModel);
            }
        } catch (Exception e) {
            Log.e("ApiBuilder", e.toString());
        }
    }

    private void getItemCumBeds() {
        itemCumBeds = new ArrayList<ItemModel>();
        try {
            JSONObject object = new JSONObject(response);
            JSONArray array = object.getJSONArray("comb beds");
            for (int i = 0; i < array.length(); i++) {
                ItemModel itemModel = new ItemModel();
                JSONObject itemObject = array.getJSONObject(i);
                itemModel.setItemName(itemObject.getString("name"));
                itemModel.setItemSize(itemObject.getString("dimension"));
                if (itemObject.has("weight"))
                    itemModel.setItemWeight(itemObject.getString("weight"));
                itemModel.setItemPrice(itemObject.getString("price"));
                itemModel.setItemImage(itemObject.getString("large image"));
                itemModel.setItemSmallImage(itemObject.getString("image"));
                itemCumBeds.add(itemModel);
            }
        } catch (Exception e) {
            Log.e("ApiBuilder", e.toString());
        }
    }

    private void getItemDinningTables() {
        itemDinningTables = new ArrayList<ItemModel>();
        try {
            JSONObject object = new JSONObject(response);
            JSONArray array = object.getJSONArray("dinning tables");
            for (int i = 0; i < array.length(); i++) {
                ItemModel itemModel = new ItemModel();
                JSONObject itemObject = array.getJSONObject(i);
                itemModel.setItemName(itemObject.getString("name"));
                itemModel.setItemSize(itemObject.getString("dimension"));
                if (itemObject.has("weight"))
                    itemModel.setItemWeight(itemObject.getString("weight"));
                itemModel.setItemPrice(itemObject.getString("price"));
                itemModel.setItemImage(itemObject.getString("large image"));
                itemModel.setItemSmallImage(itemObject.getString("image"));
                itemDinningTables.add(itemModel);
            }
        } catch (Exception e) {
            Log.e("ApiBuilder", e.toString());
        }
    }

    private void getItemOfficeChairs() {
        itemOfficeChairs = new ArrayList<ItemModel>();
        try {
            JSONObject object = new JSONObject(response);
            JSONArray array = object.getJSONArray("office chairs");
            for (int i = 0; i < array.length(); i++) {
                ItemModel itemModel = new ItemModel();
                JSONObject itemObject = array.getJSONObject(i);
                itemModel.setItemName(itemObject.getString("name"));
                itemModel.setItemSize(itemObject.getString("dimension"));
                if (itemObject.has("weight"))
                    itemModel.setItemWeight(itemObject.getString("weight"));
                itemModel.setItemPrice(itemObject.getString("price"));
                itemModel.setItemImage(itemObject.getString("large image"));
                itemModel.setItemSmallImage(itemObject.getString("image"));
                itemOfficeChairs.add(itemModel);
            }
        } catch (Exception e) {
            Log.e("ApiBuilder", e.toString());
        }
    }

    private void getItemSofas() {
        itemSofas = new ArrayList<ItemModel>();
        try {
            JSONObject object = new JSONObject(response);
            JSONArray array = object.getJSONArray("sofa");
            for (int i = 0; i < array.length(); i++) {
                ItemModel itemModel = new ItemModel();
                JSONObject itemObject = array.getJSONObject(i);
                itemModel.setItemName(itemObject.getString("name"));
                itemModel.setItemSize(itemObject.getString("dimension"));
                if (itemObject.has("weight"))
                    itemModel.setItemWeight(itemObject.getString("weight"));
                itemModel.setItemPrice(itemObject.getString("price"));
                itemModel.setItemImage(itemObject.getString("large image"));
                itemModel.setItemSmallImage(itemObject.getString("image"));
                itemSofas.add(itemModel);
            }
        } catch (Exception e) {
            Log.e("ApiBuilder", e.toString());
        }
    }

}
