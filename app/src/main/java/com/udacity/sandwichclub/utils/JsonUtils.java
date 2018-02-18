package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class JsonUtils {
    public static final String JSON_NAME_KEY = "name";
    public static final String JSON_MAIN_NAME_KEY = "mainName";
    public static final String JSON_ALSO_KNOW_AS_KEY= "alsoKnownAs";
    public static final String JSON_PLACE_OF_ORIGIN_KEY="placeOfOrigin";
    public static final String JSON_DESCRIPTION_KEY="description";
    public static final String JSON_IMAGE_KEY="image";
    public static final String JSON_INGREDIENTS_KEY="ingredients";

    public static Sandwich parseSandwichJson(String json) {
        Sandwich sandwichObj = new Sandwich();
        try {
            JSONObject jsonObject = new JSONObject(json);

            JSONObject nameJSON = jsonObject.getJSONObject(JSON_NAME_KEY);
            sandwichObj.setMainName(nameJSON.optString(JSON_MAIN_NAME_KEY));

            JSONArray alsoKnownasJSONArray = nameJSON.getJSONArray(JSON_ALSO_KNOW_AS_KEY);
            ArrayList<String> alsoKnownAs = new ArrayList<>();

            for (int i=0;i<alsoKnownasJSONArray.length();i++){
             alsoKnownAs.add(alsoKnownasJSONArray.getString(i));
            }
            sandwichObj.setAlsoKnownAs(alsoKnownAs);

            sandwichObj.setPlaceOfOrigin(jsonObject.optString(JSON_PLACE_OF_ORIGIN_KEY));

            sandwichObj.setDescription(jsonObject.optString(JSON_DESCRIPTION_KEY));

            sandwichObj.setImage(jsonObject.optString(JSON_IMAGE_KEY));


            JSONArray ingredientsJSONArray = jsonObject.getJSONArray(JSON_INGREDIENTS_KEY);
            ArrayList<String> ingredients = new ArrayList<>();

            for (int i=0;i<ingredientsJSONArray.length();i++){
                ingredients.add(ingredientsJSONArray.optString(i));
            }
            sandwichObj.setIngredients(ingredients);
            return sandwichObj;


        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}