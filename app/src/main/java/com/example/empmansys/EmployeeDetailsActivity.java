// EmployeeDetailsActivity.java
package com.example.empmansys;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class EmployeeDetailsActivity extends AppCompatActivity {

    private ListView listViewEmployees;
    private ArrayList<Employee> employeeList;
    private EmployeeAdapter adapter;
    private DatabaseReference employeeRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_details);

        // Initialize ListView and Adapter
        listViewEmployees = findViewById(R.id.listViewEmployees);
        employeeList = new ArrayList<>();
        adapter = new EmployeeAdapter(this, employeeList);
        listViewEmployees.setAdapter(adapter);

        // Firebase reference
        employeeRef = FirebaseDatabase.getInstance().getReference("employees");

        // Fetch data from Firebase
        employeeRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                employeeList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Employee employee = snapshot.getValue(Employee.class);
                    if (employee != null) {
                        employeeList.add(employee);
                    }
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(EmployeeDetailsActivity.this, "Failed to load data", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
