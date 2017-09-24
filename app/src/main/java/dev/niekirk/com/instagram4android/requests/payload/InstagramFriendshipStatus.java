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
public class InstagramFriendshipStatus {

    private boolean following;
    private boolean followed_by;
    private boolean incoming_request;
    private boolean outgoing_request;
    private boolean is_private;
    private boolean is_blocking_reel;
    private boolean is_muting_reel;
    private boolean blocking;
    private boolean is_bestie;

}
