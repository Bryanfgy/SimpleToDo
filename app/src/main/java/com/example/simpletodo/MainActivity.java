package com.example.simpletodo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button btnAdd, btnClear, btnDelete;
    EditText etInput;
    ArrayList<String> alMessage;
    ArrayAdapter<String> aaMessage;
    ListView lvMessage;
    Spinner selectM;

    String todo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnAdd = findViewById(R.id.buttonAdd);
        btnClear = findViewById(R.id.buttonClear);
        btnDelete = findViewById(R.id.buttonDelete);
        etInput = findViewById(R.id.inputMessage);
        lvMessage = findViewById(R.id.ListviewMessage);
        selectM = findViewById(R.id.selectMode);

        alMessage = new ArrayList<String>();
        aaMessage = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, alMessage);
        lvMessage.setAdapter(aaMessage);

        selectM.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        btnDelete.setEnabled(false);
                        btnAdd.setEnabled(true);
                        etInput.setText("");
                        break;
                    case 1:
                        btnAdd.setEnabled(false);
                        btnDelete.setEnabled(true);
                        etInput.setText("");
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                todo = etInput.getText().toString();
                alMessage.add(todo);
                aaMessage.notifyDataSetChanged();
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alMessage.clear();
                aaMessage.notifyDataSetChanged();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(etInput.getText().toString().isEmpty()) {
                    Toast.makeText(MainActivity.this, "You don't have any tasks to remove", Toast.LENGTH_LONG).show();
                }else {
                    int pos = Integer.parseInt(etInput.getText().toString());
                    if (pos - 1 >= alMessage.size()) {
                        Toast.makeText(MainActivity.this, "Wrong Index Number", Toast.LENGTH_LONG).show();
                    } else {
                        alMessage.remove(pos - 1);
                        aaMessage.notifyDataSetChanged();
                    }
                }
            }
        });
    }
}