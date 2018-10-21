package com.varma.hemanshu.quizdroid;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;



public class MainActivity extends AppCompatActivity {

    @BindView(R.id.team_tv)
    TextView teamTV;
    @BindView(R.id.name_tv)
    TextView nameTV;
    @BindView(R.id.branch_tv)
    TextView branchTV;
    @BindView(R.id.year_tv)
    TextView yearTV;
    @BindView(R.id.set_tv)
    TextView setTV;
    @BindView(R.id.attempts_tv)
    TextView attemptsTV;
    @BindView(R.id.submit_btn)
    Button submitBTN;

    String teamString;
    String nameString;
    String branchString;
    String yearString;
    String setString;
    String attemptString;
    String dialogInfo;

    public static final String SHARED_PREF = "Shared Pref";
    public static final String COUNT = "Count";
    public int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Setting up BindView
        ButterKnife.bind(this);

        //Retrieving the Passed Values
        Intent i = getIntent();
        teamString = i.getStringExtra("TEAM_NO");
        nameString = i.getStringExtra("NAME");
        yearString = i.getStringExtra("YEAR");
        branchString = i.getStringExtra("BRANCH");
        setString = i.getStringExtra("SET");

        //Setting up in TextView
        teamTV.setText(teamString);
        nameTV.setText(nameString);
        yearTV.setText(yearString);
        branchTV.setText(branchString);
        setTV.setText(setString);

        submitBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                score();
            }
        });
    }

    /**
     * To count the No. of Attempts.
     * Counts when - Minimized, Invoked from UserDetails Activity.
     */
    @Override
    protected void onResume() {
        super.onResume();
        loadData();
        count++;
        attemptString = String.valueOf(count);
        attemptsTV.setText(attemptString);
        saveData();
    }

    /**
     * To SAVE the Count using Shared Preferences
     */
    public void saveData() {
        SharedPreferences mySharedPref = getSharedPreferences(SHARED_PREF, MODE_PRIVATE);
        SharedPreferences.Editor editor = mySharedPref.edit();
        editor.putInt(COUNT, count);
        editor.apply();

    }

    /**
     * To GET the Count using Shared Preferences
     */
    public void loadData() {
        SharedPreferences mySharedPref = getSharedPreferences(SHARED_PREF, MODE_PRIVATE);
        count = mySharedPref.getInt(COUNT, 0);
    }

    private void score() {
        dialogInfo = getString(R.string.team_score) + "\u0020" + teamString + "\n" + getString(R.string.name_score) + "\u0020"
                + nameString + "\n" + getString(R.string.year_score) + "\u0020" + yearString + "\n"
                + getString(R.string.branch_score) + "\u0020" + branchString + "\n" + getString(R.string.set_score) + "\u0020"
                + setString + "\n" + getString(R.string.attempts_score) + "\u0020" + attemptString;
        new AlertDialog.Builder(this)
                .setTitle(R.string.title_score)
                .setMessage(dialogInfo)
                .setPositiveButton(android.R.string.yes, null).create().show();
    }

    /**
     * System Method when Back button is pressed
     */
    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle(R.string.exit_title)
                .setMessage(R.string.exit_message)
                .setNegativeButton(android.R.string.no, null)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface arg0, int arg1) {
                        finishAffinity();
                    }
                }).create().show();

    }
}
