package com.example.qualnumero.ui.main;

import android.os.AsyncTask;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

public class HttpService extends AsyncTask<Void, Void, String> {
    private static final String URL_STR = "https://us-central1-ss-devops.cloudfunctions.net/rand?min=1&max=300";

    @Override
    protected String doInBackground(Void... voids) {
        StringBuilder resposta = new StringBuilder();
        int status = 0;
        try {
            URL url = new URL(URL_STR);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-type", "application/json");
            connection.setRequestProperty("Accept", "application/json");
            connection.setConnectTimeout(5000);
            connection.connect();
            status = connection.getResponseCode();
            if (status>400){
                throw new RuntimeException(String.valueOf(status));
            }

            Scanner scanner = new Scanner(connection.getInputStream());
            while (scanner.hasNext()) {
                resposta.append(scanner.next());
            }
        }
        catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resposta.toString();
    }




}
