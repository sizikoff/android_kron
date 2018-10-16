package com.mercury.kron.ui.activity;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.mercury.kron.R;


import com.mercury.kron.utils.RoundedBitmap;

import java.util.ArrayList;

public class PaymentsActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {

    View.OnClickListener mAddButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(PaymentsActivity.this, InsPayCardActivity.class);
            startActivity(intent);
        }
    };
    private DrawerLayout mNavigationDrawer;
    private Toolbar mToolbar;

    private Button mAddCardButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    private void setupDrower() {
        NavigationView navigationView = findViewById(R.id.navigation_view);
        //navigationView.setCheckedItem(R.id.drawer_tr);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        if (mNavigationDrawer.isDrawerOpen(GravityCompat.START)) {
            mNavigationDrawer.closeDrawer(GravityCompat.START);
        } else{
            super.onBackPressed();
        }
    }

    // устанавливаем толбар
    private void setupToolBar() {
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar!=null){
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            mNavigationDrawer.openDrawer(GravityCompat.START);
            setNavigationDrawerIcon();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Intent intent;
        switch (item.getItemId()) {
            case R.id.drawer_payment:
                break;
            case R.id.drawer_auto:
                intent = new Intent(this, AutoActivity.class);
                startActivity(intent);
                break;

        }
        mNavigationDrawer.closeDrawer(GravityCompat.START);
        return false;
    }

    private void setNavigationDrawerIcon() {
        ImageView imageView = findViewById(R.id.drawer_photo);
        //imageView.setImageResource(R.drawable.userphoto);
        imageView.setImageBitmap(new RoundedBitmap(
                BitmapFactory.decodeResource(this.getResources(), R.drawable.logo_man))
                .getRoundedBitmap());
    }


}
