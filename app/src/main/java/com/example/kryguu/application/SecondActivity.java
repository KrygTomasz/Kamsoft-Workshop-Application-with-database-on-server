package com.example.kryguu.application;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SecondActivity extends AppCompatActivity {

    private ListView mListViewClients;
    private List<Client> mClientsList;
    private ClientListAdapter clientAdapter;
    private static final int REQUEST_CODE = 333;

    ClientDao clientDao = new ClientDao();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        mListViewClients = (ListView) findViewById(R.id.listViewClient);

        initClientDatabase();
        //initAdapter();
        ButterKnife.bind(this);
    }

    private void initClientDatabase() {

        UserCredentials userCredentials = new UserCredentials("uzytkownik", "haslo1");
        CrmService crm = ServiceManager.getCrmService();
        Call<List<Client>> login = crm.clientList(userCredentials.getmUsername(),userCredentials.getmPassword());
        login.enqueue(getClients());

        //LOCAL SQLITE
        //mClientsList = clientDao.getClientsList();


        //SIMPLE GENERATE - don't use - only for test
//        mClientsList = new ArrayList<Client>();
//        for (int i = 0; i < 10; i++) {
//            Client c1 = new Client("Jan", "Kowalski", "505344142", "mail");
//            mClientsList.add(c1);
//        }
    }

    private void initAdapter() {
        clientAdapter = new ClientListAdapter(mClientsList);
        mListViewClients.setAdapter(clientAdapter);
    }

    public void onAddClientClick(View v) {
        Intent intent = new Intent(this, ClientFormActivity.class);
        startActivityForResult(intent, REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (REQUEST_CODE == requestCode && Activity.RESULT_OK == resultCode && data != null) {
            Client client = data.getExtras().getParcelable(IntentExtras.CLIENT);
            mClientsList.add(client);
            clientAdapter.notifyDataSetChanged();
            //clientDao.insertObject(client);
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    @OnItemClick(R.id.listViewClient)
    public void onListViewClientsItemClick(int position) {
        Client client = (Client)clientAdapter.getItem(position);

        Intent intent = new Intent(SecondActivity.this, DetailsActivity.class);
        intent.putExtra(IntentExtras.CLIENT, client);

        startActivity(intent);
    }

    @OnClick(R.id.floating_action_button_add_client)
    public void onFloatingClick() {
        Intent intent = new Intent(SecondActivity.this, ClientFormActivity.class);
        startActivityForResult(intent, REQUEST_CODE);
    }

    private Callback<List<Client>> getClients() {
        return new Callback<List<Client>>() {
            @Override
            public void onResponse(Call<List<Client>> call, Response<List<Client>> response) {
                if (response.isSuccessful()) {

                    mClientsList = response.body();
                    clientAdapter = new ClientListAdapter(mClientsList);
                    mListViewClients.setAdapter(clientAdapter);

                } else {
                    // Status 4XX
                }
            }

            @Override
            public void onFailure(Call<List<Client>> call, Throwable t) {

            }
        };
    }
}
