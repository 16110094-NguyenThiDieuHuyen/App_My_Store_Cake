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

public class BanhCuoiAdapter extends BaseAdapter {

    Context context;
    ArrayList<SanPhamModel> arraybanhcuoi;

    public BanhCuoiAdapter(Context context, ArrayList<SanPhamModel> arraybanhcuoi) {
        this.context = context;
        this.arraybanhcuoi = arraybanhcuoi;
    }


    @Override
    public int getCount() {
        return arraybanhcuoi.size();
    }

    @Override
    public Object getItem(int position) {
        return arraybanhcuoi.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class ViewHolder
    {
        public TextView txtTenBanhCuoi, txtGiaBanhCuoi, txtMoTaBanhCuoi;
        public ImageView imageViewHinhBC;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        // khi chạy lần đầu
        if(convertView == null)
        {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.dong_banhcuoi,null);
            viewHolder.txtTenBanhCuoi = convertView.findViewById(R.id.textviewTenBanhCuoi);
            viewHolder.txtGiaBanhCuoi = convertView.findViewById(R.id.textviewGiaBanhCuoi);
            viewHolder.txtMoTaBanhCuoi = convertView.findViewById(R.id.textviewMoTaBanhCuoi);
            viewHolder.imageViewHinhBC = convertView.findViewById(R.id.imageviewbanhcuoi);
            convertView.setTag(viewHolder);

        }
        else
        {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        SanPhamModel sanPhamModel = (SanPhamModel) getItem(position);
        viewHolder.txtTenBanhCuoi.setText(sanPhamModel.getTenSanPham());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        viewHolder.txtGiaBanhCuoi.setText(decimalFormat.format(sanPhamModel.getGia()));
        viewHolder.txtMoTaBanhCuoi.setMaxLines(2);
        viewHolder.txtMoTaBanhCuoi.setEllipsize(TextUtils.TruncateAt.END);
        viewHolder.txtMoTaBanhCuoi.setText(sanPhamModel.getMoTa());
        Picasso.with(context).load(sanPhamModel.getHinh())
                .placeholder(R.drawable.pic)
                .error(R.drawable.error)
                .into(viewHolder.imageViewHinhBC);
        return convertView;
    }
}
