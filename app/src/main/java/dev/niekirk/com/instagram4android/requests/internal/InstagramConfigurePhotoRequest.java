package dev.niekirk.com.instagram4android.requests.internal;

import android.graphics.Bitmap;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.niekirk.com.instagram4android.InstagramConstants;
import dev.niekirk.com.instagram4android.requests.InstagramPostRequest;
import dev.niekirk.com.instagram4android.requests.payload.InstagramConfigurePhotoResult;

import java.io.File;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;

/**
 * Created by root on 09/06/17.
 */

@AllArgsConstructor
public class InstagramConfigurePhotoRequest extends InstagramPostRequest<InstagramConfigurePhotoResult> {

    private Bitmap mediaFile;
    private String uploadId;
    private String caption;

    @Override
    public String getUrl() {
        return "media/configure/?";
    }

    @Override
    @SneakyThrows
    public String getPayload() {

        Map<String, Object> likeMap = new LinkedHashMap<>();
        likeMap.put("_csrftoken", api.getOrFetchCsrf(null));
        likeMap.put("media_folder", "Instagram");
        likeMap.put("source_type", 4);
        likeMap.put("_uid", api.getUserId());
        likeMap.put("_uuid", api.getUuid());
        likeMap.put("caption", caption);
        likeMap.put("upload_id", uploadId);

        Map<String, Object> deviceMap = new LinkedHashMap<>();
        deviceMap.put("manufacturer", InstagramConstants.DEVICE_MANUFACTURER);
        deviceMap.put("model", InstagramConstants.DEVICE_MODEL);
        deviceMap.put("android_version", InstagramConstants.DEVICE_ANDROID_VERSION);
        deviceMap.put("android_release", InstagramConstants.DEVICE_ANDROID_RELEASE);
        likeMap.put("device", deviceMap);

        Map<String, Object> editsMap = new LinkedHashMap<>();
        editsMap.put("crop_original_size", Arrays.asList((double) mediaFile.getWidth(), (double) mediaFile.getHeight()));
        editsMap.put("crop_center", Arrays.asList((double) 0, (double) 0));
        editsMap.put("crop_zoom", 1.0);
        likeMap.put("edits", editsMap);

        Map<String, Object> extraMap = new LinkedHashMap<>();
        extraMap.put("source_width", mediaFile.getWidth());
        extraMap.put("source_height", mediaFile.getHeight());
        likeMap.put("extra", extraMap);

        ObjectMapper mapper = new ObjectMapper();
        String payloadJson = mapper.writeValueAsString(likeMap);

        return payloadJson;
    }

    @Override
    @SneakyThrows
    public InstagramConfigurePhotoResult parseResult(int statusCode, String content) {
        return parseJson(statusCode, content, InstagramConfigurePhotoResult.class);
    }

}
