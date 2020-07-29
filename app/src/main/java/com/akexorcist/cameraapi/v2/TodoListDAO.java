package com.akexorcist.cameraapi.v2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

public class TodoListDAO {
    private SQLiteDatabase database;
    private DbHelper dbHelper;

    public TodoListDAO(Context context) {
        dbHelper = new DbHelper(context);
    }

    public void open(){
        database = dbHelper.getWritableDatabase();
    }

    public void close(){
        dbHelper.close();
    }

    public ArrayList<TodoList> getAllTodoList(){
        ArrayList<TodoList> todoList = new ArrayList<TodoList>();
        Cursor cursor = database.rawQuery("SELECT * FROM coincash;",null);
        cursor.moveToFirst();
        TodoList todoList1;
        while (!cursor.isAfterLast()){
            todoList1 = new TodoList();
            todoList1.setId(cursor.getInt(0));
            todoList1.setName(cursor.getString(6));
            todoList1.setC1(cursor.getInt(5));
            todoList1.setC2(cursor.getInt(7));
            todoList1.setC5(cursor.getInt(1));
            todoList1.setC10(cursor.getInt(2));
            todoList1.setCount(cursor.getInt(3));
            todoList1.setCash(cursor.getInt(4));
            todoList.add(todoList1);
//            todoList.add(cursor.getString(1));
//            todoList.add(String.valueOf(cursor.getInt(2)));
            cursor.moveToNext();
        }
        cursor.close();
        return todoList;
    }

    public void add(TodoList todoList){
        TodoList newTodoList = new TodoList();
        newTodoList = todoList;

        ContentValues values = new ContentValues();
        values.put("Name",newTodoList.getName());
        values.put("c1",newTodoList.getC1());
        values.put("c2",newTodoList.getC2());
        values.put("c5",newTodoList.getC5());
        values.put("c10",newTodoList.getC10());
        values.put("coincount",newTodoList.getCount());
        values.put("sumcash",newTodoList.getCash());
        this.database.insert("coincash",null,values);
        Log.d("Todo List Demo:::","Add OK!!!");
    }
    public void update(TodoList todoList){
        TodoList updateTodoList = todoList;
        ContentValues values = new ContentValues();
        values.put("Name", updateTodoList.getName());
        values.put("id",updateTodoList.getId());
//        values.put("c1",updateTodoList.getC1());
//        values.put("c2",updateTodoList.getC2());
//        values.put("c5",updateTodoList.getC5());
//        values.put("c10",updateTodoList.getC10());
//        values.put("coincount",updateTodoList.getCount());

        String where = "id ="+ updateTodoList.getId();
         this.database.update("coincash",values,where,null);
         Log.d("Todo List Demo:::","Update OK!!!");
    }

    public void update2(TodoList todoList){
        TodoList updateCoinList = todoList;
        ContentValues values = new ContentValues();
//        values.put("Name", updateCoinList.getName());
        values.put("id",updateCoinList.getId());
        values.put("c1",updateCoinList.getC1());
        values.put("c2",updateCoinList.getC2());
        values.put("c5",updateCoinList.getC5());
        values.put("c10",updateCoinList.getC10());
        values.put("coincount",updateCoinList.getCount());
        values.put("sumcash",updateCoinList.getCash());
        String where = "id ="+ updateCoinList.getId();
        this.database.update("coincash",values,where,null);
        Log.d("Todo List Demo:::","Update OK!!!");
    }

    public  void delete(TodoList todoList){
        TodoList delTodolist = todoList;
        String sqlText = "DELETE FROM coincash WHERE id ="+ delTodolist.getId();
        this.database.execSQL((sqlText));

    }
}
