package com.example.hello_world;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.hello_world.databinding.ActivityDetailsBinding;
import com.example.hello_world.databinding.ActivityMainBinding;

public class DetailsActivity extends AppCompatActivity {

    ActivityDetailsBinding binding;
    String index;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_details);

        binding = ActivityDetailsBinding.inflate(getLayoutInflater());
        View viewRoot = binding.getRoot();
        setContentView(viewRoot);

        Intent intent = getIntent();
        if(intent != null) {
            String data = intent.getStringExtra("number");
            binding.editText.setText(data);
//            index = intent.getStringExtra("index");
        }

        binding.buttonDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailsActivity.this, MainActivity.class);

//                intent.putExtra("index", index);
                intent.putExtra("new_number", binding.editText.getText());

                startActivity(intent);
                finish();
            }
        });
    }
}