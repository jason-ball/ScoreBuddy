package dev.jasonball.scorebuddy;

import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.os.Build;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class HistoryActivity extends AppCompatActivity {
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
