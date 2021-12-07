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
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        ItemDTO item  = itemList.get(position);
        holder.text_item_name.setText(item.getNames());
        holder.item_stat.setText(item.getOption1());
        holder.item_option.setText(item.getOption2());




    }

    @Override
    public int getItemCount() {
        return itemList==null ? 0 : itemList.size();
    }


}



//public class ListItemAdapter extends BaseAdapter {
//    ArrayList<ListItem> items = new ArrayList<ListItem>();
//    Context context;
//
//    @Override
//    public int getCount() {
//        return items.size();
//    }
//
//    @Override
//    public Object getItem(int position) {
//        return items.get(position);
//    }
//
//    @Override
//    public long getItemId(int position) {
//        return position;
//    }
//
//    //데이터 화면출력 메소드
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        context = parent.getContext();
//        ListItem listItem = items.get(position);
//
//        if(convertView == null){
//            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            convertView = inflater.inflate(R.layout.frame_item, parent, false);
//        }
//
//
//
//        //참조
//        ImageView item_imgDrawable = convertView.findViewById(R.id.item_img);
//        TextView text_item_nameTextView = convertView.findViewById(R.id.text_item_name);
//        TextView item_statTextView  = convertView.findViewById(R.id.item_stat);
//        TextView item_optionTextView = convertView.findViewById(R.id.item_option);
//      //  Button btn_bookmarkButton = convertView.findViewById(R.id.btn_bookmark);
//
//        //set
//        item_imgDrawable.setImageDrawable(listItem.getItem_img());
//        text_item_nameTextView.setText(listItem.getText_item_name());
//        item_statTextView.setText(listItem.getItem_stat());
//        item_optionTextView.setText(listItem.getItem_option());
////        btn_bookmarkButton.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                Toast.makeText(context.getApplicationContext(),"text",Toast.LENGTH_SHORT).show();
////            }
////        });
//
//
//        return convertView;
//    }
//    public void addItem(ListItem item){
//        items.add(item);
//    }
//}
