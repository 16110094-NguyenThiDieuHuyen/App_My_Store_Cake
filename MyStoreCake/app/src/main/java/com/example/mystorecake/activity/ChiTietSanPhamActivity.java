package com.example.mystorecake.activity;

import android.content.Intent;
import android.drm.DrmStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.mystorecake.R;
import com.example.mystorecake.model.GioHangModel;
import com.example.mystorecake.model.SanPhamModel;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;

public class ChiTietSanPhamActivity extends AppCompatActivity {
    Toolbar toolbarCTSp;
    ImageView imageViewCT;
    TextView txtTenCT, txtGia, txtMoTa;
    Spinner spinner;
    Button buttondm;
    int id = 0;
    String tensp = "", hinhctsp ="", motachitiet="";
    int giactsp = 0;
    int Idlsanpham=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_san_pham);
        AnhXa();
        ActionToolBar();
        LayDuLieu();
        BatSuKienSpinner();
        BatsuKienButton();

    }

    private void BatsuKienButton() {
        buttondm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // kiểm tra mảng có rỗng ko
                if(MainActivity.gioHangModelArrayList.size()>0)
                {

                    int sl= Integer.parseInt(spinner.getSelectedItem().toString());
                    boolean exist = false;
                    for(int i=0;i<MainActivity.gioHangModelArrayList.size();i++)
                    {
                        if(MainActivity.gioHangModelArrayList.get(i).getIdsp() == id)
                        {
                            MainActivity.gioHangModelArrayList.get(i).setSoluong(MainActivity.gioHangModelArrayList.get(i).getSoluong()+sl);
                            if(MainActivity.gioHangModelArrayList.get(i).getSoluong()>=10)
                            {
                                MainActivity.gioHangModelArrayList.get(i).setSoluong(10);
                            }
                            MainActivity.gioHangModelArrayList.get(i)
                            .setGiasp(giactsp*MainActivity.gioHangModelArrayList.get(i).getSoluong());
                            exist = true;

                        }
                    }
                    if(exist == false)
                    {
                        int soluong = Integer.parseInt(spinner.getSelectedItem().toString());
                        long Giamoi = soluong*giactsp;
                        MainActivity.gioHangModelArrayList.add(new GioHangModel(id,tensp,Giamoi,hinhctsp,soluong));

                    }

                }
                else
                {
                    int soluong= Integer.parseInt(spinner.getSelectedItem().toString());
                    long Giacapnhaap = soluong*giactsp;
                    MainActivity.gioHangModelArrayList.add(new GioHangModel(id,tensp,Giacapnhaap,hinhctsp,soluong));
                }
                Intent intent = new Intent(getApplicationContext(),GioHangActivity.class);
                startActivity(intent);

            }
        });

    }

    private void BatSuKienSpinner() {
        Integer[] soluong = new Integer[]{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15};
        ArrayAdapter<Integer> arrayAdapter = new ArrayAdapter<Integer>(this,android.R.layout.simple_spinner_dropdown_item,soluong);
        spinner.setAdapter(arrayAdapter);
    }
    private void LayDuLieu() {
        SanPhamModel sanPhamModel = (SanPhamModel) getIntent().getSerializableExtra("thongtinsanpham");
        id = sanPhamModel.getID();
        tensp = sanPhamModel.getTenSanPham();
        giactsp = sanPhamModel.getGia();
        hinhctsp = sanPhamModel.getHinh();
        motachitiet = sanPhamModel.getMoTa();
        Idlsanpham = sanPhamModel.getIdLoai();
        txtTenCT.setText(tensp);
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        txtGia.setText("Giá :"+decimalFormat.format(giactsp) + "Đ");
        txtMoTa.setText(motachitiet);
        Picasso.with(getApplicationContext()).load(hinhctsp)
                .placeholder(R.drawable.pic)
                .error(R.drawable.error).into(imageViewCT);


    }

    private void ActionToolBar() {
        setSupportActionBar(toolbarCTSp);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarCTSp.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
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

    private void AnhXa() {
        toolbarCTSp = (Toolbar) findViewById(R.id.toolbarchitietsanpham);
        txtTenCT = (TextView) findViewById(R.id.textviewTenCTSP);
        txtGia = (TextView) findViewById(R.id.textviewGiaCTSP);
        txtMoTa = (TextView) findViewById(R.id.textviewMotaCTSP);
        imageViewCT = (ImageView) findViewById(R.id.imageviewchitietsanpham);
        spinner = (Spinner) findViewById(R.id.spinner);
        buttondm = (Button) findViewById(R.id.btndatmua);

    }
}
