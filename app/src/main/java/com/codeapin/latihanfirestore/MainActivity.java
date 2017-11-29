package com.codeapin.latihanfirestore;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    private static final int RC_SIGN_IN = 101;
    private static final int LIMIT = 50;
    private RecyclerView recyclerContact;
    private FirebaseAuth mFirebaseAuth = FirebaseAuth.getInstance();
    private FirebaseFirestore mFirestore = FirebaseFirestore.getInstance();
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    private String mUserId;
    private ContactAdapter mAdapter;
    private Query mQuery;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerContact = findViewById(R.id.recycler_contact);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> {
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        });
        initFirebase();
        mAdapter = new ContactAdapter(mQuery);
        recyclerContact.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerContact.setAdapter(mAdapter);
    }

    private void initFirebase(){
        mAuthStateListener = firebaseAuth -> {
            if(firebaseAuth.getCurrentUser() == null){
                mUserId = null;
                Intent intent = AuthUI.getInstance().createSignInIntentBuilder()
                        .setAvailableProviders(
                                Collections.singletonList(new AuthUI.IdpConfig.Builder(AuthUI.GOOGLE_PROVIDER).build()))
                        .build();
                startActivityForResult(intent, RC_SIGN_IN);
            }else{
                mUserId = firebaseAuth.getCurrentUser().getUid();
            }
        };
        mQuery = mFirestore.collection("Users");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_sign_out:
                AuthUI.getInstance().signOut(this);
                return true;
        }
        return false;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == RC_SIGN_IN && resultCode == RESULT_OK){

        }else if(requestCode == RC_SIGN_IN && resultCode == RESULT_CANCELED){
            finish();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (mAdapter != null) {
            mAdapter.startListening();
        }
    }

    @Override
    protected void onResume() {
        mFirebaseAuth.addAuthStateListener(mAuthStateListener);
        super.onResume();
    }

    @Override
    protected void onPause() {
        mFirebaseAuth.removeAuthStateListener(mAuthStateListener);
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mAdapter != null) {
            mAdapter.stopListening();
        }
    }
}
