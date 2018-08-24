package com.clipservice.eticket.ui.adDetail;

import android.app.Fragment;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import com.clipservice.eticket.R;

public class AdUrlFragment extends Fragment {
    View mView;
    private static final String TAG = "AdUrlFragment";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_ad_detail_url, container, false);
        setWebview();
        // Inflate the layout for this fragment
        return mView;
    }

    private void setWebview() {
        Bundle bundle = getArguments();
        String url = bundle.getString("url");
        Log.d(TAG,"url is"+url);
        WebView webView = (WebView)mView.findViewById(R.id.webview);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(url);
    }

}
