package dev.jasonball.scorebuddy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
    private Button updateVal;

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

        updateVal = findViewById(R.id.updateB);
        updateVal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                update(v);
            }
        });

        // Enables Always-on
        setAmbientEnabled();
        showAllUserData();
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

        String user_username = intent.getStringExtra("username");
        String user_name = intent.getStringExtra("name");
        String user_email = intent.getStringExtra("email");
        fullNameLabel.setText(user_name);
        usernameLabel.setText(user_username);
        email.getEditText().setText(user_email);
    }
    private void update(View view){
        if (isNameChanged() || isEmailChanged() || isUsernameChanged())
        {
            Toast.makeText(this, "Data has been updated", Toast.LENGTH_LONG).show();
        }
        else Toast.makeText(this, "Data is the same and can't updated", Toast.LENGTH_LONG).show();
    }

    private boolean isNameChanged(){
        if((fullName.getEditText().getText().toString()) == null || _NAME == null){
            return false;
        }
        if(!_NAME.equals(fullName.getEditText().getText().toString())){
            reference.child(_NAME).child("name").setValue(fullName.getEditText().getText().toString());
            _NAME = fullName.getEditText().getText().toString();
            return true;
        }
        else{
            return false;
        }
    }

    private boolean isUsernameChanged(){
        if((usernameLabel.getText().toString()) == null || _USERNAME == null){
            return false;
        }
        if(!_USERNAME.equals(usernameLabel.getText().toString())){
            reference.child(_USERNAME).child("username").setValue(usernameLabel.getText().toString());
            _USERNAME = usernameLabel.getText().toString();
            return true;
        }
        else{
            return false;
        }
    }

    private boolean isEmailChanged(){
        if((email.getEditText().getText().toString()) == null || _EMAIL == null){
            return false;
        }
        if(!_EMAIL.equals(email.getEditText().getText().toString())){
            reference.child(_USERNAME).child("email").setValue(email.getEditText().getText().toString());
            _EMAIL = email.getEditText().getText().toString();
            return true;
        }
        else{
            return false;
        }
    }

}