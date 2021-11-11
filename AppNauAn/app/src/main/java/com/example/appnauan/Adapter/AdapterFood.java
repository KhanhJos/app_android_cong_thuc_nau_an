package com.example.appnauan.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.appnauan.Model.MonAn;
import com.example.appnauan.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterFood extends BaseAdapter {

    Context context;
    int layout;
    List<MonAn> listMonAn;

    public AdapterFood(Context context, int layout, List<MonAn> listMonAn) {
        this.context = context;
        this.layout = layout;
        this.listMonAn = listMonAn;
    }

    @Override
    public int getCount() {
        return listMonAn.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    private class ViewHolder{
        ImageView imageViewFood;
        TextView textViewName;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = convertView;

        if (view == null){
            view = inflater.inflate(layout,parent,false);
            ViewHolder viewHolder = new ViewHolder();

            viewHolder.textViewName = view.findViewById(R.id.textviewName);
            viewHolder.imageViewFood = view.findViewById(R.id.imageviewFood);

            view.setTag(viewHolder);
        }

        ViewHolder viewHolder = (ViewHolder) view.getTag();

        viewHolder.textViewName.setText(listMonAn.get(position).getName());
        Picasso.get().load(listMonAn.get(position).getImage()).placeholder(R.drawable.load).error(R.drawable.error).fit().into(viewHolder.imageViewFood);
        return view;
    }
}
