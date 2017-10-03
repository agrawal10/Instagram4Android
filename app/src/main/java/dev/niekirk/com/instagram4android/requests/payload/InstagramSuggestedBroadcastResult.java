package dev.niekirk.com.instagram4android.requests.payload;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * Created by root on 28/09/17.
 */

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class InstagramSuggestedBroadcastResult {

    private List<InstagramBroadcast> broadcasts;

}
