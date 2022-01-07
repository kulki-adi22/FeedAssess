package com.example.mad_app1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class Activity2 extends AppCompatActivity {
    private Spinner subjects;
    String selectedSubject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
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

    }
}
