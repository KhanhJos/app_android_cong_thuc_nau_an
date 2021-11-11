package com.example.appnauan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


import android.content.Intent;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.squareup.picasso.Picasso;

public class CookingRecipe extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cooking_recipe);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String image = intent.getStringExtra("image");
        String cookingRecipe = intent.getStringExtra("cookingRecipe");

        ImageView imageView = findViewById(R.id.imageVieww);
        TextView textViewCongThuc = findViewById(R.id.textviewCongThuc);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.collapsingtoolbarLayout);

        collapsingToolbarLayout.setTitle(name);
        textViewCongThuc.setText(cookingRecipe);

        //Chuyển một ảnh sang bitmap để đưa vào palette
        //Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.khanhselfile);

        Picasso.get().load(image).placeholder(R.drawable.load).error(R.drawable.error).fit().into(imageView);

//        Lấy màu nổi bật từ hình ảnh cho lên toolbar
//        Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
//            @Override
//            public void onGenerated(@Nullable Palette palette) {
//                if (palette != null){
//                    //Trả về màu mặc định
//                    //getMutedColor Trả về màu bị tắt tiếng từ bảng màu dưới dạng int được đóng gói RGB.
//                    //setContentScrimColor : Set nội dung thu gọn màu cho collapsinToolbarLayout
//                    collapsingToolbarLayout.setContentScrimColor(palette.getMutedColor(R.attr.colorPrimary));
//                }
//            }
//        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        Intent intent1 = new Intent(CookingRecipe.this,MainActivity.class);
//        startActivity(intent1);
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}