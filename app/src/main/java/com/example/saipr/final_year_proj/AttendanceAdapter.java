package com.example.saipr.final_year_proj;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;

/**
 * Created by saipr on 3/29/2018.
 */


public class AttendanceAdapter extends ArrayAdapter<ModelAttendance> {
    private final List<ModelAttendance> list;
    private final Activity context;
    int listPosititon;

    public AttendanceAdapter(Activity context, List<ModelAttendance> list) {
        super(context, R.layout.row, list);
        this.context = context;
        this.list = list;
    }
    static class ViewHolder {
        protected TextView tusn;
        protected TextView tname;
        protected CheckBox chbox;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        listPosititon = position;
        ViewHolder viewHolder = null;
        if (convertView == null) {
            LayoutInflater inflator = context.getLayoutInflater();
            convertView = inflator.inflate(R.layout.rowattendance, null);
            viewHolder = new ViewHolder();
            viewHolder.tusn = convertView.findViewById(R.id.tvusn);
            viewHolder.tname = convertView.findViewById(R.id.tvname);
            viewHolder.chbox= convertView.findViewById(R.id.chkbox);
            convertView.setTag(viewHolder);
            convertView.setTag(R.id.tvusn, viewHolder.tusn);
            convertView.setTag(R.id.tvname, viewHolder.tname);
            convertView.setTag(R.id.chkbox, viewHolder.chbox);


        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.tusn.setTag(position); // This line is important.
        viewHolder.tname.setTag(position);//qwqqqqqqq
        viewHolder.chbox.setTag(position);//qwqqqqqqq


        viewHolder.tusn.setText(list.get(position).getUsn());
        viewHolder.tname.setText(list.get(position).getName());
        viewHolder.chbox.setChecked(list.get(position).getStatus());

        return convertView;
    }
}
