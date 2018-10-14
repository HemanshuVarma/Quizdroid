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

/**
 * User Details Activity
 * To get Basic Info of user.
 */
public class UserDetailsActivity extends AppCompatActivity {

    Spinner branchSpinner;
    Spinner yearSpinner;
    Spinner setSpinner;
    Button startButton;
    TextView instructionsTextView;
    EditText teamEditText;
    EditText nameEditText;

    public String selectedBranch;
    public String selectedYear;
    public String teamNo;
    public String userName;
    public String selectedSet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);

        teamEditText = findViewById(R.id.team_no_et);
        nameEditText = findViewById(R.id.name_et);
        yearSpinner = findViewById(R.id.year_sv);
        branchSpinner = findViewById(R.id.branch_sv);
        setSpinner = findViewById(R.id.set_sv);
        startButton = findViewById(R.id.start_tv);
        instructionsTextView = findViewById(R.id.instructions_tv);

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
                            Intent i = new Intent(UserDetailsActivity.this, MainActivity.class);
                            i.putExtra("TEAM_NO", teamNo);
                            i.putExtra("NAME", userName);
                            i.putExtra("BRANCH", selectedBranch);
                            i.putExtra("YEAR", selectedYear);
                            i.putExtra("SET", selectedSet);
                            startActivity(i);
                            break;
                        case 1:
                            Toast.makeText(UserDetailsActivity.this, "SET 2", Toast.LENGTH_SHORT).show();
                            break;
                        case 2:
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
