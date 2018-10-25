package com.varma.hemanshu.quizdroid;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * User Details Activity
 * To get Basic Info of user.
 */
public class UserDetailsActivity extends AppCompatActivity {


    @BindView(R.id.team_no_et)
    EditText teamEditText;
    @BindView(R.id.name_et)
    EditText nameEditText;
    @BindView(R.id.year_sv)
    Spinner yearSpinner;
    @BindView(R.id.branch_sv)
    Spinner branchSpinner;
    @BindView(R.id.set_sv)
    Spinner setSpinner;
    @BindView(R.id.instructions_tv)
    TextView instructionsTextView;
    @BindView(R.id.start_tv)
    Button startButton;

    public String selectedBranch;
    public String selectedYear;
    public String teamNo;
    public String userName;
    public String selectedSet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);

        ButterKnife.bind(this);

        // Listener for Instructions TextView
        // Displays a Dialog with all the instructions and Ok Button in it.
        instructionsTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(UserDetailsActivity.this)
                        .setTitle(R.string.instructions_title)
                        .setMessage(R.string.instructions_message)
                        .setPositiveButton(android.R.string.yes, null).create().show();
            }
        });

        /*Listener for Start Button.
         * When invoked, it checks for Empty state of EditText/Input before proceeding to MainActivity for Quiz
         * A method isEmpty is called to verify field*/
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isEmpty(teamEditText) && !isEmpty(nameEditText)) {
                    teamNo = teamEditText.getText().toString();
                    userName = nameEditText.getText().toString();
                    selectedBranch = branchSpinner.getSelectedItem().toString();
                    selectedYear = yearSpinner.getSelectedItem().toString();
                    selectedSet = setSpinner.getSelectedItem().toString();
                    switch (setSpinner.getSelectedItemPosition()) {
                        case 0:
                            Intent setOneIntent = new Intent(UserDetailsActivity.this, MainActivity.class);
                            setOneIntent.putExtra("TEAM_NO", teamNo);
                            setOneIntent.putExtra("NAME", userName);
                            setOneIntent.putExtra("BRANCH", selectedBranch);
                            setOneIntent.putExtra("YEAR", selectedYear);
                            setOneIntent.putExtra("SET", selectedSet);
                            startActivity(setOneIntent);
                            Toast.makeText(UserDetailsActivity.this, "SET 2", Toast.LENGTH_SHORT).show();
                            break;
                        case 1:
                            Intent setTwoIntent = new Intent(UserDetailsActivity.this, SetTwoActivity.class);
                            setTwoIntent.putExtra("TEAM_NO", teamNo);
                            setTwoIntent.putExtra("NAME", userName);
                            setTwoIntent.putExtra("BRANCH", selectedBranch);
                            setTwoIntent.putExtra("YEAR", selectedYear);
                            setTwoIntent.putExtra("SET", selectedSet);
                            startActivity(setTwoIntent);
                            Toast.makeText(UserDetailsActivity.this, "SET 2", Toast.LENGTH_SHORT).show();
                            break;
                        case 2:
                            Intent setThreeIntent = new Intent(UserDetailsActivity.this, SetThreeActivity.class);
                            setThreeIntent.putExtra("TEAM_NO", teamNo);
                            setThreeIntent.putExtra("NAME", userName);
                            setThreeIntent.putExtra("BRANCH", selectedBranch);
                            setThreeIntent.putExtra("YEAR", selectedYear);
                            setThreeIntent.putExtra("SET", selectedSet);
                            startActivity(setThreeIntent);
                            Toast.makeText(UserDetailsActivity.this, "SET 3", Toast.LENGTH_SHORT).show();
                            break;
                    }
                } else {
                    Toast.makeText(UserDetailsActivity.this, R.string.empty_fields, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    /**
     * To check whether EditText Field isEmpty
     *
     * @param etText EditText is passed into this
     * @return true is the EditText isEmpty
     */
    private boolean isEmpty(EditText etText) {
        return etText.getText().toString().trim().length() == 0;
    }

    /**
     * System method invoked when Back Button is pressed.
     * Exit Dialog
     */
    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle(R.string.exit_title)
                .setMessage(R.string.exit_message)
                .setNegativeButton(android.R.string.no, null)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface arg0, int arg1) {
                        UserDetailsActivity.super.onBackPressed();
                    }
                }).create().show();

    }
}
