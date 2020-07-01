package com.kaizen.nightfolks.controller.utility;

import android.content.Context;
import android.os.Build;
import android.provider.Settings;

public class DeviceIdentifier {
    //Maybe not VERY Unique
    public static String getUniqueIdentifierFromBuild() {
        return Build.BOARD.length() % 10 + Build.BRAND.length()
                % 10 + Build.DEVICE.length() % 10 +
                Build.DISPLAY.length() % 10 + Build.HOST.length() % 10 + Build.ID.length()
                % 10 + Build.MANUFACTURER.length() % 10 +
                Build.MODEL.length() % 10 + Build.PRODUCT.length() % 10 + Build.TAGS.length()
                % 10 + Build.TYPE + Build.USER.length() % 10;
    }

    //Discouraged
    public static String getUniqueIdentifierFromSecure(Context context) {
        return Settings.Secure.getString(context.getContentResolver(),
                Settings.Secure.ANDROID_ID);
    }
}
