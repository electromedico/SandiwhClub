package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {
        Sandwich sandwichObj = new Sandwich();
        try {
            JSONObject jsonObject = new JSONObject(json);

            JSONObject nameJSON = jsonObject.getJSONObject("name");
            sandwichObj.setMainName(nameJSON.getString("mainName"));

            JSONArray alsoKnownasJSONArray = nameJSON.getJSONArray("alsoKnownAs");
            ArrayList<String> alsoKnownAs = new ArrayList<>();

            for (int i=0;i<alsoKnownasJSONArray.length();i++){
             alsoKnownAs.add(alsoKnownasJSONArray.getString(i));
            }
            sandwichObj.setAlsoKnownAs(alsoKnownAs);

            sandwichObj.setPlaceOfOrigin(jsonObject.getString("placeOfOrigin"));

            sandwichObj.setDescription(jsonObject.getString("description"));

            sandwichObj.setImage(jsonObject.getString("image"));


            JSONArray ingredientsJSONArray = jsonObject.getJSONArray("ingredients");
            ArrayList<String> ingredients = new ArrayList<>();

            for (int i=0;i<ingredientsJSONArray.length();i++){
                ingredients.add(ingredientsJSONArray.getString(i));
            }
            sandwichObj.setIngredients(ingredients);
            return sandwichObj;


        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}