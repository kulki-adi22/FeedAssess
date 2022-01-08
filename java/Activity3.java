package com.example.mad_app1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.Task;

import java.util.ArrayList;
import java.util.List;

public class Activity3 extends AppCompatActivity {
    EditText ques1, ques2, ques3, ques4, ques5;
    Button btn1, btn2;
    Spinner spn;
    String sub;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3);
        spn = (Spinner) findViewById(R.id.sub_list);
        ques1 = (EditText) findViewById(R.id.ques1);
//        ques2= (EditText) findViewById(R.id.ques2);
//        ques3= (EditText) findViewById(R.id.ques3);
//        ques4= (EditText) findViewById(R.id.ques4);
//        ques5= (EditText) findViewById(R.id.ques5);

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
//        QuestionSet q = new QuestionSet(sub,ques1.getText().toString()/*,ques2.getText().toString(),ques3.getText().toString(),ques4.getText().toString(),ques5.getText().toString()*/);
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
//                Toast.makeText(getApplicationContext(), "Question is: "+ques1.getText().toString(), Toast.LENGTH_SHORT).show();
                //obj.add(q);
                Toast.makeText(getApplicationContext(), "Subject: " + sub, Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(), "Q1: " + quesList.get(0), Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(), "Q2: " + quesList.get(1), Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(), "Q3: " + quesList.get(2), Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(), "Q4: " + quesList.get(3), Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(), "Q5: " + quesList.get(4), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
