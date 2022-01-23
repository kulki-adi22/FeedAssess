package com.example.mad_app1;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends  RecyclerView.Adapter<MyAdapter.MyViewHolder>{

    Context context;
    RecyclerView revi;

    public MyAdapter(Context context, ArrayList<Responses> responsesArrayList) {
        this.context = context;
        this.responsesArrayList = responsesArrayList;
    }

    ArrayList<Responses> responsesArrayList;
    @NonNull
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);

        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.MyViewHolder holder, int position) {
        Responses responses = responsesArrayList.get(position);
        holder.sub.setText(responses.subject);
        holder.usn.setText(responses.usn);
        holder.r1.setText(responses.res1);
        holder.r2.setText(responses.res2);
        holder.r3.setText(responses.res3);
        holder.r4.setText(responses.res4);
        holder.r5.setText(responses.res5);

    }

    @Override
    public int getItemCount() {
        return responsesArrayList.size();
    }
    public static class  MyViewHolder extends RecyclerView.ViewHolder{
        TextView usn,sub,r1,r2,r3,r4,r5;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            usn = itemView.findViewById(R.id.usnDisp);
            sub = itemView.findViewById(R.id.dispSub);
            r1 = itemView.findViewById(R.id.dispR1);
            r2 = itemView.findViewById(R.id.dispR2);
            r3 = itemView.findViewById(R.id.dispR3);
            r4 = itemView.findViewById(R.id.dispR4);
            r5 = itemView.findViewById(R.id.dispR5);
        }
    }
}
