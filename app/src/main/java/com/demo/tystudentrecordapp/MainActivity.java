package com.demo.tystudentrecordapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {
    EditText ID, Name, Course, Address, Contact;
    TextView Output;
    static final int READ_BLOCK_SIZE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ID = findViewById(R.id.etStudentID);
        Name = findViewById(R.id.etStudentName);
        Course = findViewById(R.id.etStudentCourse);
        Address = findViewById(R.id.etStudentAddress);
        Contact = findViewById(R.id.etStudentContact);
        Output = findViewById(R.id.output1);
    }

    public void StoreBtn(View v){

        try {
            FileOutputStream fileout=openFileOutput("mytextfile.txt", MODE_PRIVATE);
            OutputStreamWriter outputWriter=new OutputStreamWriter(fileout);
            outputWriter.write("Student ID: " + ID.getText().toString()+"\n");
            outputWriter.write("Student Name: " + Name.getText().toString()+"\n");
            outputWriter.write("Course and Section: " + Course.getText().toString()+"\n");
            outputWriter.write("Residence Address: " + Address.getText().toString()+"\n");
            outputWriter.write("Contact Number: " + Contact.getText().toString());
            outputWriter.close();

            //display file saved message
            Toast.makeText(getBaseContext(), "File saved successfully!",
                    Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void RetrieveBtn(View v){

        try {
            String id = ID.getText().toString();
            String name = Name.getText().toString();
            String course = Course.getText().toString();
            String address = Address.getText().toString();
            String contact = Contact.getText().toString();
            FileInputStream fileIn=openFileInput("mytextfile.txt");
            InputStreamReader InputRead= new InputStreamReader(fileIn);

            char[] inputBuffer= new char[READ_BLOCK_SIZE];
            String s="I am " + name + " with " + id + " taken up " + course + " with " + address + ", for any question you may contact me at " + contact + "\n\n\n\n";
            int charRead;

            while ((charRead=InputRead.read(inputBuffer))>0) {
                // char to string conversion
                String readstring=String.copyValueOf(inputBuffer,0,charRead);
                s +=readstring;
            }
            InputRead.close();
            Output.setText(s);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void ClearBtn(View v){
        ID = findViewById(R.id.etStudentID);
        Name = findViewById(R.id.etStudentName);
        Course = findViewById(R.id.etStudentCourse);
        Address = findViewById(R.id.etStudentAddress);
        Contact = findViewById(R.id.etStudentContact);

        ID.setText("");
        Name.setText("");
        Course.setText("");
        Address.setText("");
        Contact.setText("");
    }
}