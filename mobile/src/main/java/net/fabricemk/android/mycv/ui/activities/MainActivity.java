package net.fabricemk.android.mycv.ui.activities;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.support.v4.widget.DrawerLayout;

import net.fabricemk.android.mycv.R;
import net.fabricemk.android.mycv.ui.fragments.CareerFragment;
import net.fabricemk.android.mycv.ui.fragments.ContactFragment;
import net.fabricemk.android.mycv.ui.fragments.SkillFragment;
import net.fabricemk.android.mycv.ui.fragments.TripFragment;

/**
 * The main activity of the application.
 * Is used to navigate through a {@link NavigationView} on phones
 * Will use a dedicated layout later on tablets.
 *
 * Implements the {@link IToolbarable} interface to let the fragments change the title of the main
 * toolbar
 */
public class MainActivity extends AppCompatActivity implements
        NavigationView.OnNavigationItemSelectedListener,
        IToolbarable {

    // NavigationView related constants
    private static final long DRAWER_CLOSE_DELAY_MS = 300;
    private static final String NAV_ITEM_ID = "navItemId";

    private final Handler mDrawerActionHandler = new Handler();
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private int mNavItemId;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        if (mDrawerLayout != null) {
            // Load saved navigation state if present
            if (null == savedInstanceState) {
                mNavItemId = R.id.drawer_contact;
            } else {
                mNavItemId = savedInstanceState.getInt(NAV_ITEM_ID);
            }

            // Listen for navigation events
            NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view);
            navigationView.setNavigationItemSelectedListener(this);

            // Select the correct nav menu item
            navigationView.getMenu().findItem(mNavItemId).setChecked(true);

            // Initialize the Toolbar
            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            setupToolbar(toolbar);

            // Load the selected fragment
            navigate(mNavItemId);
        }

    }

    @Override
    public void setupToolbar(Toolbar toolbar) {
        if (toolbar != null) {
            setSupportActionBar(toolbar);

            // Set up the hamburger icon to open and close the drawer
            // if a toolbar is present
            mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.open,
                    R.string.close);
            mDrawerLayout.setDrawerListener(mDrawerToggle);
            mDrawerToggle.syncState();
        }
    }

    /**
     * Navigation logic. Replaces the main content by a fragment
     * @param itemId the fragment id to navigate to
     */
    private void navigate(final int itemId) {

        if (findViewById(R.id.content) != null) {

            switch (itemId) {
                case R.id.drawer_contact:
                    Fragment contactFragment = new ContactFragment();
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.content, contactFragment).commit();
                    return;
                case R.id.drawer_career:
                    Fragment careerFragment = new CareerFragment();
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.content, careerFragment).commit();
                    return;
                case R.id.drawer_skills:
                    Fragment skillFragment = new SkillFragment();
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.content, skillFragment).commit();
                    return;
                case R.id.drawer_trips:
                    Fragment tripFragment = new TripFragment();
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.content, tripFragment).commit();
                    return;
                case R.id.drawer_about:
                    Intent intent = new Intent(this, AboutActivity.class);
                    startActivity(intent);
                    return;
            }

        }
    }

    @Override
    public boolean onNavigationItemSelected(final MenuItem menuItem) {
        // update highlighted item in the navigation menu
        menuItem.setChecked(true);
        mNavItemId = menuItem.getItemId();

        // allow some time after closing the drawer before performing real navigation
        // so the user can see what is happening
        mDrawerLayout.closeDrawer(GravityCompat.START);
        mDrawerActionHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                navigate(menuItem.getItemId());
            }
        }, DRAWER_CLOSE_DELAY_MS);
        return true;
    }

    @Override
    public void onConfigurationChanged(final Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        if (item.getItemId() == android.support.v7.appcompat.R.id.home) {
            return mDrawerToggle.onOptionsItemSelected(item);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onSaveInstanceState(final Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(NAV_ITEM_ID, mNavItemId);
    }

}
