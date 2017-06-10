package com.opendev.cn.instagram4android.requests.internal;

import android.util.Log;

import com.opendev.cn.instagram4android.InstagramConstants;
import com.opendev.cn.instagram4android.requests.InstagramRequest;
import com.opendev.cn.instagram4android.requests.payload.StatusResult;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import lombok.AllArgsConstructor;

/**
 * Created by root on 09/06/17.
 */

@AllArgsConstructor
public class InstagramUploadVideoJobRequest extends InstagramRequest<StatusResult> {

    private String uploadId;
    private String uploadUrl;
    private String uploadJob;
    private File videoFile;


    @Override
    public String getUrl() {
        return uploadUrl;
    }

    @Override
    public String getMethod() {
        return "POST";
    }

    @Override
    public StatusResult execute() throws IOException {

        String url = getUrl();
        Log.d("UPLOAD", "URL Upload: " + url);

        /*
        HttpPost post = new HttpPost(url);
        post.addHeader("X-IG-Capabilities", "3Q4=");
        post.addHeader("X-IG-Connection-Type", "WIFI");
        post.addHeader("Cookie2", "$Version=1");
        post.addHeader("Accept-Language", "en-US");
        post.addHeader("Accept-Encoding", "gzip, deflate");
        post.addHeader("Content-Type", "application/octet-stream");
        post.addHeader("Session-ID", uploadId);
        post.addHeader("Connection", "keep-alive");
        post.addHeader("Content-Disposition", "attachment; filename=\"video.mp4\"");
        post.addHeader("job", uploadJob);
        post.addHeader("Host", "upload.instagram.com");
        post.addHeader("User-Agent", InstagramConstants.USER_AGENT);




        log.info("User-Agent: " + InstagramConstants.USER_AGENT);

        try (FileInputStream is = new FileInputStream(videoFile)) {
            byte[] videoData = MyStreamUtils.readContentBytes(is);

            //TODO: long ranges? need to handle?
            int requestSize = (int) Math.floor(videoData.length / 4.0);
            int lastRequestExtra = (int) (videoData.length - (requestSize * 3));


            for (int i = 0; i < 4; i++) {

                int start = i * requestSize;
                int end;
                if (i == 3) {
                    end = i * requestSize + lastRequestExtra;
                } else {
                    end = (i + 1) * requestSize;
                }

                int actualLength = (i == 3 ? lastRequestExtra : requestSize);

                String contentRange = String.format("bytes %s-%s/%s", start, end - 1, videoData.length);
                //post.setHeader("Content-Length", String.valueOf(end - start));
                post.setHeader("Content-Range", contentRange);

                byte[] range = Arrays.copyOfRange(videoData, start, start + actualLength);
                log.info("Total is " + videoData.length + ", sending " + actualLength + " (starting from " + start + ") -- " + range.length + " bytes.");

                post.setEntity(EntityBuilder.create().setBinary(range).build());

                try (CloseableHttpResponse response = api.getClient().execute(post)) {
                    int resultCode = response.getStatusLine().getStatusCode();
                    String content = EntityUtils.toString(response.getEntity());
                    log.info("Result of part " + i + ": " + content);

                    post.releaseConnection();
                    response.close();

                    if (resultCode != 200 && resultCode != 201) {
                        throw new IllegalStateException("Failed uploading video (" + resultCode + "): " + content);
                    }

                }

            }

            return new StatusResult("ok");
        }

        */
        return new StatusResult("ok");
    }

    @Override
    public String getPayload() {
        return null;
    }

    @Override
    public StatusResult parseResult(int statusCode, String content) {
        return parseJson(statusCode, content, StatusResult.class);
    }

}
