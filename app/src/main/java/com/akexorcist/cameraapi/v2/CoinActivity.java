package com.akexorcist.cameraapi.v2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.akexorcist.cameraapi.MainActivity;
import com.akexorcist.cameraapi.R;

import java.util.ArrayList;

public class CoinActivity extends AppCompatActivity  {

    ListView todoListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coin);

        todoListView = (ListView)findViewById(R.id.list_coin);

        TodoList eName = (TodoList)getIntent().getSerializableExtra("todoUp");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home);
        {
            Intent home = new Intent(this, MainActivity.class);
            startActivity(home);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        TodoListDAO todoListDAO = new TodoListDAO(getApplicationContext());
        todoListDAO.open();

        ArrayList<TodoList> myList = todoListDAO.getAllTodoList();
        MyListView adapter = new MyListView(this,myList);
//        ArrayAdapter<TodoList> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,myList);
        todoListView.setAdapter(adapter);
        todoListDAO.close();


        todoListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(getApplicationContext(),String.valueOf(adapter.getItemId(position)),Toast.LENGTH_SHORT).show();

                Intent editIntent = new Intent(getApplicationContext(), EditActivity.class);
                editIntent.putExtra("editName",adapter.getItem(position));

                 startActivity(editIntent);
            }
        });
    }




}
