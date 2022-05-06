package com.example.pfw_ets_prj;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.example.pfw_ets_prj.data.RequestQueueCall;
import com.example.pfw_ets_prj.ui.login.LoginActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

public class EventDetailsActivity extends AppCompatActivity {

    TextView t;
    String id;
    String date;
    String time;
    String venue;
    String tt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_details);
        id = getIntent().getStringExtra("ID");
        t = findViewById(R.id.textView8);
        t.setText(id);

        date = getIntent().getStringExtra("eventDate");
        t = findViewById(R.id.txtEvDate);
        t.setText(date);

        time = getIntent().getStringExtra("eventTime");
        t = findViewById(R.id.txtEvTime);
        t.setText(time);

        venue = getIntent().getStringExtra("eventVenue");
        t = findViewById(R.id.txtEvVenue);
        t.setText(venue);
        tt = getIntent().getStringExtra("eventVenue");
        if(getIntent().getStringExtra("needtoregister").equals("false")){
        Button b;
        b = findViewById(R.id.buttonReg);
        b.setVisibility(View.INVISIBLE);
        }
        else{
        Button b;
        b = findViewById(R.id.buttonReg);
        b.setVisibility(View.VISIBLE);
        }
}

    public void GoToEventsList(View v)
    {
        Intent i = new Intent(this, LoginActivity.class);
        i.putExtra("flag", "0");
        startActivity(i);
        try {
            JSONObject params = new JSONObject();
            try {
                params.put("student_id", 10);
                params.put("event_id", 10);
                params.put("event_name", id);
                params.put("event_date", LocalDate.parse(date));
                params.put("event_time", "00:00:00");
                params.put("student_first", venue);
                params.put("student_last", "SasankThumati");
                params.put("student_email", "thums01@pfw.edu");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            String url = "http://10.0.2.2:8080/ZPETS/api/saveRegistration";
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, params,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            System.out.println("Response: " + response);

                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    System.out.println("Response: " + error.toString());
                }
            });

            jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(500000,
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            RequestQueueCall.getInstance(this).addToRequestQueue(jsonObjectRequest);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}