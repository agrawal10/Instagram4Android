package dev.niekirk.com.instagram4android.requests;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.niekirk.com.instagram4android.requests.payload.InstagramLikeResult;

import java.util.LinkedHashMap;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;

/**
 * Created by root on 09/06/17.
 */

@AllArgsConstructor
public class InstagramLikeRequest extends InstagramPostRequest<InstagramLikeResult> {

    private long mediaId;

    @Override
    public String getUrl() {
        return "media/" + mediaId + "/like/";
    }

    @Override
    @SneakyThrows
    public String getPayload() {

        Map<String, Object> likeMap = new LinkedHashMap<>();
        likeMap.put("_uuid", api.getUuid());
        likeMap.put("_uid", api.getUserId());
        likeMap.put("_csrftoken", api.getOrFetchCsrf(null));
        likeMap.put("media_id", mediaId);

        ObjectMapper mapper = new ObjectMapper();
        String payloadJson = mapper.writeValueAsString(likeMap);

        return payloadJson;
    }

    @Override
    public InstagramLikeResult parseResult(int resultCode, String content) {
        return parseJson(resultCode, content, InstagramLikeResult.class);
    }
}
