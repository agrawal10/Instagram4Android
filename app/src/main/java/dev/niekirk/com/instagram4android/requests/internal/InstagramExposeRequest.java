package dev.niekirk.com.instagram4android.requests.internal;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.niekirk.com.instagram4android.requests.InstagramPostRequest;
import dev.niekirk.com.instagram4android.requests.payload.StatusResult;

import java.util.LinkedHashMap;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;

/**
 * Created by root on 09/06/17.
 */

@AllArgsConstructor
public class InstagramExposeRequest extends InstagramPostRequest<StatusResult> {

    @Override
    public String getUrl() {
        return "qe/expose/";
    }

    @Override
    @SneakyThrows
    public String getPayload() {

        Map<String, Object> likeMap = new LinkedHashMap<>();
        likeMap.put("_uuid", api.getUuid());
        likeMap.put("_uid", api.getUserId());
        likeMap.put("id", api.getUserId());
        likeMap.put("_csrftoken", api.getOrFetchCsrf(null));
        likeMap.put("experiment", "ig_android_profile_contextual_feed");

        ObjectMapper mapper = new ObjectMapper();
        String payloadJson = mapper.writeValueAsString(likeMap);

        return payloadJson;
    }

    @Override
    @SneakyThrows
    public StatusResult parseResult(int statusCode, String content) {
        return parseJson(statusCode, content, StatusResult.class);
    }

}
