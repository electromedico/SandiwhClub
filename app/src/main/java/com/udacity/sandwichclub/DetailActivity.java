package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;


import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;
    @BindView(R.id.origin_tv)
    public TextView originTv;
    @BindView(R.id.ingredients_tv)
    public TextView ingredientsTv;
    @BindView(R.id.also_known_tv)
    public TextView alsoKnownTv;
    @BindView(R.id.description_tv)
    public TextView descriptionTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);

        ImageView ingredientsIv = findViewById(R.id.image_iv);

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
        Sandwich sandwich = JsonUtils.parseSandwichJson(json);
        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }

        populateUI(sandwich);
        Picasso.with(this)
                .load(sandwich.getImage())
                .into(ingredientsIv);

        setTitle(sandwich.getMainName());
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI(Sandwich sandwich) {
        originTv.setText(sandwich.getPlaceOfOrigin());

        String alsoKnow ="";
        for (int i=0;i<sandwich.getAlsoKnownAs().size();i++){

            alsoKnow = alsoKnow.concat(sandwich.getAlsoKnownAs().get(i));
            if (i!=(sandwich.getAlsoKnownAs().size()-1)){
                alsoKnow = alsoKnow.concat(", ");
            }
        }
        alsoKnownTv.setText(alsoKnow);

        String ingredients="";
        for (int i=0;i<sandwich.getIngredients().size();i++){

            ingredients = ingredients.concat(sandwich.getIngredients().get(i));
            if (i!=(sandwich.getIngredients().size()-1)){
                ingredients = ingredients.concat(", ");
            }
        }
        ingredientsTv.setText(ingredients);

        descriptionTv.setText(sandwich.getDescription().toString());

    }
}
