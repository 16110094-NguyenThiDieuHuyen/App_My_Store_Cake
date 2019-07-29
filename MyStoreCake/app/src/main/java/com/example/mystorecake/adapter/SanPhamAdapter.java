package com.example.mystorecake.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mystorecake.R;
import com.example.mystorecake.activity.ChiTietSanPhamActivity;
import com.example.mystorecake.model.SanPhamModel;
import com.example.mystorecake.ultil.CheckconnectInternet;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class SanPhamAdapter  extends RecyclerView.Adapter<SanPhamAdapter.IHolder> {
    // truyền màn hình muốn vẽ
    Context context;
    ArrayList<SanPhamModel> sanPhamModelArrayList;

    public SanPhamAdapter(Context context, ArrayList<SanPhamModel> sanPhamModelArrayList) {
        this.context = context;
        this.sanPhamModelArrayList = sanPhamModelArrayList;
    }

    @NonNull
    @Override
    public IHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.dong_spmoi,null);
        IHolder iHolder = new IHolder(v);
        return iHolder;
    }
    @Override
    public void onBindViewHolder(@NonNull IHolder iHolder, int i) {
        // lấy sản phẩm theo vị trí
        SanPhamModel sanPhamModel = sanPhamModelArrayList.get(i);
        iHolder.txtTenSP.setText(sanPhamModel.getTenSanPham());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        iHolder.txtGiaSP.setText("Giá : "+decimalFormat.format(sanPhamModel.getGia())+
                "Đ");
        Picasso.with(context).load(sanPhamModel.getHinh())
                .placeholder(R.drawable.pic)
                .error(R.drawable.error)
                .into(iHolder.imagehinhsanpham);
    }
    @Override
    public int getItemCount() {
        return sanPhamModelArrayList.size();
    }
// hỗ trọ bắt những giá trị trên màn hình , hỗ trợ bắt các item trog recucler
    public class IHolder extends RecyclerView.ViewHolder
    {
        public ImageView imagehinhsanpham;
        public TextView txtTenSP, txtGiaSP;

        public IHolder(@NonNull View itemView) {
            super(itemView);
            imagehinhsanpham = (ImageView) itemView.findViewById(R.id.imageviewSanPham);
            txtGiaSP = (TextView) itemView.findViewById(R.id.textviewGiasp);
            txtTenSP = (TextView) itemView.findViewById(R.id.textviewTenSp);
            // bắt sự kiện trên từng item khi click
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // chuyên màn hình
                    Intent intent = new Intent(context, ChiTietSanPhamActivity.class);
                    // gán từ khóa và bắt vị trí trên từng item
                    intent.putExtra("thongtinsanpham",sanPhamModelArrayList.get(getPosition()));
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    CheckconnectInternet.ShowToast_Short(context,sanPhamModelArrayList.get(getPosition()).getTenSanPham());

                    context.startActivity(intent);


                }
            });
        }
    }
}
