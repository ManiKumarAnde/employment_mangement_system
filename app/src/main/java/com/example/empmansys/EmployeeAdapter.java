// EmployeeAdapter.java
package com.example.empmansys;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

public class EmployeeAdapter extends ArrayAdapter<Employee> {

    public EmployeeAdapter(Context context, List<Employee> employees) {
        super(context, 0, employees);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_employee, parent, false);
        }

        Employee employee = getItem(position);
        if (employee != null) {
            TextView textViewName = convertView.findViewById(R.id.textViewName);
            TextView textViewPosition = convertView.findViewById(R.id.textViewPosition);
            TextView textViewEmail = convertView.findViewById(R.id.textViewEmail);

            if (textViewName != null) {
                textViewName.setText(employee.getName());
            }
            if (textViewPosition != null) {
                textViewPosition.setText(employee.getPosition());
            }
            if (textViewEmail != null) {
                textViewEmail.setText(employee.getEmail());
            }
        }
        return convertView;
    }
}
