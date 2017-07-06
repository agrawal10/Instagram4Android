package dev.niekirk.com.instagram4android.requests.payload;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by root on 08/06/17.
 */

@Getter
@Setter
@ToString(callSuper = true)
public class InstagramCheckUsernameResult extends StatusResult {

    private boolean available;
    private String username;
    private String error;
    private String error_type;

}
