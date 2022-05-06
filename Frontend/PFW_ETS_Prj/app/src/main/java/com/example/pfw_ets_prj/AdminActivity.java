package com.example.pfw_ets_prj;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.pfw_ets_prj.data.RequestQueueCall;

import org.json.JSONException;
import org.json.JSONObject;

import java.time.LocalDate;

public class AdminActivity extends AppCompatActivity {
    NotificationCompat.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "upp";
            String description = "may";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("1", name, importance);
            channel.setDescription(description);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
        builder = new NotificationCompat.Builder(this,"1");
    }
    private EditText Nameval;
    private Switch Regswitch;
    private String name;
    private String date;
    private String time;
    private String venue;
    private Boolean reg;
    public void RegisterEvent(View v)
    {

        Nameval = findViewById(R.id.txtEventName);
        name = Nameval.getText().toString();
        if(name.isEmpty())
        {
            Toast.makeText(this, "Event name is Empty. Please fill and try again", Toast.LENGTH_SHORT).show();
            return;
        }

        Nameval = findViewById(R.id.txtEventDate);
        date = Nameval.getText().toString();
        if(date.isEmpty())
        {
            Toast.makeText(this, "Event Date is Empty. Please fill and try again", Toast.LENGTH_SHORT).show();
            return;
        }

        Nameval = findViewById(R.id.txtEventTime);
        time = Nameval.getText().toString();
        if(time.isEmpty())
        {
            Toast.makeText(this, "Event time is Empty. Please fill and try again", Toast.LENGTH_SHORT).show();
            return;
        }

        Nameval = findViewById(R.id.txtVenue);
        venue = Nameval.getText().toString();
        if(venue.isEmpty())
        {
            Toast.makeText(this, "Event venue is Empty. Please fill and try again", Toast.LENGTH_SHORT).show();
            return;
        }

        Regswitch = findViewById(R.id.regswitch);
        reg = Regswitch.isChecked();

        RequestQueue queue = RequestQueueCall.getInstance(this.getApplicationContext()).
                getRequestQueue();
        try {
            JSONObject params = new JSONObject();
            try {
                params.put("event_name", name);
                params.put("event_date", date);
                params.put("dept", venue);
                params.put("event_reg", reg);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            String url = "http://10.0.2.2:8080/ZPETS/api/saveEvent";
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, params,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            System.out.println("Response: " + response);
                            update("New event has been Added!","Event: "+name+" will be occoring on "+date+" at "+venue);

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

        Toast.makeText(this, "Event has been added to the database", Toast.LENGTH_SHORT).show();
        ((TextView)findViewById(R.id.txtVenue)).setText("");
        ((TextView)findViewById(R.id.txtEventTime)).setText("");
        ((TextView)findViewById(R.id.txtEventDate)).setText("");
        ((TextView)findViewById(R.id.txtEventName)).setText("");
        ((Switch)findViewById(R.id.regswitch)).isChecked();


    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if(keyCode == KeyEvent.KEYCODE_BACK)
        {
            Intent i = new Intent(this, MainActivity.class);
            i.putExtra("flag","1");
            startActivity(i);
            return true;
        }
        return false;
    }
    public void update(String a, String b) {
        try{
            builder.setSmallIcon(android.R.color.transparent)
                    .setContentTitle(a)
                    .setContentText(b)
                    .setStyle(new NotificationCompat.BigTextStyle()
                            .bigText(b))
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT);
            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.notify(1,builder.build());}
        catch (Exception e){
            System.out.println(e);
        }

    }
}