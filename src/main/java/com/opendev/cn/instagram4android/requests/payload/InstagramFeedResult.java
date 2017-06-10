package com.opendev.cn.instagram4android.requests.payload;

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

    private boolean auto_load_more_enabled;
    private int num_results;
    private String next_max_id;

    private List<InstagramFeedItem> items;
    private List<InstagramFeedItem> ranked_items;

    private boolean more_available;

}
