package dev.jasonball.scorebuddy;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TextView;

public class NewGame extends AppCompatActivity {
    private TextView holeHeaderLabel;

    private TextView playerAScoreLabel;
    private TextView playerANameLabel;
    private TextView playerBScoreLabel;
    private TextView playerBNameLabel;
    private TextView playerCScoreLabel;
    private TextView playerCNameLabel;
    private TextView playerDScoreLabel;
    private TextView playerDNameLabel;

    private TableLayout scoreTable;
    private TextView scoreTablePlayerAName;
    private TextView scoreTablePlayerBName;
    private TextView scoreTablePlayerCName;
    private TextView scoreTablePlayerDName;

    private Button addScoreButton;

    private int holeNumber;
    private String playerAName;
    private String playerBName;
    private String playerCName;
    private String playerDName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_game);

        holeHeaderLabel = findViewById(R.id.holeHeaderLabel);

        playerAScoreLabel = findViewById(R.id.playerAScoreLabel);
        playerANameLabel = findViewById(R.id.playerANameLabel);
        playerBScoreLabel = findViewById(R.id.playerBScoreLabel);
        playerBNameLabel = findViewById(R.id.playerBNameLabel);
        playerCScoreLabel = findViewById(R.id.playerCScoreLabel);
        playerCNameLabel = findViewById(R.id.playerCNameLabel);
        playerDScoreLabel = findViewById(R.id.playerDScoreLabel);
        playerDNameLabel = findViewById(R.id.playerDNameLabel);

        scoreTable = findViewById(R.id.scoreTable);
        scoreTablePlayerAName = findViewById(R.id.scoreTablePlayerAName);
        scoreTablePlayerBName = findViewById(R.id.scoreTablePlayerBName);
        scoreTablePlayerCName = findViewById(R.id.scoreTablePlayerCName);
        scoreTablePlayerDName = findViewById(R.id.scoreTablePlayerDName);

        addScoreButton = findViewById(R.id.addScoreButton);
        addScoreButton.setOnClickListener(this::addScoreButtonPressed);

        playerAName = getString(R.string.player_a_default_name);
        playerBName = getString(R.string.player_b_default_name);
        playerCName = getString(R.string.player_c_default_name);
        playerDName = getString(R.string.player_d_default_name);

        setHoleNumber(1);
    }

    private void updateHeader(int hole, @SuppressWarnings("SameParameterValue") int max) {
        String s = getString(R.string.hole_header, hole, max);
        holeHeaderLabel.setText(s);
    }

    private void updateHeader(int hole) {
        updateHeader(hole, 18);
    }

    private void updateScoreButton(int hole) {
        String s = getString(R.string.add_score_button, hole);
        addScoreButton.setText(s);
    }

    private void setHoleNumber(int hole) {
        holeNumber = hole;
        updateHeader(hole);
        updateScoreButton(hole);
    }

    public void addScoreButtonPressed(View view) {
        setHoleNumber(holeNumber + 1);
    }

    public void updatePlayerNamePressed(View view) {
        if (view.getId() == R.id.playerANameGroup) {
            openUpdateNameDialog(this::setPlayerAName, playerAName);
        } else if (view.getId() == R.id.playerBNameGroup) {
            openUpdateNameDialog(this::setPlayerBName, playerBName);
        } else if (view.getId() == R.id.playerCNameGroup) {
            openUpdateNameDialog(this::setPlayerCName, playerCName);
        } else if (view.getId() == R.id.playerDNameGroup){
            openUpdateNameDialog(this::setPlayerDName, playerDName);
        }
    }

    public void openUpdateNameDialog(NameUpdater nameUpdater, String oldName) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getString(R.string.update_name_dialog_title, oldName));

        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        input.setText(oldName);
        input.setSelectAllOnFocus(true);
        input.requestFocus();
        builder.setView(input);

        builder.setPositiveButton("Update", (dialog, which) -> nameUpdater.updateName(input.getText().toString()));
        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.cancel());

        builder.show();
    }

    public void setPlayerAName(String playerAName) {
        this.playerAName = playerAName;
        playerANameLabel.setText(playerAName);
        scoreTablePlayerAName.setText(playerAName);
    }

    public void setPlayerBName(String playerBName) {
        this.playerBName = playerBName;
        playerBNameLabel.setText(playerBName);
        scoreTablePlayerBName.setText(playerBName);
    }

    public void setPlayerCName(String playerCName) {
        this.playerCName = playerCName;
        playerCNameLabel.setText(playerCName);
        scoreTablePlayerCName.setText(playerCName);
    }

    public void setPlayerDName(String playerDName) {
        this.playerDName = playerDName;
        playerDNameLabel.setText(playerDName);
        scoreTablePlayerDName.setText(playerDName);
    }
}

interface NameUpdater {
    void updateName(String name);
}