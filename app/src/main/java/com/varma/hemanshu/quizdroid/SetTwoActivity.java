package com.varma.hemanshu.quizdroid;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.varma.hemanshu.quizdroid.UserDetailsActivity.COUNT;
import static com.varma.hemanshu.quizdroid.UserDetailsActivity.SHARED_PREF;
import static com.varma.hemanshu.quizdroid.UserDetailsActivity.count;

public class SetTwoActivity extends AppCompatActivity {

    private static final String LOG_TAG = SetTwoActivity.class.getSimpleName();
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    //Linking Submit Button
    @BindView(R.id.submit2_btn)
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
        setContentView(R.layout.activity_set_two);

        //Setting up BindView
        ButterKnife.bind(this);

        //Accessing Data from UserDetailsActivity
        teamString = UserDetailsActivity.teamNo;
        nameString = UserDetailsActivity.userName;
        yearString = UserDetailsActivity.selectedYear;
        branchString = UserDetailsActivity.selectedBranch;
        setString = UserDetailsActivity.selectedSet;

        String title = getString(R.string.title_team) + teamString;
        try {
            if (toolbar != null) {
                setSupportActionBar(toolbar);
                getSupportActionBar().setLogo(R.drawable.csi_logo_india);
                getSupportActionBar().setTitle(title);
            }
        } catch (Exception e) {
            Log.v(LOG_TAG, "Toolbar inflated");
        }

        submitBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                score(2);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.quiz_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.feedback:
                Intent i = new Intent(SetTwoActivity.this, FeedbackActivity.class);
                startActivity(i);
                return true;
        }
        return super.onOptionsItemSelected(item);
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
    private void score(int points) {
        dialogInfo = getString(R.string.team_score) + "\u0020" + teamString + "\n" + getString(R.string.name_score) + "\u0020"
                + nameString + "\n" + getString(R.string.year_score) + "\u0020" + yearString + "\n"
                + getString(R.string.branch_score) + "\u0020" + branchString + "\n" + getString(R.string.set_score) + "\u0020"
                + setString + "\n" + getString(R.string.attempts_score) + "\u0020" + attemptString +
                "\n" + getString(R.string.title_score) + "\u0020" + points;
        String result = "Team:" + teamString + " Att:" + attemptString + " Scr:" + points;
        UserDetailsActivity.mDatabaseReferenceResult.push().setValue(result);
        Log.i(LOG_TAG, "Score Updated in Db");
        new AlertDialog.Builder(this)
                .setTitle(R.string.title_score)
                .setMessage(dialogInfo)
                .setPositiveButton(R.string.close, null).create().show();
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
