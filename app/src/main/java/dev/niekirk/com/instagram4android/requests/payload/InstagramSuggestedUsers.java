package dev.niekirk.com.instagram4android.requests.payload;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by root on 20/09/17.
 */

@Getter
@Setter
@ToString(callSuper = true)
public class InstagramSuggestedUsers {
    public String id;
    public String view_all_text;
    public String title;
    public String auto_dvance;
    public String type;
    public String tracking_token;
    public String landing_site_type;
    public String landing_site_title;
    public String upsell_fb_pos;
    public List<InstagramSuggestion> suggestions;
    public String netego_type;
}
