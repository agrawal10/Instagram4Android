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
public class InstagramStoryTray {

    public String id;
    public List<InstagramFeedItem> items;
    public InstagramUser user;
    public boolean can_reply;
    public String expiring_at;
    public int seen_ranked_position;
    public float seen;
    public String latest_reel_media;
    public int ranked_position;
    public boolean is_nux;
    public boolean show_nux_tooltip;
    public boolean muted;
    public int prefetch_count;
    public InstagramLocation location;
    public String source_token;
    public InstagramOwner owner;
    public String nux_id;
    public InstagramDismissCard dismiss_card;
    public boolean can_reshare;
    public boolean has_besties_media;

}
