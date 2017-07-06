package dev.niekirk.com.instagram4android.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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

    public static String generateQueryParams(Map<String, String> params) {

        List<String> parameters = new ArrayList<>();

        for(String key : params.keySet()) {
            parameters.add(key + "=" + params.get(key));
        }

        if(parameters.size() < 2) {
            return parameters.get(0);
        } else {
            String finalResult = "";
            for(String q : parameters) {
                finalResult += q + "&";
            }
            return finalResult.substring(0, finalResult.length() - 2);
        }

    }
}
