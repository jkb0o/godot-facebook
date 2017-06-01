package org.godotengine.godot;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.Application;
import android.app.NotificationManager;
import android.app.TaskStackBuilder;
import android.app.PendingIntent;
import android.os.SystemClock;
import android.content.Intent;
import android.app.Notification;
import android.content.Context;
import android.support.v4.app.NotificationCompat;
import android.content.pm.ApplicationInfo;
import com.godot.game.R;
import android.graphics.BitmapFactory;
import android.graphics.Bitmap;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.FacebookSdk;
import java.util.Currency;
import java.math.BigDecimal;

public class Facebook extends Godot.SingletonBase {
    Activity activity;
    Application app;
    AppEventsLogger logger;

    public void logPurchase(float value, String currency){
        logger.logPurchase(BigDecimal.valueOf(value), Currency.getInstance(currency));
    }

    static public Godot.SingletonBase initialize(Activity p_activity) {
        return new Facebook(p_activity);
    }

    public Facebook(Activity p_activity) {
        registerClass("Facebook", new String[]{"logPurchase", "setup"});
        activity = p_activity;
        app = p_activity.getApplication();
    }
    @Override
    protected void onMainPause() {
        logger.deactivateApp(app);
    }
    @Override
	protected void onMainResume() {
        logger.activateApp(app);
    }

    public void setup(String appId){
        FacebookSdk.setApplicationId(appId);
        FacebookSdk.sdkInitialize(app.getApplicationContext());
        //FacebookSdk.setApplicationId("144807262731174");
        //FacebookSdk.sdkInitialize(app.getApplicationContext());
        logger = AppEventsLogger.newLogger(activity);
        logger.activateApp(app);
    }
}
