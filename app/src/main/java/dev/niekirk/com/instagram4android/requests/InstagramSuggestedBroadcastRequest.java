package dev.niekirk.com.instagram4android.requests;

import dev.niekirk.com.instagram4android.requests.payload.InstagramSuggestedBroadcastResult;

/**
 * Created by root on 28/09/17.
 */

public class InstagramSuggestedBroadcastRequest extends InstagramGetRequest<InstagramSuggestedBroadcastResult> {
    @Override
    public String getUrl() {
        return "live/get_suggested_broadcasts/";
    }

    @Override
    public InstagramSuggestedBroadcastResult parseResult(int resultCode, String content) {
        return parseJson(resultCode, content, InstagramSuggestedBroadcastResult.class);
    }
}
