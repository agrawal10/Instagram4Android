package dev.niekirk.com.instagram4android.requests;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import dev.niekirk.com.instagram4android.InstagramConstants;
import dev.niekirk.com.instagram4android.requests.internal.InstagramConfigurePhotoRequest;
import dev.niekirk.com.instagram4android.requests.internal.InstagramExposeRequest;
import dev.niekirk.com.instagram4android.requests.payload.InstagramConfigurePhotoResult;
import dev.niekirk.com.instagram4android.requests.payload.StatusResult;

import java.io.File;
import java.io.IOException;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by root on 09/06/17.
 */

@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class InstagramUploadPhotoRequest extends InstagramRequest<InstagramConfigurePhotoResult> {

    @NonNull
    private File imageFile;

    @NonNull
    private String caption;
    private String uploadId;

    @Override
    public String getUrl() {
        return "upload/photo/";
    }

    @Override
    public String getMethod() {
        return "POST";
    }

    @Override
    public InstagramConfigurePhotoResult execute() throws IOException {

        if (uploadId == null) {
            uploadId = String.valueOf(System.currentTimeMillis());
        }

        Request request = createHttpRequest(createMultipartBody());

        try (Response response = api.getClient().newCall(request).execute()) {
            api.setLastResponse(response);

            int resultCode = response.code();
            String content = response.body().string();

            Log.d("Photo Upload result: ", resultCode + ", " + content);

            StatusResult result = parseResult(resultCode, content);

            if (!result.getStatus().equalsIgnoreCase("ok")) {
                throw new RuntimeException("Error happened in photo upload: " + result.getMessage());
            }


            InstagramConfigurePhotoResult configurePhotoResult = api.sendRequest(new InstagramConfigurePhotoRequest(fileToBitmap(imageFile), uploadId, caption));

            Log.d("UPLOAD", "Result: " + configurePhotoResult);
            if (!configurePhotoResult.getStatus().equalsIgnoreCase("ok")) {
                throw new IllegalArgumentException("Failed to configure image: " + configurePhotoResult.getMessage());
            }

            StatusResult exposeResult = api.sendRequest(new InstagramExposeRequest());
            Log.d("EXPOSE: ", "Expose result: " + exposeResult);
            if (!exposeResult.getStatus().equalsIgnoreCase("ok")) {
                throw new IllegalArgumentException("Failed to expose image: " + exposeResult.getMessage());
            }

            return configurePhotoResult;
        }
    }

    /**
     * Creates required multipart entity with the image binary
     * @return HttpEntity to send on the post
     * @throws IOException
     */
    protected MultipartBody createMultipartBody() throws IOException {


        /*
        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        builder.addTextBody("upload_id", uploadId);
        builder.addTextBody("_uuid", api.getUuid());
        builder.addTextBody("_csrftoken", api.getOrFetchCsrf());
        builder.addTextBody("image_compression", "{\"lib_name\":\"jt\",\"lib_version\":\"1.3.0\",\"quality\":\"87\"}");
        builder.addBinaryBody("photo", imageFile, ContentType.APPLICATION_OCTET_STREAM, "pending_media_" + uploadId + ".jpg");
        builder.setBoundary(api.getUuid());

        HttpEntity entity = builder.build();
        return entity;
        */

        MultipartBody body = new MultipartBody.Builder(api.getUuid())
                .setType(MultipartBody.FORM)
                .addFormDataPart("upload_id", uploadId)
                .addFormDataPart("_uuid", api.getUuid())
                .addFormDataPart("_csfrtoken", api.getOrFetchCsrf(null))
                .addFormDataPart("image_compression", "{\"lib_name\":\"jt\",\"lib_version\":\"1.3.0\",\"quality\":\"87\"}")
                .addFormDataPart("photo", "pending_media_" + uploadId + ".jpg", RequestBody.create(MediaType.parse("application/octet-stream"), imageFile))
                .build();

        return body;
    }

    /**
     * Creates the Post Request
     * @return Request
     */
    protected Request createHttpRequest(MultipartBody body) {


        String url = InstagramConstants.API_URL + getUrl();
        /*
        HttpPost post = new HttpPost(url);
        post.addHeader("X-IG-Capabilities", "3Q4=");
        post.addHeader("X-IG-Connection-Type", "WIFI");
        post.addHeader("Cookie2", "$Version=1");
        post.addHeader("Accept-Language", "en-US");
        post.addHeader("Accept-Encoding", "gzip, deflate");
        post.addHeader("Connection", "close");
        post.addHeader("Content-Type", "multipart/form-data; boundary=" + api.getUuid());
        post.addHeader("User-Agent", InstagramConstants.USER_AGENT);
        return post;
        */

        return new Request.Builder()
                .url(url)
                .addHeader("X-IG-Capabilities", "3Q4=")
                .addHeader("X-IG-Connection-Type", "WIFI")
                .addHeader("Cookie2", "$Version=1")
                .addHeader("Accept-Language", "en-US")
                .addHeader("Accept-Encoding", "gzip, deflate")
                .addHeader("Connection", "close")
                .addHeader("User-Agent", InstagramConstants.USER_AGENT)
                .post(body)
                .build();

    }

    @Override
    public InstagramConfigurePhotoResult parseResult(int statusCode, String content) {
        return parseJson(statusCode, content, InstagramConfigurePhotoResult.class);
    }

    private Bitmap fileToBitmap(File image) {
        return BitmapFactory.decodeFile(image.getAbsolutePath());
    }

}
