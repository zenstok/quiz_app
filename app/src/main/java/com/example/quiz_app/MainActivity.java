package com.example.quiz_app;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.TextView;

import com.example.quiz_app.activities.LoginActivity;
import com.example.quiz_app.fragments.NewQuizFragment;
import com.example.quiz_app.sqlite_db.DatabaseCreator;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public final DatabaseCreator databaseCreator = DatabaseCreator.getInstance(this.getApplication());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        setUserInfo(navigationView.getHeaderView(0));

    }

    @Override
    protected void onStart() {
        super.onStart();
        if(LoginActivity.mAuth.getCurrentUser() == null) {
            Intent goToLoginActivity = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(goToLoginActivity);
        }
    }

    private void setUserInfo(View headerView) {
        TextView emailNavHeader = headerView.findViewById(R.id.email_nav_header);
        TextView userTypeNavHeader = headerView.findViewById(R.id.user_type_nav_header);

        if(getIntent().getBooleanExtra("changeUserInfo", false) == true) {
            SharedPreferences userSharedPreferences = getApplicationContext().getSharedPreferences(getString(R.string.userSharedPreferences), Context.MODE_PRIVATE);
            String emailKey = getString(R.string.user_email);
            String userTypeKey = getString(R.string.user_type);

            emailNavHeader.setText(userSharedPreferences.getString(emailKey, "default@default.ro"));
            userTypeNavHeader.setText(userSharedPreferences.getString(userTypeKey, "Default"));
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {

        } else if (id == R.id.nav_new_quiz) {
            openNewQuizFragment();
        } else if (id == R.id.nav_quiz_list) {

        } else if (id == R.id.nav_student_list) {

        } else if (id == R.id.nav_user_settings) {

        } else if (id == R.id.nav_app_settings) {

        } else if (id == R.id.nav_app_logout) {
            LoginActivity.mAuth.signOut();
            Intent goToLoginActivity = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(goToLoginActivity);
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void openNewQuizFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fluid_container, new NewQuizFragment());
        transaction.commit();
    }

}
