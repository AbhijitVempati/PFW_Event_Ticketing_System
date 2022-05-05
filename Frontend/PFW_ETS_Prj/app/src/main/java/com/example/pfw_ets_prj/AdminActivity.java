package com.example.pfw_ets_prj;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AdminActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
    }
    private EditText Nameval;
    private String temp;
    public void RegisterEvent(View v)
    {

        Nameval = findViewById(R.id.txtEventName);
        temp = Nameval.getText().toString();
        if(temp.isEmpty())
        {
            Toast.makeText(this, "Event name is Empty. Please fill and try again", Toast.LENGTH_SHORT).show();
            return;
        }

        Nameval = findViewById(R.id.txtEventDate);
        temp = Nameval.getText().toString();
        if(temp.isEmpty())
        {
            Toast.makeText(this, "Event Date is Empty. Please fill and try again", Toast.LENGTH_SHORT).show();
            return;
        }

        Nameval = findViewById(R.id.txtEventTime);
        temp = Nameval.getText().toString();
        if(temp.isEmpty())
        {
            Toast.makeText(this, "Event time is Empty. Please fill and try again", Toast.LENGTH_SHORT).show();
            return;
        }

        Nameval = findViewById(R.id.txtVenue);
        temp = Nameval.getText().toString();
        if(temp.isEmpty())
        {
            Toast.makeText(this, "Event venue is Empty. Please fill and try again", Toast.LENGTH_SHORT).show();
            return;
        }

        Toast.makeText(this, "Event has been added to the database", Toast.LENGTH_SHORT).show();
        ((TextView)findViewById(R.id.txtVenue)).setText("");
        ((TextView)findViewById(R.id.txtEventTime)).setText("");
        ((TextView)findViewById(R.id.txtEventDate)).setText("");
        ((TextView)findViewById(R.id.txtEventName)).setText("");

    }
}