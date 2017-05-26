package cloud.heraldic.mea;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.net.Uri;
import android.widget.Button;
import android.widget.Toast;

import com.amazonaws.auth.CognitoCachingCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

//import android.hardware.SensorEventListener;
//import android.hardware.Sensor;
//import android.hardware.SensorEvent;
//import android.hardware.SensorManager;
//import android.content.Context;                          //auto imported. Tutorial didnt mention it

public class MainMenu extends AppCompatActivity /*implements SensorEventListener*/{

    /*private static String DeviceAxisX, DeviceAxisY, DeviceAxisZ;
    private SensorManager senSensorManager;
    private Sensor senAccelerometer;
    private DynamoDBManager.GyroAxis ThisDevicesAxes= new DynamoDBManager.GyroAxis();*/
    private FirebaseAnalytics mFirebaseAnalytics;
    //private AdView mAdView;
    public static final String WEBSITE_URL = "https://www.heraldic.cloud/MEA/";
    //public static AmazonClientManager clientManager = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        /*senSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        senAccelerometer = senSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        senSensorManager.registerListener(this, senAccelerometer , SensorManager.SENSOR_DELAY_NORMAL); //needs to be much more often probably

        clientManager = new AmazonClientManager(this);*/

        /*final Button insertUsersBttn = (Button) findViewById(R.id.GyroTest);
        insertUsersBttn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Log.i(TAG, "insertUsersBttn clicked.");

                new DynamoDBManagerTask()
                        .execute(DynamoDBManagerType.INSERT_USER);
            }
        });*/   //This was for testing, dont need this button in prod

        /*mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
                .build();
        mAdView.loadAd(adRequest);*/

        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        //added to log in to AWS through Cognito...
        /*CognitoCachingCredentialsProvider credentialsProvider = new CognitoCachingCredentialsProvider(
                getApplicationContext(),
                "us-west-2:3f976d17-3069-4d7d-8806-feca8c46ab7e", // Identity Pool ID
                Regions.US_WEST_2 // Region
        );*/
    }

    /*public void onSensorChanged(SensorEvent sensorEvent) {
        Sensor mySensor = sensorEvent.sensor;

        if (mySensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            DeviceAxisX = Float.toString(sensorEvent.values[0]);
            DeviceAxisY = Float.toString(sensorEvent.values[1]);
            DeviceAxisZ = Float.toString(sensorEvent.values[2]);
            ThisDevicesAxes.setXaxis(DeviceAxisX);
            ThisDevicesAxes.setYaxis(DeviceAxisY);
            ThisDevicesAxes.setZaxis(DeviceAxisZ);
            new UpdateAttributeTask().execute();
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    //Nothing, this method needs to be here only for "inplements SensorEventListener"...
    }*/

    public void OnLoginClick(View view) {
        startActivity(new Intent(MainMenu.this, DinosaVRdesktop.class)); //continue development of this feature in the next release
    }

    public void OnTryNewbieClick(View view) {
        startActivity(new Intent(MainMenu.this, LoadingCloudDesktop.class));
    }

    public void onWebsiteClick(View view) {
        //startActivity(new Intent(MainActivity.this, ViewHomePageActivity.class));
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(WEBSITE_URL));
        startActivity(intent);
    }

    public void onTechroniclesClick(View view) {
        //startActivity(new Intent(MainActivity.this, ViewHomePageActivity.class));
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("http://techronicles.heraldic.cloud/"));
        startActivity(intent);
    }

    public void onShopClientsClick(View view) {
        //startActivity(new Intent(MainActivity.this, ViewHomePageActivity.class));
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("https://www.heraldic.cloud/MEA/shop/"));
        startActivity(intent);
    }

    public void onFacebookClick(View view) {
        //startActivity(new Intent(MainActivity.this, ViewHomePageActivity.class));
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("https://www.facebook.com/HeraldicClouds/"));
        startActivity(intent);
    }

    public void onInstagramClick(View view) {
        //startActivity(new Intent(MainActivity.this, ViewHomePageActivity.class));
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("https://www.instagram.com/heraldic.cloud/"));
        startActivity(intent);
    }

    public void onTwitterClick(View view) {
        //startActivity(new Intent(MainActivity.this, ViewHomePageActivity.class));
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("https://twitter.com/HeraldicClouds/"));
        startActivity(intent);
    }

    public void onVKClick(View view) {
        //startActivity(new Intent(MainActivity.this, ViewHomePageActivity.class));
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("https://vk.com/heraldicclouds/"));
        startActivity(intent);
    }

    /*private class DynamoDBManagerTask extends
            AsyncTask<MainMenu.DynamoDBManagerType, Void, MainMenu.DynamoDBManagerTaskResult> {

        protected MainMenu.DynamoDBManagerTaskResult doInBackground(
                MainMenu.DynamoDBManagerType... types) {

            String tableStatus = DynamoDBManager.getTestTableStatus();

            MainMenu.DynamoDBManagerTaskResult result = new MainMenu.DynamoDBManagerTaskResult();
            result.setTableStatus(tableStatus);
            result.setTaskType(types[0]);

            if (types[0] == MainMenu.DynamoDBManagerType.CREATE_TABLE) {
                if (tableStatus.length() == 0) {
                    //DynamoDBManager.createTable();
                }
            } else if (types[0] == MainMenu.DynamoDBManagerType.INSERT_USER) {
                if (tableStatus.equalsIgnoreCase("ACTIVE")) {
                    DynamoDBManager.insertUsers();
                }
            } else if (types[0] == MainMenu.DynamoDBManagerType.LIST_USERS) {
                if (tableStatus.equalsIgnoreCase("ACTIVE")) {
                    //DynamoDBManager.getUserList();
                }
            } else if (types[0] == MainMenu.DynamoDBManagerType.CLEAN_UP) {
                if (tableStatus.equalsIgnoreCase("ACTIVE")) {
                    //DynamoDBManager.cleanUp();
                }
            }

            return result;
        }

        protected void onPostExecute(MainMenu.DynamoDBManagerTaskResult result) {

            if (result.getTaskType() == MainMenu.DynamoDBManagerType.CREATE_TABLE) {

                if (result.getTableStatus().length() != 0) {
                    Toast.makeText(
                            MainMenu.this,
                            "The test table already exists.\nTable Status: "
                                    + result.getTableStatus(),
                            Toast.LENGTH_LONG).show();
                }

            } else if (!result.getTableStatus().equalsIgnoreCase("ACTIVE")) {

                Toast.makeText(
                        MainMenu.this,
                        "The test table is not ready yet.\nTable Status: "
                                + result.getTableStatus(), Toast.LENGTH_LONG)
                        .show();
            } else if (result.getTableStatus().equalsIgnoreCase("ACTIVE")
                    && result.getTaskType() == MainMenu.DynamoDBManagerType.INSERT_USER) {
                Toast.makeText(MainMenu.this,
                        "Users inserted successfully!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private enum DynamoDBManagerType {
        GET_TABLE_STATUS, CREATE_TABLE, INSERT_USER, LIST_USERS, CLEAN_UP
    }

    private class DynamoDBManagerTaskResult {
        private MainMenu.DynamoDBManagerType taskType;
        private String tableStatus;

        public MainMenu.DynamoDBManagerType getTaskType() {
            return taskType;
        }

        public void setTaskType(MainMenu.DynamoDBManagerType taskType) {
            this.taskType = taskType;
        }

        public String getTableStatus() {
            return tableStatus;
        }

        public void setTableStatus(String tableStatus) {
            this.tableStatus = tableStatus;
        }
    }

    private class UpdateAttributeTask extends AsyncTask<Void, Void, Void> {

        protected Void doInBackground(Void... voids) {

            DynamoDBManager.updateGyroAxis(ThisDevicesAxes);

            return null;
        }
    }*/

    @Override
    public void onPause() {
        //senSensorManager.unregisterListener(this);
        //AdMod stuff starts
        /*if (mAdView != null) {
            mAdView.pause();
        }*/ //AdMob stuff ends
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        //senSensorManager.registerListener(this, senAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        //AdMod stuff starts
        /*if (mAdView != null) {
            mAdView.resume();
        }*/ //AdMob stuff ends
    }
/*
    @Override
    public void onDestroy() {
        if (mAdView != null) {
            mAdView.destroy();
        }
        super.onDestroy();
    }*/
}
