package com.berkay22demirel.sinavpuanhesaplama.Util;

import com.berkay22demirel.sinavpuanhesaplama.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class AdUtil {

    public static final String APPLICATION_ID = "ca-app-pub-3727603707347204~7305031067";
    public static final String MAIN_BANNER_ID = "ca-app-pub-3727603707347204/1861132690";
    public static final String EXAM_BANNER_ID = "";

    public static void showAd (AdView mAdView) {
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    }
}
