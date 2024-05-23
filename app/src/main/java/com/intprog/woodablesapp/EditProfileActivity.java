package com.intprog.woodablesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import java.util.Map;
import java.util.HashMap;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class EditProfileActivity extends AppCompatActivity {

    ImageView backBtn;
    private FirebaseFirestore db;
    private FirebaseAuth mAuth;
    private String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        db = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
        userId = mAuth.getCurrentUser().getUid();

        Button saveButton = findViewById(R.id.saveEdit);
        EditText firstNameEditText = findViewById(R.id.editFirstName);
        EditText middleNameEditText = findViewById(R.id.editMiddleName);
        EditText lastNameEditText = findViewById(R.id.editLastName);
        EditText desc2EditText = findViewById(R.id.profileDesc2);
        EditText desc3EditText = findViewById(R.id.profileDesc3);
        EditText desc4EditText = findViewById(R.id.profileDesc4);
        EditText desc5EditText = findViewById(R.id.profileDesc5);
        EditText desc6EditText = findViewById(R.id.profileDesc6);
        EditText desc7EditText = findViewById(R.id.profileDesc7);

        backBtn = findViewById(R.id.backbutton);

        backBtn.setOnClickListener(v -> {
            finish();
        });

        saveButton.setOnClickListener(v -> {
            String firstName = firstNameEditText.getText().toString();
            String middleName = middleNameEditText.getText().toString();
            String lastName = lastNameEditText.getText().toString();
            String desc2 = desc2EditText.getText().toString();
            String desc3 = desc3EditText.getText().toString();
            String desc4 = desc4EditText.getText().toString();
            String desc5 = desc5EditText.getText().toString();
            String desc6 = desc6EditText.getText().toString();
            String desc7 = desc7EditText.getText().toString();

            if (!firstName.isEmpty() || !middleName.isEmpty() || !lastName.isEmpty()) {
                Map<String, Object> userData = new HashMap<>();
                userData.put("First Name", firstName);
                userData.put("Middle Name", middleName);
                userData.put("Last Name", lastName);

                db.collection("users").document(userId)
                        .update(userData)
                        .addOnSuccessListener(aVoid -> {
                            Toast.makeText(EditProfileActivity.this, "Profile updated", Toast.LENGTH_SHORT).show();
                        })
                        .addOnFailureListener(e -> {
                            Toast.makeText(EditProfileActivity.this, "Error updating profile", Toast.LENGTH_SHORT).show();
                        });
            }

            // Check if any of the description fields are not empty
            boolean hasNonEmptyDescriptions = !desc2.isEmpty() || !desc3.isEmpty() || !desc4.isEmpty()
                    || !desc5.isEmpty() || !desc6.isEmpty() || !desc7.isEmpty();

            // Only update descriptions if there are non-empty values
            if (hasNonEmptyDescriptions) {
                ProfileDescriptions profileDescriptions = new ProfileDescriptions(desc2, desc3, desc4, desc5, desc6, desc7);
                db.collection("profile_descriptions").document(userId)
                        .set(profileDescriptions)
                        .addOnSuccessListener(aVoid -> {
                            Toast.makeText(EditProfileActivity.this, "Descriptions updated", Toast.LENGTH_SHORT).show();
                        })
                        .addOnFailureListener(e -> {
                            Toast.makeText(EditProfileActivity.this, "Error updating descriptions", Toast.LENGTH_SHORT).show();
                        });
            }

            String fullName = firstName + " " + middleName + " " + lastName;

            Intent intent = new Intent();
            intent.putExtra("FULL_NAME", fullName);
            intent.putExtra("DESC2", desc2);
            intent.putExtra("DESC3", desc3);
            intent.putExtra("DESC4", desc4);
            intent.putExtra("DESC5", desc5);
            intent.putExtra("DESC6", desc6);
            intent.putExtra("DESC7", desc7);

            setResult(Activity.RESULT_OK, intent);
            finish();
        });
    }
}