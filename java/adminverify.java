package com.example.mad_app1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class adminverify extends AppCompatActivity {

    EditText pwd,password;
    Button b1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminverify);
        b1 = findViewById(R.id.verifyBtn);
        pwd = findViewById(R.id.pwdText);
//        password.findViewById(R.id.pwdText);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(pwd.getText().toString().equals("Admin@123")) {
                    Intent intent = new Intent(adminverify.this,Activity3.class);
                    startActivity(intent);
                }
                else
                    Toast.makeText(getApplicationContext(),"Wrong Password",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        pwd.setText("");
    }
}
