package com.example.mrquang.bt2.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.Button;

import com.example.mrquang.bt2.R;
import com.example.mrquang.bt2.activity.DetailActivity;
import com.example.mrquang.bt2.activity.MainActivity;
import com.example.mrquang.bt2.fragment.DetailFragment;
import com.example.mrquang.bt2.models.XsTinh;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MrQuang on 28/04/2017.
 */

public class ProvinceAdapter extends BaseAdapter{

    List<XsTinh> xsTinhs;
    LayoutInflater inflater;

    MainActivity mainActivity;


    public ProvinceAdapter(MainActivity context){
        xsTinhs = new ArrayList<>();
        inflater = LayoutInflater.from(context);
        this.mainActivity = context;
    }

    @Override
    public int getCount() {
        return xsTinhs.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    public void setData(List<XsTinh> data){
        xsTinhs.clear();
        if(data != null){
            xsTinhs.addAll(data);
        }
        if(xsTinhs.size() >0){
            DetailFragment detailFragment = mainActivity.getDetailFragment();
            if(detailFragment != null){
                detailFragment.changeData(xsTinhs.get(0));
            }
        }
        notifyDataSetChanged();
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null) {
            convertView = inflater.inflate(R.layout.item_province, parent, false);
        }

        final XsTinh xsTinh = xsTinhs.get(position);
        Button bt1 = (Button) convertView.findViewById(R.id.button);
        bt1.setText(xsTinh.getProvince());

        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //fragment.callAbc(xsTinh);

                DetailFragment detailFragment = mainActivity.getDetailFragment();
                if(detailFragment != null){
                    detailFragment.changeData(xsTinh);
                }else{
                    //TODO Start Activity
                    Intent intent = new Intent(mainActivity, DetailActivity.class);
                    intent.putExtra("xs_tinh", xsTinh);
                    mainActivity.startActivity(intent);
                }
            }
        });

        return convertView;
    }
}
