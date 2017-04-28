package com.example.mrquang.bt2.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.mrquang.bt2.R;
import com.example.mrquang.bt2.fragment.DetailFragment;
import com.example.mrquang.bt2.models.XsTinh;

/**
 * Created by MrQuang on 27/04/2017.
 */

public class DetailActivity extends AppCompatActivity {

    DetailFragment detailFragment;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        //GET XsTinh

        detailFragment = (DetailFragment) getFragmentManager().findFragmentById(R.id.frgDetailFragmnt);

        Intent intent = getIntent();
        if(intent.hasExtra("xs_tinh")){
            XsTinh xsTinh = (XsTinh) intent.getSerializableExtra("xs_tinh");
            Toast.makeText(getApplicationContext(), "XS_TINH:" + xsTinh.getProvince(), Toast.LENGTH_SHORT).show();
            detailFragment.changeData(xsTinh);
        }else{
            //TODO
            //TODO TOAST LOI
            Toast.makeText(getApplicationContext(), "Khong hop le (...) da xay ra", Toast.LENGTH_SHORT).show();
            finish();
        }

    }
}
