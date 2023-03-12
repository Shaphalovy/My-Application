package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText editText;
    private ImageButton addButton;
    private ListView listView;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);
        addButton = findViewById(R.id.addButton);
        listView = findViewById(R.id.listView);

        items = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, items);
        listView.setAdapter(adapter);

        actionListeners();

    }

    private void actionListeners(){
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addItem();
            }
        });



        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Delete item");
                builder.setMessage("Are you sure you want to delete this item?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        items.remove(position);
                        adapter.notifyDataSetChanged();
                    }
                });
                builder.setNegativeButton("No", null);
                builder.show();
                return true;
            }
        });
    }

    private void addItem() {
        String item = editText.getText().toString().trim();
        if (!item.isEmpty()) {
            items.add(item);
            adapter.notifyDataSetChanged();
            editText.setText("");
        } else {
            Toast.makeText(this, "Write something in the text box", Toast.LENGTH_SHORT).show();
        }
    }
}