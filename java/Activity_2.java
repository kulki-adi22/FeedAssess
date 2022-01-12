package com.example.mad_app1;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class Activity2 extends AppCompatActivity {
    private Spinner subjects;
    String selectedSubject="";
    FirebaseFirestore db;
    TextView usnText;
    Button b1;
    TextView q1, q2, q3, q4, q5;
    ScrollView qList;
    List<Question> q_list;
    static final String TAG = "Read Data Activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        b1 = findViewById(R.id.btnTemp);
        db = FirebaseFirestore.getInstance();
        subjects = findViewById(R.id.spinner_subjects);
        q1 = findViewById(R.id.ques1);
        q2 = findViewById(R.id.ques2);
        q3 = findViewById(R.id.ques3);
        q4 = findViewById(R.id.ques4);
        q5 = findViewById(R.id.ques5);
        qList = findViewById(R.id.ques_list);
        usnText = findViewById(R.id.usnInput);
        qList = findViewById(R.id.ques_list);
        List<String> sub_list = new ArrayList<String>();
        ArrayList<String> questions = new ArrayList<String>();
        sub_list.add(0, "Choose a subject");
        sub_list.add("Computer Networks-1");
        sub_list.add("Mobile Application Development");
        sub_list.add("Data Structures and Algorithms");
        sub_list.add("Operating Systems");
        sub_list.add("Database Management System");
        sub_list.add("Computer Networks-2");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, sub_list);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        subjects.setAdapter(arrayAdapter);
        subjects.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selected = subjects.getSelectedItem().toString();
                if (!selected.equals("Choose a subject"))
                    selectedSubject = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                subjects.setVisibility(View.INVISIBLE);
                b1.setVisibility(View.INVISIBLE);
                usnText.setVisibility(View.INVISIBLE);
                DocumentReference docRef = db.collection("Questions").document(selectedSubject);
                docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            String qu1 = document.get("q1").toString();
                            String qu2 = document.get("q2").toString();
                            String qu3 = document.get("q3").toString();
                            String qu4 = document.get("q4").toString();
                            String qu5 = document.get("q5").toString();
                            setQuestions(qu1,qu2,qu3,qu4,qu5);
                            q1.setText(qu1);
                            if (document.exists()) {
                                Log.d(TAG, "DocumentSnapshot data: " + document.getData());
                            } else {
                                Log.d(TAG, "No such document");
                            }
                        } else {
                            Log.d(TAG, "get failed with ", task.getException());
                        }
                    }
                });

            }
        });

    }

    private void setQuestions(String qu1, String qu2, String qu3, String qu4, String qu5) {
        q1.setText(qu1);
        q2.setText(qu2);
        q3.setText(qu3);
        q4.setText(qu4);
        q5.setText(qu5);
        qList.setVisibility(View.VISIBLE);
    }

}
