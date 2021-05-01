package dev.jasonball.scorebuddy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class StatsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);

        Button hBack = findViewById(R.id.back_home);
//        Button hReset = findViewById(R.id.reset);
        hBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hBackClick();
            }
        });

        ArrayList<GameObject> g = new ArrayList<GameObject>();
        try {
            g = NewGame.getGames();
        }
        catch (Exception e) {
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
        }
        GridLayout l = (GridLayout) findViewById(R.id.gl);
        ArrayList<Integer> fin = new ArrayList<Integer>();
        ArrayList<String> name = new ArrayList<String>();
        GameObject game = new GameObject();
        TextView t = new TextView(this);
        int high = 0;
        int low = 99999999;
        if(g.size()!=0) {
            for (int i = 0; i < g.size(); i++) {
                TextView t1 = (TextView) findViewById(R.id.view1);
                TextView t2 = (TextView) findViewById(R.id.view2);
                game = g.get(i);
                fin = game.getScores();
                name = game.getPlayers();
                high = Collections.min(fin);
                low = Collections.max(fin);
                t1.setText("Best Score:\n"+low+" Points");
                t2.setText("Worst Score:\n"+high+" Points");
            }
        }
        else {
            t.setText("No history yet!");
            l.addView(t);
        }
    }

    public void hBackClick(){
        Intent i = new Intent(this, HomeActivity.class);
        startActivity(i);
    }
}