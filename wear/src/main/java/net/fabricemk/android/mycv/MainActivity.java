package net.fabricemk.android.mycv;

import android.os.Bundle;
import android.support.wearable.activity.WearableActivity;
import android.support.wearable.view.BoxInsetLayout;
import android.support.wearable.view.DotsPageIndicator;
import android.support.wearable.view.GridViewPager;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.wearable.CapabilityApi;
import com.google.android.gms.wearable.CapabilityInfo;
import com.google.android.gms.wearable.Node;
import com.google.android.gms.wearable.Wearable;

import net.fabricemk.android.mycv.ui.adapters.MyGridPagerAdapter;

import java.util.Set;

/*
 * The main activity of the Wear companion app
 */
public class MainActivity extends WearableActivity
        implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        IHandheldCommunication {

    private BoxInsetLayout mContainerView;

    Node mNode; // the best device to send the message to

    GoogleApiClient mGoogleApiClient;
    private boolean mResolvingError=false;

    private static final long CONNECTION_TIME_OUT_MS = 2000;

    // A code used by both the wear companion app and the mobile app to authorize
    // the Wear app to do some actions on the paired mobile
    private static final String SEND_ME_EMAIL = "send_me_email";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setAmbientEnabled();

        mContainerView = (BoxInsetLayout) findViewById(R.id.container);

        final GridViewPager pager = (GridViewPager) findViewById(R.id.pager);
        pager.setAdapter(new MyGridPagerAdapter(this, getFragmentManager()));

        // We use a dots indicator as we have several columns
        DotsPageIndicator dots = (DotsPageIndicator) findViewById(R.id.indicator);
        dots.setPager(pager);

        //Connect the GoogleApiClient
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(Wearable.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();
    }

    @Override
    public void onEnterAmbient(Bundle ambientDetails) {
        super.onEnterAmbient(ambientDetails);
        updateDisplay();
    }

    @Override
    public void onUpdateAmbient() {
        super.onUpdateAmbient();
        updateDisplay();
    }

    @Override
    public void onExitAmbient() {
        updateDisplay();
        super.onExitAmbient();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (!mResolvingError) {
            mGoogleApiClient.connect();
        }
    }

    private void updateDisplay() {
        if (isAmbient()) {
            mContainerView.setBackgroundColor(getResources().getColor(android.R.color.black));
        } else {
            mContainerView.setBackground(null);
        }
    }

    /*
     * Analyzes if the Wear app can use a paired devide to send mails
     */
    private void setupEmailCapabilities() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                CapabilityApi.GetCapabilityResult result =
                        Wearable.CapabilityApi.getCapability(
                                mGoogleApiClient, SEND_ME_EMAIL,
                                CapabilityApi.FILTER_REACHABLE).await();

                updateEmailCapability(result.getCapability());

                CapabilityApi.CapabilityListener capabilityListener =
                        new CapabilityApi.CapabilityListener() {
                            @Override
                            public void onCapabilityChanged(CapabilityInfo capabilityInfo) {
                                updateEmailCapability(capabilityInfo);
                            }
                        };

                Wearable.CapabilityApi.addCapabilityListener(
                        mGoogleApiClient,
                        capabilityListener,
                        SEND_ME_EMAIL);

            }
        }).start();

    }

    /**
     * Lists all the paired devices and launch the selection of the best one
     * @param capabilityInfo
     */
    private void updateEmailCapability(CapabilityInfo capabilityInfo) {
        Set<Node> connectedNodes = capabilityInfo.getNodes();
        mNode = pickBestNode(connectedNodes);
    }

    /**
     * Select the best paired device able to send a mail
     */
    private Node pickBestNode(Set<Node> nodes) {
        Node bestNode = null;
        // Find a nearby node or pick one arbitrarily
        for (Node node : nodes) {
            if (node.isNearby()) {
                return node;
            }
            bestNode = node;
        }
        return bestNode;
    }

    /*
     * Google API Client related
     */
    @Override
    public void onConnected(Bundle bundle) {
        setupEmailCapabilities();
    }

    @Override
    public void onConnectionSuspended(int i) {
        //Improve your code
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        //Improve your code
    }


    @Override
    public GoogleApiClient getGoogleApiClient() {
        return mGoogleApiClient;
    }

    @Override
    public Node getNode() {
        return mNode;
    }
}
