package com.akexorcist.cameraapi.v1;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ImageView;

import com.akexorcist.cameraapi.R;

import java.io.File;

public class CountCoin extends AppCompatActivity {

    Button delete;
    Button Next;
    ImageView imageView;

    //private File person;
//    private Person person;
//    private Object Person;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_count_coin);

        Intent intent = getIntent();
        Person person = (Person) intent.getSerializableExtra("person");

        imageView = (ImageView) findViewById(R.id.imageView);
        imageView.setImageURI(Uri.parse(person.toString()));
        delete = (Button) findViewById(R.id.delete);
        delete.setOnClickListener(v -> {
            File file = new File((person.toString()));
            file.delete();

            opencoin();
        });

        Next = (Button) findViewById(R.id.Next);
        Next.setOnClickListener(v -> {
            Intent intent1 = new Intent(CountCoin.this, text.class);
            File file2 = new File((person.toString()));
            Person2 person2 = new Person2();
            person2.setFile(file2.getPath());
            intent1.putExtra("person2", person2);
            startActivity(intent1);
            //opencoin();
        });

    }

//    private  void opennext(){
//        Intent intent = getIntent();
//        Person person = (Person) intent.getSerializableExtra("person");
//        Intent i = new Intent(this,SobelDemo.class);
//        File file2 = new File(person.toString());
//        Person2 person2 = new  Person2();
//        person2.setFile(file2.getPath());
//        person2.setFile(file2.getName());
//        intent.putExtra("person2")
//
//    }

    private void opencoin() {
        Intent intent = new Intent(this, CameraV1Activity.class);
        startActivity(intent);
    }


}
