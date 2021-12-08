package com.example.d2db;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            text_item_name = itemView.findViewById(R.id.text_item_name);
            item_stat = itemView.findViewById(R.id.item_stat);
            item_option = itemView.findViewById(R.id.item_option);
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
        String Coption1 = item.getOption1();
        Coption1 = Coption1.replace("/","\n");
        String Coption2 = item.getOption2();
        Coption2 = Coption2.replace("/","\n");

        holder.text_item_name.setText(Cnames);
        holder.item_stat.setText(Coption1);
        holder.item_option.setText(Coption2);




    }

    @Override
    public int getItemCount() {
        return itemList==null ? 0 : itemList.size();
    }


}
