package com.example.empmansys;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddEmployeeActivity extends AppCompatActivity {

    private EditText editTextName, editTextId, editTextEmail, editTextPosition;
    private Button buttonSaveEmployee;
    private DatabaseReference employeeRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_employee);

        // Initialize Firebase reference
        employeeRef = FirebaseDatabase.getInstance().getReference("employees");

        // Initialize views
        editTextName = findViewById(R.id.editTextEmployeeName);
        editTextId = findViewById(R.id.editTextEmployeeId);
        editTextEmail = findViewById(R.id.editTextEmployeeEmail);
        editTextPosition = findViewById(R.id.editTextEmployeePosition);
        buttonSaveEmployee = findViewById(R.id.buttonSaveEmployee);

        // Set button click listener
        buttonSaveEmployee.setOnClickListener(v -> saveEmployeeToFirebase());
    }

    private void saveEmployeeToFirebase() {
        String name = editTextName.getText().toString().trim();
        String id = editTextId.getText().toString().trim();
        String email = editTextEmail.getText().toString().trim();
        String position = editTextPosition.getText().toString().trim();

        // Validate required fields
        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(id) || TextUtils.isEmpty(email) || TextUtils.isEmpty(position)) {
            Toast.makeText(this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
            return;
        }

        // Create Employee object
        Employee employee = new Employee(id, name, email, position);

        // Save employee data to Firebase Realtime Database
        employeeRef.child(id).setValue(employee).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                Toast.makeText(AddEmployeeActivity.this, "Employee saved successfully!", Toast.LENGTH_SHORT).show();
                finish();  // Close activity or navigate back
            } else {
                Toast.makeText(AddEmployeeActivity.this, "Failed to save employee.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
