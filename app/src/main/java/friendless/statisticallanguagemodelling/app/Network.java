package friendless.statisticallanguagemodelling.app;

import android.util.Log;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by john on 26/08/15.
 */
public class Network {
    // Given a URL, establishes an HttpUrlConnection and retrieves
    // the web page content as a InputStream, which it returns as a string.
    public static String downloadUrl(String surl) throws IOException {
        InputStream is = null;
        try {
            URL url = new URL(surl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000 /* milliseconds */);
            conn.setConnectTimeout(15000 /* milliseconds */);
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Accept", "application/json");
            conn.setDoInput(true);
            // Starts the query
            conn.connect();
            int response = conn.getResponseCode();
            if (response != 200) {
                Log.d(Network.class.toString(), "The response is: " + response + " " + url);
            }
            is = conn.getInputStream();
            // Convert the InputStream into a string
            return readIt(is);
            // Makes sure that the InputStream is closed after the app is finished using it.
        } finally {
            if (is != null) {
                is.close();
            }
        }
    }

    // Reads an InputStream and converts it to a String.
    public static String readIt(InputStream stream) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(stream));
        String line;
        StringBuilder responseData = new StringBuilder();
        while((line = in.readLine()) != null) {
            responseData.append(line);
        }
        return responseData.toString();
    }
}
