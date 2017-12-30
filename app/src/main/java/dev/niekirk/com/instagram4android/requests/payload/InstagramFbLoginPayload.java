package dev.niekirk.com.instagram4android.requests.payload;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by root on 28/12/17.
 */

@Getter
@Setter
@ToString(callSuper = true)
@Builder
public class InstagramFbLoginPayload {
    private boolean dryrun;
    //private String username;
    private String phone_id;
    private String adid;
    private String device_id;
    private String waterfall_id;
    private String fb_access_token;
    //private String big_blue_token;
    //private boolean allow_contacts_sync;

}
