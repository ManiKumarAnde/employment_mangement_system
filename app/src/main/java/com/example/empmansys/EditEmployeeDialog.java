package com.example.empmansys;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditEmployeeDialog extends Dialog {

    private EditText editTextName, editTextPosition, editTextEmail;
    private Button buttonSave, buttonCancel;

    private final Employee employee;
    private final OnEmployeeEditedListener listener;

    public interface OnEmployeeEditedListener {
        void onEmployeeEdited(Employee employee);
    }

    // Constructor with context, employee, and listener for callback
    public EditEmployeeDialog(Context context, Employee employee, OnEmployeeEditedListener listener) {
        super(context);
        this.employee = employee;
        this.listener = listener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_edit_employee);

        // Initialize views
        editTextName = findViewById(R.id.editTextName);
        editTextPosition = findViewById(R.id.editTextPosition);
        editTextEmail = findViewById(R.id.editTextEmail);
        buttonSave = findViewById(R.id.buttonSave);
        buttonCancel = findViewById(R.id.buttonCancel);

        // Check for null employee data
        if (employee == null) {
            Toast.makeText(getContext(), "Error: No employee data to edit", Toast.LENGTH_SHORT).show();
            dismiss();
            return;
        }

        // Populate fields with employee data
        editTextName.setText(employee.getName());
        editTextPosition.setText(employee.getPosition());
        editTextEmail.setText(employee.getEmail());

        // Save button click listener
        buttonSave.setOnClickListener(v -> {
            String name = editTextName.getText().toString().trim();
            String position = editTextPosition.getText().toString().trim();
            String email = editTextEmail.getText().toString().trim();

            // Validate inputs
            if (name.isEmpty() || position.isEmpty() || email.isEmpty()) {
                Toast.makeText(getContext(), "All fields must be filled", Toast.LENGTH_SHORT).show();
                return;
            }

            // Update employee data and trigger listener callback
            employee.setName(name);
            employee.setPosition(position);
            employee.setEmail(email);
            if (listener != null) {
                listener.onEmployeeEdited(employee);
            }
            dismiss(); // Close dialog after saving
        });

        // Cancel button click listener
        buttonCancel.setOnClickListener(v -> dismiss());
    }
}
