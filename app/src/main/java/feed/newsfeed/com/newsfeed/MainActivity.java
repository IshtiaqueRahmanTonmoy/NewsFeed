package feed.newsfeed.com.newsfeed;

import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

public class MainActivity extends AppCompatActivity {

    private BottomBar bottomBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MainActivity.this.setTitle("Feed");
        bottomBar = (BottomBar) findViewById(R.id.bottomBar);
        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                if (tabId == R.id.tab_home) {
                    Toast.makeText(MainActivity.this, "home selected", Toast.LENGTH_SHORT).show();
                } else if (tabId == R.id.tab_kids) {
                    Toast.makeText(MainActivity.this, "kids selected", Toast.LENGTH_SHORT).show();
                } else if (tabId == R.id.tab_gallery) {
                    Toast.makeText(MainActivity.this, "gallery selected", Toast.LENGTH_SHORT).show();
                }
                else if (tabId == R.id.tab_notification) {
                    Toast.makeText(MainActivity.this, "notification selected", Toast.LENGTH_SHORT).show();
                }
                else if (tabId == R.id.tab_more) {
                    Toast.makeText(MainActivity.this, "more selected", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
