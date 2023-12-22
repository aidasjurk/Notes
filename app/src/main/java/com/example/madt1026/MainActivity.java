package com.example.madt1026;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> listNoteItems = new ArrayList<>();
    ArrayAdapter<String> adapter;
    ListView lvNotes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvNotes = findViewById(R.id.lvNotes);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listNoteItems);
        lvNotes.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadNotes();
    }

    private void loadNotes() {
        SharedPreferences sharedPreferences = getSharedPreferences(Constants.NOTES_FILE, MODE_PRIVATE);
        Map<String, ?> notesMap = sharedPreferences.getAll();
        listNoteItems.clear();
        for (Map.Entry<String, ?> entry : notesMap.entrySet()) {
            // Check if the key contains the prefix "LastNote"
            String key = entry.getKey();
            if (key.startsWith("LastNote")) {
                // Remove the prefix "LastNote" from the key
                key = key.substring("LastNote".length());
            }

            // If there's a colon or other separator after "LastNote", remove it as well
            key = key.replaceFirst("^:\\s+", ""); // regex to replace colon and any whitespace following it

            // Concatenate the modified key (title) and the note content
            String noteTitleAndContent = key + ": " + entry.getValue().toString();
            listNoteItems.add(noteTitleAndContent);
        }
        adapter.notifyDataSetChanged();
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.notes_options_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_note:
                startActivity(new Intent(this, AddNoteActivity.class));
                return true;
            case R.id.remove_note:
                startActivity(new Intent(this, DeleteNoteActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
