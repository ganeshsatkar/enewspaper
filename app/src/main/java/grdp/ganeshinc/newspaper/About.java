package grdp.ganeshinc.newspaper;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

import com.daimajia.numberprogressbar.NumberProgressBar;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.NativeExpressAdView;
import com.google.android.gms.ads.VideoController;
import com.google.android.gms.ads.VideoOptions;

public class About extends AppCompatActivity {

    WebView webView;

    NativeExpressAdView mAdView;
    VideoController mVideoController;
    private static String LOG_TAG = "EXAMPLE";

    Dialog main_dialog;
    private NumberProgressBar bnp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        LayoutInflater dialogLayout = LayoutInflater.from(About.this);
        View DialogView = dialogLayout.inflate(R.layout.progress_dialog, null);


        main_dialog = new Dialog(About.this, R.style.CustomAlertDialog);
        main_dialog.setContentView(DialogView);
        WindowManager.LayoutParams lp = new  WindowManager.LayoutParams();
        lp.copyFrom(main_dialog.getWindow().getAttributes());
        lp.width = (int)(getResources().getDisplayMetrics().widthPixels*0.90);
        lp.height = (int)(getResources().getDisplayMetrics().heightPixels*0.25);
        main_dialog.getWindow().setAttributes(lp);
        main_dialog.setCancelable(false);
        main_dialog.setCanceledOnTouchOutside(false);

        // Native Admob

        mAdView = (NativeExpressAdView) DialogView.findViewById(R.id.adView);
        // Set its video options.
        mAdView.setVideoOptions(new VideoOptions.Builder()
                .setStartMuted(true)
                .build());

        // The VideoController can be used to get lifecycle events and info about an ad's video
        // asset. One will always be returned by getVideoController, even if the ad has no video
        // asset.
        mVideoController = mAdView.getVideoController();
        mVideoController.setVideoLifecycleCallbacks(new VideoController.VideoLifecycleCallbacks() {
            @Override
            public void onVideoEnd() {
                Log.d(LOG_TAG, "Video playback is finished.");
                super.onVideoEnd();
            }
        });

        // Set an AdListener for the AdView, so the Activity can take action when an ad has finished
        // loading.
        mAdView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                if (mVideoController.hasVideoContent()) {
                    Log.d(LOG_TAG, "Received an ad that contains a video asset.");
                } else {
                    Log.d(LOG_TAG, "Received an ad that does not contain a video asset.");
                }
            }
        });


        mAdView.loadAd(new AdRequest.Builder().build());


        bnp = (NumberProgressBar) DialogView.findViewById(R.id.number_progress_bar);
        bnp.setProgress(0);
        bnp.setMax(100);
        //main_dialog.show();



        webView = (WebView)findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setAppCacheEnabled(true);
        webView.loadUrl("http://beingnatural.in");
        webView.setWebChromeClient(new WebChromeClient(){

            public void  onProgressChanged(WebView View, int progress){

                bnp.setProgress(progress);
                if (progress == 100){

                    main_dialog.dismiss();
                } else {
                    main_dialog.show();
                }
            }

        });



    }
}
