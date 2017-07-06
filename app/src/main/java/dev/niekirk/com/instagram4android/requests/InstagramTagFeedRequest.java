package dev.niekirk.com.instagram4android.requests;

import dev.niekirk.com.instagram4android.requests.payload.InstagramFeedResult;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;

/**
 * Created by root on 09/06/17.
 */

@AllArgsConstructor
public class InstagramTagFeedRequest extends InstagramGetRequest<InstagramFeedResult> {

    private String tag;

    @Override
    public String getUrl() {
        return "feed/tag/" + tag + "/?rank_token=" + api.getRankToken() + "&ranked_content=true&";
    }

    @Override
    @SneakyThrows
    public InstagramFeedResult parseResult(int statusCode, String content) {
        return parseJson(statusCode, content, InstagramFeedResult.class);
    }

}
