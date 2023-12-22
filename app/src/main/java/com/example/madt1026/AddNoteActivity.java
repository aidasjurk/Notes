package com.example.madt1026;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddNoteActivity extends AppCompatActivity {

    EditText edNoteTitle;
    EditText edNoteContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        edNoteTitle = findViewById(R.id.edNoteTitle);
        edNoteContent = findViewById(R.id.edNoteContent);
    }

    // Method to handle the save and close button click
    public void onBtnSaveAndCloseClick(View view) {
        String noteTitle = edNoteTitle.getText().toString();
        String noteContent = edNoteContent.getText().toString();

        SharedPreferences sharedPreferences = getSharedPreferences(Constants.NOTES_FILE, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(Constants.NOTE_KEY + noteTitle, noteContent);
        editor.apply();

        finish();
    }
}
