package dev.niekirk.com.instagram4android.requests.payload;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by root on 20/09/17.
 */

@Getter
@Setter
@ToString(callSuper = true)
public class InstagramTimelineFeedItem {
    public InstagramFeedItem media_or_ad;
    public InstagramAd4Ad ad4ad;
    public InstagramSuggestedUsers suggested_users;
    public String ad_link_type;
}
