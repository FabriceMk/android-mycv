package net.fabricemk.android.mycv.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.wearable.MessageApi;
import com.google.android.gms.wearable.Node;
import com.google.android.gms.wearable.Wearable;

import net.fabricemk.android.mycv.IHandheldCommunication;
import net.fabricemk.android.mycv.R;
import net.fabricemk.android.mycv.tools.UITools;

public class SendMessageToMobileFragment extends Fragment {

    private static final String GO_WEBSITE_WEAR_PATH = "/go-website-wear";
    private static final String SEND_EMAIL_WEAR_PATH = "/send-email-wear";

    private static final String KEY_PAYLOAD = "SendMessageToMobileFragment_payload";

    public static SendMessageToMobileFragment create(String payload) {
        SendMessageToMobileFragment fragment = new SendMessageToMobileFragment();
        Bundle args = new Bundle();

        if(!payload.isEmpty()) {
            args.putString(KEY_PAYLOAD, payload);
        }

        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.layout_action_button, container, false);

        ImageButton imageButtonView = (ImageButton) v.findViewById(R.id.action_button);
        TextView actionTitleView = (TextView) v.findViewById(R.id.action_title);

        imageButtonView.setImageResource(R.drawable.action_email);
        actionTitleView.setText(getString(R.string.compose_email));

        imageButtonView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage();
            }
        });
        
        return v;
    }

    private void sendMessage() {
        IHandheldCommunication provider = (IHandheldCommunication) getActivity();

        Node mNode = provider.getNode();
        GoogleApiClient mGoogleApiClient = provider.getGoogleApiClient();

        if (mNode != null && mGoogleApiClient != null && mGoogleApiClient.isConnected()) {
            Wearable.MessageApi.sendMessage(
                    mGoogleApiClient, mNode.getId(), SEND_EMAIL_WEAR_PATH, null).setResultCallback(

                    new ResultCallback<MessageApi.SendMessageResult>() {
                        @Override
                        public void onResult(MessageApi.SendMessageResult sendMessageResult) {

                            if (sendMessageResult.getStatus().isSuccess()) {
                                UITools.launchConfirmationAnimation(getActivity(), getString(R.string.confirmation_email));
                            } else {
                                Log.e("TAG", "Failed to send message with status code: "
                                        + sendMessageResult.getStatus().getStatusCode());
                            }

                        }
                    }
            );
        } else {
            //Improve your code
        }
    }
}
