package com.example.healthyway.ui.dietician.clientsList;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.healthyway.R;

import java.util.ArrayList;

public class ClientsListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<RecyclerData> recyclerDataArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clients_list);
        recyclerView=findViewById(R.id.idCourseRV);

        // created new array list..
        recyclerDataArrayList=new ArrayList<>();

        // added data to array list
        recyclerDataArrayList.add(new RecyclerData("Gica","Sculeru",R.drawable.ic_baseline_person_pin_24));
        recyclerDataArrayList.add(new RecyclerData("Maria","Petarda",R.drawable.ic_baseline_person_pin_24));
        recyclerDataArrayList.add(new RecyclerData("Ion","Dumitrescu",R.drawable.ic_baseline_person_pin_24));
        recyclerDataArrayList.add(new RecyclerData("Costel","Biju",R.drawable.ic_baseline_person_pin_24));
        recyclerDataArrayList.add(new RecyclerData("Bula","Fieraru",R.drawable.ic_baseline_person_pin_24));

        // added data from arraylist to adapter class.
        RecyclerViewAdapter adapter=new RecyclerViewAdapter(recyclerDataArrayList,this);

        // setting grid layout manager to implement grid view.
        // in this method '2' represents number of columns to be displayed in grid view.
        GridLayoutManager layoutManager=new GridLayoutManager(this,2);

        // at last set adapter to recycler view.
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
}
