package com.varma.hemanshu.quizdroid;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
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
