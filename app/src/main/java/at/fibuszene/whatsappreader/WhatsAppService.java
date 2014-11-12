package at.fibuszene.whatsappreader;

import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.AccessibilityServiceInfo;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.widget.RemoteViews;
import android.widget.Toast;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import at.fibuszene.whatsappreader.persistency.article.ArticleColumns;
import at.fibuszene.whatsappreader.persistency.article.ArticleContentValues;

public class WhatsAppService extends AccessibilityService {
    public static String TAG = "whatsappreaderTAG";

    @Override
    protected void onServiceConnected() {
        super.onServiceConnected();
        AccessibilityServiceInfo info = new AccessibilityServiceInfo();
        info.flags = AccessibilityServiceInfo.DEFAULT;
        info.eventTypes = AccessibilityEvent.TYPE_NOTIFICATION_STATE_CHANGED;
        info.feedbackType = AccessibilityServiceInfo.FEEDBACK_GENERIC;
        this.setServiceInfo(info);
    }

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        //second one is actually pretty important to prevent fucking up the app since this presents another notification and another and another ...
        if (event != null && event.getPackageName().toString().contains("com.whatsapp")) {

            if (event.getEventType() != AccessibilityEvent.TYPE_NOTIFICATION_STATE_CHANGED) {
                return;
            }
            Notification notification = (Notification) event.getParcelableData();
            try {
                List<String> text = getText(notification);

                ArticleContentValues values = new ArticleContentValues();
                values.putSender(text.get(0));//= sender
                String ticker = text.get(1);
                values.putTicker(ticker); //= 1 new message ...
                String charAt = ticker.trim().charAt(0) + "";

                int newMessageOffset = Integer.valueOf(charAt);
                values.putMessage(text.get(newMessageOffset + 1)); //= 1 new message ...
                getContentResolver().insert(ArticleColumns.CONTENT_URI, values.values());

                NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                Notification notificationBar = new Notification(R.drawable.ic_launcher, "Click here to not trigger blue icons", System.currentTimeMillis());

                Intent notificationIntent = new Intent(this, HomeActivity.class);

                notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);

                PendingIntent intent = PendingIntent.getActivity(this, 0,
                        notificationIntent, 0);

                notificationBar.setLatestEventInfo(this, "New Message", "Click here to not trigger blue icons", intent);
                notificationBar.flags |= Notification.FLAG_AUTO_CANCEL;
                notificationManager.notify(0, notificationBar);
            } catch (Exception npe) {
                Toast.makeText(this, "Could not retrive message", Toast.LENGTH_SHORT).show();
            }
        }

    }

    //apparently heavily based on Jon C. Hammer
    //http://stackoverflow.com/questions/9292032/extract-notification-text-from-parcelable-contentview-or-contentintent
    public List<String> getText(Notification notification) throws IllegalAccessException, NoSuchFieldException {
        List<String> notificationText = new ArrayList<String>();
        RemoteViews views = getView(notification);
        if (views == null) {
            return null;
        }
        Field field = views.getClass().getDeclaredField("mActions");
        field.setAccessible(true);
        ArrayList<Parcelable> actions = (ArrayList<Parcelable>) field.get(views);

        for (Parcelable p : actions) {
            Parcel parcel = Parcel.obtain();
            p.writeToParcel(parcel, 0);
            parcel.setDataPosition(0);
            int tag = parcel.readInt();
            if (tag != 2) {
                continue;
            }
            parcel.readInt();
            String methodName = parcel.readString();
            if (methodName.equals("setText")) {
                parcel.readInt();
                notificationText.add(TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel) + "");
            }
            parcel.recycle();
        }
        return notificationText;
    }

    public RemoteViews getView(Notification notif) {
        RemoteViews views = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            views = notif.bigContentView;
        } else {
            views = notif.contentView;
        }
        return views;
    }

    @Override
    public void onInterrupt() {
    }
}
