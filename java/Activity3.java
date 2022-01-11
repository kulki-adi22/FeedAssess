package com.example.mad_app1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class Activity3 extends AppCompatActivity {
    EditText ques1, ques2, ques3, ques4, ques5;
    Button btn1, btn2;
    Spinner spn;
    String sub;
    FirebaseFirestore db;
    DBOps qset;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3);
        spn = (Spinner) findViewById(R.id.sub_list);
        ques1 = (EditText) findViewById(R.id.ques1);
        qset = new DBOps();
        db = FirebaseFirestore.getInstance();
        List<String> sub_list = new ArrayList<String>();
        sub_list.add(0, "Choose a subject");
        sub_list.add("Computer Networks-1");
        sub_list.add("Mobile Application Development");
        sub_list.add("Data Structures and Algorithms");
        sub_list.add("Operating Systems");
        sub_list.add("Database Management System");
        sub_list.add("Computer Networks-2");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, sub_list);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spn.setAdapter(arrayAdapter);
        spn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selected = spn.getSelectedItem().toString();
                if (!selected.equals("Choose a subject"))
                    sub = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        List<String> quesList = new ArrayList<String>();
        final int[] i = {2};
        String st;
        ques1.setHint("Enter question 1");
        btn1 = findViewById(R.id.button);
        btn2 = findViewById(R.id.nextBtn);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ques1.setHint("Enter question " + i[0]);
                quesList.add(ques1.getText().toString());
                i[0]++;
                ques1.setText("");
                if (i[0] == 7) {
                    Toast.makeText(getApplicationContext(), "Max Ques Limit reached!", Toast.LENGTH_SHORT).show();
                    ques1.setVisibility(View.INVISIBLE);
                    btn2.setVisibility(View.INVISIBLE);
                    btn1.setVisibility(View.VISIBLE);
                }
                if (spn.getSelectedItem() == "Choose a subject")
                    Toast.makeText(getApplicationContext(), "Please choose a subject", Toast.LENGTH_SHORT).show();
            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (spn.getSelectedItem() == "Choose a subject")
                    Toast.makeText(getApplicationContext(), "Please choose a subject", Toast.LENGTH_SHORT).show();
                addDataToFirestore(sub,quesList);
            }
        });
    }

    private void addDataToFirestore(String subject, List<String> questionList)
    {
//        qset.setSubject(subject);
        qset.setQ1(questionList.get(0).toString());
        qset.setQ2(questionList.get(1).toString());
        qset.setQ3(questionList.get(2).toString());
        qset.setQ4(questionList.get(3).toString());
        qset.setQ5(questionList.get(4).toString());
        CollectionReference questions = db.collection("Questions");
        questions.document(subject).set(qset).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(getApplicationContext(),"Added to firestore",Toast.LENGTH_SHORT).show();
            }
        });
//        questions.add(qset).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
//            @Override
//            public void onSuccess(DocumentReference documentReference) {
//                Toast.makeText(getApplicationContext(),"Added to firestore",Toast.LENGTH_SHORT).show();
//            }
//        }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//                Toast.makeText(getApplicationContext(),"Failed to add",Toast.LENGTH_SHORT).show();
//            }
//        });
    }
}
