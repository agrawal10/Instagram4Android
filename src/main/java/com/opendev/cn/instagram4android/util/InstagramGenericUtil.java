package com.opendev.cn.instagram4android.util;

import java.util.UUID;

/**
 * Created by root on 08/06/17.
 */

public class InstagramGenericUtil {


    public static String generateUuid(boolean dash) {

        String uuid = UUID.randomUUID().toString();

        if(dash) {
            return uuid;
        }

        return uuid.replaceAll("-", "");

    }

}
