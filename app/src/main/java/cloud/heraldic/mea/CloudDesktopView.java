package cloud.heraldic.mea;

import android.os.Bundle;
import android.os.Message;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import java.net.URI;
import java.net.URISyntaxException;import cloud.heraldic.mea.R;

/**
 * Created by dmitriyfedykovich on 29.08.15.
 */
public class CloudDesktopView extends FullscreenActivity {

    public static final String REMOTE_ACCESS_URL = "https://www.heraldic.cloud/MiseEnAbyme";

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_cloud_desktop_view);

        webView = (WebView) findViewById(R.id.webView);
        webView.setWebViewClient(new HelloWebViewClient());

        WebSettings settings = webView.getSettings();

        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setSupportMultipleWindows(true);

        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public boolean onCreateWindow(
                    WebView view, boolean isDialog, boolean isUserGesture, Message resultMsg) {
                WebView.WebViewTransport transport = (WebView.WebViewTransport) resultMsg.obj;
                resultMsg.sendToTarget();
                return true;
            }
        });

        URI uri = null;

        try {
            uri = new URI(REMOTE_ACCESS_URL);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        if (uri != null) webView.loadUrl(uri.toString());
        else webView.loadUrl("http://www.google.com");
    }

    @Override
    public void onBackPressed() {
        if(webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();
        }
    }

    private class HelloWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }

        @Override
        public void onReceivedError(WebView view, int errorCode,
                                    String description, String failingUrl) {
            Toast.makeText(CloudDesktopView.this,
                    "Your Internet Connection May not be active Or " + description,
                    Toast.LENGTH_LONG).show();
            super.onReceivedError(view, errorCode, description, failingUrl);
        }

        /*@Override
        public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
            handler.proceed(); // Ignore SSL certificate errors
        }*/
    }
}
