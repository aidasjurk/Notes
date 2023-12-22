package com.example.madt1026;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Map;

public class DeleteNoteActivity extends AppCompatActivity {

    private Spinner spinnerNotes;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> noteTitles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_note);

        spinnerNotes = findViewById(R.id.spinnerNotes);
        loadNotesIntoSpinner();
    }

    private void loadNotesIntoSpinner() {
        SharedPreferences sharedPreferences = getSharedPreferences(Constants.NOTES_FILE, MODE_PRIVATE);
        Map<String, ?> notesMap = sharedPreferences.getAll();
        noteTitles = new ArrayList<>(notesMap.keySet());
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, noteTitles);
        spinnerNotes.setAdapter(adapter);
    }

    public void onDeleteNoteClick(View view) {
        String selectedNote = spinnerNotes.getSelectedItem().toString();
        SharedPreferences sharedPreferences = getSharedPreferences(Constants.NOTES_FILE, MODE_PRIVATE);
        sharedPreferences.edit().remove(selectedNote).apply();

        Toast.makeText(this, "Note deleted", Toast.LENGTH_SHORT).show();
        loadNotesIntoSpinner(); // Refresh the spinner
    }
}
