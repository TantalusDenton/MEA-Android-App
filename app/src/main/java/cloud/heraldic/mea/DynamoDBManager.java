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

import android.util.Log;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBAttribute;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBHashKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBMapper;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBTable;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.model.DescribeTableRequest;
import com.amazonaws.services.dynamodbv2.model.DescribeTableResult;
import com.amazonaws.services.dynamodbv2.model.ResourceNotFoundException;

/*   NOTE BY TANTALUS:
*   ANY reference to DinosaVRdesktop must be changed if you move your script that
*   reads gyro and updates Dynamodb
*   to another activity other than DinosaVRdesktop.
* */

public class DynamoDBManager {

    private static final String TAG = "DynamoDBManager";
    //private static String Xvalue = MainMenu.DeviceAxisX;
    //private static String Yvalue = MainMenu.DeviceAxisY;
    //private static String Zvalue = MainMenu.DeviceAxisZ;

    /*
     * Retrieves the table description and returns the table status as a string.
     */
    public static String getTestTableStatus() {

        try {
            AmazonDynamoDBClient ddb = DinosaVRdesktop.clientManager
                    .ddb();

            DescribeTableRequest request = new DescribeTableRequest()
                    .withTableName(Constants.TEST_TABLE_NAME);
            DescribeTableResult result = ddb.describeTable(request);

            String status = result.getTable().getTableStatus();
            return status == null ? "" : status;

        } catch (ResourceNotFoundException e) {
        } catch (AmazonServiceException ex) {
            DinosaVRdesktop.clientManager
                    .wipeCredentialsOnAuthError(ex);
        }

        return "";
    }

    /*
     * Inserts ten users with userNo from 1 to 10 and random names.
     */
    public static void insertUsers() {
        AmazonDynamoDBClient ddb = DinosaVRdesktop.clientManager
                .ddb();
        DynamoDBMapper mapper = new DynamoDBMapper(ddb);

        try {
                GyroAxis GyroAxis = mapper.load(GyroAxis.class, 1);
                //GyroAxis.setSessionID("1");
                //GyroAxis.setXaxis(Xvalue);
                //GyroAxis.setYaxis(Yvalue);
                //GyroAxis.setZaxis(Zvalue);

                Log.d(TAG, "Inserting users");
                mapper.save(GyroAxis);
                Log.d(TAG, "Users inserted");

        } catch (AmazonServiceException ex) {
            Log.e(TAG, "Error inserting users");
            DinosaVRdesktop.clientManager
                    .wipeCredentialsOnAuthError(ex);
        }
    }

    /*
     * Retrieves all of the attribute/value pairs for the specified user.
     */
    public static GyroAxis getGyroAxis(int userNo) {

        AmazonDynamoDBClient ddb = DinosaVRdesktop.clientManager
                .ddb();
        DynamoDBMapper mapper = new DynamoDBMapper(ddb);

        try {
            GyroAxis GyroAxis = mapper.load(GyroAxis.class,
                    userNo);

            return GyroAxis;

        } catch (AmazonServiceException ex) {
            DinosaVRdesktop.clientManager
                    .wipeCredentialsOnAuthError(ex);
        }

        return null;
    }

    /*
     * Updates one attribute/value pair for the specified user.
     */
    public static void updateGyroAxis(GyroAxis updateGyroAxis) {

        AmazonDynamoDBClient ddb = DinosaVRdesktop.clientManager
                .ddb();
        DynamoDBMapper mapper = new DynamoDBMapper(ddb);

        try {
            mapper.save(updateGyroAxis);

        } catch (AmazonServiceException ex) {
            DinosaVRdesktop.clientManager
                    .wipeCredentialsOnAuthError(ex);
        }
    }

    @DynamoDBTable(tableName = Constants.TEST_TABLE_NAME)
    public static class GyroAxis {
        private int SessionID;
        private String Xaxis;
        private String Yaxis;
        private String Zaxis;

        @DynamoDBHashKey(attributeName = "SessionID")
        public int getSessionID() {
            return SessionID;
        }

        public void setSessionID(int SessionID) {
            this.SessionID = SessionID;
        }

        @DynamoDBAttribute(attributeName = "Xaxis")
        public String getXaxis() {
            return Xaxis;
        }

        public void setXaxis(String Xaxis) {
            this.Xaxis = Xaxis;
        }

        @DynamoDBAttribute(attributeName = "Yaxis")
        public String getYaxis() { return Yaxis; }

        public void setYaxis(String Yaxis) { this.Yaxis = Yaxis; }

        @DynamoDBAttribute(attributeName = "Zaxis")
        public String getZaxis() {
            return Zaxis;
        }

        public void setZaxis(String Zaxis) {
            this.Zaxis = Zaxis;
        }

    }
}
