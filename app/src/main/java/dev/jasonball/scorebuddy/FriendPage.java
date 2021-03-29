package dev.jasonball.scorebuddy;

import android.content.Intent;
import android.os.Bundle;

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
import android.widget.Button;
import android.widget.ListView;

public class FriendPage extends AppCompatActivity
{
    private ListView listview;
    private String[] list;
    private Button goHome;
    int i;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_page);

        listview=(ListView)findViewById(R.id.lv);
        list = getResources().getStringArray(R.array.list);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,list);
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
            {
                Intent intent = new Intent(FriendPage.this, ProfilePage.class);
                intent.putExtra("Friends",listview.getItemAtPosition(i).toString());
                startActivity(intent);
            }
        });

        goHome = findViewById(R.id.goHomeFriend);
        goHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goHomeClick();
            }
        });

    }
    public void goHomeClick(){
        Intent i = new Intent(this, HomeActivity.class);
        startActivity(i);
    }
}