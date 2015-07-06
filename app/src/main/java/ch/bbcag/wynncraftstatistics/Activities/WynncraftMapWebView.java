package ch.bbcag.wynncraftstatistics.Activities;


import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

import ch.bbcag.wynncraftstatistics.R;

public class WynncraftMapWebView extends Activity {

    private WebView webView;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wynncraft_map_web_view);

        webView = (WebView) findViewById(R.id.webView1);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("https://map.wynncraft.com");

    }

}
