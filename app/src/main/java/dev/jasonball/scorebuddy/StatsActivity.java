package dev.jasonball.scorebuddy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StatsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);

        Button hBack = findViewById(R.id.back_home);
        Button hReset = findViewById(R.id.reset);
        hBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hBackClick();
            }
        });
    }
    public void hBackClick(){
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }
}