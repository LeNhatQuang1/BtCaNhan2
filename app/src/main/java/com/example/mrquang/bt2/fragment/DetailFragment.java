package com.example.mrquang.bt2.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mrquang.bt2.R;
import com.example.mrquang.bt2.adapters.XsDetailAdapter;
import com.example.mrquang.bt2.models.XsTinh;

/**
 * Created by MrQuang on 27/04/2017.
 */

public class DetailFragment extends Fragment {

    TextView txtInfo;
    ListView lv;

    XsTinh xsTinh;

    XsDetailAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_detail, container, true);
        /*txtInfo = (TextView)fragmentView.findViewById(R.id.txtInfo);
        txtInfo.setText("ban chua click");*/

        lv = (ListView)fragmentView.findViewById(R.id.lv);

        adapter = new XsDetailAdapter(getActivity());
        lv.setAdapter(adapter);

        return fragmentView;
    }


    public void changeData(XsTinh xsTinh){
        this.xsTinh = xsTinh;
        if(adapter != null) {
            adapter.setData(xsTinh);
        }
    }



}
