package com.example.kryguu.application;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ClientFormActivity extends AppCompatActivity {

    @BindView(R.id.editTextName)
    EditText name;
    @BindView(R.id.editTextSurname)
    EditText surname;
    @BindView(R.id.editTextPhone)
    EditText phone;
    @BindView(R.id.editTextEmail)
    EditText email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_form);
        ButterKnife.bind(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_add_client, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_save:
                doSaveAction();
                break;
        }

        return true;
    }

    private void doSaveAction() {
        addNewClient();
    }

    private void addNewClient() {
        if(name.getText().toString().isEmpty() || surname.getText().toString().isEmpty() || phone.getText().toString().isEmpty() || email.getText().toString().isEmpty()) {
            AlertDialog.Builder builder = new AlertDialog.Builder(ClientFormActivity.this);
            builder.setTitle("Error");
            builder.setMessage("At least one field is empty.");
            builder.setPositiveButton("Back", null);
            builder.show();
        }
        else {
            Client client = new Client(
                    name.getText().toString(),
                    surname.getText().toString(),
                    phone.getText().toString(),
                    email.getText().toString());

            UserCredentials userCredentials = new UserCredentials("uzytkownik", "haslo1");
            CrmService crmService = ServiceManager.getCrmService();
            Call<Client> call = crmService.addClient(userCredentials.getmUsername(), userCredentials.getmPassword(), client);
            call.enqueue(addClient());

            /* LOCAL SQLITE
            Client client = new Client(
                    name.getText().toString(),
                    surname.getText().toString(),
                    phone.getText().toString(),
                    email.getText().toString());
            ClientDao cd = new ClientDao();
            cd.insertObject(client);
            Intent intent = new Intent();
            intent.putExtra(IntentExtras.CLIENT, client);
            setResult(Activity.RESULT_OK, intent);
            finish();
            */
        }
    }

    private Callback<Client> addClient() {
        return new Callback<Client>() {
            @Override
            public void onResponse(Call<Client> call, Response<Client> response) {
                if (response.isSuccessful()) {
                    finish();
                } else {

                }
            }

            @Override
            public void onFailure(Call<Client> call, Throwable t) {

            }
        };
    }

}
