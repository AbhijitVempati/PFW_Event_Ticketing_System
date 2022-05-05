package com.example.pfw_ets_prj;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.pfw_ets_prj.data.RequestQueueCall;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AccountActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        Intent intent = getIntent();
        TextView Welc = findViewById(R.id.txtWelcome);
        Welc.setText(Welc.getText().toString().replace("User",intent.getStringExtra("userName")));

        //sa
        try {
            String url = "http://10.0.2.2:8080/ZPETS/api/getRegistration";

            StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            // Display the first 500 characters of the response string.
                            System.out.println("Response: " + response);
                            JSONArray json = null;
                            try {
                                json = new JSONArray (response);
                                ArrayList<Event> dailyEvents2 = new ArrayList<>();
                                Event event;
                                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("N");
                                for(int i=0; i < json.length(); i++) {
                                    JSONObject jsonobject = json.getJSONObject(i);
                                    String title    = jsonobject.getString("event_name");
                                    String date  = jsonobject.getString("event_date");
                                    //String venue = jsonobject.getString("dept");
                                    //String reg = jsonobject.getString("event_reg");
                                    String stfname = jsonobject.getString("student_last");
                                    if(stfname.equals(intent.getStringExtra("userName")) ){
                                    //LocalDate d = LocalDate.parse(date,formatter);
                                    event= new Event(title, LocalDate.now(), "2:00 P.M.", "Classic Ballroom","1");
                                    dailyEvents2.add(event);
                                    }
                                    //event= new Event("NCAA Workshops", LocalDate.now(), "2:00 P.M.", "Classic Ballroom","1");
                                    ListView bookedevents = findViewById(R.id.lViewBooked);
                                    EventAdapter eventAdapter2 = new EventAdapter(getApplicationContext(), dailyEvents2);
                                    bookedevents.setAdapter(eventAdapter2);

                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            System.out.println(json.toString());
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    System.out.println("Response: " + error.toString());
                }
            });
            stringRequest.setRetryPolicy(new DefaultRetryPolicy(500000,
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            RequestQueueCall.getInstance(this).addToRequestQueue(stringRequest);

        } catch (Exception e) {
            e.printStackTrace();
        }
        //sa


        //Event events1 = new Event("NCAA Workshops", LocalDate.now(), "2:00 P.M.", "Classic Ballroom","1");
        //Event events2 = new Event("Honors Banquet", LocalDate.now(), "7:00 P.M.", "International Ballroom","1");


        //ListView bookedevents = findViewById(R.id.lViewBooked);
        //ArrayList<Event> dailyEvents2 = new ArrayList<>(Arrays.asList(events1, events2));
        //EventAdapter eventAdapter2 = new EventAdapter(getApplicationContext(), dailyEvents2);
        //bookedevents.setAdapter(eventAdapter2);


    }
}