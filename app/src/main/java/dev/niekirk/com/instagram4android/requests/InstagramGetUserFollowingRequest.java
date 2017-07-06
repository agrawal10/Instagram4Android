package dev.niekirk.com.instagram4android.requests;

import dev.niekirk.com.instagram4android.InstagramConstants;
import dev.niekirk.com.instagram4android.requests.payload.InstagramGetUserFollowersResult;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * Created by root on 09/06/17.
 */

@RequiredArgsConstructor
@AllArgsConstructor
public class InstagramGetUserFollowingRequest extends InstagramGetRequest<InstagramGetUserFollowersResult> {

    @NonNull
    private long userId;
    private String maxId;

    @Override
    public String getUrl() {
        String baseUrl = "friendships/" + userId + "/following/?rank_token=" + api.getRankToken() + "&ig_sig_key_version=" + InstagramConstants.API_KEY_VERSION;
        if (maxId != null && !maxId.isEmpty()) {
            baseUrl += "&max_id=" + maxId;
        }
        return baseUrl;
    }

    @Override
    public InstagramGetUserFollowersResult parseResult(int resultCode, String content) {
        return parseJson(resultCode, content, InstagramGetUserFollowersResult.class);
    }
}
