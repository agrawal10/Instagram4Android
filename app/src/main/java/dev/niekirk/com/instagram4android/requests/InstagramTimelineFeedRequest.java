package dev.niekirk.com.instagram4android.requests;

import dev.niekirk.com.instagram4android.requests.payload.InstagramFeedResult;
import dev.niekirk.com.instagram4android.requests.payload.StatusResult;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

/**
 * Created by root on 09/06/17.
 */

@NoArgsConstructor
@AllArgsConstructor
public class InstagramTimelineFeedRequest extends InstagramGetRequest<InstagramFeedResult> {

    private String maxId = null;

    @Override
    public String getUrl() {
        String url = "feed/timeline/";
        if(maxId != null && !maxId.isEmpty()) {
            url += "&max_id=" + maxId;
        }
        return url;
    }

    @Override
    public String getPayload() {
        return null;
    }

    @Override
    @SneakyThrows
    public InstagramFeedResult parseResult(int statusCode, String content) {
        return parseJson(statusCode, content, InstagramFeedResult.class);
    }

}
