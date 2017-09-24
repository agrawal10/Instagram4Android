package dev.niekirk.com.instagram4android.requests.payload;

import java.util.List;

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
public class InstagramPostLive {

    private List<InstagramPostLiveItem> post_live_items;

}
