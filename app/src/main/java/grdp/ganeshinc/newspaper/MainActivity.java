package grdp.ganeshinc.newspaper;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

public class MainActivity extends AppCompatActivity {

    private Button mLevelTwoButton;



    private InterstitialAd mInterstitialAd;
    private AdView mBannerAd;


    AdView adView;
    private InterstitialAd interstitialAd;

    private long backPressedTime;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);





        mLevelTwoButton = findViewById(R.id.b1);
        // Load the add into Admob banner view.
        mBannerAd = findViewById(R.id.banner_AdView);
        // Text view to show the level number.



        mLevelTwoButton.setEnabled(true);
        mLevelTwoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showIntAdd();
            }
        });

        // Creating and load a  new InterstitialAd .
        mInterstitialAd = createNewIntAd();
        loadIntAdd();
        //Load BannerAd
        showBannerAd();



        //CardView c = (CardView) findViewById(R.id.sakal);
        CardView c1 = findViewById(R.id.lokmat);
        CardView c2 = findViewById(R.id.mata);
        CardView c3 = findViewById(R.id.loksatta);
        CardView c4 = findViewById(R.id.pudhari);
        CardView c5 = findViewById(R.id.punyanagari);
        CardView c6 = findViewById(R.id.samna);
        CardView c7 = findViewById(R.id.prahar);
        CardView c8 = findViewById(R.id.divyamarathi);
        CardView c9 = findViewById(R.id.tarunbharat);




        c1.setOnClickListener(new View.OnClickListener() {

                                  public void onClick(View v) {

                                      Intent intent = new Intent(MainActivity.this, About.class);

                                      startActivity(intent);
                                  }

                              }
        );

        c2.setOnClickListener(new View.OnClickListener() {

                                  public void onClick(View v) {

                                      Intent intent = new Intent(MainActivity.this, Mata.class);

                                      startActivity(intent);
                                  }

                              }
        );


        c3.setOnClickListener(new View.OnClickListener() {

                                  public void onClick(View v) {

                                      Intent intent = new Intent(MainActivity.this, Loksatta.class);

                                      startActivity(intent);
                                  }

                              }
        );

        c4.setOnClickListener(new View.OnClickListener() {

                                  public void onClick(View v) {

                                      Intent intent = new Intent(MainActivity.this, Pudhari.class);

                                      startActivity(intent);
                                  }

                              }
        );

        c5.setOnClickListener(new View.OnClickListener() {

                                  public void onClick(View v) {

                                      Intent intent = new Intent(MainActivity.this, Punyanagari.class);

                                      startActivity(intent);
                                  }

                              }
        );

        c6.setOnClickListener(new View.OnClickListener() {

                                  public void onClick(View v) {

                                      Intent intent = new Intent(MainActivity.this, Samana.class);

                                      startActivity(intent);
                                  }

                              }
        );



        c7.setOnClickListener(new View.OnClickListener() {

                                  public void onClick(View v) {

                                      Intent intent = new Intent(MainActivity.this, Prahar.class);

                                      startActivity(intent);
                                  }

                              }
        );




        c8.setOnClickListener(new View.OnClickListener() {

                                  public void onClick(View v) {

                                      Intent intent = new Intent(MainActivity.this, Divyamarathi.class);

                                      startActivity(intent);
                                  }

                              }
        );



        c9.setOnClickListener(new View.OnClickListener() {

                                  public void onClick(View v) {

                                      Intent intent = new Intent(MainActivity.this, Tarunbharat.class);

                                      startActivity(intent);
                                  }

                              }
        );







    }

    private void showBannerAd() {
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice("754DB6521943676637AE86202C5ACE52")
                .build();
        mBannerAd.loadAd(adRequest);

    }


    private InterstitialAd createNewIntAd() {
        InterstitialAd intAd = new InterstitialAd(this);
        // set the adUnitId (defined in values/strings.xml)
        intAd.setAdUnitId(getString(R.string.ad_id_interstitial));
        intAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                mLevelTwoButton.setEnabled(true);
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                mLevelTwoButton.setEnabled(true);
            }

            @Override
            public void onAdClosed() {
                // Proceed to the next level.
                levelTwo();
            }
        });
        return intAd;
    }

    private void showIntAdd() {

// Show the ad if it's ready. Otherwise toast and reload the ad.
        if (mInterstitialAd != null && mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        } else {
            levelTwo();
        }
    }
    private void loadIntAdd() {
        // Disable the  level two button and load the ad.
        mLevelTwoButton.setEnabled(false);
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice("754DB6521943676637AE86202C5ACE52")
                .build();
        mInterstitialAd.loadAd(adRequest);
    }


    private void levelTwo() {
        // Show the next level
        mLevelTwoButton.setOnClickListener(new View.OnClickListener() {

                                               public void onClick(View v) {

                                                   Intent intent = new Intent(MainActivity.this, Sakal.class);

                                                   startActivity(intent);
                                               }

                                           }
        );

    }







    @Override
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



