package com.opendev.cn.instagram4android.requests;

import com.opendev.cn.instagram4android.requests.payload.StatusResult;

import lombok.SneakyThrows;

/**
 * Created by root on 09/06/17.
 */

public class InstagramTimelineFeedRequest extends InstagramGetRequest<StatusResult> {

    @Override
    public String getUrl() {
        return "feed/timeline/";
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
