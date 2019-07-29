package com.example.mystorecake.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.mystorecake.R;
import com.example.mystorecake.adapter.QuaTangApdapter;
import com.example.mystorecake.model.SanPhamModel;
import com.example.mystorecake.ultil.CheckconnectInternet;
import com.example.mystorecake.ultil.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class QuaTangActivity extends AppCompatActivity {
    Toolbar toolbarquatang;
    ListView lvquatang;
    QuaTangApdapter quatangAdapter;
    ArrayList<SanPhamModel> quatangarraylist;
    int idqt;
    int page = 1;
    // gán vào layout thanh progessbar
    View footerview;
    boolean isloading = false, limitdadta= false;
    mHandler myHander;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qua_tang);
        if(CheckconnectInternet.haveNetworkConnection(getApplicationContext())) {
            AnhXa();
            GetIdloaisp();
            ActionToolbar();
            GetData(page);
            LoadMoreData();
        }
        else {
            CheckconnectInternet.ShowToast_Short(getApplicationContext(),"Hãy kiểm tra lại kết nối internet");
            finish();
        }
    }

    private void AnhXa() {
        toolbarquatang = (Toolbar) findViewById(R.id.toolbarquatang);
        lvquatang = (ListView) findViewById(R.id.listviewquatang);
        quatangarraylist = new ArrayList<>();
        quatangAdapter = new QuaTangApdapter(getApplicationContext(), quatangarraylist);
        lvquatang.setAdapter(quatangAdapter);
        // gan layout cho bien footer view
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        footerview = inflater.inflate(R.layout.progessbar,null);
        myHander = new mHandler();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
       /* int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item); */
        switch (item.getItemId())
        {
            case R.id.menugiohang:
                Intent intent = new Intent(getApplicationContext(),GioHangActivity.class);
                startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    private void GetIdloaisp() {
        idqt = getIntent().getIntExtra("idloaisanpham", -1);
        Log.d("giatriloaisp", idqt + "");
    }

    private void ActionToolbar() {
        setSupportActionBar(toolbarquatang);
        // tạo nút home
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // xét chức năng cho nút home
        toolbarquatang.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void GetData(int Page) {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        String duongdang = Server.linkbanhkem + String.valueOf(Page);
        Log.d("linkbanhkem", duongdang);
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                duongdang, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // tạo biến hứng giá trị
                int id = 0;
                String tenbanhkem = "";
                int giabanhkem = 0;
                String hinhbanhkem = "";
                String motabanhkem = "";
                int Idlsp = 0;
                if (response != null && response.length() != 2) {
                    // xoa thanh progess
                    lvquatang.removeFooterView(footerview);
                    try {
                        JSONArray jsonArray = new JSONArray(response);
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            id = jsonObject.getInt("id");
                            tenbanhkem = jsonObject.getString("TenSanPham");
                            giabanhkem = jsonObject.getInt("Gia");
                            hinhbanhkem = jsonObject.getString("Hinh");
                            motabanhkem = jsonObject.getString("MoTa");
                            Idlsp = jsonObject.getInt("IdLoai");
                            quatangarraylist.add(new SanPhamModel(id, tenbanhkem, giabanhkem, hinhbanhkem, motabanhkem, Idlsp));
                            // cập nhập adapter
                            quatangAdapter.notifyDataSetChanged();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
                else
                {
                    limitdadta = true;
                    lvquatang.removeFooterView(footerview);
                    CheckconnectInternet.ShowToast_Short(getApplicationContext(),"Đã hết dữ liệu!!!");
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> param = new HashMap<String, String>();
                param.put("idloaisanpham", String.valueOf(idqt));
                return param;
            }
        };
        requestQueue.add(stringRequest);
    }
    public class mHandler extends Handler
    {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what)
            {
                case 0:
                    lvquatang.addFooterView(footerview);
                    break;
                case 1:
                    GetData(++page);
                    isloading = false;
                    break;
            }
            super.handleMessage(msg);
        }
    }
    // giúp đọc dữ liệu đa luồng - luồng chính đọc dữ liệu, luồng phụ load dữ liệu
    public class ThreadData extends Thread{
        @Override
        public void run() {
            myHander.sendEmptyMessage(0);
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // obtain messge ohuongw thuc ket noi voi handler
            // gửi obtain message mới cho thằng 1
            Message message = myHander.obtainMessage(1);
            myHander.sendMessage(message);
            super.run();
        }

    }

    private void LoadMoreData() {
        // click item
        lvquatang.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // chuyen du lieu sang man hinh chitietsan pham
                Intent intent = new Intent(getApplicationContext(),ChiTietSanPhamActivity.class);
                intent.putExtra("thongtinsanpham",quatangarraylist.get(position));
                startActivity(intent);
            }
        });
        // bắt sự kiện kéo của list view
        lvquatang.setOnScrollListener(new AbsListView.OnScrollListener() {
            //  hỗ trợ vị trí đứng
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }
            // xác dịnh bạn đang kéo ở đâu
            @Override
            public void onScroll(AbsListView view, int firstItem, int visibleItem, int totalItem) {
                // bắt giá trị cuối trong list view
                // run lên lần đầu tiền không cần vào
                if(firstItem+visibleItem == totalItem && totalItem!=0
                        && isloading == false && limitdadta == false)
                {
                    isloading = true;
                    ThreadData threadData = new ThreadData();
                    threadData.start();

                }

            }
        });
    }
}
