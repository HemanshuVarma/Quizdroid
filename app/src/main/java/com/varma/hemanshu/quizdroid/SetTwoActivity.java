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
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.varma.hemanshu.quizdroid.UserDetailsActivity.COUNT;
import static com.varma.hemanshu.quizdroid.UserDetailsActivity.SHARED_PREF;
import static com.varma.hemanshu.quizdroid.UserDetailsActivity.count;

public class SetTwoActivity extends AppCompatActivity {

    private static final String LOG_TAG = SetTwoActivity.class.getSimpleName();
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.s2_que1_radios)
    RadioGroup que1;
    @BindView(R.id.s2_que2_radios)
    RadioGroup que2;
    @BindView(R.id.s2_que3_et)
    EditText que3;
    @BindView(R.id.s2_que4_radios)
    RadioGroup que4;
    @BindView(R.id.s2_que5_cb1)
    CheckBox que5_1;
    @BindView(R.id.s2_que5_cb2)
    CheckBox que5_2;
    @BindView(R.id.s2_que6_et)
    EditText que6;
    @BindView(R.id.s2_que7_radios)
    RadioGroup que7;
    @BindView(R.id.s2_que8_radios)
    RadioGroup que8;
    @BindView(R.id.s2_que9_et)
    EditText que9;
    @BindView(R.id.s2_que10_radios)
    RadioGroup que10;
    @BindView(R.id.s2_que11_cb1)
    CheckBox que11_1;
    @BindView(R.id.s2_que11_cb3)
    CheckBox que11_2;
    @BindView(R.id.s2_que12_radios)
    RadioGroup que12;
    @BindView(R.id.s2_que13_radios)
    RadioGroup que13;
    @BindView(R.id.s2_que14_radios)
    RadioGroup que14;
    @BindView(R.id.s2_que15_et)
    EditText que15;
    @BindView(R.id.s2_que16_et)
    EditText que16;
    @BindView(R.id.s2_que17_radios)
    RadioGroup que17;
    @BindView(R.id.s2_que18_radios)
    RadioGroup que18;
    @BindView(R.id.s2_que19_cb1)
    CheckBox que19_1;
    @BindView(R.id.s2_que19_cb2)
    CheckBox que19_2;
    @BindView(R.id.s2_que20_cb1)
    CheckBox que20_1;
    @BindView(R.id.s2_que20_cb4)
    CheckBox que20_2;
    @BindView(R.id.s2_que21_et)
    EditText que21;
    @BindView(R.id.s2_que22_radios)
    RadioGroup que22;
    @BindView(R.id.s2_que23_radios)
    RadioGroup que23;
    @BindView(R.id.s2_que24_radios)
    RadioGroup que24;
    @BindView(R.id.s2_que25_et)
    EditText que25;

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
    int submitCount;

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
            public void onClick(View v) { evaluateQuiz();
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

    // Evaluation is done out of 57 POINTS.
    // 1 for Radio, 5 for EditText, 2 for CheckBox
    private void evaluateQuiz() {
        int points = 0;
        try {
            if (getRadioValue(que1).equalsIgnoreCase(getString(R.string.s2_que1_r2))) {
                points++;
            }
            if (getRadioValue(que2).equalsIgnoreCase(getString(R.string.s2_que2_r1))) {
                points++;
            }
            if (getEditTextValue(que3).equalsIgnoreCase(getString(R.string.s2_que3_ans))) {
                points += 5;
            }
            if (getRadioValue(que4).equalsIgnoreCase(getString(R.string.s2_que4_r3))) {
                points++;
            }
            if (getCheckBoxStatus(que5_1) && getCheckBoxStatus(que5_2)) {
                points += 2;
            }
            if (getEditTextValue(que6).equalsIgnoreCase(getString(R.string.s2_que6_ans))) {
                points += 5;
            }
            if (getRadioValue(que7).equalsIgnoreCase(getString(R.string.s2_que7_r1))) {
                points++;
            }
            if (getRadioValue(que8).equalsIgnoreCase(getString(R.string.s2_que8_r2))) {
                points++;
            }
            if (getEditTextValue(que9).equalsIgnoreCase(getString(R.string.s2_que9_ans))) {
                points += 5;
            }
            if (getRadioValue(que10).equalsIgnoreCase(getString(R.string.s2_que10_r3))) {
                points++;
            }
            if (getCheckBoxStatus(que11_1) && getCheckBoxStatus(que11_2)) {
                points += 2;
            }
            if (getRadioValue(que12).equalsIgnoreCase(getString(R.string.s2_que12_r2))) {
                points++;
            }
            if (getRadioValue(que13).equalsIgnoreCase(getString(R.string.s2_que13_r1))) {
                points++;
            }
            if (getRadioValue(que14).equalsIgnoreCase(getString(R.string.s2_que14_r4))) {
                points++;
            }
            if (getEditTextValue(que15).equalsIgnoreCase(getString(R.string.s2_que15_ans))) {
                points += 5;
            }
            if (getEditTextValue(que16).equalsIgnoreCase(getString(R.string.s2_que16_ans))) {
                points += 5;
            }
            if (getRadioValue(que17).equalsIgnoreCase(getString(R.string.s2_que17_r4))) {
                points++;
            }
            if (getRadioValue(que18).equalsIgnoreCase(getString(R.string.s2_que18_r2))) {
                points++;
            }
            if (getCheckBoxStatus(que19_1) && getCheckBoxStatus(que19_2)) {
                points += 2;
            }
            if (getCheckBoxStatus(que20_1) && getCheckBoxStatus(que20_2)) {
                points += 2;
            }
            if (getEditTextValue(que21).equalsIgnoreCase(getString(R.string.s2_que21_ans))) {
                points += 5;
            }
            if (getRadioValue(que22).equalsIgnoreCase(getString(R.string.s2_que22_r2))) {
                points++;
            }
            if (getRadioValue(que23).equalsIgnoreCase(getString(R.string.s2_que23_r2))) {
                points++;
            }
            if (getRadioValue(que24).equalsIgnoreCase(getString(R.string.s2_que24_r2))) {
                points++;
            }
            if (getEditTextValue(que25).equalsIgnoreCase(getString(R.string.s2_que25_ans))) {
                points += 5;
            }

            //Displaying Score only if FIELDS are not EMPTY
            if (submitCount < 2) {
                score(points);
                submitCount++;
                if (submitCount == 2) {
                    submitBTN.setEnabled(false);
                    submitBTN.setBackgroundColor(getResources().getColor(android.R.color.darker_gray));
                }
                Log.i(LOG_TAG, "Submitted");
            }
        } catch (Exception e) {
            Log.e(LOG_TAG, "Caused by Empty Fields");
            e.printStackTrace();
            Toast.makeText(SetTwoActivity.this, R.string.empty_fields, Toast.LENGTH_LONG).show();
        }
    }

    /**
     * Function to return the RadioValue
     *
     * @param radios Passing RadioGroup to get the selected value
     * @return checked radio value
     */
    private String getRadioValue(RadioGroup radios) {
        return ((RadioButton) findViewById(radios.getCheckedRadioButtonId())).getText().toString();
    }

    /**
     * Function to return EditText Value
     *
     * @param et Passing EditText whose value is to be obtained
     * @return EditText to String
     */
    private String getEditTextValue(EditText et) {
        return et.getText().toString().trim();
    }

    /**
     * Function to return Checked CheckBox
     *
     * @param cb CheckBox
     * @return True if CheckBox is selected.
     */
    private boolean getCheckBoxStatus(CheckBox cb) {
        return cb.isChecked();
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
        if (submitCount < 1) {
            UserDetailsActivity.mDatabaseReferenceResult.push().setValue(result);
        }
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
