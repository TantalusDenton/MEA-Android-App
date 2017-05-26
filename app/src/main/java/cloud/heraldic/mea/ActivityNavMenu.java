package cloud.heraldic.mea;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import cloud.heraldic.mea.R;

public class ActivityNavMenu extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    //private AdView mAdView;
    private static final String TAG = "GyroAxisActivity";
    public static AmazonClientManager clientManager = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_nav_menu);

        clientManager = new AmazonClientManager(this);

        /*final ToggleButton insertUsersBttn = (ToggleButton) findViewById(R.id.accelerometer_switch);
        insertUsersBttn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
                if (isChecked) {
                    Log.i(TAG, "insertUsersBttn clicked.");

                    new ActivityNavMenu.DynamoDBManagerTask()
                            .execute(DynamoDBManagerType.INSERT_USER);
                } else {
                    // The toggle is disabled
                }

            }
        });*/
        /*mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
                .build();
        mAdView.loadAd(adRequest);*/

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_nav_menu, menu);
        return true;
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        //****************insertUsersBttn toggle should probably go here...****************
        /*clientManager = new AmazonClientManager(this);

        final ToggleButton insertUsersBttn = (ToggleButton) findViewById(R.id.accelerometer_switch);
        insertUsersBttn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
                if (isChecked) {
                    Log.i(TAG, "insertUsersBttn clicked.");

                    new ActivityNavMenu.DynamoDBManagerTask()
                            .execute(DynamoDBManagerType.INSERT_USER);
                } else {
                    // The toggle is disabled
                }

            }
        });*/

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private class DynamoDBManagerTask extends
            AsyncTask<ActivityNavMenu.DynamoDBManagerType, Void, ActivityNavMenu.DynamoDBManagerTaskResult> {

        protected ActivityNavMenu.DynamoDBManagerTaskResult doInBackground(
                ActivityNavMenu.DynamoDBManagerType... types) {

            String tableStatus = DynamoDBManager.getTestTableStatus();

            ActivityNavMenu.DynamoDBManagerTaskResult result = new ActivityNavMenu.DynamoDBManagerTaskResult();
            result.setTableStatus(tableStatus);
            result.setTaskType(types[0]);

            if (types[0] == ActivityNavMenu.DynamoDBManagerType.CREATE_TABLE) {
                if (tableStatus.length() == 0) {
                    //DynamoDBManager.createTable();
                }
            } else if (types[0] == ActivityNavMenu.DynamoDBManagerType.INSERT_USER) {
                if (tableStatus.equalsIgnoreCase("ACTIVE")) {
                    DynamoDBManager.insertUsers();
                }
            } else if (types[0] == ActivityNavMenu.DynamoDBManagerType.LIST_USERS) {
                if (tableStatus.equalsIgnoreCase("ACTIVE")) {
                    //DynamoDBManager.getUserList();
                }
            } else if (types[0] == ActivityNavMenu.DynamoDBManagerType.CLEAN_UP) {
                if (tableStatus.equalsIgnoreCase("ACTIVE")) {
                    //DynamoDBManager.cleanUp();
                }
            }

            return result;
        }

        protected void onPostExecute(ActivityNavMenu.DynamoDBManagerTaskResult result) {

            if (result.getTaskType() == ActivityNavMenu.DynamoDBManagerType.CREATE_TABLE) {

                if (result.getTableStatus().length() != 0) {
                    Toast.makeText(
                            ActivityNavMenu.this,
                            "The test table already exists.\nTable Status: "
                                    + result.getTableStatus(),
                            Toast.LENGTH_LONG).show();
                }

            } else if (!result.getTableStatus().equalsIgnoreCase("ACTIVE")) {

                Toast.makeText(
                        ActivityNavMenu.this,
                        "The test table is not ready yet.\nTable Status: "
                                + result.getTableStatus(), Toast.LENGTH_LONG)
                        .show();
            } else if (result.getTableStatus().equalsIgnoreCase("ACTIVE")
                    && result.getTaskType() == ActivityNavMenu.DynamoDBManagerType.INSERT_USER) {
                Toast.makeText(ActivityNavMenu.this,
                        "Users inserted successfully!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private enum DynamoDBManagerType {
        GET_TABLE_STATUS, CREATE_TABLE, INSERT_USER, LIST_USERS, CLEAN_UP
    }

    private class DynamoDBManagerTaskResult {
        private ActivityNavMenu.DynamoDBManagerType taskType;
        private String tableStatus;

        public ActivityNavMenu.DynamoDBManagerType getTaskType() {
            return taskType;
        }

        public void setTaskType(ActivityNavMenu.DynamoDBManagerType taskType) {
            this.taskType = taskType;
        }

        public String getTableStatus() {
            return tableStatus;
        }

        public void setTableStatus(String tableStatus) {
            this.tableStatus = tableStatus;
        }
    }

    /*@Override
    public void onPause() {
        if (mAdView != null) {
            mAdView.pause();
        }
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mAdView != null) {
            mAdView.resume();
        }
    }

    @Override
    public void onDestroy() {
        if (mAdView != null) {
            mAdView.destroy();
        }
        super.onDestroy();
    }*/
}
