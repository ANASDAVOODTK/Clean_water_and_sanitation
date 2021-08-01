package com.anasandalexy.cleanwaterandsanitation;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.speech.tts.TextToSpeech;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.yangp.ypwaveview.YPWaveView;

import java.util.ArrayList;
import java.util.List;

public class RainfallAapter extends RecyclerView.Adapter<RainfallAapter.ViewHolder> {
    private List<RainfallModelList> list_data;
    private Context context;
    TextToSpeech tts;

    public RainfallAapter(List<RainfallModelList> list_data, Context context) {
        this.list_data = list_data;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.rainfall_adapter_lout,parent,false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        RainfallModelList listData=list_data.get(position);


       holder.district.setText("State : "+listData.getSubdivision());
       holder.lsgd.setText("Year : "+listData.get_year());
     //  holder.wards.setText("Category : "+listData.getAnnual());

        if(!listData.getAnnual().equals("NA"))
        {
            double a = Double.parseDouble(listData.getAnnual());
            double b = a*10;
            holder.ypWaveView.setProgress((int) b);
        }







    }

    @Override
    public int getItemCount() {
        return list_data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView district,lsgd;
        CardView cardView;
        YPWaveView ypWaveView;
        public ViewHolder(View itemView) {
            super(itemView);

            district= (TextView)itemView.findViewById(R.id.district);
            lsgd=(TextView)itemView.findViewById(R.id.lsgd);
            cardView=(CardView) itemView.findViewById(R.id.cardht);
            ypWaveView = (YPWaveView) itemView.findViewById(R.id.pd);
        }
    }

    public void filterList(ArrayList<RainfallModelList> filteredList) {
        list_data = filteredList;
        notifyDataSetChanged();
    }


}