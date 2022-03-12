package com.example.newsproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.daimajia.androidanimations.library.Techniques
import com.google.android.gms.ads.*
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import com.viksaa.sssplash.lib.activity.AwesomeSplash
import com.viksaa.sssplash.lib.cnst.Flags
import com.viksaa.sssplash.lib.model.ConfigSplash

class SplashActivity : AwesomeSplash() {
    private var mInterstitialAd: InterstitialAd? = null

    override fun initSplash(configSplash: ConfigSplash?) {
        /* you don't have to override every property */

        MobileAds.initialize(this){}

        var adRequest = AdRequest.Builder().build()

        InterstitialAd.load(this,"ca-app-pub-3940256099942544/1033173712", adRequest,
            object : InterstitialAdLoadCallback() {
            override fun onAdFailedToLoad(adError: LoadAdError) {

                mInterstitialAd = null
            }

            override fun onAdLoaded(interstitialAd: InterstitialAd) {

                mInterstitialAd = interstitialAd
            }
        })

        //Customize Circular Reveal
        configSplash?.backgroundColor = R.color.teal_700; //any color you want form colors.xml
        configSplash?.animCircularRevealDuration = 3000; //int ms
        configSplash?.revealFlagX = Flags.REVEAL_RIGHT;  //or Flags.REVEAL_LEFT
        configSplash?.revealFlagY = Flags.REVEAL_TOP; //or Flags.REVEAL_TOP

        //Choose LOGO OR PATH; if you don't provide String value for path it's logo by default
        //Customize Logo
        configSplash?.logoSplash = R.mipmap.ic_launcher; //or any other drawable
        configSplash?.animLogoSplashDuration = 3000; //int ms
        configSplash?.animLogoSplashTechnique = (Techniques.RubberBand) //choose one form Techniques (ref: https://github.com/daimajia/AndroidViewAnimations)

        //Customize Title
        configSplash?.titleSplash = "News Project";
        configSplash?.titleTextColor = R.color.purple_700;
        configSplash?.titleTextSize = 30f; //float value
        configSplash?.animTitleDuration = (3000)
        configSplash?.animTitleTechnique = Techniques.FlipInX;
        //configSplash?.titleFont = "fonts/myfont.ttf"; //provide string to your font located in assets/fonts/

    }

    override fun animationsFinished() {
        if(mInterstitialAd != null){
            mInterstitialAd?.show(this)
            mInterstitialAd?.fullScreenContentCallback = object: FullScreenContentCallback() {
                override fun onAdDismissedFullScreenContent() {
                    openActivity()
                }

                override fun onAdFailedToShowFullScreenContent(adError: AdError?) {
                    openActivity()
                }

                override fun onAdShowedFullScreenContent() {

                    mInterstitialAd = null
                }
            }
        } else
            openActivity()
    }

    fun openActivity(){
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
        finish()
    }

}