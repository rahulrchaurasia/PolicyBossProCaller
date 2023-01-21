package com.policyboss.policybosscaller.WebView;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.webkit.JavascriptInterface;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;


import com.policyboss.policybosscaller.BaseActivity;
import com.policyboss.policybosscaller.Home.HomeActivity;
import com.policyboss.policybosscaller.R;

import java.io.File;

public class CommonWebViewActivity extends BaseActivity {

    WebView webView;
    ImageView imgPic;
    Toolbar toolbar;

    String url = "";
    String name = "";
    String title = "";
    public static boolean isActive = false;
    String APPMENU = "";

    // initializing
    // FusedLocationProviderClient
    // object


    // Initializing other items
    // from layout file
    private static final int LOCATION_PERMISSION_ID = 44;
    private static final int pic_id = 123;

    private static final int CAMERA_REQUEST = 1888;
    public static final int PERMISSION_CAMERA_STORACGE_CONSTANT = 103;
    public static final int PERMISSION_ALL_LOAD = 104;

    private String PHOTO_File = "";
    File Docfile;
    File file;
    Uri imageUri;


    // private String URL = "http://mgfm.co.in/andr.html";

    private String URL =  "http://api.magicfinmart.com/images/android.html";

    String[] perms = {
            "android.permission.CAMERA",
            "android.permission.WRITE_EXTERNAL_STORAGE",
            "android.permission.READ_EXTERNAL_STORAGE"

    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common_web_view);

        webView = (WebView) findViewById(R.id.webview);

        toolbar = (Toolbar) findViewById(R.id.toolbar);

        url = getIntent().getStringExtra("URL");
        name = getIntent().getStringExtra("NAME");
        title = getIntent().getStringExtra("TITLE");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(title);


        if (isNetworkConnected()) {
            settingWebview();

        } else{
            Toast.makeText(this, "Check your internet connection", Toast.LENGTH_SHORT).show();


        }




    }


    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null;
    }


    //region JavascriptHandling
    private void settingWebview() {
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);

        settings.setBuiltInZoomControls(true);
        settings.setUseWideViewPort(false);
        settings.setSupportMultipleWindows(false);

        settings.setLoadsImagesAutomatically(true);
        settings.setLightTouchEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setLoadWithOverviewMode(true);


      /*  MyWebViewClient webViewClient = new MyWebViewClient(this);
        webView.setWebViewClient(webViewClient);*/
        webView.setWebViewClient(new WebViewClient() {


            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                // TODO show you progress image
                if (isActive)
                    showDialog("");
                // new ProgressAsync().execute();
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                // TODO hide your progress image
                cancelDialog();
                super.onPageFinished(view, url);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                //whatsapp plugin call.. via WEB
//                if (url != null && url.startsWith("whatsapp://")) {
//                    view.getContext().startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
//                    return true;
//                } else
                if (url.endsWith(".pdf")) {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setDataAndType(Uri.parse(url), "application/pdf");
                    try {
                        startActivity(intent);
                    } catch (ActivityNotFoundException e) {
                        //user does not have a pdf viewer installed
                        String googleDocs = "https://docs.google.com/viewer?url=";
                        webView.loadUrl(googleDocs + url);
                    }
                }

                /*qacamp@gmail.com/01011980
                download policy QA user
                878769 crn
                */
                return false;
            }
        });
        webView.getSettings().setBuiltInZoomControls(true);
        webView.addJavascriptInterface(new MyJavaScriptInterface(), "Android");
        webView.addJavascriptInterface(new PaymentInterface(), "PaymentInterface");
        // webView.setWebChromeClient(new WebChromeClient();
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
                //Required functionality here
                return super.onJsAlert(view, url, message, result);
            }
        });
        // webView.setWebChromeClient(new WebChromeClient());
        Log.d("URL", url);

        if (url.endsWith(".pdf")) {

            webView.loadUrl("https://docs.google.com/viewer?url=" + url);
            //webView.loadUrl("http://drive.google.com/viewerng/viewer?embedded=true&url=" + url);
        } else {
            webView.loadUrl(url);
        }
        //webView.loadUrl(url);
    }



    //endregion


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            switch (keyCode) {
                case KeyEvent.KEYCODE_BACK:

                    Log.i("BACK", "Back Triggered");
                    if (webView.canGoBack()) {
                        webView.goBack();
                    } else {
//                        if (!APPMENU.equals("")) {
//                            navigateToHome();
//                        }else{
//                            finish();
//                        }
                        navigateToHome();

                    }
                    return true;
            }

        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:

//                if(APPMENU.equals("")){
//                    finish();
//                }else{
//                    navigateToHome();
//                }

                navigateToHome();
                return true;


            default:
                return super.onOptionsItemSelected(item);
        }

    }


    class PaymentInterface {
        @JavascriptInterface
        public void success(String data) {
        }

        @JavascriptInterface
        public void error(String data) {
        }
    }

    class MyJavaScriptInterface {

        @JavascriptInterface
        public void crossselltitle(String dynamicTitle) {

            getSupportActionBar().setTitle(dynamicTitle);

        }

        // region Raise Ticket Note :Below Method  Upload_doc and Upload_doc_view Called For Activity Not For Dialog
        // For Dialog We have Used "Base Activity" JavaScript (All Insurance Popup Coming from there because it will already open from CommonWebView)
        // In Menu Raise Tickets Activity :Upload_doc and Upload_doc_view comming From Below code since its Activity Page.



        @JavascriptInterface
        public void RedirectToHomepage() {//Android.RedirectToHomepage();
            Intent intent = new Intent(CommonWebViewActivity.this, HomeActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        }

        @JavascriptInterface
        public void callPDFCREDIT(String u) {

            webView.loadUrl("http://www.google.com");

//            startActivity(new Intent(CommonWebViewActivity.this, CommonWebViewActivity.class)
//                    .putExtra("URL", url)
//                    .putExtra("NAME", "FREE CREDIT REPORT")
//                    .putExtra("TITLE", "LIC FREE CREDIT REPORT"));
        }






    }




    private void navigateToHome(){

        Intent intent = new Intent(this, HomeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.putExtra("MarkTYPE", "FROM_HOME");

        startActivity(intent);
        finish();
    }

}