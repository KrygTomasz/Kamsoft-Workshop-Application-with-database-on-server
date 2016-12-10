package com.example.kryguu.application;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    EditText editTextUsername;
    EditText editTextPassword;
    Button buttonLogIn;
    Button buttonRegister;
    private UserCredentials mUserCredentials;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUiComponents();

        buttonLogIn.setOnClickListener(getOnButtonLogInClick());
    }

    private View.OnClickListener getOnButtonLogInClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = editTextUsername.getText().toString();
                String password = editTextPassword.getText().toString();

                if(userName.isEmpty() || password.isEmpty()){
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("Error");
                    builder.setMessage("At least one field is empty.");
                    builder.setPositiveButton("Back", null);
                    builder.show();
                }
                else {
                    mUserCredentials = new UserCredentials(userName, password);
                    CrmService crmService = ServiceManager.getCrmService();
                    Call<Void> login = crmService.login(mUserCredentials);
                    login.enqueue(getLoginCallback());
                    HttpLoggingInterceptor logging = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                        @Override public void log(String message) {
                            Log.d("MyTAG", "OkHttp: " + message);
                        }
                    });
                    logging.setLevel(HttpLoggingInterceptor.Level.BODY);
                }
                /*else {
                    Intent menuIntent = new Intent(MainActivity.this, SecondActivity.class);
                    menuIntent.putExtra(IntentExtras.USERNAME, userName);
                    menuIntent.putExtra(IntentExtras.PASSWORD, password);
                    startActivity(menuIntent);

                    finish();
                }*/
            }
        };
    }

    private void initUiComponents () {
        editTextUsername = (EditText) findViewById(R.id.editTextLogin);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        buttonLogIn = (Button) findViewById(R.id.buttonLogin);
        buttonRegister = (Button) findViewById(R.id.buttonRegister);
    }

    private Callback<Void> getLoginCallback() {
        return new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(response.isSuccessful()) {
                    Intent menuIntent = new Intent(MainActivity.this, SecondActivity.class);
//                    menuIntent.putExtra(IntentExtras.USERNAME, mUserCredentials.getmUsername());
//                    menuIntent.putExtra(IntentExtras.PASSWORD, mUserCredentials.getmPassword());
                    startActivity(menuIntent);

                    finish();
                } else {

                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        };
    }
}
