package com.opendev.cn.instagram4android.requests.payload;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by root on 08/06/17.
 */

@Getter
@Setter
@ToString(callSuper = true)
public class InstagramUserSummary {
    public boolean is_verified;
    public String profile_pic_id;
    public boolean is_favorite;
    public boolean is_private;
    public String username;
    public long pk;
    public String profile_pic_url;
    public boolean has_anonymous_profile_picture;
    public String full_name;

}
