package com.example.madt1026;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddNoteActivity extends AppCompatActivity {

    EditText edNoteTitle; // Declare as an instance variable
    EditText edNoteContent; // Declare as an instance variable
    Button btnSaveAndClose; // Declare the Save and Close button

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        // Initialize both EditText fields
        edNoteTitle = findViewById(R.id.edNoteTitle);
        edNoteContent = findViewById(R.id.edNoteContent);

        // Initialize the Save and Close button
        btnSaveAndClose = findViewById(R.id.btnSaveNote);
        btnSaveAndClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSaveAndCloseClick();
            }
        });
    }

    private void onSaveAndCloseClick() {
        // Get the title and content from the EditText fields
        String noteTitle = edNoteTitle.getText().toString();
        String noteContent = edNoteContent.getText().toString();


        finish();
    }
}
