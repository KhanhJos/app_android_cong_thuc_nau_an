package com.example.appnauan;

import android.util.Log;

import com.example.appnauan.Model.MonAn;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ParseJsonFoods {

    String dulieu;
    ArrayList<MonAn> arrayList;

    public ParseJsonFoods(String dulieu){
        this.dulieu = dulieu;
    }
    public ArrayList<MonAn> LayDuLieuFoods(){
        arrayList = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(dulieu);

            for (int i=0;i< jsonArray.length();i++){

                JSONObject js = jsonArray.getJSONObject(i);

                arrayList.add(new MonAn(js.getInt("id"),js.getString("name"),js.getString("cookingRecipe"),js.getString("image")));

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return arrayList;
    }
}
