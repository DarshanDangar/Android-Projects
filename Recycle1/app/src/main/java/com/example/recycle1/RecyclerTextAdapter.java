package com.example.recycle1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerTextAdapter extends RecyclerView.Adapter<RecyclerTextAdapter.ViewHolder> {
    Context context;
    ArrayList<TextModel> arrayText;

    RecyclerTextAdapter(Context context,ArrayList<TextModel> arrayText){
        this.context = context;
        this.arrayText = arrayText;
    }

    @NonNull
    @Override
    public RecyclerTextAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.text1_row,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            holder.text.setText(arrayText.get(position).text);
            holder.description.setText(arrayText.get(position).description);
    }

    @Override
    public int getItemCount() {return arrayText.size();}

    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView text,description;

        public ViewHolder(View itemView){
            super(itemView);

            text = itemView.findViewById(R.id.fname);
            description = itemView.findViewById(R.id.lname);
        }
    }

}
