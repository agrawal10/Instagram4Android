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
public class InstagramSuggestion {
    public List<String> media_infos;
    public String social_context;
    public String algorithm;
    public List<String> thumbnail_urls;
    public String value;
    public String caption;

    public InstagramUser user;
    public List<String> large_urls;
    public List<String> media_ids;
    public String icon;
}
