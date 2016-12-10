package com.example.kryguu.application;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.StringDef;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.ButtonBarLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by kryguu on 19.11.2016.
 */

public class ClientDetailsFragment extends Fragment {

    private Client mClient;

    @BindView(R.id.textView1)
    protected TextView textViewName;
    @BindView(R.id.textView2)
    protected TextView textViewSurname;
    @BindView(R.id.textView3)
    protected TextView textViewPhone;
    @BindView(R.id.textView4)
    protected TextView textViewEmail;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getArguments();
        if (bundle != null) {
            mClient = bundle.getParcelable(IntentExtras.CLIENT);
        }
    }

    public static ClientDetailsFragment getInstance(Client client) {
        ClientDetailsFragment fragment = new ClientDetailsFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(IntentExtras.CLIENT, client);
        fragment.setArguments(bundle);

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.client_details, container, false);
        ButterKnife.bind(this, fragmentView);
        setUIComponents();

        return fragmentView;
    }

    private void setUIComponents() {
        textViewName.setText(mClient.getmName());
        textViewSurname.setText(mClient.getmSurname());
        textViewPhone.setText(mClient.getmPhone());
        textViewEmail.setText(mClient.getmEmail());
    }

    @OnClick(R.id.button_call)
    public void onClickCall() {
        String uri = String.format("tel:%s", textViewPhone.getText().toString());
        Intent callIntent = new Intent("android.intent.action.CALL", Uri.parse(uri));
        startActivity(callIntent);
    }

    @OnClick(R.id.button_locate)
    public void onClickLocate() {

    }


}
