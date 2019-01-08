package com.varma.hemanshu.quizdroid;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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
    @BindView(R.id.instructions_cb)
    CheckBox instructionsCheckbox;
    @BindView(R.id.instructions_tv)
    TextView instructionsTextView;
    @BindView(R.id.start_tv)
    Button startButton;

    public static String teamNo;
    public static String userName;
    public static String selectedYear;
    public static String selectedBranch;
    public static String selectedSet;

    //Shared Pref
    public static final String SHARED_PREF = "Shared Pref";
    public static final String COUNT = "Count";
    public static int count;

    //FireBase Instances
    public static FirebaseDatabase mFirebaseDatabase;
    public static DatabaseReference mDatabaseReferenceResult;
    public static DatabaseReference mDatabaseReferenceFeedback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);

        ButterKnife.bind(this);

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReferenceResult = mFirebaseDatabase.getReference().child("result");
        mDatabaseReferenceFeedback = mFirebaseDatabase.getReference().child("feedback");

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
         * When invoked, it checks for Empty state of EditText/Input before proceeding to SetOneActivity for Quiz
         * A method isEmpty is called to verify field*/
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isEmpty(teamEditText) && !isEmpty(nameEditText) && instructionsCheckbox.isChecked()) {
                    teamNo = teamEditText.getText().toString();
                    userName = nameEditText.getText().toString();
                    selectedBranch = branchSpinner.getSelectedItem().toString();
                    selectedYear = yearSpinner.getSelectedItem().toString();
                    selectedSet = setSpinner.getSelectedItem().toString();
                    switch (setSpinner.getSelectedItemPosition()) {
                        case 0:
                            Intent setOneIntent = new Intent(UserDetailsActivity.this, SetOneActivity.class);
                            startActivity(setOneIntent);
                            Toast.makeText(UserDetailsActivity.this, R.string.set1, Toast.LENGTH_SHORT).show();
                            break;
                        case 1:
                            Intent setTwoIntent = new Intent(UserDetailsActivity.this, SetTwoActivity.class);
                            startActivity(setTwoIntent);
                            Toast.makeText(UserDetailsActivity.this, R.string.set2, Toast.LENGTH_SHORT).show();
                            break;
                    }
                } else {
                    if (isEmpty(teamEditText)) {
                        teamEditText.setError(getString(R.string.teamETError));
                    }
                    if (isEmpty(nameEditText)) {
                        nameEditText.setError(getString(R.string.nameETError));
                    }
                    if (!instructionsCheckbox.isChecked()) {
                        Toast.makeText(UserDetailsActivity.this, getString(R.string.read_instructions),
                                Toast.LENGTH_SHORT).show();
                    }
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
    public boolean isEmpty(EditText etText) {
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
