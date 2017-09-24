package dev.niekirk.com.instagram4android.requests;

import dev.niekirk.com.instagram4android.requests.payload.InstagramUserStoryFeedResult;
import lombok.AllArgsConstructor;

/**
 * Created by root on 24/09/17.
 */

@AllArgsConstructor
public class InstagramUserStoryFeedRequest extends InstagramGetRequest<InstagramUserStoryFeedResult> {

    private String userId;

    @Override
    public String getUrl() {
        return String.format("feed/user/%s/story/", userId);
    }

    @Override
    public InstagramUserStoryFeedResult parseResult(int resultCode, String content) {
        return parseJson(resultCode, content, InstagramUserStoryFeedResult.class);
    }
}
