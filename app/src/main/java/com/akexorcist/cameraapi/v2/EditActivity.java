package com.akexorcist.cameraapi.v2;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.akexorcist.cameraapi.R;
import com.akexorcist.cameraapi.v1.CameraV1Activity;


public class EditActivity extends AppCompatActivity  {


    private SQLiteDatabase database;
    private TodoList editName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        TodoList editName = (TodoList) getIntent().getSerializableExtra("editName");


        EditText editText = (EditText)findViewById(R.id.edit_editText);
        editText.setText(editName.getName());

        TextView cc1 = (TextView)findViewById(R.id.textc1);
        TextView cc2= (TextView)findViewById(R.id.textc2);
        TextView cc5 = (TextView)findViewById(R.id.textc5);
        TextView cc10 = (TextView)findViewById(R.id.textc10);
        TextView ccount = (TextView)findViewById(R.id.textccount);
        TextView ccash = (TextView)findViewById(R.id.textccash);
        cc1.setText(String.valueOf(editName.getC1()));
        cc2.setText(String.valueOf(editName.getC2()));
        cc5.setText(String.valueOf(editName.getC5()));
        cc10.setText(String.valueOf(editName.getC10()));
        ccount.setText(String.valueOf(editName.getCount()));
        ccash.setText(String.valueOf(editName.getCash()));

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Button editBtn = (Button)findViewById(R.id.edit_button);
        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TodoList eName = new TodoList();
                eName.setId(editName.getId());
                eName.setName(String.valueOf(editText.getText()));
//                eName.setC1(Integer.parseInt(String.valueOf(cc1.getText())));
//                eName.setC2(Integer.parseInt(String.valueOf(cc2.getText())));
//                eName.setC5(Integer.parseInt(String.valueOf(cc5.getText())));
//                eName.setC10(Integer.parseInt(String.valueOf(cc10.getText())));
//                eName.setCount(Integer.parseInt(String.valueOf(ccount.getText())));

                TodoListDAO todoListDAO = new TodoListDAO(getApplicationContext());
                todoListDAO.open();
                todoListDAO.update(eName);
                todoListDAO.close();
                finish();

            }
        });



        Button delBtn = findViewById(R.id.delete_btn);
        delBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TodoListDAO todoListDAODel = new TodoListDAO(getApplicationContext());
                todoListDAODel.open();
                todoListDAODel.delete(editName);
                todoListDAODel.close();
                finish();
            }
        });

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent aaa = new  Intent(EditActivity.this, CameraV1Activity.class);
                TodoList eName = new TodoList();
                eName.setId(editName.getId());
                eName.setName(editName.getName());
                eName.setC1(editName.getC1());
                eName.setC2(editName.getC2());
                eName.setC5(editName.getC5());
                eName.setC10(editName.getC10());
                eName.setCount(editName.getCount());
                eName.setCash(editName.getCash());
                aaa.putExtra("eName",eName);
                startActivity(aaa);

            }
        });

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home);
        {
            this.finish();
        }

        return super.onOptionsItemSelected(item);
    }


}
