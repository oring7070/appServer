package com.example.node_server_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.net.URISyntaxException;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

public class MainActivity extends AppCompatActivity {

    TextView searchTextView;
    Button searchButton;
    Socket mSocket;  // socket 변수 생성

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchTextView = findViewById(R.id.search_TextView);
        searchButton = findViewById(R.id.search_Btn);

        init(); // 생성한 socket 함수 실행

        mSocket.emit("start", true); // 서버로 보낼 데이터

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String searchValue = searchTextView.getText().toString(); // 검색한 단어 저장

                Intent intent = new Intent(MainActivity.this, searchScreen.class);
                intent.putExtra("searchValue", searchValue);  // 저장된 검색단어 데이터 다음페이지에 옮기기
                startActivity(intent);
            }
        });

    }


    private void init() { //socket 생성 및 연결
        try {
            mSocket = IO.socket("http://34.221.211.139:3003/room"); // 생성
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        mSocket.connect(); // 연결
    }
}