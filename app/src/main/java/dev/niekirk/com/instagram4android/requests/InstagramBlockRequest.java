package dev.niekirk.com.instagram4android.requests;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.niekirk.com.instagram4android.requests.payload.StatusResult;

import java.util.LinkedHashMap;
import java.util.Map;

import lombok.SneakyThrows;
import okhttp3.HttpUrl;

/**
 * Created by root on 08/06/17.
 */

public class InstagramBlockRequest extends InstagramPostRequest<StatusResult> {

    private long userId;

    @Override
    public String getUrl() {
        return "friendships/block/" + userId + "/";
    }

    @Override
    @SneakyThrows
    public String getPayload() {

        HttpUrl httpUrl = HttpUrl.parse(getUrl());

        Map<String, Object> likeMap = new LinkedHashMap<>();
        likeMap.put("_uuid", api.getUuid());
        likeMap.put("_uid", api.getUserId());
        likeMap.put("user_id", userId);
        likeMap.put("_csrftoken", api.getOrFetchCsrf(httpUrl));

        ObjectMapper mapper = new ObjectMapper();
        String payloadJson = mapper.writeValueAsString(likeMap);

        return payloadJson;

    }

    @Override
    public StatusResult parseResult(int resultCode, String content) {
        return parseJson(resultCode, content, StatusResult.class);
    }
}
