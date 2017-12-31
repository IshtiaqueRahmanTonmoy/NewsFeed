package feed.newsfeed.com.newsfeed;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import feed.newsfeed.com.adapter.FeedAdapter;
import feed.newsfeed.com.app.AppController;
import feed.newsfeed.com.entity.FeedItem;

public class MainActivity extends AppCompatActivity {

    private String admin_name,created_at,updated_at,title,description,comment,image;
    private BottomBar bottomBar;
    private RecyclerView recyclerView;
    private String TAG = MainActivity.class.getSimpleName();
    private static final String endpoint = "https://api.androidhive.info/json/glide.json";
    private ArrayList<FeedItem> feedItem;
    private ProgressDialog pDialog;
    private FeedAdapter fAdapter;
    private StringRequest stringRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MainActivity.this.setTitle("Feed");

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        pDialog = new ProgressDialog(this);
        feedItem = new ArrayList<>();

        fAdapter = new FeedAdapter(getApplicationContext(), feedItem);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.addItemDecoration(new SimpleDividerItemDecoration(this, LinearLayout.VERTICAL,16));
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(fAdapter);

        fetchData();
        
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

    private void fetchData() {
        stringRequest = new StringRequest("http://cc.krazyit.com.au/api/show_story_by_post_type?ccorg_id=33&post_type=general",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            FeedItem feeditem = new FeedItem();
                            JSONArray j = jsonObject.getJSONArray("success");
                            for(int i=0;i<j.length();i++){
                                try {
                                    //Getting json object
                                    JSONObject json = j.getJSONObject(i);

                                    admin_name = json.getString("admin_name");
                                    created_at = json.getString("created_at");
                                    updated_at = json.getString("updated_at");
                                    title = json.getString("title");
                                    description = json.getString("description");
                                    comment = json.getString("comments");
                                    image = "uploads/story/images2017-12-31_09:01:53_33_1_image.png";

                                    feedItem.add(new FeedItem(admin_name,created_at,updated_at,title,description,comment,image));
                                    fAdapter.notifyDataSetChanged();
                                    //feeditem.setAdmin_name(admin_name);
                                    //feeditem.setCreated_at(created_at);
                                    //feeditem.setUpdated_at(updated_at);
                                    //feeditem.setTitle(title);
                                    //feeditem.setDescription(description);
                                    //feeditem.setComment(comment);
                                    //feeditem.setImage(image);

                                    //feedItem.add(feeditem);
                                    Log.d("data",created_at+""+updated_at+""+title+""+description+""+comment);

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        Log.d("response",response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this,error.getMessage(),Toast.LENGTH_LONG).show();
                    }
                });


        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
        requestQueue.add(stringRequest);
        //progressDialog = new ProgressDialog(MainActivity.this);
        //progressDialog.setMessage("Please wait....");
        //progressDialog.show();
    }

    public class SimpleDividerItemDecoration extends RecyclerView.ItemDecoration {

        private final int[] ATTRS = new int[]{
                android.R.attr.listDivider
        };

        public static final int HORIZONTAL_LIST = LinearLayoutManager.HORIZONTAL;

        public static final int VERTICAL_LIST = LinearLayoutManager.VERTICAL;

        private Drawable mDivider;
        private Context context;
        private int margin;
        private int mOrientation;

        public SimpleDividerItemDecoration(Context context, int orientation,int margin) {
            this.context = context;
            this.margin = margin;
            final TypedArray a = context.obtainStyledAttributes(ATTRS);
            mDivider = a.getDrawable(0);
            a.recycle();
            setOrientation(orientation);
        }

        public void setOrientation(int orientation) {
            if (orientation != HORIZONTAL_LIST && orientation != VERTICAL_LIST) {
                throw new IllegalArgumentException("invalid orientation");
            }
            mOrientation = orientation;
        }

        @Override
        public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
            if (mOrientation == VERTICAL_LIST) {
                drawVertical(c, parent);
            } else {
                drawHorizontal(c, parent);
            }
        }

        public void drawVertical(Canvas c, RecyclerView parent) {
            final int left = parent.getPaddingLeft();
            final int right = parent.getWidth() - parent.getPaddingRight();

            final int childCount = parent.getChildCount();
            for (int i = 0; i < childCount; i++) {
                final View child = parent.getChildAt(i);
                final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                        .getLayoutParams();
                final int top = child.getBottom() + params.bottomMargin;
                final int bottom = top + mDivider.getIntrinsicHeight();
                mDivider.setBounds(left, top, right, bottom);
                mDivider.draw(c);
            }
        }

        public void drawHorizontal(Canvas c, RecyclerView parent) {
            final int top = parent.getPaddingTop();
            final int bottom = parent.getHeight() - parent.getPaddingBottom();

            final int childCount = parent.getChildCount();
            for (int i = 0; i < childCount; i++) {
                final View child = parent.getChildAt(i);
                final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                        .getLayoutParams();
                final int left = child.getRight() + params.rightMargin;
                final int right = left + mDivider.getIntrinsicHeight();
                mDivider.setBounds(left, top, right, bottom);
                mDivider.draw(c);
            }
        }

        @Override
        public void getItemOffsets(Rect outRect, int itemPosition, RecyclerView parent) {
            if (mOrientation == VERTICAL_LIST) {
                outRect.set(0, 0, 0, mDivider.getIntrinsicHeight());
            } else {
                outRect.set(0, 0, mDivider.getIntrinsicWidth(), 0);
            }
        }
    }
}
