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
public class InstagramLoginPayload {
    private String username;
    private String phone_id;
    private String _csrftoken;
    private String guid;
    private String device_id;
    private String password;
    private int login_attempt_account = 0;


}
