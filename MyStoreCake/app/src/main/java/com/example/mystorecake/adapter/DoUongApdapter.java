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

public class DoUongApdapter extends BaseAdapter {

    Context context;
    ArrayList<SanPhamModel> arraydouong;

    public DoUongApdapter(Context context, ArrayList<SanPhamModel> arraydouong) {
        this.context = context;
        this.arraydouong = arraydouong;
    }


    @Override
    public int getCount() {
        return arraydouong.size();
    }

    @Override
    public Object getItem(int position) {
        return arraydouong.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class ViewHolder
    {
        public TextView txtTenDoUong, txtGiaDoUong, txtMoTaDoUong;
        public ImageView imageViewHinhDoUong;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        // khi chạy lần đầu
        if(convertView == null)
        {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.dong_thucuong,null);
            viewHolder.txtTenDoUong = convertView.findViewById(R.id.textviewTenDoUong);
            viewHolder.txtGiaDoUong = convertView.findViewById(R.id.textviewGiaDoUong);
            viewHolder.txtMoTaDoUong = convertView.findViewById(R.id.textviewMoTaDoUong);
            viewHolder.imageViewHinhDoUong = convertView.findViewById(R.id.imageviewDoUong);
            convertView.setTag(viewHolder);

        }
        else
        {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        SanPhamModel sanPhamModel = (SanPhamModel) getItem(position);
        viewHolder.txtTenDoUong.setText(sanPhamModel.getTenSanPham());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        viewHolder.txtGiaDoUong.setText(decimalFormat.format(sanPhamModel.getGia()));
        viewHolder.txtMoTaDoUong.setMaxLines(2);
        viewHolder.txtMoTaDoUong.setEllipsize(TextUtils.TruncateAt.END);
        viewHolder.txtMoTaDoUong.setText(sanPhamModel.getMoTa());
        Picasso.with(context).load(sanPhamModel.getHinh())
                .placeholder(R.drawable.pic)
                .error(R.drawable.error)
                .into(viewHolder.imageViewHinhDoUong);
        return convertView;
    }
}
