package com.paraxco.commontools.Utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.paraxco.commontools.R;


/**
 * Created by Amin on 5/17/2017.
 */

public class SharedPreferenceHelper {
    private static final String USER_ID = "user_id";
    private static final String LOGED_IN = "loged_in";
    private static final String ONE_SIGNAL_ID = "one_signal_id";
    private static final String SEND_TOKEN_NEEDE = "send_token_needed";
    private static final String NOTIFICATION_COUNT = "notification_count";
    private static final String CURRENT_SATUDENT_ID = "current_student_id";

    private final Context context;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;


    public SharedPreferenceHelper(Context context) {
        if (context == null)
            throw new NullPointerException("Attemp to create SharedPreferenceHelper with Null context");
        this.context = context;
        sharedPreferences = getSharedPreferences();
        editor = sharedPreferences.edit();
    }

    private SharedPreferences getSharedPreferences() {
        return context.getSharedPreferences(
                context.getPackageName(), Context.MODE_PRIVATE);
    }

    public void setNotificationCount(int count) {
        editor.putInt(NOTIFICATION_COUNT, count);
        editor.commit();
    }

    public int getNotificationCount() {
        return sharedPreferences.getInt(NOTIFICATION_COUNT, 0);
    }




}
