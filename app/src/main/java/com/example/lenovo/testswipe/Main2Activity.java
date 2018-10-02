package com.example.lenovo.testswipe;

import android.content.Intent;
import android.media.FaceDetector;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Main2Activity extends AppCompatActivity {

    EditText gettext; //ส่งข้อมูล
    Button button;  //ส่งข้อมูล
    FirebaseDatabase database; //ส่งข้อมูล
    DatabaseReference myRef; //ส่งข้อมูล
    addData addData = new addData(); //ส่งข้อมูล


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        gettext=(EditText)findViewById(R.id.add_name); //ส่งข้อมูล
        button=(Button) findViewById(R.id.btn_add); //ส่งข้อมูล
        swipe();//เปลี่ยนหน้า
        button.setOnClickListener(new View.OnClickListener() { //ส่งข้อมูล
            @Override
            public void onClick(View view) {

                getvalue();
            }
        });
    }

    public void getvalue(){ //ส่งข้อมูล
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("29");
        addData.setName(gettext.getText().toString());

        myRef.setValue(addData);


    }

    public  void swipe(){//เปลี่ยนหน้า

        RelativeLayout rel=findViewById(R.id.main2);
        rel.setOnTouchListener(new swipe(this){
            @Override
            public void onSwipeLeft() {
                Intent intent = new Intent(Main2Activity.this,MainActivity.class);
                startActivity(intent);
        }
        });

    }
}
