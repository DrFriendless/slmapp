package friendless.statisticallanguagemodelling.app;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by john on 27/08/15.
 */
public class Story {
    public static final int MAX_STORY_LENGTH = 16;

    private List<Integer> words = new ArrayList<Integer>();

    public void write(int i) {
        if (words.size() < MAX_STORY_LENGTH) words.add(i);
    }

    public void clear() {
        words.clear();
    }

    public void backspace() {
        if (words.size() > 0) {
            words.remove(words.size()-1);
        }
    }

    public String getWordString() {
        StringBuilder builder = new StringBuilder();
        for (int i=0; i<words.size(); i++) {
            if (i > 0) builder.append(",");
            builder.append(words.get(i));
        }
        return builder.toString();
    }

    public int size() {
        return words.size();
    }

    public int getWordAt(int n) {
        return words.get(n);
    }

    @Override
    public String toString() {
        return "Story" + words;
    }
}
