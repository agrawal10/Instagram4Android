package dev.niekirk.com.instagram4android.requests.payload;

import java.io.Serializable;
import java.util.Objects;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by root on 08/06/17.
 */

@Getter
@Setter
@ToString(callSuper = true)
public class InstagramUserSummary implements Serializable {
    public boolean is_verified;
    public String profile_pic_id;
    public boolean is_favorite;
    public boolean is_private;
    public String username;
    public long pk;
    public String profile_pic_url;
    public boolean has_anonymous_profile_picture;
    public String full_name;

    @Override
    public int hashCode() {
        return Objects.hash(username, pk);
    }

    @Override
    public boolean equals(Object obj) {

        if (obj == this) return true;
        if (!(obj instanceof InstagramUserSummary)) {
            return false;
        }

        InstagramUserSummary user = (InstagramUserSummary) obj;
        return pk == user.getPk();

    }
}
