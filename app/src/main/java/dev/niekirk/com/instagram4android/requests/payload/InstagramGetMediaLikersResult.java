package dev.niekirk.com.instagram4android.requests.payload;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by Charlie on 13/06/17.
 */

@Getter
@Setter
@ToString(callSuper = true)
public class InstagramGetMediaLikersResult extends StatusResult {

    public int user_count;

    public List<InstagramUserSummary> users;
}
