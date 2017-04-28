package com.example.mrquang.bt2.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.example.mrquang.bt2.R;
import com.example.mrquang.bt2.activity.DetailActivity;
import com.example.mrquang.bt2.activity.MainActivity;
import com.example.mrquang.bt2.adapters.ProvinceAdapter;
import com.example.mrquang.bt2.models.Giai;
import com.example.mrquang.bt2.models.Xs;
import com.example.mrquang.bt2.models.XsTinh;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by MrQuang on 27/04/2017.
 */

public class MenuFragmentOfMainActyFragment extends Fragment{

    List<XsTinh> xsTinhs;

    interface Callback{
        void callBack(List<XsTinh> result);
    }

    ListView listView;
    ProvinceAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_menu, container, true);
        listView = (ListView) fragmentView.findViewById(R.id.listView);

        adapter = new ProvinceAdapter((MainActivity) getActivity());
        listView.setAdapter(adapter);
        return fragmentView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



        xsTinhs = new ArrayList<>();
        new DocJson(new Callback() {
            @Override
            public void callBack(List<XsTinh> result) {
                xsTinhs = result;
                Toast.makeText(getActivity(), "KETQUA:" +
                        result.size(), Toast.LENGTH_LONG).show();
                /*XsTinh tinh = xsTinhs.get(0);
                tinh.getProvince();
                List<Xs> xs = tinh.getXs();
                Xs xs1 = xs.get(0);
                xs1.getGiai();
                xs1.getDate();*/

                adapter.setData(xsTinhs);

            }
        }).execute();

    }


    class DocJson extends AsyncTask<Callback, Integer, List<XsTinh>> {

        String URL = "http://thanhhungqb.tk:8080/kqxsmn";
        Callback callback;
        DocJson(Callback callback){
            this.callback = callback;
        }


        @Override
        protected List<XsTinh> doInBackground(Callback... params) {
            String json =  docNoiDungTuURL(URL);
            List<XsTinh> xsTinhs = parseJson(json);
            return xsTinhs;
        }

        @Override
        protected void onPostExecute(List<XsTinh>  s) {
            if(callback != null){
                callback.callBack(s);
            }
        }



        private List<XsTinh> parseJson(String json){
            List<XsTinh> result = new ArrayList<>();
            try {

                Log.i("JSON_DATA", json +"");

                JSONObject jsonObject = new JSONObject(json);
                Iterator<String> keys = jsonObject.keys();
                while (keys.hasNext()){

                    String provinceName = keys.next();

                    JSONObject provinceJson = jsonObject.getJSONObject(provinceName); // Moi Tinh thanh
                    XsTinh xsTinh = new XsTinh();
                    xsTinh.setXs(parseXsTinh(provinceJson));
                    xsTinh.setProvince(provinceName);
                    result.add(xsTinh);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
            return result;
        }

        private String docNoiDungTuURL(String theUrl){
            StringBuilder content = new StringBuilder();
            try{
                java.net.URL url = new URL(theUrl);
                URLConnection urlConnection = url.openConnection();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                String line;
                while((line= bufferedReader.readLine())!= null){
                    content.append(line + "\n");
                }
                bufferedReader.close();
            }
            catch (Exception e){
                e.printStackTrace();
            }
            return content.toString();
        }

        public List<Xs> parseXsTinh(JSONObject provinceJson) throws JSONException {

            if(provinceJson == null){
                Log.d("showInfo -> " , "jsonObject: NULL");
                return null;
            }

            Iterator<String> keys = provinceJson.keys();

            List<Xs> xss = new ArrayList<>();
            while (keys.hasNext()){ // for moi ngay
                try {
                    String dateKeys = keys.next();

                    Log.d("Date:", dateKeys + "yyyyyy");

                    JSONObject jsonDate = provinceJson.getJSONObject(dateKeys);// JSON Ngay


                    Giai giai = new Giai();
                    giai.setGiai1(parseGiai(jsonDate.getJSONArray("1")));
                    giai.setGiai2(parseGiai(jsonDate.getJSONArray("2")));
                    giai.setGiai3(parseGiai(jsonDate.getJSONArray("3")));
                    giai.setGiai4(parseGiai(jsonDate.getJSONArray("4")));
                    giai.setGiai5(parseGiai(jsonDate.getJSONArray("5")));
                    giai.setGiai6(parseGiai(jsonDate.getJSONArray("6")));
                    giai.setGiai7(parseGiai(jsonDate.getJSONArray("7")));
                    giai.setGiai8(parseGiai(jsonDate.getJSONArray("8")));
                    giai.setGiaiDB(parseGiai(jsonDate.getJSONArray("DB")));

                    Xs xs = new Xs();
                    xs.setDate(dateKeys);
                    xs.setGiai(giai);

                    xss.add(xs);
                }catch (JSONException e){
                    e.printStackTrace();
                }
            }

            //Ta dc so so
/*
        for(Xs xs : xss){
            Log.d("XS", xs.getDate() +"");
            Giai giai = xs.getGiai();
            Log.d("XS-> Giai 1:", giai.getGiai1()[0] + "");
        }*/
            return  xss;
        }


        private  int[] parseGiai(JSONArray json)throws JSONException{
            JSONArray jsonArray = json;
            if(jsonArray.length() <= 0){
                return null;
            }
            int giai[] = new int[jsonArray.length()];
            for(int i = 0; i < jsonArray.length(); i++){
                giai[i] = jsonArray.getInt(i);
            }
            return giai;
        }

    }


}
