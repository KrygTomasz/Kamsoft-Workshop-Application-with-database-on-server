package com.example.kryguu.application;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by kryguu on 10.12.2016.
 */

public class ServiceManager {
    private static final String BASE_URL = "http://mobile1.osoz.pl:8080/crm/rest/";
    private static ServiceManager instance;
    private Retrofit retrofit;

    private ServiceManager() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static CrmService getCrmService() {
        if (instance == null) {
            instance = new ServiceManager();
        }

        return instance.retrofit.create(CrmService.class);
    }
}
