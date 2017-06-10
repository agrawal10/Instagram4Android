package com.opendev.cn.instagram4android.requests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.opendev.cn.instagram4android.requests.payload.StatusResult;

import java.util.LinkedHashMap;
import java.util.Map;

import lombok.SneakyThrows;

/**
 * Created by root on 09/06/17.
 */

public class InstagramFollowRequest extends InstagramPostRequest<StatusResult> {

    private long userId;

    @Override
    public String getUrl() {
        return "friendships/create/" + userId + "/";
    }

    @Override
    @SneakyThrows
    public String getPayload() {

        Map<String, Object> likeMap = new LinkedHashMap<>();
        likeMap.put("_uuid", api.getUuid());
        likeMap.put("_uid", api.getUserId());
        likeMap.put("user_id", userId);
        likeMap.put("_csrftoken", api.getOrFetchCsrf(null));

        ObjectMapper mapper = new ObjectMapper();
        String payloadJson = mapper.writeValueAsString(likeMap);

        return payloadJson;

    }

    @Override
    public StatusResult parseResult(int resultCode, String content) {
        return parseJson(resultCode, content, StatusResult.class);
    }
}
