package dev.niekirk.com.instagram4android.requests;

import dev.niekirk.com.instagram4android.requests.payload.StatusResult;

import lombok.SneakyThrows;

/**
 * Created by root on 09/06/17.
 */

public class InstagramGetInboxRequest extends InstagramGetRequest<StatusResult> {

    @Override
    public String getUrl() {
        return "direct_v2/inbox/?";
    }

    @Override
    public String getPayload() {
        return null;
    }

    @Override
    @SneakyThrows
    public StatusResult parseResult(int statusCode, String content) {
        return parseJson(statusCode, content, StatusResult.class);
    }

}
