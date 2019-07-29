package com.example.mystorecake.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ViewFlipper;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.mystorecake.R;
import com.example.mystorecake.adapter.LoaiSanPhamAdapter;
import com.example.mystorecake.adapter.SanPhamAdapter;
import com.example.mystorecake.model.GioHangModel;
import com.example.mystorecake.model.LoaisanphamModel;
import com.example.mystorecake.model.SanPhamModel;
import com.example.mystorecake.ultil.CheckconnectInternet;
import com.example.mystorecake.ultil.Server;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    ViewFlipper viewFlipper;
    ListView listViewMHC;
    NavigationView navigationView;
    RecyclerView recyclerViewMHC;
    DrawerLayout drawerLayout;
    ArrayList<LoaisanphamModel> mangloaisp;
    LoaiSanPhamAdapter loaiSanPhamAdapter;
    int id =0;
    String tenLoai ="";
    String HinhLoai ="";
    ArrayList<SanPhamModel> sanPhamModelArrayList;
    SanPhamAdapter sanPhamAdapter;
    public static ArrayList<GioHangModel>gioHangModelArrayList ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }

        });*/
        if(CheckconnectInternet.haveNetworkConnection(getApplicationContext())) {
            Anhxa();
            ActionBar();
            ActionViewFilpper();
            LayDuLieuLoaiSp();
            LayDuLieuSanPhamMoi();
            CatchOnItemListView();
        }
        else
        {
            CheckconnectInternet.ShowToast_Short(getApplicationContext(),"Thiết bị của bạn không có kết nối tới Internet");
            finish();
        }

    }


    private void CatchOnItemListView() {
        listViewMHC.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        if (CheckconnectInternet.haveNetworkConnection(getApplicationContext())) {
                            //phuong thuc chuyen man hinh
                            // khởi tạo những màn hình muốn chuyển
                            Intent intent = new Intent(MainActivity.this, MainActivity.class);
                            // thực hiện chuyển
                            startActivity(intent);
                        } else {
                            CheckconnectInternet.ShowToast_Short(getApplicationContext(), "Bạn hãy kiểm tra lại kết nối Internet");

                        }
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 1:
                        if (CheckconnectInternet.haveNetworkConnection(getApplicationContext())) {
                            //phuong thuc chuyen man hinh
                            // khởi tạo những màn hình muốn chuyển
                            Intent intent = new Intent(MainActivity.this, BanhKemActivity.class);
                            //  truyền tham số khi chuyển sang màn hình
                            intent.putExtra("idloaisanpham", mangloaisp.get(position).getId());
                            // thực hiện chuyển
                            startActivity(intent);
                        } else {
                            CheckconnectInternet.ShowToast_Short(getApplicationContext(), "Bạn hãy kiểm tra lại kết nối Internet");

                        }
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 2:
                        if (CheckconnectInternet.haveNetworkConnection(getApplicationContext())) {
                            //phuong thuc chuyen man hinh
                            // khởi tạo những màn hình muốn chuyển
                            Intent intent = new Intent(MainActivity.this, BanhNgotActivity.class);
                            //  truyền tham số khi chuyển sang màn hình
                            intent.putExtra("idloaisanpham", mangloaisp.get(position).getId());
                            // thực hiện chuyển
                            startActivity(intent);
                        } else {
                            CheckconnectInternet.ShowToast_Short(getApplicationContext(), "Bạn hãy kiểm tra lại kết nối Internet");

                        }
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 3:
                        if (CheckconnectInternet.haveNetworkConnection(getApplicationContext())) {
                            //phuong thuc chuyen man hinh
                            // khởi tạo những màn hình muốn chuyển
                            Intent intent = new Intent(MainActivity.this, BanhCuoiActivity.class);
                            //  truyền tham số khi chuyển sang màn hình
                            intent.putExtra("idloaisanpham", mangloaisp.get(position).getId());
                            // thực hiện chuyển
                            startActivity(intent);
                        } else {
                            CheckconnectInternet.ShowToast_Short(getApplicationContext(), "Bạn hãy kiểm tra lại kết nối Internet");

                        }
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 4:
                        if (CheckconnectInternet.haveNetworkConnection(getApplicationContext())) {
                            //phuong thuc chuyen man hinh
                            // khởi tạo những màn hình muốn chuyển
                            Intent intent = new Intent(MainActivity.this, QuaTangActivity.class);
                            //  truyền tham số khi chuyển sang màn hình
                            intent.putExtra("idloaisanpham", mangloaisp.get(position).getId());
                            // thực hiện chuyển
                            startActivity(intent);
                        } else {
                            CheckconnectInternet.ShowToast_Short(getApplicationContext(), "Bạn hãy kiểm tra lại kết nối Internet");

                        }
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 5:
                        if (CheckconnectInternet.haveNetworkConnection(getApplicationContext())) {
                            //phuong thuc chuyen man hinh
                            // khởi tạo những màn hình muốn chuyển
                            Intent intent = new Intent(MainActivity.this, ThucUongActivity.class);
                            //  truyền tham số khi chuyển sang màn hình
                            intent.putExtra("idloaisanpham", mangloaisp.get(position).getId());
                            // thực hiện chuyển
                            startActivity(intent);
                        } else {
                            CheckconnectInternet.ShowToast_Short(getApplicationContext(), "Bạn hãy kiểm tra lại kết nối Internet");

                        }
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 6:
                        if (CheckconnectInternet.haveNetworkConnection(getApplicationContext())) {
                            //phuong thuc chuyen man hinh
                            // khởi tạo những màn hình muốn chuyển
                            Intent intent = new Intent(MainActivity.this, LienHeActivity.class);
                            //  truyền tham số khi chuyển sang màn hình
                            // intent.putExtra("idloaisanpham",mangloaisp.get(position).getId());
                            // thực hiện chuyển
                            startActivity(intent);
                        } else {
                            CheckconnectInternet.ShowToast_Short(getApplicationContext(), "Bạn hãy kiểm tra lại kết nối Internet");

                        }
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 7:
                        if (CheckconnectInternet.haveNetworkConnection(getApplicationContext())) {
                            //phuong thuc chuyen man hinh
                            // khởi tạo những màn hình muốn chuyển
                            Intent intent = new Intent(MainActivity.this, ThongTinActivity.class);
                            //  truyền tham số khi chuyển sang màn hình
                            //intent.putExtra("idloaisanpham",mangloaisp.get(position).getId());
                            // thực hiện chuyển
                            startActivity(intent);
                        } else {
                            CheckconnectInternet.ShowToast_Short(getApplicationContext(), "Bạn hãy kiểm tra lại kết nối Internet");

                        }
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                }
            }
        });

    }

    private void LayDuLieuSanPhamMoi() {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Server.linkspmoi, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if(response!= null)
                {
                    int ID =0;
                    String tenSp="";
                    Integer giaSP = 0;
                    String HinhSP = "";
                    String MoTa = "";
                    int IDLoai = 0;
                    for(int i=0; i<response.length();i++)
                    {
                        try
                        {
                            JSONObject jsonObject = response.getJSONObject(i);
                            // truyền dữ liệu từ response
                            ID = jsonObject.getInt("id");
                            tenSp = jsonObject.getString("TenSanPham");
                            giaSP = jsonObject.getInt("Gia");
                            HinhSP = jsonObject.getString("Hinh");
                            MoTa = jsonObject.getString("MoTa");
                            IDLoai = jsonObject.getInt("IdLoai");
                            // đưa vào mảng
                            sanPhamModelArrayList.add(new SanPhamModel(ID,tenSp,giaSP,HinhSP,MoTa,IDLoai));
                            // set lại bản vẽ
                            sanPhamAdapter.notifyDataSetChanged();
                            ;                        }
                        catch (JSONException e)
                        {
                            e.printStackTrace();

                        }
                    }

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(jsonArrayRequest);

    }

    private void LayDuLieuLoaiSp() {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Server.link, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                // kiểm tra dữ liệu
                // nếu có thì đọc không thì thui để tránh gây lỗi cho app
               if(response!= null)
                {
                    for(int i=0; i<response.length();i++)
                    {
                        try
                        {
                            // truyền dữ liệu từ response
                            JSONObject jsonObject = response.getJSONObject(i);
                            id = jsonObject.getInt("id");
                            tenLoai = jsonObject.getString("tenLoai");
                            HinhLoai = jsonObject.getString("Hinh");
                            // đưa vào mảng
                            mangloaisp.add(new LoaisanphamModel(id,tenLoai,HinhLoai));
                            // update lại trên bản vẽ
                            loaiSanPhamAdapter.notifyDataSetChanged();
;                        }
                        catch (JSONException e)
                        {
                            e.printStackTrace();

                        }
                    }

                }
                mangloaisp.add(new LoaisanphamModel(0,"Liên Hệ",
                            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTlSKytUfDzHlV7UCT0yjK8lxiQUIcLyrn2ipqhNS2FgdkcnpT70w"));
                mangloaisp.add(new LoaisanphamModel(0,"Thông Tin",
                            "http://icons.iconarchive.com/icons/custom-icon-design/pretty-office-2/256/personal-information-icon.png"));
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                CheckconnectInternet.ShowToast_Short(getApplicationContext(),error.toString());
            }
        });
        requestQueue.add(jsonArrayRequest);
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
    private void Anhxa()
    {
        toolbar = (Toolbar) findViewById(R.id.toolbarmanhinhchinh);
        viewFlipper = (ViewFlipper) findViewById(R.id.viewflipper);
        listViewMHC = (ListView) findViewById(R.id.listviewmanhinhchinh);
        navigationView = (NavigationView) findViewById(R.id.navigationview);
        recyclerViewMHC = (RecyclerView) findViewById(R.id.recyclerview);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerlayout);
        mangloaisp = new ArrayList<>();
        // tạo phần tử trang chính
        mangloaisp.add(0, new LoaisanphamModel(0,"Trang Chính",
                        "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTnDkFMBORAT6tQF012bMKb0NraRuZXVbQvPhxwhGfAHPQwbXuK"));
        // tạo bản vẽ
        loaiSanPhamAdapter = new LoaiSanPhamAdapter(mangloaisp,getApplicationContext());
        // đưa vào list view
        listViewMHC.setAdapter(loaiSanPhamAdapter);
        sanPhamModelArrayList = new ArrayList<>();
        sanPhamAdapter = new SanPhamAdapter(getApplicationContext(),sanPhamModelArrayList);
        recyclerViewMHC.setHasFixedSize(true);
        // truyền vào số cột hiển thị trên màn hình
        recyclerViewMHC.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));
        recyclerViewMHC.setAdapter(sanPhamAdapter);
        if(gioHangModelArrayList !=null)
        {

        }else {
            gioHangModelArrayList = new ArrayList<>();
        }


    }
    private void ActionBar()
    {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(android.R.drawable.ic_menu_sort_by_size);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
    }
    private void ActionViewFilpper()
    {
        // tao mang chua duong dan hinh quang cao
        ArrayList<String> hinhquangcao = new ArrayList<>();
        // thêm vào địa chỉ hình ảnh
        hinhquangcao.add("https://d1yn1kh78jj1rr.cloudfront.net/image/preview/HrrabAxWinloumzm/graphicstock-table-with-cupcakes-chocolate-chip-cookies-cakepops-and-horn-pastries-studio-shot-on-brown-wooden-background-flat-lay_S_UU7iHMZ_SB_PM.jpg");
        hinhquangcao.add("https://previews.123rf.com/images/ekaterinayatcenko/ekaterinayatcenko1806/ekaterinayatcenko180600038/104238117-picnic-background-with-white-wine-and-summer-fruits-on-green-grass.jpg");
        hinhquangcao.add("https://s1.best-wallpaper.net/wallpaper/s/1812/Raspberry-jam-cake-lemon-slice-dessert_s.webp");
        hinhquangcao.add("https://s1.best-wallpaper.net/wallpaper/m/1812/One-slice-cake-cream-strawberry-blueberry_m.webp");
        hinhquangcao.add("https://s1.best-wallpaper.net/wallpaper/s/1810/One-slice-cake-berries-blueberry-fork_s.webp");
        hinhquangcao.add("https://images5.alphacoders.com/346/thumb-1920-346902.jpg");
        // load hình
        for (int i =0; i<hinhquangcao.size();i++)
        {
            ImageView imageView = new ImageView(getApplicationContext());
            // load hình vào trong imageview dùng thư viện Picasso bàng uri theo biến i
            Picasso.with(getApplicationContext()).load(hinhquangcao.get(i)).into(imageView);
            // xét lại kích cỡ hình ảnh cho fit
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            // truyền vào flipper
            viewFlipper.addView(imageView);

        }
        // xét cho flipper tự chạy
        viewFlipper.setAutoStart(true);
        // xét thời gian cho nó tự chạy là 5s
        viewFlipper.setFlipInterval(5000);
        //  khai báo các animation
        Animation animation_slide_int = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_in_right);
        Animation animation_slide_out = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_out_right);
        // xét hai animation vào viewFlipper
        viewFlipper.setInAnimation(animation_slide_int);
        viewFlipper.setOutAnimation(animation_slide_out);


    }
}
