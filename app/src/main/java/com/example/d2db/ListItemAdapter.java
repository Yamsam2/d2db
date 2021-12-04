package com.example.d2db;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class ListItemAdapter extends BaseAdapter {
    ArrayList<ListItem> items = new ArrayList<ListItem>();
    Context context;

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    //데이터 화면출력 메소드
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        context = parent.getContext();
        ListItem listItem = items.get(position);

        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.frame_item, parent, false);
        }



        //참조
        ImageView item_imgDrawable = convertView.findViewById(R.id.item_img);
        TextView text_item_nameTextView = convertView.findViewById(R.id.text_item_name);
        TextView item_statTextView  = convertView.findViewById(R.id.item_stat);
        TextView item_optionTextView = convertView.findViewById(R.id.item_option);
        Button btn_bookmarkButton = convertView.findViewById(R.id.btn_bookmark);

        //set
        item_imgDrawable.setImageDrawable(listItem.getItem_img());
        text_item_nameTextView.setText(listItem.getText_item_name());
        item_statTextView.setText(listItem.getItem_stat());
        item_optionTextView.setText(listItem.getItem_option());
        btn_bookmarkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context.getApplicationContext(),"text",Toast.LENGTH_SHORT).show();
            }
        });


        return convertView;
    }
    public void addItem(ListItem item){
        items.add(item);
    }
}
