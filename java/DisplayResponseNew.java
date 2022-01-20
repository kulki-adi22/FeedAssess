package com.example.mad_app1;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class DisplayResponseNew extends AppCompatActivity {
    FirebaseFirestore db2;
    ArrayList<Responses> responsesArrayList;
    MyAdapter myAdapter;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_response_new);
        recyclerView = findViewById(R.id.recyclerView1);
        responsesArrayList = new ArrayList<Responses>();
        myAdapter = new MyAdapter(DisplayResponseNew.this,responsesArrayList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(myAdapter);
        db2 = FirebaseFirestore.getInstance();
        db2.collection("Responses")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        for (DocumentChange dc : value.getDocumentChanges()){
                            if(dc.getType()==DocumentChange.Type.ADDED){
                                responsesArrayList.add(dc.getDocument().toObject(Responses.class));
                            }
                            myAdapter.notifyDataSetChanged();
                        }
                    }
                });
    }
}
