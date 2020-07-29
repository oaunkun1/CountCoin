package com.akexorcist.cameraapi.v1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.akexorcist.cameraapi.R;
import com.akexorcist.cameraapi.v2.CoinActivity;
import com.akexorcist.cameraapi.v2.TodoList;
import com.akexorcist.cameraapi.v2.TodoListDAO;


import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import java.io.File;

public class text extends AppCompatActivity {



    ImageView imageView2;

    TextView textView2;
    TextView textView3;
    TextView textView4;
    TextView textView5;
    TextView textView6;
    TextView textView11;
    EditText newTodoListText;
    Button buttonNew;
    Button buttonSum;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text);
//        new SobelDemoRun().run(args);
       // Intent intent1 = getIntent();
        // Person2 person2 = (Person2) intent1.getSerializableExtra("person2");
        newTodoListText = (EditText) findViewById(R.id.addnew_editText1);
        textView2 = (TextView) findViewById(R.id.textView2);
        textView3 = (TextView) findViewById(R.id.textView3);
        textView4 = (TextView) findViewById(R.id.textView4);
        textView5 = (TextView) findViewById(R.id.textView5);
        textView6 = (TextView) findViewById(R.id.textView6);
        textView11 = (TextView) findViewById(R.id.textView11);


        buttonNew = (Button) findViewById(R.id.buttonNew);
        buttonSum = (Button) findViewById(R.id.buttonSum);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);



        new SobelDemoRun().run();
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



    class SobelDemoRun {
        Intent intent = getIntent();
        Person person = (Person) intent.getSerializableExtra("person");
        TodoList aaa = (TodoList) getIntent().getSerializableExtra("aaa");

        public void run() {
            String defaultfile1 = person.toString();//"C:\\opencv\\sources\\data\\5photo2\\Hu\\10_1.bmp";
            //String filename = ((args.length > 0) ? args[0] : default_file);
            // Load an image
            Mat src = Imgcodecs.imread(defaultfile1, Imgcodecs.IMREAD_COLOR);
// First we declare the variables we are going to use
            //Mat src, src_gray = new Mat();
            Mat grad = new Mat();
            String window_name = "Sobel Demo - Simple Edge Detector";
            int scale = 1;
            int delta = 0;
            int ddepth = CvType.CV_16S;
            // As usual we load our source image (src)
            // Check number of arguments
            if( src.empty() ) {
                System.out.println("Error opening image!");
                System.out.println("Program Arguments: [image_name -- default "
                        + defaultfile1 +"] \n");
                System.exit(-1);
            }

            Mat src_gray = new Mat();
            // Remove noise by blurring with a Gaussian filter ( kernel size = 3 )
            Imgproc.GaussianBlur( src, src, new Size(3, 3), 0, 0, Core.BORDER_DEFAULT );
            // Convert the image to grayscale
            Imgproc.cvtColor( src, src_gray, Imgproc.COLOR_RGB2GRAY );
            Mat grad_x = new Mat(), grad_y = new Mat();
            Mat abs_grad_x = new Mat(), abs_grad_y = new Mat();
            //Imgproc.Scharr( src_gray, grad_x, ddepth, 1, 0, scale, delta, Core.BORDER_DEFAULT );
            Imgproc.Sobel( src_gray, grad_x, ddepth, 1, 0, 3, scale, delta, Core.BORDER_DEFAULT );
            //Imgproc.Scharr( src_gray, grad_y, ddepth, 0, 1, scale, delta, Core.BORDER_DEFAULT );
            Imgproc.Sobel( src_gray, grad_y, ddepth, 0, 1, 3, scale, delta, Core.BORDER_DEFAULT );
            // converting back to CV_8U
            Core.convertScaleAbs( grad_x, abs_grad_x );
            Core.convertScaleAbs( grad_y, abs_grad_y );
            Core.addWeighted( abs_grad_x, 0.3, abs_grad_y, 0.3, 0, grad );
            Mat circles = new Mat();
            Imgproc.HoughCircles(grad, circles, Imgproc.HOUGH_GRADIENT, 1.0,
                    (double)grad.rows()/20, // change this value to detect circles with different distances to each other
                    100.0, 23.0, 49, 67 ); // change the last two parameters
            // (min_radius & max_radius) to detect larger circles
            int count=0,c1=0,c2=0,c5=0,c10=0;
            double r1=0,r2=0,r5=0,r10=0;
            double r_t1=0,r_t2=0,r_t5=0,r_t10=0;

            for (int x = 0; x < circles.cols(); x++) {
                double[] c = circles.get(0, x);
                Point center = new Point(Math.round(c[0]), Math.round(c[1]));
                // circle center
                Imgproc.circle(grad, center, 1, new Scalar(0,100,100), 3, 10, 0 );
                // circle outline
                int radius = (int) Math.round(c[2]);
                Imgproc.circle(grad, center, radius, new Scalar(255,0,255), 2, 10, 0 );
                System.out.println(""+ radius);

                count+=1;
                r1=(Math.pow(radius-49,2))+(Math.pow(radius-51,2));
                r2=(Math.pow(radius-54,2))+(Math.pow(radius-56,2));
                r5=(Math.pow(radius-59,2))+(Math.pow(radius-61,2));
                r10=(Math.pow(radius-64,2))+(Math.pow(radius-66,2));
                // sqrt
                r_t1=Math.sqrt(r1);
                r_t2=Math.sqrt(r2);
                r_t5=Math.sqrt(r5);
                r_t10=Math.sqrt(r10);

                if(r_t1<r_t2&&r_t1<r_t5&&r_t1<r_t10){
                    c1+=1;
                }
                else if(r_t2<r_t1&&r_t2<r_t5&&r_t2<r_t10){
                    c2+=1;
                }
                else if(r_t5<r_t1&&r_t5<r_t2&&r_t5<r_t10){
                    c5+=1;
                }
                else if(r_t10<r_t1&&r_t10<r_t5&&r_t10<r_t1){
                    c10+=1;
                }
                else{
                    if (radius>=63&&radius<=68)
                        c10=c10+1;
                    else if (radius>=57&&radius<=62)
                        c5=c5+1;
                    else if (radius>=54&&radius<=56)
                        c2=c2+1;
                    else if (radius>=45&&radius<=51)
                        c1=c1+1;
                }
            }
//            System.out.println("1:"+ c1);
//            System.out.println("2:"+ c2);
//            System.out.println("5:"+ c5);
//            System.out.println("10:"+ c10);
//
//            System.out.println(" sum:"+ count);
//            HighGui.imshow( window_name, grad );
//            HighGui.waitKey(0);
//            System.exit(0);


            int sum_cash =((c1*1)+(c2*2)+(c5*5)+(c10*10));

            textView2.setText(": " + c1);
            textView3.setText( " : " + c2 );
            textView4.setText( " : " + c5);
            textView5.setText( " : " + c10);
            textView6.setText( " : " + count);
            textView11.setText(" : " + sum_cash);

            int finalC = c1;
            int fina2C = c2;
            int fina5C = c5;
            int fina10C = c10;
            int finaCount = count;
            int finaCash = sum_cash;



            TodoList todoList = new TodoList();
//            if (aaa == null) {buttonNew.setEnabled(true);}
            buttonNew.setOnClickListener(v ->{

//                todoList.setId(todoList.getId());
                todoList.setC5(fina5C);
                todoList.setC10(fina10C);
                todoList.setCash(finaCash);
                todoList.setCount(finaCount);
                todoList.setC1(finalC);
                todoList.setName(String.valueOf(newTodoListText.getText()));
                todoList.setC2(fina2C);


                TodoListDAO todoListDAO = new TodoListDAO(getApplicationContext());
                todoListDAO.open();
                todoListDAO.add(todoList);
                todoListDAO.close();

                Intent aa = new  Intent(text.this, CoinActivity.class);
                startActivity(aa);
                finish();

                File file = new File(defaultfile1);
                file.delete();
            });

            if(aaa != null ){buttonSum.setEnabled(true);}
            else if (aaa == null) {buttonNew.setEnabled(true);}

            if(aaa != null ){newTodoListText.setText(aaa.getName());}

                buttonSum.setOnClickListener(v1 -> {
//                    TodoList todoList = new TodoList();

                    todoList.setId(aaa.getId());
                    todoList.setC12(aaa.getC1());
                    todoList.setC22(aaa.getC2());
                    todoList.setC52(aaa.getC5());
                    todoList.setC102(aaa.getC10());
                    todoList.setCount2(aaa.getCount());
                    todoList.setCash2(aaa.getCash());

                    int coin1 = aaa.getC1();
                    int coin2 = aaa.getC2();
                    int coin5 = aaa.getC5();
                    int coin10 = aaa.getC10();
                    int coincount = aaa.getCount();
                    int coincash = aaa.getCash();

                    int Sum1 = coin1 + finalC;
                    int Sum2 = coin2 + fina2C;
                    int Sum5 = coin5 + fina5C;
                    int Sum10 = coin10 + fina10C;
                    int Sumcount = coincount + finaCount;
                    int Sumcash = coincash + finaCash;
                    todoList.setC5(Sum5);
                    todoList.setC10(Sum10);
                    todoList.setCash(Sumcash);
                    todoList.setCount(Sumcount);
                    todoList.setC1(Sum1);
//                todoList.setName2(String.valueOf(newTodoListText.getText()));
                    todoList.setC2(Sum2);


                    TodoListDAO todoListDAO = new TodoListDAO(getApplicationContext());
                    todoListDAO.open();
                    todoListDAO.update2(todoList);
                    todoListDAO.close();
                    finish();
                    Intent aaa = new Intent(text.this, CoinActivity.class);
                    startActivity(aaa);
                    finish();

                    File file = new File(defaultfile1);
                    file.delete();

                });


        }

    }

}