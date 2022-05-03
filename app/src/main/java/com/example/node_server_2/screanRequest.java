package com.example.node_server_2;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class screanRequest extends StringRequest {

    // 연결할 주소 및 서버로 보낼 데이터 map 으로 지정하기
    final  static  private  String URL = "http://34.221.211.139:3003/postServer";
    private Map<String, String> map;

    public screanRequest(String searchValue,  Response.Listener<String> listener){
        super(Method.POST, URL, listener, null);

        map = new HashMap<>(); //map 생성
        map.put("searchValue", searchValue); //서버로 보낼 데이터
    }

    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }

}
