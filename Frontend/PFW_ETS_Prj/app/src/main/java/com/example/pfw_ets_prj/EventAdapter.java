package com.example.pfw_ets_prj;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.List;

public class EventAdapter extends ArrayAdapter<Event>
{

    public Context cont;
    public EventAdapter(@NonNull Context context, List<Event> events)
    {
        super(context, 0, events);
        cont = context;
    }

    //ConstraintLayout conlayout;

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
        Event event = getItem(position);
        //cont = convertView.getContext();
        if (convertView == null)
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.event_cell, parent, false);

        TextView eventCellTV = convertView.findViewById(R.id.eventCellTV);

        String eventTitle = event.getName();
        eventCellTV.setText(eventTitle);
        eventCellTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(cont, EventDetailsActivity.class);
                i.putExtra("ID", event.getName());
                i.putExtra("eventDate",event.getDateString());
                i.putExtra("eventTime",event.getTime());
                i.putExtra("eventVenue",event.getVenue());
                i.putExtra("needtoregister",event.getRegFlag());
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                cont.startActivity(i);

            }
        });


        return convertView;
    }





}
