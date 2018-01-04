package dev.niekirk.com.instagram4android.requests;

import android.util.Base64;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import dev.niekirk.com.instagram4android.InstagramConstants;
import dev.niekirk.com.instagram4android.requests.payload.InstagramFeedResult;

import dev.niekirk.com.instagram4android.requests.payload.InstagramTimelineFeedResult;
import dev.niekirk.com.instagram4android.util.InstagramHashUtil;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import okhttp3.FormBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by root on 09/06/17.
 */

@NoArgsConstructor
@AllArgsConstructor
public class InstagramTimelineFeedRequest extends InstagramRequest<InstagramTimelineFeedResult> {

    private String maxId = null;
    private List<String> seen_posts = null;

    @Override
    public String getUrl() {
            return "feed/timeline/";
    }

    @Override
    public String getMethod() {
        return "POST";
    }

    @Override
    public InstagramTimelineFeedResult execute() throws IOException {
        System.out.println("EXEC CALLED");
        Request request = createRequest(createRequestBody());


        try(Response response = api.getClient().newCall(request).execute()) {
            api.setLastResponse(response);

            int responseCode = response.code();
            String content = response.body().string();

            return parseResult(responseCode, content);

        }
    }

    private Request createRequest(RequestBody body) {

        String url = InstagramConstants.API_URL + getUrl();

        return new Request.Builder()
                .url(url)
                .header("X-IG-Capabilities", "3Q4=")
                .header("X-IG-Connection-Type", "WIFI")
                .header("Cookie2", "$Version=1")
                .header("Accept-Language", "en-US")
                .header("Connection", "close")
                .header("User-Agent", InstagramConstants.USER_AGENT)
                .header("X-Ads-Opt-Out", "0")
                .header("X-DEVICE-ID", InstagramHashUtil.generateDeviceId("gsdgds", "gsdgsd"))
                .header("X-Google-AD-ID", InstagramHashUtil.generateDeviceId("ggdasgdsa", "gsfdsagas"))
                .header("X-IG-INSTALLED-APPS", Base64.encodeToString("{\"1\":0,\"2\":0}".getBytes(), Base64.URL_SAFE | Base64.NO_WRAP))
                .post(body)
                .build();
    }

    @SneakyThrows
    private RequestBody createRequestBody() {

        TimeZone timezone = TimeZone.getTimeZone("Europe/Paris");

        if(maxId != null && !maxId.isEmpty()) {
            if(seen_posts == null) {
                return new FormBody.Builder()
                        .add("_uuid", api.getUuid())
                        .add("_csrftoken", api.getOrFetchCsrf(null))
                        .add("is_prefetch", "0")
                        .add("battery_level", "100")
                        .add("is_charging", "1")
                        .add("phone_id", InstagramHashUtil.generateDeviceId("fsges", "gsrges"))
                        .add("timezone_offset", "" + timezone.getOffset(Calendar.ZONE_OFFSET))
                        .add("is_pull_to_refresh", "0")
                        .add("seen_posts", "")
                        .add("unseen_posts", "")
                        .add("feed_view_info", "")
                        .add("reason", "pagination")
                        .add("max_id", maxId)
                        .build();
            } else {
                String posts = "";
                for(String post : seen_posts) {
                    posts += post + ",";
                }
                return new FormBody.Builder()
                        .add("_uuid", api.getUuid())
                        .add("_csrftoken", api.getOrFetchCsrf(null))
                        .add("is_prefetch", "0")
                        .add("battery_level", "100")
                        .add("is_charging", "1")
                        .add("phone_id", InstagramHashUtil.generateDeviceId("fsges", "gsrges"))
                        .add("timezone_offset", "" + timezone.getOffset(Calendar.ZONE_OFFSET))
                        .add("is_pull_to_refresh", "0")
                        .add("seen_posts", posts)
                        .add("unseen_posts", "")
                        .add("feed_view_info", "")
                        .add("reason", "pagination")
                        .add("max_id", maxId)
                        .build();
            }
        } else {
            System.out.println("INIT");
            return new FormBody.Builder()
                    .add("_uuid", api.getUuid())
                    .add("_csrftoken", api.getOrFetchCsrf(null))
                    .add("is_prefetch", "0")
                    .add("battery_level", "100")
                    .add("is_charging", "1")
                    .add("phone_id", InstagramHashUtil.generateDeviceId("fsges", "gsrges"))
                    .add("timezone_offset", "" + timezone.getOffset(Calendar.ZONE_OFFSET))
                    .add("is_pull_to_refresh", "0")
                    .add("seen_posts", "")
                    .add("unseen_posts", "")
                    .add("feed_view_info", "")
                    .build();
        }

    }

    @Override
    @SneakyThrows
    public InstagramTimelineFeedResult parseResult(int statusCode, String content) {
        return parseJson(statusCode, content, InstagramTimelineFeedResult.class);
    }

}
