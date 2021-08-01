package com.anasandalexy.cleanwaterandsanitation;

import android.annotation.SuppressLint;
import android.content.Context;
import android.speech.tts.TextToSpeech;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.yangp.ypwaveview.YPWaveView;

import java.util.ArrayList;
import java.util.List;

public class LABAapter extends RecyclerView.Adapter<LABAapter.ViewHolder> {
    private List<LABModelList> list_data;
    private Context context;
    TextToSpeech tts;

    public LABAapter(List<LABModelList> list_data, Context context) {
        this.list_data = list_data;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.lab_adapter_lout,parent,false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        LABModelList listData=list_data.get(position);


       holder.district.setText("Lab : "+listData.getState());
       holder.lsgd.setText("Level : "+listData.getLevel() +" m");
       holder.lab.setText("Category : "+listData.getLname());








    }

    @Override
    public int getItemCount() {
        return list_data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView district,lsgd,lab;
        CardView cardView;
        YPWaveView ypWaveView;
        public ViewHolder(View itemView) {
            super(itemView);

            district= (TextView)itemView.findViewById(R.id.district);
            lab= (TextView)itemView.findViewById(R.id.lab);
            lsgd=(TextView)itemView.findViewById(R.id.lsgd);
            cardView=(CardView) itemView.findViewById(R.id.cardht);
            ypWaveView = (YPWaveView) itemView.findViewById(R.id.pd);
        }
    }

    public void filterList(ArrayList<LABModelList> filteredList) {
        list_data = filteredList;
        notifyDataSetChanged();
    }


}