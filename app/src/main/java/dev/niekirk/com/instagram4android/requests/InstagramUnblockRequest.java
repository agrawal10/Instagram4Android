package dev.niekirk.com.instagram4android.requests;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.niekirk.com.instagram4android.requests.payload.StatusResult;

import java.util.LinkedHashMap;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;

/**
 * Created by root on 09/06/17.
 */

@AllArgsConstructor
public class InstagramUnblockRequest extends InstagramPostRequest<StatusResult> {

    private long userId;

    @Override
    public String getUrl() {
        return "friendships/unblock/" + userId + "/";
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
    @SneakyThrows
    public StatusResult parseResult(int statusCode, String content) {
        return parseJson(statusCode, content, StatusResult.class);
    }

}
