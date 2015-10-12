package friendless.statisticallanguagemodelling.app;

import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.net.SocketException;

/**
 * Created by john on 26/08/15.
 */
// Uses AsyncTask to create a task away from the main UI thread. This task takes a
// URL string and uses it to create an HttpUrlConnection. Once the connection
// has been established, the AsyncTask downloads the contents of the webpage as
// an InputStream. Finally, the InputStream is converted into a string, which is
// displayed in the UI by the AsyncTask's onPostExecute method.
public class FetchUrlTask extends AsyncTask<String, Void, String> {
    protected boolean disconnected = false;

    @Override
    protected String doInBackground(String... params) {
        try {
            return Network.downloadUrl(params[0]);
        } catch (SocketException ex) {
            ex.printStackTrace();
            this.disconnected = true;
            return null;
        } catch (IOException e) {
            Log.e(FetchUrlTask.class.toString(), "IOException", e);
            return null;
        }
    }
}
