package com.branch.www.screencapture;

import android.content.Context;
import android.content.Intent;
import android.media.projection.MediaProjectionManager;
import android.os.Build;
import android.os.Bundle;
import androidx.fragment.app.FragmentActivity;

public class MainActivity extends FragmentActivity {


  public static final int REQUEST_MEDIA_PROJECTION = 18;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    requestCapturePermission();
  }


  public void requestCapturePermission() {

    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
      //5.0 涔嬪悗鎵嶅厑璁镐娇鐢ㄥ睆骞曟埅鍥?
      return;
    }

    MediaProjectionManager mediaProjectionManager = (MediaProjectionManager)
        getSystemService(Context.MEDIA_PROJECTION_SERVICE);
    startActivityForResult(
        mediaProjectionManager.createScreenCaptureIntent(),
        REQUEST_MEDIA_PROJECTION);
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);

    switch (requestCode) {
      case REQUEST_MEDIA_PROJECTION:

        if (resultCode == RESULT_OK && data != null) {
          FloatWindowsService.setResultData(data);
          startService(new Intent(getApplicationContext(), FloatWindowsService.class));
        }
        break;
    }

  }

}

