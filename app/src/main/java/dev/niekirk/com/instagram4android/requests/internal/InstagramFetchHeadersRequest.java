package dev.niekirk.com.instagram4android.requests.internal;

import dev.niekirk.com.instagram4android.requests.InstagramGetRequest;
import dev.niekirk.com.instagram4android.requests.payload.StatusResult;
import dev.niekirk.com.instagram4android.util.InstagramGenericUtil;

/**
 * Created by root on 08/06/17.
 */

public class InstagramFetchHeadersRequest extends InstagramGetRequest<StatusResult> {

    @Override
    public String getUrl() {
        return "si/fetch_headers/?challenge_type=signup&guid=" + InstagramGenericUtil.generateUuid(false);
    }

    @Override
    public boolean requiresLogin() {
        return false;
    }

    @Override
    public StatusResult parseResult(int resultCode, String content) {
        return parseJson(resultCode, content, StatusResult.class);
    }
}
