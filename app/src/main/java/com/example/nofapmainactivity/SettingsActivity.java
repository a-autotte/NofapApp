package com.example.nofapmainactivity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Activity;
import android.content.ClipData;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nofapmainactivity.databinding.ActivitySettingsBinding;
import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;


import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.squareup.picasso.Picasso;

import java.util.Locale;


public class SettingsActivity extends AppCompatActivity {

    GoogleSignInClient mGoogleSignInClient;
    String TAGGoogle = "SettingsActivity";
    FirebaseAuth mAuth;

    int RC_SIGN_IN = 1;

    ActionBarDrawerToggle abdt;
    CallbackManager mCallbackManager;
    FirebaseAuth mFirebaseAuth;
    FirebaseAuth.AuthStateListener authStateListener;
    ImageView mLogo;
    AccessTokenTracker accessTokenTracker;
    static final String TAG = "FacebookAuthentication";
    ActivitySettingsBinding binding;
    DrawerLayout dl;
    NavigationView nav_settings;
    NavigationView nav_view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadLocale();
        setContentView(R.layout.activity_settings);
        abdt = new ActionBarDrawerToggle(this, binding.dl, R.string.Open, R.string.Close);
        abdt.setDrawerIndicatorEnabled(true);

        dl.addDrawerListener(abdt);
        abdt.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        nav_settings.setNavigationItemSelectedListener(item -> {
            int id = item.getItemId();

            switch(id)
            {
                case R.id.IconLanguageOptionsId:
                    showChangeLanguageDialog();

                    break;

                case R.id.IconShareId:
                    Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                    sharingIntent.setType("text/plain");
                    String shareBody="Your body Here";
                    String shareSubject="Your Subject here";

                    sharingIntent.putExtra(Intent.EXTRA_TEXT, shareBody);
                    sharingIntent.putExtra(Intent.EXTRA_SUBJECT, shareSubject);

                    startActivity(Intent.createChooser(sharingIntent, "Share Using"));
                    break;
            }

            return true;
        });


        nav_view.setNavigationItemSelectedListener(item -> {
            int id = item.getItemId();

            switch (id)
            {
                case R.id.IconProfileId:
                    OpenActivity(ProfileActivity.class);
                    break;

                case R.id.IconSettingId:
                    binding.dl.closeDrawers();
                    break;

                case R.id.IconTimerId:
                    OpenActivity(TimerActivity.class);
                    break;

                case R.id.IconCommunityId:
                    OpenActivity(CommunityActivity.class);
                    break;

                case R.id.IconToDoListId:
                    OpenActivity(ToDoListActivity.class);
                    break;

                case R.id.IconTrophyId:
                    OpenActivity(TrophyActivity.class);
                    break;
            }
            return true;
        });


        mFirebaseAuth = FirebaseAuth.getInstance();
        FacebookSdk.sdkInitialize(getApplicationContext());



        binding.loginButton.setReadPermissions("email", "public_profile");

        mCallbackManager = CallbackManager.Factory.create();
        binding.loginButton.registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.d(TAG, "onSuccess" + loginResult);
                handleFacebookToken(loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {
                Log.d(TAG, "onCancel");
            }

            @Override
            public void onError(FacebookException error) {
                Log.d(TAG, "onError" + error);
            }
        });

        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();

                if (user != null)
                {
                    updateUI(user);
                }
                else {
                    updateUI(null);
                }
            }

        };

        accessTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
                if (currentAccessToken == null)
                {
                    mFirebaseAuth.signOut();
                }
            }
        };


        mAuth = FirebaseAuth.getInstance();


        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        binding.signInButton.setOnClickListener(viewNav -> signIn());

        binding.signOutButton.setOnClickListener(viewNav -> {
            mGoogleSignInClient.signOut();
            Toast.makeText(SettingsActivity.this,"You are Logged Out",Toast.LENGTH_SHORT).show();
            binding.signOutButton.setVisibility(View.INVISIBLE);
        });
    }

    private void signIn(){
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        /*if (method == "Facebook")
        {

        }

        if (method == "Google")
        {
            super.onActivityResult(requestCode, resultCode, data);
            if(requestCode == RC_SIGN_IN){
                Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
                handleSignInResult(task);
            }
        }*/

        mCallbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);

    }


    private void handleSignInResult(Task<GoogleSignInAccount> completedTask){
        try{

            GoogleSignInAccount acc = completedTask.getResult(ApiException.class);
            Toast.makeText(SettingsActivity.this,"Signed In Successfully",Toast.LENGTH_SHORT).show();
            FirebaseGoogleAuth(acc);
        }
        catch (ApiException e){
            Toast.makeText(SettingsActivity.this,"Sign In Failed",Toast.LENGTH_SHORT).show();
            FirebaseGoogleAuth(null);
        }
    }

    private void FirebaseGoogleAuth(GoogleSignInAccount acct) {
        //check if the account is null
        if (acct != null) {
            AuthCredential authCredential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
            mAuth.signInWithCredential(authCredential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(SettingsActivity.this, "Successful", Toast.LENGTH_SHORT).show();
                        FirebaseUser user = mAuth.getCurrentUser();
                        updateUI(user);
                    } else {
                        Toast.makeText(SettingsActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                        updateUI(null);
                    }
                }
            });
        }
        else{
            Toast.makeText(SettingsActivity.this, "acc failed", Toast.LENGTH_SHORT).show();
        }
    }

    private void updateUI(FirebaseUser fUser)
    {

        if (fUser != null)
        {

            if (fUser.getPhotoUrl() != null)
            {
                String photoUrl = fUser.getPhotoUrl().toString();
                photoUrl = photoUrl + "?type=large";
                Picasso.get().load(photoUrl).into(mLogo);
            }

        } else {
            binding.textUser.setText("");
        }

        binding.signOutButton.setVisibility(View.VISIBLE);
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(getApplicationContext());
        if(account !=  null){
            String personName = account.getDisplayName();
            String personGivenName = account.getGivenName();
            String personFamilyName = account.getFamilyName();
            String personEmail = account.getEmail();
            String personId = account.getId();
            Uri personPhoto = account.getPhotoUrl();

            Toast.makeText(SettingsActivity.this,personName + personEmail ,Toast.LENGTH_SHORT).show();
        }
    }

    private void handleFacebookToken(AccessToken token)
    {
        Log.d(TAG, "handleFacebookToken" + token);
        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        mFirebaseAuth.signInWithCredential(credential).addOnCompleteListener(this, task -> {
            if (task.isSuccessful())
            {
                Log.d(TAG, "sign in with credential: successful");
                FirebaseUser user = mFirebaseAuth.getCurrentUser();
                updateUI(user);
            } else
            {
                Log.d(TAG, "sign in with credential: failure", task.getException());
                Toast.makeText(SettingsActivity.this, "Authentication Failed", Toast.LENGTH_SHORT).show();
                updateUI(null);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        mFirebaseAuth.addAuthStateListener(authStateListener);
    }

    @Override
    protected void onStop() {
        super.onStop();

        if (authStateListener != null)
        {
            mFirebaseAuth.removeAuthStateListener(authStateListener);
        }


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return abdt.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
    }

    private void OpenActivity(Class<?> ActivityToOpen)
    {
        Intent intent = new Intent(getApplicationContext(), ActivityToOpen);
        startActivity(intent);
    }


    private void showChangeLanguageDialog() {
        final String[] listItems = {"English", "Spanish", "French", "Italian"};
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(SettingsActivity.this);
        mBuilder.setTitle("Choose Language...");
        mBuilder.setSingleChoiceItems(listItems, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                if (i == 0)
                {
                    setLocale("en");
                    recreate();
                }

                else if (i == 1)
                {
                    setLocale("es");
                    recreate();
                }

                else if (i == 2)
                {
                    setLocale("fr");
                    recreate();
                }

                else if (i == 3)
                {
                    setLocale("it");
                    recreate();
                }

                dialog.dismiss();
            }
        });

        AlertDialog mDialog = mBuilder.create();
        mDialog.show();
    }

    private void setLocale(String lang)
    {
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
        SharedPreferences.Editor editor = getSharedPreferences("Settings", MODE_PRIVATE).edit();
        editor.putString("My_Lang", lang);
        editor.apply();
    }

    public void loadLocale()
    {
        SharedPreferences prefs = getSharedPreferences("Settings", Activity.MODE_PRIVATE);
        String language = prefs.getString("My_Lang", "");
        setLocale(language);

    }



}