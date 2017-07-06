package dev.niekirk.com.instagram4android.requests.payload;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by root on 08/06/17.
 */

@Getter
@Setter
@ToString(callSuper = true)
public class InstagramLikeResult extends StatusResult {
    private boolean spam;
    private String feedback_ignore_label;
    private String feedback_title;
    private String feedback_message;
    private String feedback_url;
    private String feedback_action;
    private String feedback_appeal_label;


}
