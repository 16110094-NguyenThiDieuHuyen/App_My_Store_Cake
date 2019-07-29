package com.example.mystorecake.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mystorecake.R;
import com.example.mystorecake.activity.GioHangActivity;
import com.example.mystorecake.activity.MainActivity;
import com.example.mystorecake.model.GioHangModel;
import com.example.mystorecake.model.SanPhamModel;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class GioHangAdapter extends BaseAdapter
{
    Context context;
    ArrayList <GioHangModel> arraygiohang;


    public GioHangAdapter(Context context, ArrayList<GioHangModel> giohanglist) {
        this.context = context;
        this.arraygiohang = giohanglist;
    }

    @Override
    public int getCount() {
        return arraygiohang.size();
    }

    @Override
    public Object getItem(int position) {
        return arraygiohang.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    public class ViewHolder

    {
        public TextView txtTenGioHang, txtGiaGioHang;
        public ImageView imglohang;
        public Button btnminius, btcplus, btnvalue;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if(convertView == null)
        {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.dong_giohang,null);
            viewHolder.txtTenGioHang = (TextView) convertView.findViewById(R.id.textviewtengiohang);
            viewHolder.txtGiaGioHang = (TextView) convertView.findViewById(R.id.textviewgiagiohang);
            viewHolder.imglohang = (ImageView) convertView.findViewById(R.id.imageviewgiohang);
            viewHolder.btcplus = (Button) convertView.findViewById(R.id.buttomplus);
            viewHolder.btnminius = (Button) convertView.findViewById(R.id.buttominus);
            viewHolder.btnvalue = (Button) convertView.findViewById(R.id.buttomvalue);
            convertView.setTag(viewHolder);

        }
        else

        {
            viewHolder= (ViewHolder) convertView.getTag();
        }
        final GioHangModel gioHangModel = (GioHangModel) getItem(position);
        viewHolder.txtTenGioHang .setText(gioHangModel.getTensp());
        //DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
       // viewHolder.txtGiaGioHang.setText(decimalFormat.format(String.valueOf(gioHangModel.getGiasp())+"Đ"));
        viewHolder.txtGiaGioHang.setText(gioHangModel.getGiasp()+"Đ");
        Picasso.with(context).load(gioHangModel.getHinhsp())
                .placeholder(R.drawable.pic)
                .error(R.drawable.error)
                .into(viewHolder.imglohang);
        viewHolder.btnvalue.setText(gioHangModel.getSoluong()+"");
        int sl = Integer.parseInt(viewHolder.btnvalue.getText().toString());
        if(sl>=15)
        {
            viewHolder.btcplus.setVisibility(View.INVISIBLE);
            viewHolder.btnminius.setVisibility(View.VISIBLE);
        }
        else if(sl<=1)
        {
            viewHolder.btcplus.setVisibility(View.VISIBLE);
            viewHolder.btnminius.setVisibility(View.INVISIBLE);
        }
        else if(sl>=1)
        {
            viewHolder.btnminius.setVisibility(View.VISIBLE);
            viewHolder.btcplus.setVisibility(View.VISIBLE);
        }
        // khi nhán nút thêm cập lại giá và sô lượng
        final ViewHolder finalViewHolder = viewHolder;
        viewHolder.btcplus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int slmoi = Integer.parseInt(finalViewHolder.btnvalue.getText().toString())+1 ;
                int slht = MainActivity.gioHangModelArrayList.get(position).getSoluong();
                long giaht = MainActivity.gioHangModelArrayList.get(position).getGiasp();
                MainActivity.gioHangModelArrayList.get(position).setSoluong(slmoi);
                long giamoi = (giaht*slmoi)/slht;
                MainActivity.gioHangModelArrayList.get(position).setGiasp(giamoi);
                //DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
                //finalViewHolder.txtGiaGioHang.setText(decimalFormat.format(String.valueOf(giamoi)+"Đ"));
                finalViewHolder.txtGiaGioHang.setText(giamoi+"");
                GioHangActivity.Duadulieulenlistview();
                if(slmoi > 14)
                {
                    finalViewHolder.btcplus.setVisibility(View.INVISIBLE);
                    finalViewHolder.btnminius.setVisibility(View.VISIBLE);
                    finalViewHolder.btnvalue.setText(String.valueOf(slmoi));
                }
                else
                {
                    finalViewHolder.btcplus.setVisibility(View.VISIBLE);
                    finalViewHolder.btnminius.setVisibility(View.VISIBLE);
                    finalViewHolder.btnvalue.setText(String.valueOf(slmoi));
                }
            }
        });
        viewHolder.btnminius.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int slmoi = Integer.parseInt(finalViewHolder.btnvalue.getText().toString())-1 ;
                int slht = MainActivity.gioHangModelArrayList.get(position).getSoluong();
                long giaht = MainActivity.gioHangModelArrayList.get(position).getGiasp();
                MainActivity.gioHangModelArrayList.get(position).setSoluong(slmoi);
                long giamoi = (giaht*slmoi)/slht;
                MainActivity.gioHangModelArrayList.get(position).setGiasp(giamoi);
               //DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
              // finalViewHolder.txtGiaGioHang.setText(decimalFormat.format(String.valueOf(giamoi)+"Đ"));
                finalViewHolder.txtGiaGioHang.setText(giamoi+"");
                GioHangActivity.Duadulieulenlistview();
                if(slmoi < 2)
                {
                    finalViewHolder.btcplus.setVisibility(View.VISIBLE);
                    finalViewHolder.btnminius.setVisibility(View.INVISIBLE);
                    finalViewHolder.btnvalue.setText(String.valueOf(slmoi));
                }
                else
                {
                    finalViewHolder.btcplus.setVisibility(View.VISIBLE);
                    finalViewHolder.btnminius.setVisibility(View.VISIBLE);
                    finalViewHolder.btnvalue.setText(String.valueOf(slmoi));
                }

            }
        });
        return convertView;
    }
}
