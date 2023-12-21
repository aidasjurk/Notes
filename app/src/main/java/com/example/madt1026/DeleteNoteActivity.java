package com.example.madt1026;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Set;
import android.util.Log;
import java.util.HashSet;


public class DeleteNoteActivity extends AppCompatActivity {

    private Spinner spinnerNotes;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_note);

        spinnerNotes = findViewById(R.id.spinnerNotes);
        loadNotesIntoSpinner();

        Log.d("DeleteNoteActivity", "onCreate invoked");
    }

    private void loadNotesIntoSpinner() {
        SharedPreferences sharedPref = getSharedPreferences(Constants.NOTES_FILE, MODE_PRIVATE);
        Set<String> notesSet = sharedPref.getStringSet(Constants.NOTES_ARRAY_KEY, null);

        if (notesSet != null) {
            ArrayList<String> notesList = new ArrayList<>(notesSet);
            adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, notesList);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerNotes.setAdapter(adapter);
        }
    }

    public void onDeleteNoteClick(View view) {
        String selectedNote = spinnerNotes.getSelectedItem().toString();
        SharedPreferences sharedPref = getSharedPreferences(Constants.NOTES_FILE, MODE_PRIVATE);
        Set<String> notesSet = new HashSet<>(sharedPref.getStringSet(Constants.NOTES_ARRAY_KEY, new HashSet<>()));

        if (notesSet.remove(selectedNote)) {
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putStringSet(Constants.NOTES_ARRAY_KEY, notesSet); // Re-put the set after editing
            editor.apply();

            // Update the adapter and spinner
            ArrayAdapter<String> adapter = (ArrayAdapter<String>) spinnerNotes.getAdapter();
            adapter.remove(selectedNote);
            adapter.notifyDataSetChanged();

            Toast.makeText(this, "Note deleted", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Error: Note not found", Toast.LENGTH_SHORT).show();
        }

        Log.d("DeleteNoteActivity", "Note Deleted: " + selectedNote);
    }
}
