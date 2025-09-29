package com.example.lab2_layout;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity4 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        setupBottomNavigation();
    }

    private void setupBottomNavigation() {
        Button btn1 = findViewById(R.id.btn_activity1);
        Button btn2 = findViewById(R.id.btn_activity2);
        Button btn3 = findViewById(R.id.btn_activity3);
        Button btn4 = findViewById(R.id.btn_activity4);

        // 当前Activity的按钮设置为不可点击状态
        btn4.setEnabled(false);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity4.this, MainActivity1.class);
                startActivity(intent);
                finish();
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity4.this, MainActivity2.class);
                startActivity(intent);
                finish();
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity4.this, MainActivity3.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
