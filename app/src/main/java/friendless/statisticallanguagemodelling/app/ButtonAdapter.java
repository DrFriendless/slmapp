package friendless.statisticallanguagemodelling.app;

import android.app.ActionBar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;

import java.util.List;

public class ButtonAdapter extends BaseAdapter {
    private WriteStoryActivity context;
    private List<Integer> idMap;
    private View.OnClickListener listener;

    public ButtonAdapter(WriteStoryActivity c, List<Integer> ids) {
        this.context = c;
        this.idMap = ids;
        listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.writeWord((Integer) v.getTag());
            }
        };
    }

    public int getCount() {
        return idMap.size();
    }

    public Object getItem(int position) {
        return idMap.get(position);
    }

    public long getItemId(int position) {
        return idMap.get(position);
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageButton button;
        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            button = new ImageButton(context);
        } else {
            button = (ImageButton) convertView;
        }
        button.setClickable(true);
        button.setTag(idMap.get(position));
        button.setOnClickListener(listener);
        button.setImageDrawable(SvgImages.getImage(idMap.get(position)));
        int width = parent.getWidth() / 7;
        int height = parent.getHeight() / 4;
        int size = width < height ? width : height;
        button.setMinimumWidth(size);
        button.setMinimumHeight(size);
        button.setPadding(4, 4, 4, 4);
        return button;
    }
}