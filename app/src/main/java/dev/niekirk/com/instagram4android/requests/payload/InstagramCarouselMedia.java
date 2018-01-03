package dev.niekirk.com.instagram4android.requests.payload;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by root on 03/01/18.
 */

@Getter
@Setter
@ToString(callSuper = true)
public class InstagramCarouselMedia implements Serializable {

    public String pk;
    public String id;
    public String carousel_parent_id;
    public InstagramImageVersions_2 image_versions2;
    public List<InstagramVideoVersions> video_versions;
    public int media_type;

}
