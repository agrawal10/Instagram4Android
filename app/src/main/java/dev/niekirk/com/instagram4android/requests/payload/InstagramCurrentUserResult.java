package dev.niekirk.com.instagram4android.requests.payload;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by Charlie on 15/06/17.
 */

@Getter
@Setter
@ToString(callSuper = true)
public class InstagramCurrentUserResult extends StatusResult {
    private InstagramUser user;

}
