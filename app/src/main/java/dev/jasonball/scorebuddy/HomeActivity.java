package dev.jasonball.scorebuddy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class HomeActivity extends AppCompatActivity {

    private Button logout;
    private Button newGame;
    private Button friends;
    private Button profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        logout = findViewById(R.id.bLogout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logoutUser();
            }
        });

        newGame = findViewById(R.id.bNewGame);
        newGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchNewGame();
            }
        });

        friends = findViewById(R.id.bFriends);
        friends.setOnClickListener(view -> startFriendsActivity());

        profile = findViewById(R.id.bProfile);
        profile.setOnClickListener(view -> startProfileActivity());

    }

    public void logoutUser(){
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(HomeActivity.this, MainActivity.class);
        startActivity(intent);
    }

    public void launchNewGame(){
        Intent intent = new Intent(HomeActivity.this, NewGame.class);
        startActivity(intent);
    }

    private void startFriendsActivity() {
        Intent intent = new Intent(this, FriendPage.class);
        startActivity(intent);
    }

    private void startProfileActivity() {
        Intent intent = new Intent(this, ProfilePage.class);
        startActivity(intent);
    }
}