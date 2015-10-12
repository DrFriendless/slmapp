package friendless.statisticallanguagemodelling.app;

import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.widget.ImageButton;

/**
 * Created by john on 28/08/15.
 */
public class SetButtonImageTask extends AsyncTask<Integer, Void, Drawable> {
    private ImageButton button;
    private int param;

    SetButtonImageTask(ImageButton button) {
        this.button = button;
    }

    @Override
    protected Drawable doInBackground(Integer... params) {
        this.param = params[0];
        return SvgImages.getImage(params[0]);
    }

    @Override
    protected void onPostExecute(Drawable d) {
        System.out.println("" + param + " " + button + " -> " + d);
        button.setImageDrawable(d);
    }
}
