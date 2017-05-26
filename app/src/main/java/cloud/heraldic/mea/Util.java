/*
 * Copyright 2015 Amazon.com, Inc. or its affiliates. All Rights Reserved.
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

import android.content.Context;
import android.net.Uri;

import com.amazonaws.auth.CognitoCachingCredentialsProvider;
import com.amazonaws.auth.policy.actions.EC2Actions;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.AmazonEC2Client;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.UUID;

/*
 * Handles basic helper functions used throughout the app.
 */
public class Util {

    // We only need one instance of the clients and credentials provider
    private static AmazonEC2Client sEC2Client;
    private static CognitoCachingCredentialsProvider sCredProvider;
    private static AmazonEC2 ec2Utility;

    /**
     * Gets an instance of CognitoCachingCredentialsProvider which is
     * constructed using the given Context.
     *
     * @param context An Context instance.
     * @return A default credential provider.
     */
    private static CognitoCachingCredentialsProvider getCredProvider(Context context) {
        if (sCredProvider == null) {
            sCredProvider = new CognitoCachingCredentialsProvider(
                    context.getApplicationContext(),
                    Constants.IDENTITY_POOL_ID,
                    Regions.US_EAST_1);
        }
        return sCredProvider;
    }

    /**
     * Gets an instance of a S3 client which is constructed using the given
     * Context.
     *
     * @param context An Context instance.
     * @return A default S3 client.
     */
    public static AmazonEC2Client getEC2Client(Context context) {
        if (sEC2Client == null) {
            sEC2Client = new AmazonEC2Client(getCredProvider(context.getApplicationContext()));
        }
        return sEC2Client;
    }
/* commented out because i think this function is not needed. i rewote this from s3 sample.
    public static AmazonEC2 getec2Utility(Context context) {
        if (ec2Utility == null) {
            ec2Utility = new AmazonEC2(getEC2Client(context.getApplicationContext()), //Instead of AmazonEC2 find another method that I'm supposed to use
                    context.getApplicationContext());
        }

        return ec2Utility;
    }
*/
}
