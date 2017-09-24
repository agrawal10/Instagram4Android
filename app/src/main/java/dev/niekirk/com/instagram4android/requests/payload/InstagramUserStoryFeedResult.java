package dev.niekirk.com.instagram4android.requests.payload;

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
public class InstagramUserStoryFeedResult {

    public InstagramBroadcast broadcast;
    public InstagramReel reel;
    public InstagramPostLiveItem post_live_item;

}
