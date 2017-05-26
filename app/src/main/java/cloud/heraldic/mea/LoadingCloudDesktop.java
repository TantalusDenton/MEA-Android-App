package cloud.heraldic.mea;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import java.io.IOException;
import java.io.InputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import java.util.logging.LogManager;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.CognitoCachingCredentialsProvider;
import com.amazonaws.auth.PropertiesCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.AmazonEC2Client;
import com.amazonaws.services.ec2.model.KeyPair;
import com.amazonaws.services.ec2.model.StartInstancesRequest;
import com.amazonaws.services.ec2.model.StopInstancesRequest;

public class LoadingCloudDesktop extends AppCompatActivity {

    static KeyPair keyPair;
    static int count = 1;

    //static AmazonEC2 ec2;

    private static final String TAG = "MEA_APP";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.loadingclouddesktop_layout);

        //ec2 = new AmazonEC2Client((AWSCredentials) getProperties()); //cast parameter as suggested by intellisense

       /*try {

            List<String> instanceIds = new LinkedList<String>();
            instanceIds.add("i-8731d210");

            //stop
            StopInstancesRequest stopIR = new StopInstancesRequest(instanceIds);
            //ec2.stopInstances(stopIR);

            //start
            StartInstancesRequest startIR = new StartInstancesRequest(instanceIds);
            ec2.startInstances(startIR);

            ec2.shutdown();


        } catch (AmazonServiceException ase) {
            System.out.println("Caught Exception: " + ase.getMessage());
            System.out.println("Reponse Status Code: " + ase.getStatusCode());
            System.out.println("Error Code: " + ase.getErrorCode());
            System.out.println("Request ID: " + ase.getRequestId());
        }*/

    }

    public void OnContinueClick(View view) {
        startActivity(new Intent(LoadingCloudDesktop.this, CloudDesktopView.class)); //continue development of this feature in the next release
    }

}