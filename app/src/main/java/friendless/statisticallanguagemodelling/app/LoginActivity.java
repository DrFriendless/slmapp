package friendless.statisticallanguagemodelling.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.concurrent.Semaphore;

public class LoginActivity extends Activity {
    private Semaphore imageLoadDone = new Semaphore(0);
    private boolean imagesLoaded = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button button = (Button) findViewById(R.id.btnSubmit1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView idView = (TextView) findViewById(R.id.editText1);
                String corpusId = idView.getText().toString().trim();
                if (corpusId.length() == 0) return;
                try {
                    Integer.parseInt(corpusId);
                } catch (NumberFormatException ex) {
                    return;
                }
                if (!imagesLoaded) {
                    try {
                        imageLoadDone.acquire();
                    } catch (InterruptedException ex) {
                        // no idea what to do here.
                    }
                }
                Intent next = new Intent(LoginActivity.this, WriteStoryActivity.class);
                next.putExtra(getString(R.string.corpus), corpusId);
                LoginActivity.this.startActivity(next);
            }
        });
        if (!imagesLoaded) {
            Thread t = new Thread() {
                @Override
                public void run() {
                    SvgImages.loadIcons(LoginActivity.this);
                    imageLoadDone.release();
                    imagesLoaded = true;
                }
            };
            t.start();
        }
    }
}
