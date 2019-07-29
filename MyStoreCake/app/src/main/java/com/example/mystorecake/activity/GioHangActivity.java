package com.example.mystorecake.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.mystorecake.R;
import com.example.mystorecake.adapter.GioHangAdapter;
import com.example.mystorecake.model.GioHangModel;
import com.example.mystorecake.ultil.CheckconnectInternet;

import java.text.DecimalFormat;

public class GioHangActivity extends AppCompatActivity {
    ListView lvgiohang;
    TextView txtthongbao;
    static TextView txttongtien;
    Button btnthanhtoan, btntieptuc;
    Toolbar toolbargiohang;
    GioHangAdapter gioHangAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gio_hang);
        AnhXa();
        ActionToolBar();
        KiemTraDuLieu();
        Duadulieulenlistview();
        Batsukienchotungitemlistview();
        SuKienNhanNut();
    }

    private void SuKienNhanNut() {
        btntieptuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });
        btnthanhtoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(MainActivity.gioHangModelArrayList.size()>0)
                {
                    Intent intent = new Intent(getApplicationContext(),ThongTinKhachHangActivity.class);
                    startActivity(intent);
                }
                else
                {
                    CheckconnectInternet.ShowToast_Short(getApplicationContext(),
                            "Bạn chưa chọn sản phẩm thanh toán");
                }
            }
        });
    }

    private void Batsukienchotungitemlistview() {
        lvgiohang.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(GioHangActivity.this);
                builder.setTitle("Xác nhận xóa sản phẩm");
                builder.setMessage("Bạn có chắc muốn xóa sản phẩm này");
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(MainActivity.gioHangModelArrayList.size()<=0)
                        {
                            txtthongbao.setVisibility(View.VISIBLE);
                        }
                        else
                        {
                            MainActivity.gioHangModelArrayList.remove(position);
                            gioHangAdapter.notifyDataSetChanged();
                            Duadulieulenlistview();
                            if(MainActivity.gioHangModelArrayList.size()<=0)
                            {
                                txtthongbao.setVisibility(View.INVISIBLE);
                            }
                            else
                            {
                                txtthongbao.setVisibility(View.INVISIBLE);
                                gioHangAdapter.notifyDataSetChanged();
                                Duadulieulenlistview();
                            }
                        }
                    }
                });
                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        gioHangAdapter.notifyDataSetChanged();
                        Duadulieulenlistview();
                    }
                });
                return true;
            }
        });

    }

    public static void Duadulieulenlistview() {
        long tongtien =0;
        for(int i=0;i<MainActivity.gioHangModelArrayList.size();i++)
        {
            tongtien+=MainActivity.gioHangModelArrayList.get(i).getGiasp();

        }
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        txttongtien.setText(decimalFormat.format(tongtien)+"Đ");

    }


    private void KiemTraDuLieu() {
        // kiem tra cod tuoov tinh trong gio hang
        if(MainActivity.gioHangModelArrayList.size() <=0)
        {
            gioHangAdapter.notifyDataSetChanged();
            txtthongbao.setVisibility(View.VISIBLE);
            lvgiohang.setVisibility(View.INVISIBLE);
        }
        else
        {
            gioHangAdapter.notifyDataSetChanged();
            txtthongbao.setVisibility(View.INVISIBLE);
            lvgiohang.setVisibility(View.VISIBLE);
        }

    }

    private void ActionToolBar() {
        setSupportActionBar(toolbargiohang);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbargiohang.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void AnhXa() {
        lvgiohang =(ListView) findViewById(R.id.listviewgiohang);
        txtthongbao = (TextView) findViewById(R.id.textviewThongBao);
        txttongtien = (TextView) findViewById(R.id.textviewtonggiohang);
        btnthanhtoan = (Button) findViewById(R.id.btnthanhtoan);
        btntieptuc = (Button) findViewById(R.id.btntieptuc);
        toolbargiohang = (Toolbar) findViewById(R.id.toolbargiohang);
        gioHangAdapter = new GioHangAdapter(GioHangActivity.this, MainActivity.gioHangModelArrayList);
        lvgiohang.setAdapter(gioHangAdapter);
    }
}
