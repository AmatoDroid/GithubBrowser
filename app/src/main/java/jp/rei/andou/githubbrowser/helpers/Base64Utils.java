package jp.rei.andou.githubbrowser.helpers;

import android.util.Base64;

import java.nio.charset.StandardCharsets;

public class Base64Utils {

    public static String encodeToBase64(String input) {
        byte[] bytes = input.getBytes(StandardCharsets.UTF_8);
        return "Basic " + Base64.encodeToString(bytes, Base64.NO_WRAP);
    }

}
