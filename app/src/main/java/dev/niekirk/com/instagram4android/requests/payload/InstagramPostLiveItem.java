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
public class InstagramPostLiveItem {

    public String pk;
    public InstagramUser user;
    public List<InstagramBroadcast> broadcasts;
    public String last_seen_broadcast_ts;
    public boolean can_reply;
    public int ranked_position;
    public int seen_ranked_position;
    public boolean muted;
    public boolean can_reshare;

}
