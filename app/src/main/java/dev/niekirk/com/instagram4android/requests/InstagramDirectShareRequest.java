package dev.niekirk.com.instagram4android.requests;

import android.text.TextUtils;

import dev.niekirk.com.instagram4android.InstagramConstants;
import dev.niekirk.com.instagram4android.requests.payload.StatusResult;
import dev.niekirk.com.instagram4android.util.InstagramGenericUtil;

import java.io.IOException;
import java.util.List;

import lombok.Builder;
import lombok.NonNull;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by root on 09/06/17.
 */

@Builder(builderClassName = "DirectShareRequestBuilder", builderMethodName = "internalBuilder")
public class InstagramDirectShareRequest extends InstagramRequest<StatusResult> {

    @NonNull
    private ShareType shareType;
    @NonNull
    private List<String> recipients;
    /**
     * The media ID in instagram's internal format (ie "223322332233_22332").
     */
    private String mediaId;
    private String message;

    @Override
    public String getUrl() {

        String result;
        switch (shareType) {
            case MESSAGE:
                result = "direct_v2/threads/broadcast/text/";
                break;
            case MEDIA:
                result = "direct_v2/threads/broadcast/media_share/?media_type=photo";
                break;
            default:
                throw new IllegalArgumentException("Invalid shareType parameter value: " + shareType);
        }
        return result;

    }

    @Override
    public String getMethod() {
        return "POST";
    }

    // Construct relevent multipart body, build request and execute it
    @Override
    public StatusResult execute() throws IOException {

        String recipients = "\"" + TextUtils.join("\",\"", this.recipients.toArray(new String[0])) + "\"";
        System.out.println("NULL" + message);
        MultipartBody body;
        if (shareType == ShareType.MEDIA) {
             body = new MultipartBody.Builder(api.getUuid())
                    .addFormDataPart("media_id", mediaId)
                    .addFormDataPart("recipient_users", "[[" + recipients + "]]")
                    .addFormDataPart("client_context", InstagramGenericUtil.generateUuid(true))
                    .addFormDataPart("thread_ids", "[]")
                    .addFormDataPart("text", message.isEmpty() ? "" : message)
                    .build();
        } else {
            System.out.println("EXECUTED");
            body = new MultipartBody.Builder(api.getUuid())
                    .addFormDataPart("recipient_users", "[[" + recipients + "]]")
                    .addFormDataPart("client_context", InstagramGenericUtil.generateUuid(true))
                    .addFormDataPart("thread_ids", "[]")
                    .addFormDataPart("text", message)
                    .build();
            System.out.println("ELYTOI" + body.part(3).toString());
        }

        Request post = createHttpRequest(body);

        Response response = api.getClient().newCall(post).execute();
        api.setLastResponse(response);

        int resultCode = response.code();
        String content = response.body().string();

        StatusResult result = parseResult(resultCode, content);

        return result;

    }

    /*
    * Builds an HTTP request using the prvided MultipartBody
    *
    * @param MultipartBody body The body used as the POST payload
    * @returns Request the completed HTTP web request
    * */
    protected Request createHttpRequest(MultipartBody body) {

        String url = InstagramConstants.API_URL + getUrl();

        Request request = new Request.Builder()
                .url(url)
                .addHeader("User-Agent", InstagramConstants.USER_AGENT)
                .addHeader("Connection", "keep-alive")
                .addHeader("Proxy-Connection", "keep-alive")
                .addHeader("Accept", "*/*")
                .addHeader("Accept-Language", "en-US")
                .post(body)
                .build();

        return request;

    }

    protected void init() {
        switch (shareType) {
            case MEDIA:
                if (mediaId == null || mediaId.isEmpty()) {
                    throw new IllegalArgumentException("mediaId cannot be null or empty.");
                }
                break;
            case MESSAGE:
                if (message == null || message.isEmpty()) {
                    throw new IllegalArgumentException("message cannot be null or empty.");
                }
                break;
            default:
                break;
        }
    }

    public static Builder builder(ShareType shareType, List<String> recipients) {
        Builder b = new Builder();
        b.shareType(shareType).recipients(recipients);
        return b;
    }

    public static class Builder extends DirectShareRequestBuilder {
        Builder() {
            super();
        }

        @Override
        public InstagramDirectShareRequest build() {
            InstagramDirectShareRequest i = super.build();
            i.init();
            return i;
        }
    }

    @Override
    public StatusResult parseResult(int resultCode, String content) {
        return parseJson(resultCode, content, StatusResult.class);
    }

    public enum ShareType {
        MESSAGE, MEDIA
    }
}
