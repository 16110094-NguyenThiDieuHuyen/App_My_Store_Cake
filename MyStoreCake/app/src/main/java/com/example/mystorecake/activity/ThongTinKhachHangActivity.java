package com.example.mystorecake.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.mystorecake.R;
import com.example.mystorecake.ultil.CheckconnectInternet;
import com.example.mystorecake.ultil.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ThongTinKhachHangActivity extends AppCompatActivity {

    EditText editTexttenkhach, editTextsdt, editTextdiachi;
    Button btnxacnhan, btntrove;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_tin_khach_hang);
        Anhxa();
        btntrove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        if(CheckconnectInternet.haveNetworkConnection(getApplicationContext()))
        {
            SuKiennhannutxacnhan();
        }
        else
        {
            CheckconnectInternet.ShowToast_Short(getApplicationContext(),"Bạn hãy kiểm tra lại kết nối");
        }

    }

    private void SuKiennhannutxacnhan() {
        btnxacnhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String ten = editTexttenkhach.getText().toString().trim();
                final String sdt = editTextsdt.getText().toString().trim();
                final String diachi = editTextdiachi.getText().toString().trim();
                if(ten.length()>0 && sdt.length()>0&& diachi.length()>0)
                {
                    RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, Server.linkthongtin,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(final String madonhang) {
                                    Log.d("madonhang",madonhang);
                                    if(Integer.parseInt(madonhang) >0)
                                    {
                                        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                                        StringRequest request = new StringRequest(Request.Method.POST, Server.linkchitietdonhang,
                                                new Response.Listener<String>() {
                                                    @Override
                                                    public void onResponse(String response1) {
                                                        Log.d("thanhcong",response1);
                                                        if(response1.equals("1"))
                                                        {
                                                            MainActivity.gioHangModelArrayList.clear();
                                                            CheckconnectInternet.ShowToast_Short(getApplicationContext(),"Bạn đx thêm giỏ hàng thành công!!Chờ để xác nhận order nhé!!!");
                                                            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                                                            startActivity(intent);
                                                            CheckconnectInternet.ShowToast_Short(getApplicationContext(),"Mời bạn tiếp tục mua hàng!!!");
                                                        }
                                                        else
                                                        {
                                                            CheckconnectInternet.ShowToast_Short(getApplicationContext(),"Xin lỗi bạn dữ liệu giỏ hàng của bạn đã bị lỗi.Mời bạn vui lòng chọn lại");

                                                        }

                                                    }
                                                }, new Response.ErrorListener() {
                                            @Override
                                            public void onErrorResponse(VolleyError error) {

                                            }
                                        }){
                                            @Override
                                            protected Map<String, String> getParams() throws AuthFailureError {
                                                // tạo chuỗi json chứa dữ liệu
                                                JSONArray jsonArray = new JSONArray();
                                                for(int i=0;i<MainActivity.gioHangModelArrayList.size();i++)
                                                {
                                                    JSONObject jsonObject = new JSONObject();
                                                    try {
                                                        jsonObject.put("madonhang",madonhang);
                                                        jsonObject.put("masanpham",MainActivity.gioHangModelArrayList.get(i).getIdsp());
                                                        jsonObject.put("tensanpham",MainActivity.gioHangModelArrayList.get(i).getTensp());
                                                        jsonObject.put("giasanpham",MainActivity.gioHangModelArrayList.get(i).getGiasp());
                                                        jsonObject.put("soluongsanpham",MainActivity.gioHangModelArrayList.get(i).getSoluong());

                                                    } catch (JSONException e) {
                                                        e.printStackTrace();
                                                    }
                                                    jsonArray.put(jsonObject);
                                                }
                                                HashMap<String,String> hashMap = new HashMap<String, String>();
                                                hashMap.put("json",jsonArray.toString());
                                                return hashMap;
                                            }
                                        };
                                        queue.add(request);
                                    }

                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    }){
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            HashMap<String,String> hashMap = new HashMap<String, String>();
                            hashMap.put("tenkhachhang", ten);
                            hashMap.put("sodienthoai",sdt);
                            hashMap.put("diachi", diachi);
                            return hashMap;
                        }
                    };
                    requestQueue.add(stringRequest);
                }
                else
                {
                    CheckconnectInternet.ShowToast_Short(getApplicationContext(),
                            "Hyac kiểm tra lại việc nhập dữ lệu");
                }

            }
        });
    }

    private void Anhxa() {
        editTexttenkhach = findViewById(R.id.edittexttenkhachhang);
        editTextsdt = findViewById(R.id.edittextsdtkh);
        editTextdiachi = findViewById(R.id.edittextdiachi);
        btnxacnhan = findViewById(R.id.btnxacnhan);
        btntrove = findViewById(R.id.btntrove);
    }
}
