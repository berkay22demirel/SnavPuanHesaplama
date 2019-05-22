package com.berkay22demirel.sinavpuanhesaplama.Util;

import com.berkay22demirel.sinavpuanhesaplama.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class AdUtil {

    public static void showAd (AdView mAdView) {
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    }
}
