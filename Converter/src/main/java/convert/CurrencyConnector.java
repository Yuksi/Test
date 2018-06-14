package convert;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Yuksi on 13.06.2018.
 */
public class CurrencyConnector {

    public String getJsonRate(String currency) throws IOException {
        String urlString = String.format(
                "https://free.currencyconverterapi.com/api/v5/convert?q=USD_%s&compact=ultra", currency);
        URL url = new URL(urlString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        conn.setRequestMethod("GET");
        conn.setRequestProperty("Accept", "application/json");

        BufferedReader br = new BufferedReader(
                new InputStreamReader(
                        conn.getInputStream()));

        return br.readLine();
    }


}