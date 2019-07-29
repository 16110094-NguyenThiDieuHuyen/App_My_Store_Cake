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

public class BanhKemAdapter extends BaseAdapter {
    Context context;
    ArrayList<SanPhamModel> banhkemarraylist;

    public BanhKemAdapter(Context context, ArrayList<SanPhamModel> banhkemarraylist) {
        this.context = context;
        this.banhkemarraylist = banhkemarraylist;
    }

    @Override
    public int getCount() {
        return banhkemarraylist.size();
    }

    @Override
    public Object getItem(int position) {
        return banhkemarraylist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class ViewHolder {
        public TextView txtTenBanhKem,txtGiaBanhKem,txtMoTaBanhKem;
        public ImageView imageViewbanhkem;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if(convertView == null)
        {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.dong_banhkem,null);
            viewHolder.txtTenBanhKem = (TextView) convertView.findViewById(R.id.textviewTenBanhKem);
            viewHolder.txtGiaBanhKem = (TextView) convertView.findViewById(R.id.textviewGiaBanhKem);
            viewHolder.txtMoTaBanhKem = (TextView) convertView.findViewById(R.id.textviewMoTaBanhKem);
            viewHolder.imageViewbanhkem = (ImageView) convertView.findViewById(R.id.imageviewbanhkem);
            convertView.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        SanPhamModel sanpham = (SanPhamModel) getItem(position);
        // set lại những dữ liệu của sản phẩm
        viewHolder.txtTenBanhKem.setText(sanpham.getTenSanPham());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        viewHolder.txtGiaBanhKem.setText(decimalFormat.format(sanpham.getGia())+
                "Đ");
        viewHolder.txtMoTaBanhKem.setMaxLines(2);
        // gắn dấu ba chấm..
        viewHolder.txtMoTaBanhKem.setEllipsize(TextUtils.TruncateAt.END);
        viewHolder.txtMoTaBanhKem.setText(sanpham.getMoTa());
        Picasso.with(context).load(sanpham.getHinh())
                .placeholder(R.drawable.pic)
                .error(R.drawable.error)
                .into(viewHolder.imageViewbanhkem);
        return convertView;
    }
}
