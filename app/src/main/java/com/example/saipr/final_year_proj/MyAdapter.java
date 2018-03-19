package com.example.saipr.final_year_proj;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by saipr on 3/19/2018.
 */

public class MyAdapter extends ArrayAdapter<Model> {
    private final List<Model> list;
    private final Activity context;
    int listPosititon;

    public MyAdapter(Activity context, List<Model> list) {
        super(context, R.layout.row, list);
        this.context = context;
        this.list = list;
    }
    static class ViewHolder {
        protected TextView tusn;
        protected TextView tmarks;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        listPosititon = position;
        ViewHolder viewHolder = null;
        if (convertView == null) {
            LayoutInflater inflator = context.getLayoutInflater();
            convertView = inflator.inflate(R.layout.row, null);
            viewHolder = new ViewHolder();
            viewHolder.tusn = convertView.findViewById(R.id.tvusn);
            viewHolder.tmarks = convertView.findViewById(R.id.tvmarks);

            convertView.setTag(viewHolder);
            convertView.setTag(R.id.tvusn, viewHolder.tusn);
            convertView.setTag(R.id.tvmarks, viewHolder.tmarks);


        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.tusn.setTag(position); // This line is important.
        viewHolder.tmarks.setTag(position);//qwqqqqqqq

        viewHolder.tusn.setText(list.get(position).getUsn());
        viewHolder.tmarks.setText(list.get(position).getMarks());

        return convertView;
    }
}
