package dev.jasonball.scorebuddy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class HomeActivity extends AppCompatActivity {

    private Button newGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        newGame = findViewById(R.id.bNewGame);
        newGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchNewGame();
            }
        });

    }
    

    public void launchNewGame(){
        Intent intent = new Intent(HomeActivity.this, NewGame.class);
        startActivity(intent);
    }
}