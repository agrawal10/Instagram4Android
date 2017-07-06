package dev.niekirk.com.instagram4android.requests.payload;

import java.util.List;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by root on 09/06/17.
 */

@Getter
@Setter
@ToString(callSuper = true)
public class InstagramUploadVideoResult extends StatusResult {
    private String upload_id;
    private List<Map<String, Object>> video_upload_urls;

}
