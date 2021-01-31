package com.example.learn_parse_server;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

public class MainActivity extends AppCompatActivity {
    EditText edtStudentName ;
    EditText edtStudentAge;
    EditText edtStudentIsClever;
    Button btnSave;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ParseInstallation.getCurrentInstallation().saveInBackground();
        edtStudentName=(EditText)findViewById(R.id.edtStudentName);
        edtStudentAge=(EditText)findViewById(R.id.edtStudentAge);
        edtStudentIsClever=(EditText)findViewById(R.id.edtIsClever);
        btnSave=(Button)findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                String name=edtStudentName.getText().toString();
                int age= Integer.valueOf(edtStudentAge.getText().toString());
                Boolean isClever=Boolean.valueOf(edtStudentIsClever.getText().toString());
                ParseObject student=new ParseObject("Student");
                student.put("name",name);
                student.put("age",age);
                student.put("clever",isClever);
                student.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {
                        if(e==null) {//no errors, data is saved successfully
                            FancyToast.makeText(MainActivity.this,"Data of Student "+name+" is saved successfully",FancyToast.LENGTH_LONG,FancyToast.SUCCESS,true).show();
                        }else{
                            FancyToast.makeText(MainActivity.this,"failed to store student data "+e.getMessage(),FancyToast.LENGTH_LONG,FancyToast.ERROR,true).show();
                        }
                    }
                });
                }catch (Exception e){
                    FancyToast.makeText(MainActivity.this,"failed to store student data "+e.getMessage(),FancyToast.LENGTH_LONG,FancyToast.ERROR,true).show();

                }
            }
        });
    }
}