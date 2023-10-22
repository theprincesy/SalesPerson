package com.example.e_salesperson;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class ApiClient {
    public static String fetchDataFromApi(String apiUrl) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(apiUrl)
                .build();

        try {
            Response response = client.newCall(request).execute();

            if (response.isSuccessful()) {
                ResponseBody responseBody = response.body();
                if (responseBody != null) {
                    return responseBody.string();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
