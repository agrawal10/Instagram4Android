package dev.niekirk.com.instagram4android.requests.payload;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by root on 08/06/17.
 */

@Getter
@Setter
@ToString(callSuper = true)
public class InstagramSearchUsersResult extends StatusResult {
    private List<InstagramSearchUsersResultUser> users;
    private boolean has_more;
    private int num_results;
}
