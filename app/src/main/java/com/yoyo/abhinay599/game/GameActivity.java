package com.yoyo.abhinay599.game;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class GameActivity extends AppCompatActivity {

    Button easy,medium,hard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        easy=findViewById(R.id.button5);
        medium=findViewById(R.id.button6);
        hard=findViewById(R.id.button7);
        easy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(GameActivity.this,MainActivity.class));
            }
        });
    }
}
