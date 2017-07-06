package dev.niekirk.com.instagram4android.requests;

import dev.niekirk.com.instagram4android.requests.payload.StatusResult;

/**
 * Created by root on 08/06/17.
 */

public class InstagramAutoCompleteUserListRequest extends InstagramGetRequest<StatusResult> {

    @Override
    public String getUrl() {
        return "friendships/autocomplete_user_list/";
    }

    @Override
    public StatusResult parseResult(int resultCode, String content) {
        return parseJson(resultCode, content, StatusResult.class);
    }
}
