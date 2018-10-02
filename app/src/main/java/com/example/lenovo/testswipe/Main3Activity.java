package com.example.lenovo.testswipe;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Main3Activity extends AppCompatActivity {

    private DatabaseReference databaseReference;
    TextView t1,t2;
    addData dataadd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        t1 = findViewById(R.id.first);
        t2 = findViewById(R.id.last);


        databaseReference = FirebaseDatabase.getInstance().getReference();
        final Query before = databaseReference.orderByKey().equalTo("28");
        before.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot before_n: dataSnapshot.getChildren()){

                    dataadd = before_n.getValue(addData.class);
                    String id = before_n.getKey();
                    t1.setText(id+""+dataadd.getName());
                }


            }

            @Override
            public void onCancelled(@NonNull  DatabaseError databaseError) {

            }
        });



        final Query after = databaseReference.orderByKey().equalTo("30");
        after.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot after_n: dataSnapshot.getChildren()) {
                    dataadd = after_n.getValue(addData.class);
                    String id = after_n.getKey();
                    t2.setText(id + " " + dataadd.getName());

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    public void swipe(){

        ConstraintLayout con=findViewById(R.id.main3);
        con.setOnTouchListener(new swipe(this){

            @Override
            public void onSwipeRight() {
                Intent intent = new Intent(Main3Activity.this,Main2Activity.class);
                startActivity(intent);
            }
        });


    }


}
