package dev.niekirk.com.instagram4android.requests;

import dev.niekirk.com.instagram4android.requests.payload.InstagramSearchUsernameResult;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;

/**
 * Created by root on 09/06/17.
 */

@AllArgsConstructor
public class InstagramSearchUsernameRequest extends InstagramGetRequest<InstagramSearchUsernameResult> {

    private String username;

    @Override
    public String getUrl() {
        return "users/" + username + "/usernameinfo/";
    }

    @Override
    @SneakyThrows
    public InstagramSearchUsernameResult parseResult(int statusCode, String content) {
        return parseJson(statusCode, content, InstagramSearchUsernameResult.class);
    }

}
