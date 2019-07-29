package com.example.mystorecake.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mystorecake.R;
import com.example.mystorecake.model.LoaisanphamModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class LoaiSanPhamAdapter extends BaseAdapter {

    ArrayList<LoaisanphamModel> arrayListLoaiSanPham;

    public LoaiSanPhamAdapter(ArrayList<LoaisanphamModel> arrayListLoaiSanPham, Context context) {
        this.arrayListLoaiSanPham = arrayListLoaiSanPham;
        this.context = context;
    }

    Context context;

    // trả về số lượng sản phẩm
    @Override
    public int getCount() {
        return arrayListLoaiSanPham.size();
    }
// trả về từng thuộc tính trong mảng
    @Override
    public Object getItem(int position) {
        return arrayListLoaiSanPham.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    // giúp cho việc load dữ liệu lên nhanh hơn
    public class ViewHolder
    {
        TextView txttenloaisp;
        ImageView imageloaisp;

    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null ;
        if(convertView==null)
        {
            viewHolder = new ViewHolder();
            LayoutInflater layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.dong_listview_loaisanpham,null);
            // gán id cho các thuộc tính bên trong layout
            viewHolder.txttenloaisp = (TextView) convertView.findViewById(R.id.textviewloaisp);
            viewHolder.imageloaisp = (ImageView) convertView.findViewById(R.id.imageviewloaisp);
            // ánh xạ qua
            convertView.setTag(viewHolder);
        }
        else
        {
            viewHolder = (ViewHolder) convertView.getTag();

        }
        LoaisanphamModel loaisanpham = (LoaisanphamModel) getItem(position);
        // set lại những dữ liệu của sản phẩm
        viewHolder.txttenloaisp.setText(loaisanpham.getTenLoai());
        Picasso.with(context).load(loaisanpham.getHinh())
                .placeholder(R.drawable.pic)
                .error(R.drawable.error)
                .into(viewHolder.imageloaisp);
        return convertView;
    }
}
