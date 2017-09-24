package dev.niekirk.com.instagram4android.requests.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * Created by root on 24/09/17.
 */

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class InstagramBroadcastOwner {

    public String pk;
    public InstagramFriendshipStatus friendship_status;
    public String full_name;
    public boolean is_verified;
    public String profile_pic_url;
    public String profile_pic_id;
    public boolean is_private;
    public String username;

}
