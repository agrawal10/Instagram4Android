package dev.niekirk.com.instagram4android.requests.payload;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by root on 08/06/17.
 */

@Getter
@Setter
@ToString(callSuper = true)
public class InstagramFeedResult extends StatusResult {

    private int num_results;
    private boolean auto_load_more_enabled;
    private boolean more_available;
    //private boolean is_direct_v2_enabled;
    private String next_max_id;

    private List<InstagramTimelineFeedItem> feed_items;

}
