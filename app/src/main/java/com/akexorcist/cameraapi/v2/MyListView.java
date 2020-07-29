package com.akexorcist.cameraapi.v2;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.akexorcist.cameraapi.R;

import java.util.ArrayList;

public class MyListView extends BaseAdapter {
    private static Activity activity;
    private static LayoutInflater inflater;
    ArrayList<TodoList> myTodoList;

    public MyListView(Activity activity, ArrayList<TodoList> myTodoList) {
        this.myTodoList = myTodoList;
        this.activity = activity;
        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return myTodoList.size();
    }

    @Override
    public TodoList getItem(int position) {
        return myTodoList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return myTodoList.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        v = inflater.inflate(R.layout.my_list_layout,null);
        TextView textView = (TextView)v.findViewById(R.id.listview_text);

        TodoList todoList = myTodoList.get(position);
        textView.setText(todoList.getName());
        return v;
    }
}
