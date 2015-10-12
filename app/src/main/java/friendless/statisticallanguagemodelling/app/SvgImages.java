package friendless.statisticallanguagemodelling.app;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.PictureDrawable;
import com.caverock.androidsvg.SVG;
import com.caverock.androidsvg.SVGParseException;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by john on 27/08/15.
 */
public class SvgImages {
    private static final Map<Integer, Drawable> cache = new HashMap<>();

    public static Drawable getImage(int n) {
        while (true) {
            synchronized (cache) {
                if (cache.containsKey(n)) break;
                try {
                    cache.wait();
                } catch (InterruptedException ex) {
                    return null;
                }
            }
        }
        synchronized (cache) {
            return cache.get(n);
        }
    }

    public static void loadIcons(Context context) {
        load(context, 0, R.raw.icon0);
        load(context, 1, R.raw.icon1);
        load(context, 2, R.raw.icon2);
        load(context, 3, R.raw.icon3);
        load(context, 4, R.raw.icon4);
        load(context, 5, R.raw.icon5);
        load(context, 6, R.raw.icon6);
        load(context, 7, R.raw.icon7);
        load(context, 8, R.raw.icon8);
        load(context, 9, R.raw.icon9);
        load(context, 10, R.raw.icon10);
        load(context, 11, R.raw.icon11);
        load(context, 12, R.raw.icon12);
        load(context, 13, R.raw.icon13);
        load(context, 14, R.raw.icon14);
        load(context, 15, R.raw.icon15);
        load(context, 16, R.raw.icon16);
        load(context, 17, R.raw.icon17);
    }

    private static void load(Context context, int n, int resourceID) {
        Resources rs = context.getResources();
        InputStream is = rs.openRawResource(resourceID);
        try {
            SVG svg = SVG.getFromInputStream(is);
            synchronized (cache) {
                cache.put(n, new PictureDrawable(svg.renderToPicture()));
            }
        } catch (SVGParseException ex) {
            ex.printStackTrace();
        }
    }
}
