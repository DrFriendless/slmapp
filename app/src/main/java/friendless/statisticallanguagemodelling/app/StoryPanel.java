package friendless.statisticallanguagemodelling.app;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import org.apmem.tools.layouts.FlowLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by john on 27/08/15.
 */
public class StoryPanel {
    private Story story;
    private ViewGroup viewGroup;
    private List<ImageView> images = new ArrayList<ImageView>();
    private Context context;

    StoryPanel(Context context, Story story, ViewGroup viewGroup) {
        this.story = story;
        this.viewGroup = viewGroup;
        this.images = new ArrayList<>();
        this.context = context;
    }

    public void repaint() {
        while (story.size() < images.size()) {
            View v = viewGroup.getChildAt(images.size()-1);
            viewGroup.removeView(v);
            images.remove(images.size()-1);
        }
        while (story.size() > images.size()) {
            int w = story.getWordAt(images.size());
            ImageView v = new ImageView(context);
            FlowLayout.LayoutParams params = new FlowLayout.LayoutParams(
                    FlowLayout.LayoutParams.WRAP_CONTENT, FlowLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(0,0,0,0);
            v.setImageDrawable(SvgImages.getImage(w));
            viewGroup.addView(v, params);
            images.add(v);
        }
    }
}
