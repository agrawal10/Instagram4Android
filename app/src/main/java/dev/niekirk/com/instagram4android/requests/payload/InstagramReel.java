package dev.niekirk.com.instagram4android.requests.payload;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * Created by root on 24/09/17.
 */

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class InstagramReel {

    public String id;
    public List<InstagramFeedItem> items;
    public InstagramUser user;
    public float expiring_at;
    public float seen;
    public boolean can_reply;
    public InstagramLocation location;
    public String latest_reel_media;
    public int prefetch_count;
    public InstagramBroadcast broadcast;

}
