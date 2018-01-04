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
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.gson.Gson;
import java.lang.reflect.Type;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import feed.newsfeed.com.newsfeed.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import feed.newsfeed.com.entity.FeedItem;

/**
 * Created by TONMOYPC on 12/31/2017.
 */

public class FeedAdapter extends RecyclerView.Adapter<FeedAdapter.MyViewHolder> {

    private List<FeedItem> feedItems;
    private List<FeedItem> imglist = new ArrayList<FeedItem>();
    private Context mContext;
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/M/yyyy hh:mm:ss");

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView adminName,createdAt,updateAt,title,description,comment;
        public ImageView thumbnail,thumbnails,thumbnailss,like;

        public MyViewHolder(View view) {
            super(view);
            adminName = (TextView) view.findViewById(R.id.adminName);
            createdAt = (TextView) view.findViewById(R.id.created_at);
            //updateAt = (TextView) view.findViewById(R.id.update_at);
            title = (TextView) view.findViewById(R.id.titlevalue);
            description = (TextView) view.findViewById(R.id.description);
            comment = (TextView) view.findViewById(R.id.comment);
            thumbnail = (ImageView) view.findViewById(R.id.thumbnail);
            thumbnails = (ImageView) view.findViewById(R.id.thumbnails);
            thumbnailss = (ImageView) view.findViewById(R.id.thumbnailss);
            like = (ImageView) view.findViewById(R.id.like);
        }
    }


    public FeedAdapter(Context context, List<FeedItem> feedItems) {
        mContext = context;
        this.feedItems = feedItems;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.feed_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        FeedItem feed = feedItems.get(position);

        holder.adminName.setText(feed.getAdmin_name());
        holder.createdAt.setText("4d ago");
        //holder.updateAt.setText(feed.getUpdated_at());
        holder.title.setText(feed.getTitle());
        holder.description.setText(feed.getDescription());
        holder.comment.setText("7 comments");

        /*
        try {
            Date date1 = simpleDateFormat.parse(feed.getCreated_at().toString());
            Date date2 = simpleDateFormat.parse(feed.getUpdated_at().toString());

            Toast.makeText(mContext, ""+date1+""+date2, Toast.LENGTH_SHORT).show();
            long different = date2.getTime() - date1.getTime();


            long secondsInMilli = 1000;
            long minutesInMilli = secondsInMilli * 60;
            long hoursInMilli = minutesInMilli * 60;
            long daysInMilli = hoursInMilli * 24;

            long elapsedDays = different / daysInMilli;
            //Toast.makeText(mContext, ""+elapsedDays, Toast.LENGTH_SHORT).show();

            String day = String.valueOf(elapsedDays);
            holder.createdAt.setText(day);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        */



        if(position==1) {
            try {
                //File file = new File(list.get(position).getImage());
                //Toast.makeText(mContext, ""+file, Toast.LENGTH_SHORT).show();
                Glide.with(mContext)
                        .load("http://cc.krazyit.com.au/uploads/story/images2017-12-31_09:01:53_33_1_image.png")
                        .asBitmap()
                        .skipMemoryCache(true)
                        .diskCacheStrategy(DiskCacheStrategy.NONE)
                        .into(holder.thumbnail);

                Glide.with(mContext)
                        .load("http://cc.krazyit.com.au/uploads/story/images2017-12-31_09:01:53_33_2_image.png")
                        .asBitmap()
                        .skipMemoryCache(true)
                        .diskCacheStrategy(DiskCacheStrategy.NONE)
                        .into(holder.thumbnails);

                Glide.with(mContext)
                        .load("http://cc.krazyit.com.au/uploads/story/images2017-12-31_09:01:53_33_3_image.png")
                        .asBitmap()
                        .skipMemoryCache(true)
                        .diskCacheStrategy(DiskCacheStrategy.NONE)
                        .into(holder.thumbnailss);

            } catch (Exception e) {
            }
        }

            if(position==2){
                try {
                    //File file = new File(list.get(position).getImage());
                    //Toast.makeText(mContext, ""+file, Toast.LENGTH_SHORT).show();
                    Glide.with(mContext)
                            .load("http://cc.krazyit.com.au/uploads/story/images2017-12-31_08:59:02_33_1_image.png")
                            .asBitmap()
                            .skipMemoryCache(true)
                            .diskCacheStrategy(DiskCacheStrategy.NONE)
                            .into(holder.thumbnail);

                    Glide.with(mContext)
                            .load("http://cc" +
                                    ".krazyit.com.au/uploads/story/images2017-12-31_08:59:02_33_2_image.png")
                            .asBitmap()
                            .skipMemoryCache(true)
                            .diskCacheStrategy(DiskCacheStrategy.NONE)
                            .into(holder.thumbnails);

                    Glide.with(mContext)
                            .load("http://cc.krazyit.com.au/uploads/story/images2017-12-31_08:59:02_33_3_image.png")
                            .asBitmap()
                            .skipMemoryCache(true)
                            .diskCacheStrategy(DiskCacheStrategy.NONE)
                            .into(holder.thumbnailss);

                } catch (Exception e) {
                }
            }


        //Gson gson = new Gson();
        //Type type = new TypeToken<List<FeedItem>>(){}.getType();
        //imglist = gson.fromJson(feed.getImage(), type);
        //Toast.makeText(mContext, ""+imglist.size(), Toast.LENGTH_SHORT).show();

    }

    @Override
    public int getItemCount() {
        return feedItems.size();
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