package com.example.kryguu.application;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.content.res.ConfigurationHelper;

/**
 * Created by kryguu on 19.11.2016.
 */

public class ClientDetailsPagerAdapter extends FragmentStatePagerAdapter {
    private Client mClient;
    private Context mContext;

    private final int CLIENT_DETAILS_PAGE = 0;
    private final int CONTACTS_DETAILS_PAGE = 1;

    public ClientDetailsPagerAdapter(FragmentManager fm, Context context, Client client) {
        super(fm);
        mClient = client;
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case CLIENT_DETAILS_PAGE:
                return ClientDetailsFragment.getInstance(mClient);
            case CONTACTS_DETAILS_PAGE:
                return ClientDetailsFragment.getInstance(mClient);
            default:
                return null;
        }
        //Client client = new Client("Asia", "Asia", "111");
        //ClientDetailsFragment cdf = ClientDetailsFragment.getInstance(client);
        //return null;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case CLIENT_DETAILS_PAGE:
                return mContext.getString(R.string.clients);
            case CONTACTS_DETAILS_PAGE:
                return mContext.getString(R.string.contacts);
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }
}
