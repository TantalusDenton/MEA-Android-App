/*
 * Copyright 2010-2012 Amazon.com, Inc. or its affiliates. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License").
 * You may not use this file except in compliance with the License.
 * A copy of the License is located at
 *
 *  http://aws.amazon.com/apache2.0
 * 
 * or in the "license" file accompanying this file. This file is distributed
 * on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */

package cloud.heraldic.mea;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class GyroAxisActivity extends Activity {
/*
    private class DynamoDBManagerTask extends
            AsyncTask<GyroAxisActivity.DynamoDBManagerType, Void, GyroAxisActivity.DynamoDBManagerTaskResult> {

        protected GyroAxisActivity.DynamoDBManagerTaskResult doInBackground(
                GyroAxisActivity.DynamoDBManagerType... types) {

            String tableStatus = DynamoDBManager.getTestTableStatus();

            GyroAxisActivity.DynamoDBManagerTaskResult result = new GyroAxisActivity.DynamoDBManagerTaskResult();
            result.setTableStatus(tableStatus);
            result.setTaskType(types[0]);

            if (types[0] == GyroAxisActivity.DynamoDBManagerType.CREATE_TABLE) {
                if (tableStatus.length() == 0) {
                    //DynamoDBManager.createTable();
                }
            } else if (types[0] == GyroAxisActivity.DynamoDBManagerType.INSERT_USER) {
                if (tableStatus.equalsIgnoreCase("ACTIVE")) {
                    DynamoDBManager.insertUsers();
                }
            } else if (types[0] == GyroAxisActivity.DynamoDBManagerType.LIST_USERS) {
                if (tableStatus.equalsIgnoreCase("ACTIVE")) {
                    //DynamoDBManager.getUserList();
                }
            } else if (types[0] == GyroAxisActivity.DynamoDBManagerType.CLEAN_UP) {
                if (tableStatus.equalsIgnoreCase("ACTIVE")) {
                    //DynamoDBManager.cleanUp();
                }
            }

            return result;
        }

        protected void onPostExecute(GyroAxisActivity.DynamoDBManagerTaskResult result) {

            if (result.getTaskType() == GyroAxisActivity.DynamoDBManagerType.CREATE_TABLE) {

                if (result.getTableStatus().length() != 0) {
                    Toast.makeText(
                            GyroAxisActivity.this,
                            "The test table already exists.\nTable Status: "
                                    + result.getTableStatus(),
                            Toast.LENGTH_LONG).show();
                }

            } else if (!result.getTableStatus().equalsIgnoreCase("ACTIVE")) {

                Toast.makeText(
                        GyroAxisActivity.this,
                        "The test table is not ready yet.\nTable Status: "
                                + result.getTableStatus(), Toast.LENGTH_LONG)
                        .show();
            } else if (result.getTableStatus().equalsIgnoreCase("ACTIVE")
                    && result.getTaskType() == GyroAxisActivity.DynamoDBManagerType.INSERT_USER) {
                Toast.makeText(GyroAxisActivity.this,
                        "Users inserted successfully!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private enum DynamoDBManagerType {
        GET_TABLE_STATUS, CREATE_TABLE, INSERT_USER, LIST_USERS, CLEAN_UP
    }

    private class DynamoDBManagerTaskResult {
        private GyroAxisActivity.DynamoDBManagerType taskType;
        private String tableStatus;

        public GyroAxisActivity.DynamoDBManagerType getTaskType() {
            return taskType;
        }

        public void setTaskType(GyroAxisActivity.DynamoDBManagerType taskType) {
            this.taskType = taskType;
        }

        public String getTableStatus() {
            return tableStatus;
        }

        public void setTableStatus(String tableStatus) {
            this.tableStatus = tableStatus;
        }
    }*/
/*
    private static final String TAG = "GyroAxisActivity";
    public static AmazonClientManager clientManager = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        clientManager = new AmazonClientManager(this);

        final Button insertUsersBttn = (Button) findViewById(R.id.accelerometer_switch);
        insertUsersBttn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Log.i(TAG, "insertUsersBttn clicked.");

                new DynamoDBManagerTask()
                        .execute(DynamoDBManagerType.INSERT_USER);
            }
        });

    }

    private class DynamoDBManagerTask extends
            AsyncTask<DynamoDBManagerType, Void, DynamoDBManagerTaskResult> {

        protected DynamoDBManagerTaskResult doInBackground(
                DynamoDBManagerType... types) {

            String tableStatus = DynamoDBManager.getTestTableStatus();

            DynamoDBManagerTaskResult result = new DynamoDBManagerTaskResult();
            result.setTableStatus(tableStatus);
            result.setTaskType(types[0]);

            if (types[0] == DynamoDBManagerType.CREATE_TABLE) {
                if (tableStatus.length() == 0) {
                    //DynamoDBManager.createTable();
                }
            } else if (types[0] == DynamoDBManagerType.INSERT_USER) {
                if (tableStatus.equalsIgnoreCase("ACTIVE")) {
                    DynamoDBManager.insertUsers();
                }
            } else if (types[0] == DynamoDBManagerType.LIST_USERS) {
                if (tableStatus.equalsIgnoreCase("ACTIVE")) {
                    //DynamoDBManager.getUserList();
                }
            } else if (types[0] == DynamoDBManagerType.CLEAN_UP) {
                if (tableStatus.equalsIgnoreCase("ACTIVE")) {
                    //DynamoDBManager.cleanUp();
                }
            }

            return result;
        }

        protected void onPostExecute(DynamoDBManagerTaskResult result) {

            if (result.getTaskType() == DynamoDBManagerType.CREATE_TABLE) {

                if (result.getTableStatus().length() != 0) {
                    Toast.makeText(
                            GyroAxisActivity.this,
                            "The test table already exists.\nTable Status: "
                                    + result.getTableStatus(),
                            Toast.LENGTH_LONG).show();
                }

            } else if (!result.getTableStatus().equalsIgnoreCase("ACTIVE")) {

                Toast.makeText(
                        GyroAxisActivity.this,
                        "The test table is not ready yet.\nTable Status: "
                                + result.getTableStatus(), Toast.LENGTH_LONG)
                        .show();
            } else if (result.getTableStatus().equalsIgnoreCase("ACTIVE")
                    && result.getTaskType() == DynamoDBManagerType.INSERT_USER) {
                Toast.makeText(GyroAxisActivity.this,
                        "Users inserted successfully!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private enum DynamoDBManagerType {
        GET_TABLE_STATUS, CREATE_TABLE, INSERT_USER, LIST_USERS, CLEAN_UP
    }

    private class DynamoDBManagerTaskResult {
        private DynamoDBManagerType taskType;
        private String tableStatus;

        public DynamoDBManagerType getTaskType() {
            return taskType;
        }

        public void setTaskType(DynamoDBManagerType taskType) {
            this.taskType = taskType;
        }

        public String getTableStatus() {
            return tableStatus;
        }

        public void setTableStatus(String tableStatus) {
            this.tableStatus = tableStatus;
        }
    }*/
}
