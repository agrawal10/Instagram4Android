package dev.niekirk.com.instagram4android.requests;

import com.fasterxml.jackson.databind.ObjectMapper;

import dev.niekirk.com.instagram4android.requests.payload.InstagramFbLoginPayload;
import dev.niekirk.com.instagram4android.requests.payload.InstagramLoginResult;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;

/**
 * Created by root on 28/12/17.
 */

@AllArgsConstructor
public class InstagramFbLoginRequest extends InstagramPostRequest<InstagramLoginResult> {

    private InstagramFbLoginPayload payload;

    @Override
    public String getUrl() {
        return "fb/facebook_signup/";
    }

    @Override
    @SneakyThrows
    public String getPayload() {
        ObjectMapper mapper = new ObjectMapper();
        String payloadJson = mapper.writeValueAsString(payload);

        return payloadJson;
    }

    @Override
    @SneakyThrows
    public InstagramLoginResult parseResult(int statusCode, String content) {
        return parseJson(statusCode, content, InstagramLoginResult.class);
    }

    @Override
    public boolean requiresLogin() {
        return false;
    }
}
