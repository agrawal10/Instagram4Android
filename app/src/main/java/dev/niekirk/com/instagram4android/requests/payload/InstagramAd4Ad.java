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
public class InstagramAd4Ad {
    public String title;
    public String type;
    public String id;
    public String tracking_token;
    public String footer;

    public InstagramFeedItem media;
}
