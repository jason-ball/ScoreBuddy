package dev.jasonball.scorebuddy;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

import org.w3c.dom.Text;

public class ProfilePage extends AppCompatActivity {

    private FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();


    private TextView mTextView;
    private TextView tVUsersname_field;
    private TextView tvEmail_view;
    private EditText eTFullName;
    private EditText eTUsername;
    private EditText eTEmail;
    private Button bUpdate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page);
        tvEmail_view = (TextView) findViewById(R.id.email_field);
        tvEmail_view.setText(user.getEmail());
        mTextView = (TextView) findViewById(R.id.text);
        tVUsersname_field = (TextView) findViewById(R.id.fullname_field);
        tVUsersname_field.setText(user.getDisplayName());
        eTFullName = (EditText) findViewById(R.id.full_name_profile);
        eTUsername = (EditText) findViewById(R.id.username_profile);
        eTEmail = (EditText) findViewById(R.id.email_profile);
        bUpdate = (Button) findViewById(R.id.update_profile);
        bUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(user!=null) {
                    String newName = eTFullName.getText().toString().trim();
                    String newUN = eTUsername.getText().toString().trim();
                    String email = eTEmail.getText().toString().trim();
                    if (newName == null || newUN == null|| email == null) {
                        Toast.makeText(ProfilePage.this, "Update failed, check info and try again.",
                                Toast.LENGTH_SHORT).show();
                    } else {
                        updateUser(newName, newUN, email);
                    }
                }
            }
        });


        // Enables Always-on
        setAmbientEnabled();
    }

    private void setAmbientEnabled() {
    }

    private void updateUser(String name, String newUserName, String newEmail)
    {
        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                .setDisplayName(name)
                .build();
        user.updateProfile(profileUpdates)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Log.d("", "User profile updated.");
                        }
                    }
                });
        user.updateEmail(newEmail)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Log.d("","Email Update Successful");
                        }
                        else{
                            Log.d("","Email Update wasn't Successful");
                        }
                    }
                });
        reload();
        tVUsersname_field.setText(user.getDisplayName());
        tvEmail_view.setText(user.getEmail());


    }

    public void reload(){
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }
}