package dev.niekirk.com.instagram4android.requests;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaMetadataRetriever;
import android.media.ThumbnailUtils;
import android.provider.MediaStore;
import android.util.Log;

import dev.niekirk.com.instagram4android.InstagramConstants;
import dev.niekirk.com.instagram4android.requests.internal.InstagramConfigureVideoRequest;
import dev.niekirk.com.instagram4android.requests.internal.InstagramExposeRequest;
import dev.niekirk.com.instagram4android.requests.internal.InstagramUploadVideoJobRequest;
import dev.niekirk.com.instagram4android.requests.payload.InstagramUploadVideoResult;
import dev.niekirk.com.instagram4android.requests.payload.StatusResult;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by root on 09/06/17.
 */

@AllArgsConstructor
@RequiredArgsConstructor
public class InstagramUploadVideoRequest extends InstagramRequest<StatusResult> {

    @NonNull
    private File videoFile;
    @NonNull
    private String caption;
    private File thumbnailFile;

    @Override
    public String getUrl() {
        return "upload/video/";
    }

    @Override
    public String getMethod() {
        return "POST";
    }

    @Override
    public StatusResult execute() throws IOException {

        String uploadId = String.valueOf(System.currentTimeMillis());
        Request post = createHttpRequest(createMultipartBody(uploadId));

        try (Response response = api.getClient().newCall(post).execute()) {
            api.setLastResponse(response);

            int resultCode = response.code();
            String content = response.body().string();
            Log.d("UPLOAD", "First phase result " + resultCode + ": " + content);

            InstagramUploadVideoResult firstPhaseResult = parseJson(content, InstagramUploadVideoResult.class);

            if (!firstPhaseResult.getStatus().equalsIgnoreCase("ok")) {
                throw new RuntimeException("Error happened in video upload session start: " + firstPhaseResult.getMessage());
            }


            String uploadUrl = firstPhaseResult.getVideo_upload_urls().get(3).get("url").toString();
            String uploadJob = firstPhaseResult.getVideo_upload_urls().get(3).get("job").toString();

            StatusResult uploadJobResult = api.sendRequest(new InstagramUploadVideoJobRequest(uploadId, uploadUrl, uploadJob, videoFile));
            Log.d("UPLOAD", "Upload result: " + uploadJobResult);

            if (!uploadJobResult.getStatus().equalsIgnoreCase("ok")) {
                throw new RuntimeException("Error happened in video upload submit job: " + uploadJobResult.getMessage());
            }


            StatusResult thumbnailResult = configureThumbnail(uploadId);

            if (!thumbnailResult.getStatus().equalsIgnoreCase("ok")) {
                throw new IllegalArgumentException("Failed to configure thumbnail: " + thumbnailResult.getMessage());
            }

            return api.sendRequest(new InstagramExposeRequest());


        }
    }

    /**
     * Configures the thumbnails for the given uploadId
     * @param uploadId The session id
     * @return Result
     * @throws Exception
     * @throws IOException
     */
    protected StatusResult configureThumbnail(String uploadId) throws IOException {

        try(FileOutputStream fos = new FileOutputStream(thumbnailFile)) {

            Bitmap thumbnail = ThumbnailUtils.createVideoThumbnail(videoFile.getAbsolutePath(), MediaStore.Video.Thumbnails.FULL_SCREEN_KIND);
            thumbnail.compress(Bitmap.CompressFormat.JPEG, 100, fos);

            MediaMetadataRetriever retriever = new MediaMetadataRetriever();
            retriever.setDataSource(videoFile.getAbsolutePath());
            long timeInMillis = Long.parseLong(retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION));

            holdOn();

            StatusResult thumbnailResult = api.sendRequest(new InstagramUploadPhotoRequest(thumbnailFile, caption, uploadId));
            Log.d("UPLOAD", "Thumbnail result: " + thumbnailResult);

            StatusResult configureResult = api.sendRequest(InstagramConfigureVideoRequest.builder().uploadId(uploadId)
                    .caption(caption)
                    .duration(timeInMillis)
                    .width(thumbnail.getWidth())
                    .height(thumbnail.getHeight())
                    .build());

            Log.d("UPLOAD", "Video configure result: " + configureResult);

            return configureResult;

        }


    }

    /**
     * Create the required multipart entity
     * @param uploadId Session ID
     * @return Entity to submit to the upload
     * @throws IOException
     */
    protected MultipartBody createMultipartBody(String uploadId) throws IOException {
        /*
        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        builder.addTextBody("upload_id", uploadId);
        builder.addTextBody("_uuid", api.getUuid());
        builder.addTextBody("_csrftoken", api.getOrFetchCsrf());
        builder.addTextBody("media_type", "2");
        builder.setBoundary(api.getUuid());

        HttpEntity entity = builder.build();
        */

        return new MultipartBody.Builder(api.getUuid())
                .setType(MultipartBody.FORM)
                .addFormDataPart("upload_id", uploadId)
                .addFormDataPart("_uuid", api.getUuid())
                .addFormDataPart("_csfrtoken", api.getOrFetchCsrf(null))
                .addFormDataPart("media_type", "2")
                .build();
    }

    /**
     * @return http request
     */
    protected Request createHttpRequest(MultipartBody body) {
        String url = InstagramConstants.API_URL + getUrl();
        Log.d("UPLAOD", "URL Upload: " + url);

        /*
        HttpPost post = new HttpPost(url);
        post.addHeader("X-IG-Capabilities", "3Q4=");
        post.addHeader("X-IG-Connection-Type", "WIFI");
        post.addHeader("Host", "i.instagram.com");
        post.addHeader("Cookie2", "$Version=1");
        post.addHeader("Accept-Language", "en-US");
        post.addHeader("Connection", "close");
        post.addHeader("Content-Type", "multipart/form-data; boundary=" + api.getUuid());
        post.addHeader("User-Agent", InstagramConstants.USER_AGENT);
        */

        return new Request.Builder()
                .url(url)
                .addHeader("X-IG-Capabilities", "3Q4=")
                .addHeader("X-IG-Connection-Type", "WIFI")
                .addHeader("Cookie2", "$Version=1")
                .addHeader("Accept-Language", "en-US")
                .addHeader("Host", "i.instagram.com")
                .addHeader("Connection", "close")
                .addHeader("User-Agent", InstagramConstants.USER_AGENT)
                .post(body)
                .build();
    }

    /**
     *
     */
    protected void holdOn() {
        //sad but helps to prevent Transcode Timeout
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getPayload() {
        return null;
    }

    @Override
    public StatusResult parseResult(int statusCode, String content) {
        return parseJson(statusCode, content, StatusResult.class);
    }

    private Bitmap fileToBitmap(File image) {
        return BitmapFactory.decodeFile(image.getAbsolutePath());
    }

}
