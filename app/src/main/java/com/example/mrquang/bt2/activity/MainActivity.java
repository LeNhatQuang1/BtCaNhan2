package com.example.mrquang.bt2.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.mrquang.bt2.R;
import com.example.mrquang.bt2.fragment.DetailFragment;

public class MainActivity extends AppCompatActivity {

    DetailFragment detailFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            detailFragment = (DetailFragment) getFragmentManager().findFragmentById(R.id.frgDetailFragmnt);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public DetailFragment getDetailFragment() {
        return detailFragment;
    }
}
