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
public class InstagramLoggedUser {
    public String profile_pic_url;
    public boolean allow_contacts_sync;
    public String username;
    public String full_name;
    public boolean is_private;
    public String profile_pic_id;
    public long pk;
    public boolean is_verified;
    public boolean has_anonymous_profile_picture;

}
