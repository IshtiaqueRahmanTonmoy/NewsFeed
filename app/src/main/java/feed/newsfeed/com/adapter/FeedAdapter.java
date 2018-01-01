package feed.newsfeed.com.adapter;

import android.content.Context;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import feed.newsfeed.com.newsfeed.R;
import java.util.List;

import feed.newsfeed.com.entity.FeedItem;

/**
 * Created by TONMOYPC on 12/31/2017.
 */

public class FeedAdapter extends RecyclerView.Adapter<FeedAdapter.MyViewHolder> {

    private List<FeedItem> feedItem;
    private Context mContext;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView adminName,createdAt,updateAt,title,description,comment;
        public ImageView thumbnail,like;

        public MyViewHolder(View view) {
            super(view);
            adminName = (TextView) view.findViewById(R.id.adminName);
            createdAt = (TextView) view.findViewById(R.id.created_at);
            updateAt = (TextView) view.findViewById(R.id.update_at);
            title = (TextView) view.findViewById(R.id.titlevalue);
            description = (TextView) view.findViewById(R.id.description);
            comment = (TextView) view.findViewById(R.id.comment);
            thumbnail = (ImageView) view.findViewById(R.id.thumbnail);
            like = (ImageView) view.findViewById(R.id.like);
        }
    }


    public FeedAdapter(Context context, List<FeedItem> feedItem) {
        mContext = context;
        this.feedItem = feedItem;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.feed_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        FeedItem feed = feedItem.get(position);

        holder.adminName.setText(feed.getAdmin_name());
        holder.createdAt.setText(feed.getCreated_at());
        holder.updateAt.setText(feed.getUpdated_at());
        holder.title.setText(feed.getTitle());
        holder.description.setText(feed.getDescription());
        holder.comment.setText("0 comments");
        try {
            //File file = new File(list.get(position).getImage());
            //Toast.makeText(mContext, ""+file, Toast.LENGTH_SHORT).show();
            Glide.with(mContext)
                    .load("http://cc.krazyit.com.au/"+feed.getImage())
                    .asBitmap()
                    .skipMemoryCache(true)
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .into(holder.thumbnail);
        } catch (Exception e) {
        }

    }

    @Override
    public int getItemCount() {
        return feedItem.size();
    }

    public interface ClickListener {
        void onClick(View view, int position);

        void onLongClick(View view, int position);
    }

    public static class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {

        private GestureDetector gestureDetector;
        private FeedAdapter.ClickListener clickListener;

        public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final FeedAdapter.ClickListener clickListener) {
            this.clickListener = clickListener;
            gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

                @Override
                public void onLongPress(MotionEvent e) {
                    View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                    if (child != null && clickListener != null) {
                        clickListener.onLongClick(child, recyclerView.getChildPosition(child));
                    }
                }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {

            View child = rv.findChildViewUnder(e.getX(), e.getY());
            if (child != null && clickListener != null && gestureDetector.onTouchEvent(e)) {
                clickListener.onClick(child, rv.getChildPosition(child));
            }
            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {
        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }
    }
}