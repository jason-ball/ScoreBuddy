package dev.jasonball.scorebuddy;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class NewGame extends AppCompatActivity {
    private static final int MAX_HOLES = 18;

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

    private int playerAScore;
    private int playerBScore;
    private int playerCScore;
    private int playerDScore;

    List<TableRow> scoreRows = new ArrayList<>();

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
        updateHeader(hole, MAX_HOLES);
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
        openAddScoreDialog();
    }

    public void resetButtonPressed(View view) {
        reset();
    }

    private void reset() {
        /*setPlayerAName(getString(R.string.player_a_default_name));
        setPlayerBName(getString(R.string.player_b_default_name));
        setPlayerCName(getString(R.string.player_c_default_name));
        setPlayerDName(getString(R.string.player_d_default_name));*/

        setPlayerAScore(0);
        setPlayerBScore(0);
        setPlayerCScore(0);
        setPlayerDScore(0);

        setHoleNumber(1);
        addScoreButton.setEnabled(true);

        for (TableRow row : scoreRows) {
            scoreTable.removeView(row);
        }
    }

    private void openAddScoreDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getString(R.string.add_score_dialog_title, holeNumber));

        final EditText playerA = new EditText(this);
        playerA.setInputType(InputType.TYPE_CLASS_NUMBER);
        playerA.setHint(getString(R.string.add_score_dialog_player_name, playerAName));

        final EditText playerB = new EditText(this);
        playerB.setInputType(InputType.TYPE_CLASS_NUMBER);
        playerB.setHint(getString(R.string.add_score_dialog_player_name, playerBName));

        final EditText playerC = new EditText(this);
        playerC.setInputType(InputType.TYPE_CLASS_NUMBER);
        playerC.setHint(getString(R.string.add_score_dialog_player_name, playerCName));

        final EditText playerD = new EditText(this);
        playerD.setInputType(InputType.TYPE_CLASS_NUMBER);
        playerD.setHint(getString(R.string.add_score_dialog_player_name, playerDName));

        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.addView(playerA);
        linearLayout.addView(playerB);
        linearLayout.addView(playerC);
        linearLayout.addView(playerD);
        builder.setView(linearLayout);

        builder.setPositiveButton("Add", (dialog, which) -> {
            int scoreA = safeParseInt(playerA.getText().toString(), 0);
            int scoreB = safeParseInt(playerB.getText().toString(), 0);
            int scoreC = safeParseInt(playerC.getText().toString(), 0);
            int scoreD = safeParseInt(playerD.getText().toString(), 0);

            setPlayerAScore(playerAScore + scoreA);
            setPlayerBScore(playerBScore + scoreB);
            setPlayerCScore(playerCScore + scoreC);
            setPlayerDScore(playerDScore + scoreD);
            addScoreRow(scoreA, scoreB, scoreC, scoreD);
            if (holeNumber == MAX_HOLES) {
                addScoreButton.setText(R.string.doneButton);
                addScoreButton.setEnabled(false);
            } else {
                setHoleNumber(holeNumber + 1);
            }
        });
        builder.setNegativeButton("Cancel", ((dialog, which) -> dialog.cancel()));

        builder.show();
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

    private void addScoreRow(int scoreA, int scoreB, int scoreC, int scoreD) {
        TableRow row = new TableRow(this);
        row.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT));

        TextView holeNumber = new TextView(this);
        holeNumber.setText(String.valueOf(this.holeNumber));
        holeNumber.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        holeNumber.setTextAppearance(R.style.TextAppearance_AppCompat_Body1);
        holeNumber.setBackground(ContextCompat.getDrawable(this, R.drawable.border));

        TextView playerA = new TextView(this);
        playerA.setText(String.valueOf(scoreA));
        playerA.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        playerA.setTextAppearance(R.style.TextAppearance_AppCompat_Body1);
        playerA.setBackground(ContextCompat.getDrawable(this, R.drawable.border));

        TextView playerB = new TextView(this);
        playerB.setText(String.valueOf(scoreB));
        playerB.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        playerB.setTextAppearance(R.style.TextAppearance_AppCompat_Body1);
        playerB.setBackground(ContextCompat.getDrawable(this, R.drawable.border));

        TextView playerC = new TextView(this);
        playerC.setText(String.valueOf(scoreC));
        playerC.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        playerC.setTextAppearance(R.style.TextAppearance_AppCompat_Body1);
        playerC.setBackground(ContextCompat.getDrawable(this, R.drawable.border));

        TextView playerD = new TextView(this);
        playerD.setText(String.valueOf(scoreD));
        playerD.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        playerD.setTextAppearance(R.style.TextAppearance_AppCompat_Body1);
        playerD.setBackground(ContextCompat.getDrawable(this, R.drawable.border));

        row.addView(holeNumber);
        row.addView(playerA);
        row.addView(playerB);
        row.addView(playerC);
        row.addView(playerD);

        scoreRows.add(row);
        scoreTable.addView(row);
    }

    public void setPlayerAScore(int playerAScore) {
        this.playerAScore = playerAScore;
        playerAScoreLabel.setText(String.valueOf(playerAScore));
    }

    public void setPlayerBScore(int playerBScore) {
        this.playerBScore = playerBScore;
        playerBScoreLabel.setText(String.valueOf(playerBScore));
    }

    public void setPlayerCScore(int playerCScore) {
        this.playerCScore = playerCScore;
        playerCScoreLabel.setText(String.valueOf(playerCScore));
    }

    public void setPlayerDScore(int playerDScore) {
        this.playerDScore = playerDScore;
        playerDScoreLabel.setText(String.valueOf(playerDScore));
    }

    private static int safeParseInt(String s, int defaultValue) {
        try {
            return Integer.parseInt(s);
        } catch(NumberFormatException ex) {
            return defaultValue;
        }
    }
}

interface NameUpdater {
    void updateName(String name);
}