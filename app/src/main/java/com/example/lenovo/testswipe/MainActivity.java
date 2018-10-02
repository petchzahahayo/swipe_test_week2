package com.example.lenovo.testswipe;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.solver.widgets.ConstraintAnchor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView lis;  //แสดงข้อมูล
    private DatabaseReference databaseReference; //แสดงข้อมูล
    private ArrayList<String> alistname;  //แสดงข้อมูล
    private ArrayAdapter<String> adaptername;  //แสดงข้อมูล
    addData dataadd =new addData();  //แสดงข้อมูล
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Swipe();//เปลี่ยนหน้า

        lis=findViewById(R.id.lis1);  //แสดงข้อมูล
        alistname = new ArrayList<>();  //แสดงข้อมูล
        adaptername = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,alistname);  //แสดงข้อมูล
        databaseReference = FirebaseDatabase.getInstance().getReference();  //แสดงข้อมูล


        databaseReference.addValueEventListener(new ValueEventListener() {  //แสดงข้อมูล
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ds: dataSnapshot.getChildren()){

                    dataadd  = ds.getValue(addData.class);
                    String id = ds.getKey();
                    String name = dataadd.getName();
                    String full_name = "No: "+ id +"   |   "+"Name:   "+ name;
                    alistname.add(full_name);
                }
                lis.setAdapter(adaptername);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) { //แสดงข้อมูล

            }
        });
    }


    public void Swipe(){  //เปลี่ยนหน้า

        ConstraintLayout Con=findViewById(R.id.main1);
        Con.setOnTouchListener(new swipe(this){

            @Override
            public void onSwipeRight() {
                Intent intent = new Intent(MainActivity.this,Main2Activity.class);
                startActivity(intent);
            }
        });
    }

}
