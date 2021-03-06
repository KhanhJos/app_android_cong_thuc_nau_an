package com.example.appnauan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;


import com.example.appnauan.Adapter.AdapterFood;
import com.example.appnauan.Model.MonAn;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    ListView listViewFood;
    Toolbar toolbar;
    ArrayList<MonAn> arrayListFoods;

    AdapterFood adapter;

    String duongdan = "https://json-server-demo-json.herokuapp.com/api/foods/";

    int back = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listViewFood = findViewById(R.id.listviewFood);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        LayDuLieuJsonFood();
        adapter = new AdapterFood(MainActivity.this,R.layout.custom_item_listview_mon_an,arrayListFoods);
        listViewFood.setAdapter(adapter);

        listViewFood.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this,CookingRecipe.class);

                String name = arrayListFoods.get(position).getName();
                String image = arrayListFoods.get(position).getImage();
                String cookingRecipe = arrayListFoods.get(position).getCookingRecipe();

                intent.putExtra("name",name);
                intent.putExtra("image",image);
                intent.putExtra("cookingRecipe",cookingRecipe);

                startActivity(intent);
            }
        });

    }

    public void LayDuLieuJsonFood() {
        //String duongdanmoi = duongdan+edtDiaChi.getText().toString();
//        String duongdanmoi = duongdan;
        //N???u ???????ng d???n c?? d???u c??ch, ph???i thay th??? n?? b???ng %20 th?? ta d??ng
//        String duongdanchinhxac = duongdanmoi.replace(" ","%20");
        //G???i t???i Asynctask ????? g??n ???????ng d???n v?? s??? d???ng d??? li???u
        DownloadDuLieuJson downloadDuLieuJson = new DownloadDuLieuJson();
        downloadDuLieuJson.execute(duongdan);

        try {

            ParseJsonFoods parserJsonDuLieu = new ParseJsonFoods(downloadDuLieuJson.get());

            arrayListFoods = parserJsonDuLieu.LayDuLieuFoods();

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //class AsyncTask truy c???p internet l???y d??? li???u JSON
    public class DownloadDuLieuJson extends AsyncTask<String,Void,String> {
        //StringBuilder gi???ng v???i trong code java, ???????c s??? d???ng ????? c???ng chu???i c?? d??? li???u l???n, d??? li???u nh??? th?? d??ng String l?? ?????
        StringBuilder builder;
        @Override
        protected String doInBackground(String... strings) {
            try {
                //Truy c???p internet
                URL url = new URL(strings[0]);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.connect();

                InputStream inputStream = httpURLConnection.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String dong= "";
                builder = new StringBuilder();
                while ((dong = bufferedReader.readLine()) != null){
                    builder.append(dong);
                }
                //????ng l???i
                bufferedReader.close();
                inputStream.close();
                inputStreamReader.close();
                httpURLConnection.disconnect();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            //Return l???i chu???i json
            return builder.toString();
        }
    }

    @Override
    public void onBackPressed() {
        back++;
        if (back == 2){
            //Khoi tao lai Activity main
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);

            // Tao su kien ket thuc app
            Intent startMain = new Intent(Intent.ACTION_MAIN);
            startMain.addCategory(Intent.CATEGORY_HOME);
            startActivity(startMain);
            finish();
        }
    }
}