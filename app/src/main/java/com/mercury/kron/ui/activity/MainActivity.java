package com.mercury.kron.ui.activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.mercury.kron.R;


import com.mercury.kron.ui.activity.tamplets.LoginPartnerActivity;
import com.mercury.kron.utils.RoundedBitmap;

/**
 * Основной класс приложения
 */

public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener{
    private DrawerLayout mNavigationDrawer;
    private Toolbar mToolbar;
    public ProgressDialog mProgressDialog;
    private boolean isPartner = false;
    public static final String EXTRA_BOOLEAN = "boolean" ;
    private RelativeLayout mRelativeLayout;
    private  NavigationView mNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRelativeLayout = (RelativeLayout) findViewById(R.id.relative_layout_main);
        isPartner = getIntent().getBooleanExtra(LoginActivity.EXTRA_BOOLEAN,false);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mNavigationDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
       mNavigationView = (NavigationView) findViewById(R.id.navigation_view);


        setStyleForPartner(isPartner);
        setupToolBar();
        setupDrower();



    }



    public void showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setMessage(getString(R.string.loading));
            mProgressDialog.setIndeterminate(true);
        }

        mProgressDialog.show();
    }

    private void setupDrower() {

        //navigationView.setCheckedItem(R.id.drawer_tr);
        mNavigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        if (mNavigationDrawer.isDrawerOpen(GravityCompat.START)) {
            mNavigationDrawer.closeDrawer(GravityCompat.START);
        } else {
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
        if (item.getItemId()==android.R.id.home){
            mNavigationDrawer.openDrawer(GravityCompat.START);
            setNavigationDrawerIcon();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Intent intent;
        switch (item.getItemId()){
            case R.id.drawer_payment:
                intent = new Intent(this,PaymentsActivity.class);
                startActivity(intent);
                break;
            case R.id.drawer_auto:
                intent = new Intent(this,AutoActivity.class);
                intent.putExtra("boolean",isPartner);
                startActivity(intent);
                break;
            case R.id.ship:

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Недоступно")
                        .setMessage("Модуль в разработке")
                        .setCancelable(false)
                        .setNegativeButton("ОК",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                });
                AlertDialog alert = builder.create();
                alert.show();
                break;
            case R.id.food:
                AlertDialog.Builder builder2 = new AlertDialog.Builder(MainActivity.this);
                builder2.setTitle("Недоступно")
                        .setMessage("Модуль в разработке")
                        .setCancelable(false)
                        .setNegativeButton("ОК",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                });
                AlertDialog alert2 = builder2.create();
                alert2.show();
                break;
            case R.id.zayavki:
                AlertDialog.Builder builder1 = new AlertDialog.Builder(MainActivity.this);
                builder1.setTitle("Недоступно")
                        .setMessage("Модуль в разработке")
                        .setCancelable(false)
                        .setNegativeButton("ОК",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                });
                AlertDialog alert1 = builder1.create();
                alert1.show();
                break;
            case R.id.exit:
                Intent intent1 = new Intent(this,LoginActivity.class);
                intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent1);
                break;

        }
        mNavigationDrawer.closeDrawer(GravityCompat.START);
        return false;
    }

    private void setNavigationDrawerIcon(){
        ImageView imageView=(ImageView) findViewById(R.id.drawer_photo);
        //imageView.setImageResource(R.drawable.userphoto);
        imageView.setImageBitmap(new RoundedBitmap(
                BitmapFactory.decodeResource(this.getResources(), R.drawable.logo_man))
                .getRoundedBitmap());
    }
    private void setStyleForPartner( boolean isChecked){
        LinearLayout linearLayout = new LinearLayout(this);
        if(isChecked) {
            int contextCompat = ContextCompat.getColor(MainActivity.this, R.color.app_partner_background);
            mRelativeLayout.setBackgroundColor(contextCompat);
            mToolbar.setBackgroundColor(contextCompat);
            mNavigationDrawer.setBackgroundColor(contextCompat);
            mNavigationView.setBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.app_partner_background));

            View layout = getLayoutInflater().inflate(R.layout.nav_head_main_drawer,linearLayout,false);

             TextView textView =(TextView) layout.findViewById(R.id.drawer_client_fio);
             textView.setText(R.string.namePartner);
            mNavigationView.addHeaderView(layout);
            getApplication().setTheme(R.style.Partner_AppTheme_NoActionBar);


        } else {
            int contextCompat = ContextCompat.getColor(MainActivity.this, R.color.app_main_background);
            mRelativeLayout.setBackgroundColor(contextCompat);
            mToolbar.setBackgroundColor(contextCompat);
            mNavigationDrawer.setBackgroundColor(contextCompat);
            mNavigationView.setBackgroundColor(contextCompat);

            View layout = getLayoutInflater().inflate(R.layout.nav_head_main_drawer,linearLayout,false);
            getApplication().setTheme(R.style.AppTheme_NoActionBar);
            TextView textView =(TextView) layout.findViewById(R.id.drawer_client_fio);
            textView.setText(R.string.nameClient);
            mNavigationView.addHeaderView(layout);
        }

    }

}
