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
public class InstagramSearchTagsResult extends StatusResult {
    private List<InstagramSearchTagsResultTag> results;
    private boolean has_more;
    private int num_results;

}
