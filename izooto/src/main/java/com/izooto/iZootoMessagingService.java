/* Code by Amit Kumar Gupta*/
package com.izooto;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import org.json.JSONObject;
import java.util.Map;

import static com.izooto.AppConstant.TAG;

public class iZootoMessagingService extends FirebaseMessagingService {

    private  Payload payload = null;
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        try {
            if (remoteMessage.getData().size() > 0) {
                Map<String, String> data = remoteMessage.getData();
                Log.d(AppConstant.APP_NAME_TAG, AppConstant.PAYLOAD + remoteMessage.getData());
                handleNow(data);
            }
            if (remoteMessage.getNotification() != null) {
                Log.d(AppConstant.APP_NAME_TAG, AppConstant.NOTIFICATIONBODY  + remoteMessage.getNotification().getBody());
            }
        }
        catch (Exception ex)
        {
            Log.e(AppConstant.FIREBASEEXCEPTION,ex.toString());
        }


    }
    public   void handleNow(final Map<String, String> data) {

        Log.d(TAG, AppConstant.NOTIFICATIONRECEIVED);

        try {

            if(data.get(AppConstant.CAMPNAME)!=null) {

                JSONObject payloadObj = new JSONObject(data.get(AppConstant.CAMPNAME));
                if (payloadObj.optLong(AppConstant.CREATEDON) >PreferenceUtil.getInstance(this).getLongValue(AppConstant.DEVICE_REGISTRATION_TIMESTAMP)) {
                    payload = new Payload();
                    payload.setFetchURL(payloadObj.optString(AppConstant.FETCHURL));
                    payload.setKey(payloadObj.optString(AppConstant.KEY));
                    payload.setId(payloadObj.optString(AppConstant.ID));
                    payload.setRid(payloadObj.optString(AppConstant.RID));
                    payload.setLink(payloadObj.optString(AppConstant.LINK));
                    payload.setTitle(payloadObj.optString(AppConstant.TITLE));
                    payload.setMessage(payloadObj.optString(AppConstant.NMESSAGE));
                    payload.setIcon(payloadObj.optString(AppConstant.ICON));
                    payload.setReqInt(payloadObj.optInt(AppConstant.REQINT));
                    payload.setTag(payloadObj.optString(TAG));
                    payload.setBanner(payloadObj.optString(AppConstant.BANNER));
                    payload.setAct_num(payloadObj.optInt(AppConstant.ACTNUM));

                    // Button 1
                    payload.setAct1name(payloadObj.optString(AppConstant.ACT1NAME));
                    payload.setAct1link(payloadObj.optString(AppConstant.ACT1LINK));
                    payload.setAct1icon(payloadObj.optString(AppConstant.ACT1ICON));
                    payload.setAct1ID(payloadObj.optString(AppConstant.ACT1ID));
                    // Button 2
                    payload.setAct2name(payloadObj.optString(AppConstant.ACT2NAME));
                    payload.setAct2link(payloadObj.optString(AppConstant.ACT2LINK));
                    payload.setAct2icon(payloadObj.optString(AppConstant.ACT2ICON));
                    payload.setAct2ID(payloadObj.optString(AppConstant.ACT2ID));

                    payload.setInapp(payloadObj.optInt(AppConstant.INAPP));
                    payload.setTrayicon(payloadObj.optString(AppConstant.TARYICON));
                    payload.setSmallIconAccentColor(payloadObj.optString(AppConstant.ICONCOLOR));
                    payload.setSound(payloadObj.optString(AppConstant.SOUND));
                    payload.setLedColor(payloadObj.optString(AppConstant.LEDCOLOR));
                    payload.setLockScreenVisibility(payloadObj.optInt(AppConstant.VISIBILITY));
                    payload.setGroupKey(payloadObj.optString(AppConstant.GKEY));
                    payload.setGroupMessage(payloadObj.optString(AppConstant.GMESSAGE));
                    payload.setFromProjectNumber(payloadObj.optString(AppConstant.PROJECTNUMBER));
                    payload.setCollapseId(payloadObj.optString(AppConstant.COLLAPSEID));
                    payload.setPriority(payloadObj.optInt(AppConstant.PRIORITY));
                    payload.setRawPayload(payloadObj.optString(AppConstant.RAWDATA));
                    payload.setAp(payloadObj.optString(AppConstant.ADDITIONALPARAM));
                    payload.setCfg(payloadObj.optInt(AppConstant.CFG));

                }
                else
                    return;
            }
            else
            {
                JSONObject payloadObj = new JSONObject(data);
                if (payloadObj.optLong(ShortpayloadConstant.CREATEDON) > PreferenceUtil.getInstance(this).getLongValue(AppConstant.DEVICE_REGISTRATION_TIMESTAMP))
                {
                    payload = new Payload();
                    payload.setFetchURL(payloadObj.optString(ShortpayloadConstant.FETCHURL));
                    payload.setKey(payloadObj.optString(ShortpayloadConstant.KEY));
                    payload.setId(payloadObj.optString(ShortpayloadConstant.ID));
                    payload.setRid(payloadObj.optString(ShortpayloadConstant.RID));
                    payload.setLink(payloadObj.optString(ShortpayloadConstant.LINK));
                    payload.setTitle(payloadObj.optString(ShortpayloadConstant.TITLE));
                    payload.setMessage(payloadObj.optString(ShortpayloadConstant.NMESSAGE));
                    payload.setIcon(payloadObj.optString(ShortpayloadConstant.ICON));
                    payload.setReqInt(payloadObj.optInt(ShortpayloadConstant.REQINT));
                    payload.setTag(payloadObj.optString(ShortpayloadConstant.TAG));
                    payload.setBanner(payloadObj.optString(ShortpayloadConstant.BANNER));
                    payload.setAct_num(payloadObj.optInt(ShortpayloadConstant.ACTNUM));
                    // Button 2
                    payload.setAct1name(payloadObj.optString(ShortpayloadConstant.ACT1NAME));
                    payload.setAct1link(payloadObj.optString(ShortpayloadConstant.ACT1LINK));
                    payload.setAct1icon(payloadObj.optString(ShortpayloadConstant.ACT1ICON));
                    payload.setAct1ID(payloadObj.optString(ShortpayloadConstant.ACT1ID));
                    // Button 2
                    payload.setAct2name(payloadObj.optString(ShortpayloadConstant.ACT2NAME));
                    payload.setAct2link(payloadObj.optString(ShortpayloadConstant.ACT2LINK));
                    payload.setAct2icon(payloadObj.optString(ShortpayloadConstant.ACT2ICON));
                    payload.setAct2ID(payloadObj.optString(ShortpayloadConstant.ACT2ID));

                    payload.setInapp(payloadObj.optInt(ShortpayloadConstant.INAPP));
                    payload.setTrayicon(payloadObj.optString(ShortpayloadConstant.TARYICON));
                    payload.setSmallIconAccentColor(payloadObj.optString(ShortpayloadConstant.ICONCOLOR));
                    payload.setSound(payloadObj.optString(ShortpayloadConstant.SOUND));
                    payload.setLedColor(payloadObj.optString(ShortpayloadConstant.LEDCOLOR));
                    payload.setLockScreenVisibility(payloadObj.optInt(ShortpayloadConstant.VISIBILITY));
                    payload.setGroupKey(payloadObj.optString(ShortpayloadConstant.GKEY));
                    payload.setGroupMessage(payloadObj.optString(ShortpayloadConstant.GMESSAGE));
                    payload.setFromProjectNumber(payloadObj.optString(ShortpayloadConstant.PROJECTNUMBER));
                    payload.setCollapseId(payloadObj.optString(ShortpayloadConstant.COLLAPSEID));
                    payload.setPriority(payloadObj.optInt(ShortpayloadConstant.PRIORITY));
                    payload.setRawPayload(payloadObj.optString(ShortpayloadConstant.RAWDATA));
                    payload.setAp(payloadObj.optString(ShortpayloadConstant.ADDITIONALPARAM));
                    payload.setCfg(payloadObj.optInt(ShortpayloadConstant.CFG));
                }
                else
                    return;
            }
        } catch (Exception e) {

            e.printStackTrace();
            Lg.d(TAG,e.toString());
        }


        if (iZooto.appContext == null)
            iZooto.appContext = this;
        Handler mainHandler = new Handler(Looper.getMainLooper());
        Runnable myRunnable = new Runnable() {
            @Override
            public void run() {
                iZooto.processNotificationReceived(payload);
                iZooto.notificationView(payload);

            } // This is your code
        };
        mainHandler.post(myRunnable);
    }



}