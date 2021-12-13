package com.example.d2db;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ItemDTO_Adapter extends RecyclerView.Adapter<ItemDTO_Adapter.MyViewHolder>{
    private List<ItemDTO> itemList;


    public ItemDTO_Adapter(List<ItemDTO> itemList){this.itemList = itemList;}
    class MyViewHolder extends RecyclerView.ViewHolder{

        private TextView text_item_name;
        private TextView item_stat;
        private TextView item_option;
        private ImageView item_img;

        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            text_item_name = itemView.findViewById(R.id.text_item_name);
            item_stat = itemView.findViewById(R.id.item_stat);
            item_option = itemView.findViewById(R.id.item_option);
            item_img = itemView.findViewById(R.id.item_img);
        }

    }

    @NonNull
    @Override
    public ItemDTO_Adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.frame_item, parent, false);

        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemDTO_Adapter.MyViewHolder holder, int position) {
        ItemDTO item  = itemList.get(position);

        String Cnames = item.getNames();
        Cnames = Cnames.replace("/","\n");
        Cnames = Cnames.replaceAll("[0-9]","");
        String Coption1 = item.getOption1();
        Coption1 = Coption1.replace("/","\n");
        String Coption2 = item.getOption2();
        Coption2 = Coption2.replace("/","\n");
        String Crecommend = item.getRecommends();
        Crecommend = Crecommend.replace("/","\n");

        String detailname = Cnames;
        String detailOption1 = Coption1;
        String detailOption2 = Coption2;
        String detailrecommend = Crecommend;


        // MainActi
        String resName = "@drawable/p" + item.getNum();
        String packName = ((MainActivity)MainActivity.context_main).getPackageName();
        int resID = ((MainActivity)MainActivity.context_main).getResources().getIdentifier(resName,"drawable",packName);
        int position_number = item.getNum().intValue();
        Long likes = item.getLikes();
        Long dislikes = item.getDislikes();

        holder.text_item_name.setText(Cnames);
        holder.item_stat.setText(Coption1);
        holder.item_option.setText(Coption2);
        holder.item_img.setImageResource(resID);

        Log.d("msg",resName);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(v.getContext(), com.example.d2db.ItemPage.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("name", detailname);
                intent.putExtra("option1",detailOption1);
                intent.putExtra("option2",detailOption2);
                intent.putExtra("recommend",detailrecommend);
                intent.putExtra("resID",resID);
                intent.putExtra("position",position_number);
                intent.putExtra("likes",likes);
                intent.putExtra("dislikes",dislikes);
                v.getContext().startActivity(intent);


            }
        });

    }

    @Override
    public int getItemCount() {
        return itemList==null ? 0 : itemList.size();
    }


}
