package net.fabricemk.android.mycv;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.wearable.Node;

/**
 * An interface implemented by the classes which handles the connectivity with the paired devices
 * and the Google ApiClient in order to provide some services to other classes
 */
public interface IHandheldCommunication {

    /**
     * Gets the Google API Client
     * @return a Google API Client
     */
    GoogleApiClient getGoogleApiClient();

    /**
     * Gets a paired node
     * @return a oaired node
     */
    Node getNode();
}
