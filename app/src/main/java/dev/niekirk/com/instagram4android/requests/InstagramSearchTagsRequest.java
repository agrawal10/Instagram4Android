package dev.niekirk.com.instagram4android.requests;

import dev.niekirk.com.instagram4android.requests.payload.InstagramSearchTagsResult;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;

/**
 * Created by root on 09/06/17.
 */

@AllArgsConstructor
public class InstagramSearchTagsRequest extends InstagramGetRequest<InstagramSearchTagsResult> {

    private String query;


    @Override
    public String getUrl() {
        return "tags/search/?is_typeahead=true&q="+ query + "&rank_token=" + api.getRankToken();
    }

    @Override
    public String getPayload() {
        return null;
    }

    @Override
    @SneakyThrows
    public InstagramSearchTagsResult parseResult(int statusCode, String content) {
        return parseJson(statusCode, content, InstagramSearchTagsResult.class);
    }

}
