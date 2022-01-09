package com.example.mad_app1;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
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
    String selectedSubject;
    FirebaseFirestore db;
    TextView t1;
    Button b1;
    static final String TAG="Read Data Activity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        b1 = findViewById(R.id.btntemp);
        db = FirebaseFirestore.getInstance();
        subjects=findViewById(R.id.spinner_subjects);
        List<String> sub_list = new ArrayList<String>();
        sub_list.add(0,"Choose a subject");
        sub_list.add("Computer Networks-1");
        sub_list.add("Mobile Application Development");
        sub_list.add("Data Structures and Algorithms");
        sub_list.add("Operating Systems");
        sub_list.add("Database Management System");
        sub_list.add("Computer Networks-2");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,sub_list);
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

        QuestionSet q = new QuestionSet();
        final String[] str = new String[1];
//        DocumentReference documentReference = db.collection("Questions").document();
//        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
//            @Override
//            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
//                str[0] = ((String) value.get("Questions"));
//            }
//        });
        List<String> q_list = new ArrayList<String>();
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.collection("Questions")
                        .whereEqualTo("subject","Computer Networks-1")
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if (task.isSuccessful())
                                {
                                    Toast.makeText(Activity2.this,"read Succesful",Toast.LENGTH_SHORT).show();
                                    for(QueryDocumentSnapshot document : task.getResult()){
                                        Log.d(TAG,document.getId()+"=>"+document.getData());
                                        q_list.add(document.getData().toString());
                                    }

                                }
                            }
                        });
                b1.setVisibility(View.INVISIBLE);
            }
        });
        



    }


}
