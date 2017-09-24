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
public class InstagramBroadcast {

    private InstagramBroadcastOwner broadcast_owner;
    private String broadcast_status;
    private String cover_frame_url;
    private String published_time;
    private String broadcast_message;
    private boolean muted;

    private String media_id;
    private String id;
    private String rtmp_playback_url;
    private String dash_abr_playback_url;
    private String dash_playback_url;
    private int ranked_position;
    private String organic_tracking_token;
    private String seen_ranked_position;
    private int viewer_count;
    private String dash_manifest;
    private String expire_at;
    private String encoding_tag;
    private int total_unique_viewer_count;
    private String internal_only;
    private int number_of_qualities;

}
