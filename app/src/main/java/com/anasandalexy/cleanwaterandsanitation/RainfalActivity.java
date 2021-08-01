package com.anasandalexy.cleanwaterandsanitation;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class RainfalActivity extends AppCompatActivity {


    BottomNavigationView bottomNavigation;
    private RecyclerView rv;
    private List<RainfallModelList> list_data;
    private RainfallAapter adapter;
    LottieAnimationView load;
    public EditText serch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rainfall_hotspot);

        load = findViewById(R.id.load);

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        rv=(RecyclerView)findViewById(R.id.recycleview);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this));
        list_data=new ArrayList<>();
        adapter=new RainfallAapter(list_data,this);
        getData();
        serch = findViewById(R.id.seachhtpt);
        serch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());
            }
        });




    }
    private void filter(String text) {
        ArrayList<RainfallModelList> filteredList = new ArrayList<>();
        for (RainfallModelList item : list_data) {
            if (item.getSubdivision().toLowerCase().contains(text.toLowerCase()) | item.get_year().toLowerCase().contains(text.toLowerCase()) ) {
                filteredList.add(item);
            }
        }
        adapter.filterList(filteredList);
    }

    private void getData() {
        StringRequest stringRequest=new StringRequest(Request.Method.GET, "https://api.data.gov.in/resource/f9adeeab-582f-40d0-ad99-4405a074a742?api-key=579b464db66ec23bdd000001897453e78c7f4a786dd907d6eaf2a46dd&format=json&offset=0&limit=10000&filters[parameter]=Percentage%20departure", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject=new JSONObject(response);
                    JSONArray array=jsonObject.getJSONArray("records");
                    Log.d("ddddddd", String.valueOf(array));




                    for (int i=0; i<array.length(); i++){
                        JSONObject ob=array.getJSONObject(i);
                        RainfallModelList ld=new RainfallModelList(ob.getString("subdivision"),ob.getString("_year"),ob.getString("annual"));
                        list_data.add(ld);
                        load.setVisibility(View.GONE);
                    }
                    rv.setAdapter(adapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }


}