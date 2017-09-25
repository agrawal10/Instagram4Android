package dev.niekirk.com.instagram4android.requests.payload;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by root on 21/09/17.
 */

@Getter
@Setter
@ToString(callSuper = true)
public class InstagramImageVersions_2 implements Serializable {

    public List<InstagramCandidate> candidates;
    public String trace_token;

}
