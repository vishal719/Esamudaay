package com.example.esamudaay;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.example.esamudaay.databinding.ActivityGetStartedBinding;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class GetStartedActivity extends AppCompatActivity {
    private static final int RC_SIGN_IN = 100;
    ActivityGetStartedBinding binding;
    private GoogleSignInClient mGoogleSignInClient;
    FirebaseDatabase database;
    FirebaseAuth mAuth;

    public static void setWindowFlag(Activity activity, final int bits, boolean on) {
        Window win = activity.getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void hideStatus() {
        /* To make the status bar transparent*/

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);

        setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        binding = ActivityGetStartedBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            hideStatus();
        database = FirebaseDatabase.getInstance("https://esamudaay-4ae43-default-rtdb.asia-southeast1.firebasedatabase.app/");


        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        checkUsername(currentUser);


        // Configure Google Sign In
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();


        binding.loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    FirebaseAuth.getInstance().signInWithEmailAndPassword(binding.loginEmail.getText().toString(),
                            binding.loginPassword.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                                Intent intent = new Intent(GetStartedActivity.this, MainActivity.class);
                                startActivity(intent);
                                finish();
                            } else {
                                Toast.makeText(GetStartedActivity.this, "This user does not exist", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                } catch (IllegalArgumentException ex) {
                    Toast.makeText(GetStartedActivity.this, "Please enter the login details", Toast.LENGTH_SHORT).show();
                }
            }
        });

        binding.forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.loginEmail.getText().toString().trim().equals("")) {
                    Toast.makeText(GetStartedActivity.this, "Please enter an Email address", Toast.LENGTH_SHORT).show();
                } else {

                    FirebaseAuth auth = FirebaseAuth.getInstance();
                    String emailAddress = binding.loginEmail.getText().toString().trim();

                    auth.sendPasswordResetEmail(emailAddress)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(GetStartedActivity.this, "Email sent", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                    }
            }
        });

    }

    public void checkUsername(FirebaseUser currentUser) {
        if (currentUser != null) {
            currentUser.reload();
            Intent intent = new Intent(GetStartedActivity.this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();

        }
    }
}