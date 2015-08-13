package net.fabricemk.android.mycv;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.wearable.Node;

public interface IHandheldCommunication {

    public GoogleApiClient getGoogleApiClient();

    public Node getNode();
}
