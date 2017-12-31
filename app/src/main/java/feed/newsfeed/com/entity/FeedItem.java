package feed.newsfeed.com.entity;

/**
 * Created by TONMOYPC on 12/31/2017.
 */

public class FeedItem {
   int id;
   String admin_name;
    String created_at;
    String updated_at;
    String title;
    String description;
    String comment;
    String image;

    public FeedItem(String admin_name, String created_at, String updated_at, String title, String description, String comment, String image) {
        this.admin_name = admin_name;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.title = title;
        this.description = description;
        this.comment = comment;
        this.image = image;
    }

    public FeedItem() {

    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAdmin_name() {
        return admin_name;
    }

    public void setAdmin_name(String admin_name) {
        this.admin_name = admin_name;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
