package com.example.node_server_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class searchScreen extends AppCompatActivity {

    TextView fish_name,fish_lifespan; // 어류 이름, 수명 나타낼 자리

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_screen);

        Intent intentValue = getIntent();
        String searchValue = intentValue.getStringExtra("searchValue"); // 전달된 데이터 받기


        fish_name = findViewById(R.id.fish_name);
        fish_lifespan = findViewById(R.id.fish_lifespan);


        //서버 송수신후 수신데이터 확인 및 컨트롤 하기
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response); // 받은 데이터 json 으로 받기

                    //json 데이터 화면에 출력
                    fish_name.setText(jsonObject.getString("name"));
                    fish_lifespan.setText(jsonObject.getString("lifespan"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };

        // 서버 송수신 코드
        screanRequest screanRequest = new screanRequest(searchValue, responseListener);
        RequestQueue queue = Volley.newRequestQueue(searchScreen.this);
        queue.add(screanRequest);
    }
}