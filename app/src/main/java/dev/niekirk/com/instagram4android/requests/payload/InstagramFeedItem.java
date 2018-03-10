package dev.niekirk.com.instagram4android.requests.payload;

import android.os.Parcelable;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by root on 08/06/17.
 */

@Getter
@Setter
@ToString(callSuper = true)
public class InstagramFeedItem implements Serializable {

    public long taken_at;
    public long pk;
    public String id;
    public long device_timestamp;
    public int media_type;
    public String code;
    public String client_cache_key;
    public int filter_type;
    public boolean has_audio;
    public double video_duration;
    public Map<String, Object> attribution;
    public List<InstagramVideoVersions> video_versions;
    public InstagramImageVersions_2 image_versions2;
    public Map<String, Object> usertags;
    public Map<String, Object> location;
    public float lng;
    public float lat;
    public int original_width;
    public int original_height;
    public int view_count;
    public InstagramUser user;
    public int dr_ad_type;
    public List<InstagramCarouselMedia> carousel_media;

    public String organic_tracking_token;
    public int like_count;
    public List<String> top_likers;
    public List<InstagramUserSummary> likers;
    public boolean has_liked;
    public boolean comment_likes_enabled;
    public boolean has_more_comments;
    public long next_max_id;
    public int max_num_visible_preview_comments;
    public List<Object> preview_comments;
    public List<Object> comments;
    public int comment_count;
    public Map<String, Object> caption;

    public boolean caption_is_edited;
    public boolean photo_of_you;
    public boolean comments_disabled;

}
