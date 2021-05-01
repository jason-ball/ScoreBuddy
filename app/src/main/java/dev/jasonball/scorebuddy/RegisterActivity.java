package dev.jasonball.scorebuddy;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    private static final String TAG = "RegisterActivity";

    private FirebaseAuth mAuth;

    private Button signUp;

    private EditText userEmail;
    private EditText userPassword;
    private EditText fullName;

    private String _email;
    private String _password;
    private String _userDisplayName;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            homePage();
        }

        userEmail = findViewById(R.id.etNewEmail);
        userPassword = findViewById(R.id.etNewPassword);
        fullName = findViewById(R.id.etUserDisplayName);

        signUp = findViewById(R.id.bSignUp);
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _email = userEmail.getText().toString().trim();
                _password = userPassword.getText().toString().trim();
                _userDisplayName = fullName.getText().toString().trim();

                registerUser(_userDisplayName, _email, _password);
            }
        });


    }

    public void homePage(){
        Intent intent = new Intent(RegisterActivity.this, HomeActivity.class);
        startActivity(intent);
    }

    public void registerUser(String name, String email, String password){
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                                    .setDisplayName(name)
                                    .build();
                            user.updateProfile(profileUpdates)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                Log.d(TAG, "User profile updated.");
                                            }
                                        }
                                    });
                            openHomePage();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(RegisterActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            reload();
                        }

                        // ...
                    }
                });
    }

    public void openHomePage(){
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }

    public void reload(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}