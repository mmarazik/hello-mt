package com.mt.hello;

import android.app.Activity; 
import android.content.Intent; 
import android.net.Uri; 
import android.os.Bundle; 


import io.flutter.embedding.android.FlutterActivity;

import io.flutter.plugin.common.MethodCall; 
import io.flutter.plugin.common.MethodChannel; 
import io.flutter.plugin.common.MethodChannel.MethodCallHandler; 
import io.flutter.plugin.common.MethodChannel.Result; 
import io.flutter.plugins.GeneratedPluginRegistrant;


public class MainActivity extends FlutterActivity {
    private static final String CHANNEL = "flutterapp.tutorialspoint.com/browser";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new MethodChannel(getFlutterEngine().getDartExecutor().getBinaryMessenger(), CHANNEL).setMethodCallHandler( 
            new MethodCallHandler() { 
                @Override 
                public void onMethodCall(MethodCall call, Result result) { 
                    String url = call.argument("url"); 
                    if (call.method.equals("openBrowser")) {
                        openBrowser(call, result, url); 
                    } else { 
                        result.notImplemented(); 
                    } 
                } 
            });
   }

    private void openBrowser(MethodCall call, Result result, String url) { 
        Activity activity = this; 
        if (activity == null) { 
            result.error("ACTIVITY_NOT_AVAILABLE", 
            "Browser cannot be opened without foreground activity", null); 
            return; 
        } 
        Intent intent = new Intent(Intent.ACTION_VIEW); 
        intent.setData(Uri.parse(url)); 

        activity.startActivity(intent); 
        result.success((Object) true); 
        // Intent intent = new Intent(activity, SecondActivity.class);
        // activity.startActivity(intent);
    }
}
