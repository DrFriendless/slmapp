package friendless.statisticallanguagemodelling.app;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by john on 26/08/15.
 */
public class WriteStoryActivity extends Activity {
    private Story currentStory = new Story();
    private StoryPanel storyPanel;
    private String corpusId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write);
        Intent intent = getIntent();
        GridView iconGrid = (GridView) findViewById(R.id.gridview1);
        int iconCount = 18;
        this.corpusId = intent.getStringExtra(getString(R.string.corpus));
        List<Integer> idMap = new ArrayList<>();
        for (int i=0; i<iconCount; i++) {
            idMap.add(i);
        }
        Collections.shuffle(idMap);
        iconGrid.setAdapter(new ButtonAdapter(this, idMap));
        ViewGroup storyView = (ViewGroup) findViewById(R.id.story);
        storyPanel = new StoryPanel(this, currentStory, storyView);
        Button button = (Button) findViewById(R.id.btnSubmit2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submit();
            }
        });
        Button backspace = (Button) findViewById(R.id.btnBackspace);
        backspace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backspace();
            }
        });
    }

    public void writeWord(int i) {
        currentStory.write(i);
        storyPanel.repaint();
    }

    private void submit() {
        AsyncTask<String, Void, Void> task = new AsyncTask<String, Void, Void>() {
            private boolean success = false;
            @Override
            protected Void doInBackground(String... params) {
                String corpusId = params[0];
                String url = MessageFormat.format(getString(R.string.host) + getString(R.string.story_url),
                        corpusId, currentStory.getWordString());
                try {
                    Network.downloadUrl(url);
                    success = true;
                } catch (Throwable ex) {
                    success = false;
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                if (success) {
                    currentStory.clear();
                    storyPanel.repaint();
                } else {
                    String mesg = getString(R.string.storyFailed);
                    Toast toast = Toast.makeText(WriteStoryActivity.this, mesg, Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        };
        task.execute(corpusId);
    }

    private void backspace() {
        currentStory.backspace();
        storyPanel.repaint();
    }
}
