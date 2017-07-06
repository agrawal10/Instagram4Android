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
public class InstagramGetUserFollowersResult extends StatusResult {
    public boolean big_list;
    public String next_max_id;
    public int page_size;

    public List<InstagramUserSummary> users;

}
