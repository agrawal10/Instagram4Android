package dev.niekirk.com.instagram4android.requests.payload;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by root on 08/06/17.
 */

@Getter
@Setter
@ToString(callSuper = true)
@Builder
public class InstagramSyncFeaturesPayload {
    private String _uuid;
    private long _uid;
    private long id;
    private String _csrftoken;
    private String experiments;
}
