package com.example.mystorecake.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mystorecake.R;
import com.example.mystorecake.model.SanPhamModel;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class BanhNgotAdapter extends BaseAdapter {

    Context context;
    ArrayList<SanPhamModel> arraybanhngot;

    public BanhNgotAdapter(Context context, ArrayList<SanPhamModel> arraybanhngot) {
        this.context = context;
        this.arraybanhngot = arraybanhngot;
    }


    @Override
    public int getCount() {
        return arraybanhngot.size();
    }

    @Override
    public Object getItem(int position) {
        return arraybanhngot.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class ViewHolder
    {
        public TextView txtTenBanhNgot, txtGiaBanhNgot, txtMoTaBanhNgot;
        public ImageView imageViewHinhBN;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        // khi chạy lần đầu
        if(convertView == null)
        {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.dong_banhngot,null);
            viewHolder.txtTenBanhNgot = convertView.findViewById(R.id.textviewTenBanhNgot);
            viewHolder.txtGiaBanhNgot = convertView.findViewById(R.id.textviewGiaBanhNgot);
            viewHolder.txtMoTaBanhNgot = convertView.findViewById(R.id.textviewMoTaBanhNgot);
            viewHolder.imageViewHinhBN = convertView.findViewById(R.id.imageviewbanhngot);
            convertView.setTag(viewHolder);

        }
        else
        {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        SanPhamModel sanPhamModel = (SanPhamModel) getItem(position);
        viewHolder.txtTenBanhNgot.setText(sanPhamModel.getTenSanPham());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        viewHolder.txtGiaBanhNgot.setText(decimalFormat.format(sanPhamModel.getGia()));
        viewHolder.txtMoTaBanhNgot.setMaxLines(2);
        viewHolder.txtMoTaBanhNgot.setEllipsize(TextUtils.TruncateAt.END);
        viewHolder.txtMoTaBanhNgot.setText(sanPhamModel.getMoTa());
        Picasso.with(context).load(sanPhamModel.getHinh())
                .placeholder(R.drawable.pic)
                .error(R.drawable.error)
                .into(viewHolder.imageViewHinhBN);
        return convertView;
    }
}
