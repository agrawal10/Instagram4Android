package dev.niekirk.com.instagram4android.requests;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.niekirk.com.instagram4android.requests.payload.InstagramCheckUsernameResult;
import dev.niekirk.com.instagram4android.requests.payload.StatusResult;

import java.util.LinkedHashMap;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.SneakyThrows;

/**
 * Created by root on 09/06/17.
 */

@AllArgsConstructor
public class InstagramCheckUsernameRequest extends InstagramPostRequest<InstagramCheckUsernameResult> {

    @NonNull
    private String username;

    @Override
    public String getUrl() {
        return "users/check_username/";
    }

    @Override
    @SneakyThrows
    public String getPayload() {

        ObjectMapper mapper = new ObjectMapper();

        Map<String, Object> payloadMap = new LinkedHashMap<>();
        payloadMap.put("username", username);
        payloadMap.put("_csrftoken", api.getOrFetchCsrf(null));

        String payloadJson = mapper.writeValueAsString(payloadMap);

        return payloadJson;

    }

    @Override
    public InstagramCheckUsernameResult parseResult(int resultCode, String content) {
        return this.parseJson(content, InstagramCheckUsernameResult.class);
    }

    @Override
    public boolean requiresLogin() {
        return false;
    }
}
