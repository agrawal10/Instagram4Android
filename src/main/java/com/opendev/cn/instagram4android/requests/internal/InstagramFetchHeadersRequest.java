package com.opendev.cn.instagram4android.requests.internal;

import com.opendev.cn.instagram4android.requests.InstagramGetRequest;
import com.opendev.cn.instagram4android.requests.payload.StatusResult;
import com.opendev.cn.instagram4android.util.InstagramGenericUtil;

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
