// EmployeeListActivity.java
package com.example.empmansys;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class EmployeeListActivity extends AppCompatActivity {

    private Button buttonAddEmployee;
    private Button buttonViewEmployee;
    private Button buttonEditEmployee;
    private Button buttonDeleteEmployee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_list);

        // Initialize buttons
        buttonAddEmployee = findViewById(R.id.buttonAddEmployee);
        buttonViewEmployee = findViewById(R.id.buttonViewEmployee);
        buttonEditEmployee = findViewById(R.id.buttonEditEmployee);
        buttonDeleteEmployee = findViewById(R.id.buttonDeleteEmployee);

        // Redirect to AddEmployeeActivity
        buttonAddEmployee.setOnClickListener(v -> startActivity(new Intent(EmployeeListActivity.this, AddEmployeeActivity.class)));

        // Redirect to ViewEmployeeListActivity
        buttonViewEmployee.setOnClickListener(v -> startActivity(new Intent(EmployeeListActivity.this, EmployeeDetailsActivity.class)));

        // Redirect to EditEmployeeDialog
        buttonEditEmployee.setOnClickListener(v -> {
            Intent intent = new Intent(EmployeeListActivity.this, EditEmployeeDialog.class);
            intent.putExtra("employeeId", "EMPLOYEE_ID_HERE");  // Pass the relevant employee ID here
            startActivity(intent);
        });

        // Redirect to DeleteEmployeeActivity

    }
}
