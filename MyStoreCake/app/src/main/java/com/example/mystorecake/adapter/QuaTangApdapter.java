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

public class QuaTangApdapter extends BaseAdapter {

    Context context;
    ArrayList<SanPhamModel> arrayQuaTang;

    public QuaTangApdapter (Context context, ArrayList<SanPhamModel> arrayQuaTang) {
        this.context = context;
        this.arrayQuaTang = arrayQuaTang;
    }


    @Override
    public int getCount() {
        return arrayQuaTang.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayQuaTang.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class ViewHolder
    {
        public TextView txtTenQuaTang, txtGiaQuaTang, txtMoTaQuaTang;
        public ImageView imageViewHinhQuaTang;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        // khi chạy lần đầu
        if(convertView == null)
        {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.dong_quatang,null);
            viewHolder.txtTenQuaTang = convertView.findViewById(R.id.textviewTenQuaTang);
            viewHolder.txtGiaQuaTang = convertView.findViewById(R.id.textviewGiaQuaTang);
            viewHolder.txtMoTaQuaTang = convertView.findViewById(R.id.textviewMoTaQuaTang);
            viewHolder.imageViewHinhQuaTang = convertView.findViewById(R.id.imageviewquatang);
            convertView.setTag(viewHolder);

        }
        else
        {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        SanPhamModel sanPhamModel = (SanPhamModel) getItem(position);
        viewHolder.txtTenQuaTang.setText(sanPhamModel.getTenSanPham());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        viewHolder.txtGiaQuaTang.setText(decimalFormat.format(sanPhamModel.getGia()));
        viewHolder.txtMoTaQuaTang.setMaxLines(2);
        viewHolder.txtMoTaQuaTang.setEllipsize(TextUtils.TruncateAt.END);
        viewHolder.txtMoTaQuaTang.setText(sanPhamModel.getMoTa());
        Picasso.with(context).load(sanPhamModel.getHinh())
                .placeholder(R.drawable.pic)
                .error(R.drawable.error)
                .into(viewHolder.imageViewHinhQuaTang);
        return convertView;
    }
}
