package com.varma.hemanshu.quizdroid;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView teamTV;
    TextView nameTV;
    TextView branchTV;
    TextView yearTV;
    TextView setTV;
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

        loadData();
        count++;

        teamTV = findViewById(R.id.team_tv);
        nameTV = findViewById(R.id.name_tv);
        yearTV = findViewById(R.id.year_tv);
        branchTV = findViewById(R.id.branch_tv);
        setTV = findViewById(R.id.set_tv);
        attemptsTV = findViewById(R.id.attempts_tv);

        Intent i = getIntent();
        teamString = i.getStringExtra("TEAM_NO");
        nameString = i.getStringExtra("NAME");
        yearString = i.getStringExtra("YEAR");
        branchString = i.getStringExtra("BRANCH");
        setString = i.getStringExtra("SET");

        teamTV.setText(teamString);
        nameTV.setText(nameString);
        yearTV.setText(yearString);
        branchTV.setText(branchString);
        setTV.setText(setString);

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
