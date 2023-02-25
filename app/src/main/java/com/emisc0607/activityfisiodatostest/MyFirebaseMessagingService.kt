package com.emisc0607.activityfisiodatostest

import android.os.Looper
import android.widget.Toast
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import android.os.Handler

class MyFirebaseMessagingService: FirebaseMessagingService() {
    override fun onMessageReceived(remoteMessage : RemoteMessage) {
        super.onMessageReceived(remoteMessage)
        Looper.prepare()
        Handler().post{
            Toast.makeText(baseContext, remoteMessage.notification?.title, Toast.LENGTH_SHORT).show()
        }
        Looper.loop()
    }
}