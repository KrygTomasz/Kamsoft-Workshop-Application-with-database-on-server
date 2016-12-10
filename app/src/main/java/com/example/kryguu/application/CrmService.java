package com.example.kryguu.application;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by kryguu on 10.12.2016.
 */

public interface CrmService {
    @POST(CrmServiceEndpoint.LOGIN)
    Call<Void> login(@Body UserCredentials userCredentials);

    @GET(CrmServiceEndpoint.CLIENT_LIST)
    Call<List<Client>> clientList(@Header(UserCredentials.USERNAME) String username,
                                  @Header(UserCredentials.PASSWORD) String password);

    @POST(CrmServiceEndpoint.ADD_CLIENT)
    Call<Client> addClient(@Header(UserCredentials.USERNAME) String username,
                           @Header(UserCredentials.PASSWORD) String password,
                           @Body Client client);

    @GET(CrmServiceEndpoint.GET_CLIENT)
    Call<Client> getClient(@Header(UserCredentials.USERNAME) String username,
                           @Header(UserCredentials.PASSWORD) String password,
                           @Path("id") long id);
}
