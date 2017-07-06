package dev.niekirk.com.instagram4android.util;

import dev.niekirk.com.instagram4android.InstagramConstants;

import org.apache.commons.codec.binary.Hex;

import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import lombok.SneakyThrows;

/**
 * Created by root on 08/06/17.
 */

public class InstagramHashUtil {

    public static final String XLATE = "0123456789abcdef";

    protected static String digest(String codec, String source) {
        try {
            MessageDigest digest = MessageDigest.getInstance(codec);
            byte[] digestBytes = digest.digest(source.getBytes(Charset.forName("UTF-8")));
            return hexlate(digestBytes, digestBytes.length);
        } catch (NoSuchAlgorithmException nsae) {
            throw new RuntimeException(codec + " codec not available");
        }
    }

    public static String md5hex(String source) {
        return digest("MD5", source);
    }

    protected static String hexlate(byte[] bytes, int initialCount) {
        if (bytes == null) {
            return "";
        }

        int count = Math.min(initialCount, bytes.length);
        char[] chars = new char[count * 2];

        for (int i = 0; i < count; ++i) {
            int val = bytes[i];
            if (val < 0) {
                val += 256;
            }
            chars[(2 * i)] = XLATE.charAt(val / 16);
            chars[(2 * i + 1)] = XLATE.charAt(val % 16);
        }

        return new String(chars);
    }

    public static String generateDeviceId(String username, String password) {
        String seed = md5hex(username + password);
        String volatileSeed = "12345";

        return "android-" + md5hex(seed + volatileSeed).substring(0, 16);
    }

    public static String generateHash(String key, String string) {
        SecretKeySpec object = new SecretKeySpec(key.getBytes(), "HmacSHA256");
        try {
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init((Key) object);
            byte[] byteArray = mac.doFinal(string.getBytes("UTF-8"));
            return new String(new Hex().encode(byteArray), "ISO-8859-1");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @SneakyThrows
    public static String generateSignature(String payload) {
        String parsedData = URLEncoder.encode(payload, "UTF-8");

        String signedBody = generateHash(InstagramConstants.API_KEY, payload);

        return "ig_sig_key_version=" + InstagramConstants.API_KEY_VERSION + "&signed_body=" + signedBody + '.'
                + parsedData;

    }

}