package fr.univtln.m1dapm.g3.g3vote.Interface;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class CWebSiteActivity extends AppCompatActivity {
    private WebView mWebView;

    protected void onCreate(Bundle pSavedInstanceState){
        super.onCreate(pSavedInstanceState);
        mWebView = new WebView(this);
        mWebView.getSettings().setJavaScriptEnabled(true);
        final Activity lActivity = this;
        mWebView.setWebViewClient(new WebViewClient(){
            public void onReceivedError(WebView pView, int pErrorCode, String pDescription, String pFailingUrl) {
                Toast.makeText(lActivity, pDescription, Toast.LENGTH_SHORT).show();
            }
        });
        mWebView.loadUrl("http://s467087892.onlinehome.fr/Documentation/Home.html");
        setContentView(mWebView);
    }
}
