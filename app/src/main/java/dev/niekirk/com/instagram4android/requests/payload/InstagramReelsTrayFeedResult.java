package dev.niekirk.com.instagram4android.requests.payload;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by root on 24/09/17.
 */

@Getter
@Setter
@ToString(callSuper = true)
public class InstagramReelsTrayFeedResult {

    public List<InstagramStoryTray> tray;
    public List<InstagramBroadcast> broadcasts;
    public InstagramPostLive post_live;
    public String sticker_version;
    public String face_filter_nux_version;
    public String story_ranking_token;

}
