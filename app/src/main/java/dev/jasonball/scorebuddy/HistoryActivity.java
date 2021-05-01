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
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class HistoryActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
//        Button otherReset = findViewById(R.id.resetButton);
        Button hBack = findViewById(R.id.hBack);
//        Button hReset = findViewById(R.id.hReset);
//        otherReset.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                otherResetClick();
//            }
//        });
        ArrayList<GameObject> g = new ArrayList<GameObject>();
        try {
            g = NewGame.getGames();
        }
        catch (Exception e) {
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
        }
        LinearLayout l = (LinearLayout) findViewById(R.id.ll);
        ArrayList<Integer> fin = new ArrayList<Integer>();
        ArrayList<String> name = new ArrayList<String>();
        GameObject game = new GameObject();
        TextView t = new TextView(this);
        if(g.size()!=0) {
            for (int i = 0; i < g.size(); i++) {
                t = new TextView(this);
                game = g.get(i);
                fin = game.getScores();
                name = game.getPlayers();

                if (fin.size() == 1)    {
                    t.setText("Player: " + name.get(0) + "      -       Score: " + fin.get(0) +"\n\n");
                }
                else if(fin.size()==2) {
                    t.setText("Player: " + name.get(0) + "      -       Score: " + fin.get(0) + "\nPlayer: " + name.get(1) + "      -       Score:        " + fin.get(1) +"\n\n");
                }
                else if(fin.size()==3) {
                    t.setText("Player: " + name.get(0) + "      -       Score: " + fin.get(0) + "\nPlayer: " + name.get(1) + "      -       Score:        " + fin.get(1) + "\nPlayer: " + name.get(2) + "     -       Score: " + fin.get(2) +"\n\n");
                }
                else if(fin.size()==4) {
                    t.setText("Player: " + name.get(0) + "      -       Score: " + fin.get(0) + "\nPlayer: " + name.get(1) + "      -       Score:        " + fin.get(1) + "\nPlayer: " + name.get(2) + "     -       Score: " + fin.get(2) + "\nPlayer: " + name.get(3) + "      -       Score: " + fin.get(3)+"\n\n");
                }
                else
                    t.setText("No history yet!");
                l.addView(t);
            }
        }
        else {
            t.setText("No history yet!");
            l.addView(t);
        }
        hBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hBackClick();
            }
        });
    }
    public void hBackClick(){
        Intent i = new Intent(this, HomeActivity.class);
        startActivity(i);
    }

//    public void otherResetClick() {
////        LinearLayout l = (LinearLayout) findViewById(R.id.ll);
////        ArrayList<Integer> fin = NewGame.getFinalScore();
////        ArrayList<String> name = NewGame.getNames();
////        TextView t = new TextView(this);
//////        t.setLayoutParams();
////        if (fin.size() == 1)    {
////            t.setText("Player: " + name.get(0) + " - Score: " + fin.get(0));
////        }
////        else if(fin.size()==2) {
////            t.setText("Player: "+name.get(0)+" - Score: "+fin.get(0)+"\nPlayer: "+name.get(1)+" - Score: "+fin.get(1));
////        }
////        else if(fin.size()==3) {
////            t.setText("Player: " + name.get(0) + " - Score: " + fin.get(0) + "\nPlayer: " + name.get(1) + " - Score: " + fin.get(1) + "\nPlayer: " + name.get(2) + " - Score: " + fin.get(2));
////        }
////        else if(fin.size()==4) {
////            t.setText("Player: " + name.get(0) + " - Score: " + fin.get(0) + "\nPlayer: " + name.get(1) + " - Score: " + fin.get(1) + "\nPlayer: " + name.get(2) + " - Score: " + fin.get(2) + "\nPlayer: " + name.get(2) + " - Score: " + fin.get(2));
////        }
////        else
////            t.setText("No history yet!");
////        l.addView(t);
////        Intent i = new Intent(this, HomeActivity.class);
////        startActivity(i);
//    }
}
