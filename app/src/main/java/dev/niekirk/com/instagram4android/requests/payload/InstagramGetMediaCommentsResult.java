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
public class InstagramGetMediaCommentsResult extends StatusResult {
    private boolean comment_likes_enabled;
    private int comment_count;
    private boolean caption_is_edited;
    private boolean has_more_comments;
    private boolean has_more_headload_comments;
    private String next_max_id;
    private List<InstagramComment> comments;
}
