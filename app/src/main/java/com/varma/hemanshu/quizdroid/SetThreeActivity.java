package com.varma.hemanshu.quizdroid;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.varma.hemanshu.quizdroid.SetOneActivity.COUNT;
import static com.varma.hemanshu.quizdroid.SetOneActivity.SHARED_PREF;
import static com.varma.hemanshu.quizdroid.SetOneActivity.count;

public class SetThreeActivity extends AppCompatActivity {

    @BindView(R.id.submit3_btn)
    Button submitBTN;

    String teamString;
    String nameString;
    String branchString;
    String yearString;
    String setString;
    String attemptString;
    String dialogInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_three);

        //Setting up BindView
        ButterKnife.bind(this);

        //Retrieving the Passed Values
        Intent i = getIntent();
        teamString = i.getStringExtra("TEAM_NO");
        nameString = i.getStringExtra("NAME");
        yearString = i.getStringExtra("YEAR");
        branchString = i.getStringExtra("BRANCH");
        setString = i.getStringExtra("SET");

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
     * To display the score in Dialog when Submit Button is clicked.
     */
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
