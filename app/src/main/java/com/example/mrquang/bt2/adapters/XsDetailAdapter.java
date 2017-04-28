package com.example.mrquang.bt2.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.mrquang.bt2.R;
import com.example.mrquang.bt2.models.Giai;
import com.example.mrquang.bt2.models.Xs;
import com.example.mrquang.bt2.models.XsTinh;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MrQuang on 28/04/2017.
 */

public class XsDetailAdapter extends BaseAdapter {
    XsTinh xsTinh;
    List<Xs> xs;

    LayoutInflater inflater;

    public XsDetailAdapter(Context context){
        xs = new ArrayList<>();
        inflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return (xsTinh != null)? xs.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public void setData(XsTinh data){
        this.xsTinh = data;
        xs.clear();
        List<Xs> listXs = data.getXs();
        if(data != null && listXs != null && listXs.size() > 0){
            this.xs.addAll(listXs);
        }
        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = inflater.inflate(R.layout.item_xs_detail, parent, false);
        }
        Xs xs = this.xs.get(position);
        Giai giai = xs.getGiai();


        TextView textGiai = (TextView) convertView.findViewById(R.id.txt_ten_giai);
        textGiai.setText( xsTinh.getProvince() + "  " + xs.getDate());

        TextView txtGiai1 = (TextView) convertView.findViewById(R.id.txt_giai1);
        txtGiai1.setText(buildGiai(giai.getGiai1()));

        TextView txtGiai2 = (TextView) convertView.findViewById(R.id.txt_giai2);
        txtGiai2.setText(buildGiai(giai.getGiai2()));

        TextView txtGiai3 = (TextView) convertView.findViewById(R.id.txt_giai3);
        txtGiai3.setText(buildGiai(giai.getGiai3()));

        TextView txtGiai4 = (TextView) convertView.findViewById(R.id.txt_giai4);
        txtGiai4.setText(buildGiai(giai.getGiai4()));

        TextView txtGiai5 = (TextView) convertView.findViewById(R.id.txt_giai5);
        txtGiai5.setText(buildGiai(giai.getGiai5()));

        TextView txtGiai6 = (TextView) convertView.findViewById(R.id.txt_giai6);
        txtGiai6.setText(buildGiai(giai.getGiai6()));

        TextView txtGiai7 = (TextView) convertView.findViewById(R.id.txt_giai7);
        txtGiai7.setText(buildGiai(giai.getGiai7()));

        TextView txtGiai8 = (TextView) convertView.findViewById(R.id.txt_giai8);
        txtGiai8.setText(buildGiai(giai.getGiai8()));

        TextView txtGiaiDB = (TextView) convertView.findViewById(R.id.txt_giaiDB);
        txtGiaiDB.setText(buildGiai(giai.getGiaiDB()));


        return convertView;
    }


    private String buildGiai(int[] giai){
        if(giai == null || giai.length == 0){
            return "";
        }
        StringBuilder builder = new StringBuilder();
        for(int i = 0; i < giai.length; i++){
            if(i == 0){
                builder.append(giai[i] + "");
            }else{
               builder.append(" - " + giai[i]);
            }
        }
        return builder.toString();
    }

}
