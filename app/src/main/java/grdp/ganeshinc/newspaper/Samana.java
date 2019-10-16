package grdp.ganeshinc.newspaper;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

public class Samana extends AppCompatActivity {

private long backPressedTime;


        WebView webView;
        ProgressBar bar;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_samana);



            webView = (WebView) findViewById(R.id.webView);
            bar=(ProgressBar) findViewById(R.id.progressBar2);
            webView.setWebViewClient(new myWebclient());
            webView.getSettings().setJavaScriptEnabled(true);
            webView.loadUrl("http://www.saamana.com/");

        }

        public class myWebclient extends WebViewClient {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                bar.setVisibility(View.GONE);
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return super.shouldOverrideUrlLoading(view, url);
            }
        }

        @Override
        public boolean onKeyDown(int keyCode, KeyEvent event) {
            if((keyCode== KeyEvent.KEYCODE_BACK) && webView.canGoBack()){
                webView.goBack();
                return true;
            }

            return super.onKeyDown(keyCode, event);
        }




    public void onBackPressed()
    {


        if ( backPressedTime+2000> System.currentTimeMillis()) {

            super.onBackPressed();
            return;

        }

        else {
            Toast.makeText(getBaseContext(),"Tap Back button again to exit",Toast.LENGTH_SHORT).show();



        }

        backPressedTime=System.currentTimeMillis();

    }


    }