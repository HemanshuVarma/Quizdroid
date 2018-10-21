package com.varma.hemanshu.quizdroid;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

    String teamString;
    String nameString;
    String branchString;
    String yearString;
    String setString;

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
        attemptsTV.setText(String.valueOf(count));
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
