package com.andyapp.prakashproducts;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.andyapp.prakashproducts.Fragments.HomeFragment;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private Toolbar mtoolbar;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mtoggle;
    private NavigationView mnavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mtoolbar = (Toolbar) findViewById(R.id.toolbar);
        mnavigationView = (NavigationView) findViewById(R.id.nav_view);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mtoolbar.setTitle("");
        setSupportActionBar(mtoolbar);
        mtoggle = new ActionBarDrawerToggle(this, mDrawerLayout, mtoolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.addDrawerListener(mtoggle);
        mtoggle.syncState();

        getSupportFragmentManager().beginTransaction().add(R.id.content, new HomeFragment(), HomeFragment.TAG).commit();


        mnavigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.nav_home) {
            HomeFragment homeFragment = new HomeFragment();
            getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
            getSupportFragmentManager().beginTransaction().replace(R.id.content, homeFragment, HomeFragment.TAG).commit();

        } else if (id == R.id.nav_aboutUs) {
            showAboutUsDialog();
        } else if (id == R.id.nav_contactUs) {
            showContactUs();
        } else if (id == R.id.nav_rateUs) {
            intentRateUs();
        } else if (id == R.id.nav_share) {
            intentShare();
        }
//        else if (id == R.id.nav_logout) {
//            AppController.getInstance().logOut();
//            startActivity(new Intent(this, SplashScreen.class));
//        }

        if (mDrawerLayout.isDrawerOpen(GravityCompat.START))
        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else if (getSupportFragmentManager().getBackStackEntryCount() == 0)
            showAlertDialog();
        else
            getSupportFragmentManager().popBackStackImmediate();
    }

    public Toolbar getToolbar() {
        return mtoolbar;
    }

    private void showAlertDialog() {
        AlertDialog alertDialog;

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Conformation");
        builder.setMessage("Are you sure want to exit");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                HomeActivity.super.onBackPressed();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        alertDialog = builder.create();
        alertDialog.show();
    }

    private void showAboutUsDialog() {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.content_aboutus);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        mDrawerLayout.closeDrawer(GravityCompat.START);
        dialog.show();
    }

    private void showContactUs() {
        try {
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plan");
            shareIntent.putExtra(Intent.EXTRA_EMAIL,new String[] { "omprakashg050@gmail.com"});
            shareIntent.setPackage("com.google.android.gm");
            startActivity(shareIntent);
        }catch (Exception e){
            Snackbar.make(mtoolbar,"Gmail not found", Snackbar.LENGTH_SHORT).show();
        }

    }

    private void intentRateUs() {
        Intent rateUsIntent = new Intent(Intent.ACTION_VIEW,
                Uri.parse(getResources().getString(R.string.app_link)));
        startActivity(rateUsIntent);
    }

    private void intentShare() {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/*");
        shareIntent.putExtra(Intent.EXTRA_TEXT, getResources().getString(R.string.share_caption) +
                "\n" + getResources().getString(R.string.app_link));
        startActivity(shareIntent);
    }
}
