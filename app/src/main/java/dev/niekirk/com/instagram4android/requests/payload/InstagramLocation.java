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
public class InstagramLocation {

    public String name;
    public String external_id_source;
    public String external_source;
    public String address;
    public float lat;
    public float lng;
    public String external_id;
    public String facebook_places_id;
    public String city;
    public String pk;
    public String short_name;
    public String facebook_events_id;
    public String start_time;
    public String end_time;
    public InstagramLocation location_dict;
    public String type;
    public String profile_pic_url;
    public String profile_pic_username;
    public String time_granularity;
    public String timezone;

}
