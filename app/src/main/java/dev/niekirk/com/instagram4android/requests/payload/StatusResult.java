package dev.niekirk.com.instagram4android.requests.payload;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by root on 08/06/17.
 */

@Getter
@Setter
@ToString(callSuper = true)
@RequiredArgsConstructor
@NoArgsConstructor
public class StatusResult {
    @NonNull
    private String status;
    private String message;
}
