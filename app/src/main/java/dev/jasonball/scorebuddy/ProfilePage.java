package dev.jasonball.scorebuddy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ProfilePage extends AppCompatActivity {

    private TextView mTextView;
    TextInputLayout fullName, email;
    TextView fullNameLabel, usernameLabel;
    String _USERNAME, _NAME, _EMAIL;

    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page);

        reference = FirebaseDatabase.getInstance().getReference("users");

        mTextView = (TextView) findViewById(R.id.text);
        //Hooks
        fullName = findViewById(R.id.full_name_profile);
        email = findViewById(R.id.email_profile);
        usernameLabel = findViewById(R.id.username_field);
        fullNameLabel = findViewById(R.id.fullname_field);
        showAllUserData();

        // Enables Always-on
        setAmbientEnabled();
    }

    private void setAmbientEnabled() {
    }
    private void showAllUserData(){
        Intent intent = getIntent();
        _USERNAME = intent.getStringExtra( "username");
        _NAME = intent.getStringExtra("name");
        _EMAIL = intent.getStringExtra("email");

        fullNameLabel.setText(_NAME);
        usernameLabel.setText(_USERNAME);
        fullName.getEditText().setText(_NAME);
        email.getEditText().setText(_EMAIL);
    }
    private void update(View view){
        if (isNameChanged())
        {
            Toast.makeText(this, "Data has been updated", Toast.LENGTH_LONG).show();
        }
        else Toast.makeText(this, "Data is the same and can't updated", Toast.LENGTH_LONG).show();
    }

    private boolean isNameChanged(){
        if(! _NAME.equals(fullName.getEditText().getText().toString())){
            reference.child(_USERNAME).child("name").setValue(fullName.getEditText().getText().toString());
            return true;
        }
        else{
            return false;
        }
    }
}