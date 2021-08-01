package com.anasandalexy.cleanwaterandsanitation;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class GWActivity extends AppCompatActivity {


    BottomNavigationView bottomNavigation;
    private RecyclerView rv;
    private List<GWModelList> list_data;
    private GWAapter adapter;
    LottieAnimationView load;
    public EditText serch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gw_hotspot);

        load = findViewById(R.id.load);

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        rv=(RecyclerView)findViewById(R.id.recycleview);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this));
        list_data=new ArrayList<>();
        adapter=new GWAapter(list_data,this);
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
        ArrayList<GWModelList> filteredList = new ArrayList<>();
        for (GWModelList item : list_data) {
            if (item.getState().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
            }
        }
        adapter.filterList(filteredList);
    }

    private void getData() {
        StringRequest stringRequest=new StringRequest(Request.Method.GET, "https://jsonkeeper.com/b/NSBC", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject=new JSONObject(response);
                    JSONArray array=jsonObject.getJSONArray("2020");
                    Log.d("ddddddd", String.valueOf(array));




                    for (int i=0; i<array.length(); i++){
                        JSONObject ob=array.getJSONObject(i);
                        GWModelList ld=new GWModelList(ob.getString("state"),ob.getString("level"));
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