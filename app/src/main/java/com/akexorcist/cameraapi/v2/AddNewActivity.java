package com.akexorcist.cameraapi.v2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;

import com.akexorcist.cameraapi.R;

public class AddNewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new);

        EditText newTodoListText = (EditText) findViewById(R.id.addnew_editText);
        Button newTodoListBtn = (Button)findViewById(R.id.addnew_button);

        newTodoListBtn.setOnClickListener(v -> {
            TodoList todoList = new TodoList();
            todoList.setName(String.valueOf(newTodoListText.getText()));
            todoList.getC1();
            TodoListDAO todoListDAO = new TodoListDAO(getApplicationContext());
            todoListDAO.open();
            todoListDAO.add(todoList);
            todoListDAO.close();

            Intent aa = new  Intent(this,CoinActivity.class);
            startActivity(aa);
            finish();
        });
    }


}
