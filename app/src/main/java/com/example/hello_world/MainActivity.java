package com.example.hello_world;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.hello_world.databinding.ActivityMainBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private FloatingActionButton btnPlus;
    private FloatingActionButton btnMinus;

    private ArrayAdapter<String> arrayAdapter;
    private ArrayList<String> arrayList;

    ActivityMainBinding binding;
    private MyViewModel model;
    String new_number;
    int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View viewRoot = binding.getRoot();
        setContentView(viewRoot);

        model = new ViewModelProvider(this).get(MyViewModel.class);
        model.getNumber().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                binding.textView.setText("" + ++integer);
                arrayList.add("" + integer);
                arrayAdapter.notifyDataSetChanged();
            }
        });



//        textView = findViewById(R.id.text_view);
//        btnPlus = findViewById(R.id.btn_add);
//        btnMinus = findViewById(R.id.btn_minus);

//        btnPlus.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                int count = Integer.parseInt(textView.getText().toString());
//                textView.setText("" + ++count);
//            }
//        });
        binding.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                int count = Integer.parseInt(textView.getText().toString());
//                binding.textView.setText("" + ++count);
                model.increaseNumber();
            }

        });

        binding.lvCount.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                arrayList.remove(position);
                arrayAdapter.notifyDataSetChanged();
                return false;
            }
        });
        binding.lvCount.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
                intent.putExtra("number", arrayList.get(position));
                index = position;
                intent.putExtra("index", position);
                startActivity(intent);
//                finish();
            }

        });

        arrayList = new ArrayList<String>();
        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arrayList);
        binding.lvCount.setAdapter(arrayAdapter);

//        btnMinus.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                int count = Integer.parseInt(textView.getText().toString());
//                textView.setText("" + --count);
//            }
//        });



    }

//    Intent intent = getIntent();
//    if(intent != null) {
//        new_number = intent.getStringExtra("new_number");
////      int index = Integer.parseInt(intent.getStringExtra("index"));
//        if(!arrayList.isEmpty()){
//            arrayList.set(index, new_number);
//            arrayAdapter.notifyDataSetChanged();
//        }
//
//    }
}