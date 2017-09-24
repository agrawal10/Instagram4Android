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
public class InstagramOwner {

    public String type;
    public String pk;
    public String name;
    public String profile_pic_url;
    public String profile_pic_username;
    public String short_name;
    public float lat;
    public float lng;
    public InstagramLocation location_dict;

}
