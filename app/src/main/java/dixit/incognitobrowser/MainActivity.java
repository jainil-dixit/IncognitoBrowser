package dixit.browser;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Camera;
import android.graphics.Color;
import android.hardware.camera2.CameraDevice;
import android.os.Bundle;
import android.support.annotation.NonNull;

import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public WebView myWebView;
    public RelativeLayout relativeLayout, r1;
    public EditText editText;
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         final View b= findViewById(R.id.r2);
         final View c = findViewById(R.id.r1);

        r1 = (RelativeLayout) findViewById(R.id.r1);
        relativeLayout = (RelativeLayout) findViewById(R.id.r2);
        GridView gridview = (GridView) findViewById(R.id.gridview);
        gridview.setAdapter(new ImageAdapter(this));

        editText = (EditText) findViewById(R.id.editText);

        myWebView = (WebView) findViewById(R.id.webView);
        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        myWebView.loadUrl("");
        myWebView.setWebViewClient(new WebViewClient());


        myWebView.getSettings().setSupportZoom(true);
        myWebView.getSettings().setBuiltInZoomControls(false);
        myWebView.getSettings().setLoadWithOverviewMode(true);
        myWebView.getSettings().setUseWideViewPort(true);
        myWebView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        myWebView.setBackgroundColor(Color.WHITE);


        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                b.setVisibility(View.INVISIBLE);
                c.setVisibility(View.INVISIBLE);
                switch (position) {

                    case 0: {


                        myWebView.loadUrl("https://www.facebook.com/");

                        break;
                    }
                    case 1: {
                        myWebView.loadUrl("https://en.wikipedia.org/wiki/Main_Page");

                        break;
                    }
                    case 2: {
                        myWebView.loadUrl("https://wikileaks.org/");

                        break;
                    }
                    case 3: {
                        myWebView.loadUrl("https://translate.google.co.in/");
                        break;
                    }
                    case 4:
                        {
                            myWebView.loadUrl("https://www.quora.com/");
                            break;
                    }
                    case 5:
                    {
                        myWebView.loadUrl("http://www.daviddarling.info/");
                        break;
                    }
                    case 6:
                    {
                        myWebView.loadUrl("http://radio.garden/live");
                        break;
                    }

                }
            }
        });


    }


    @Override
    public void onBackPressed() {
        if (myWebView.canGoBack()) {
            myWebView.goBack();

        } else {
            super.onBackPressed();
        }
    }

    public void forward(View view) {

        if (myWebView.canGoForward()) {
            myWebView.goForward();

        }
    }

    public void search(View view) {

        relativeLayout = (RelativeLayout) findViewById(R.id.r2);
        relativeLayout.setVisibility(View.INVISIBLE);
        myWebView.loadUrl("https://www.google.com");

    }


    public void home(View view) {
        relativeLayout.setVisibility(View.VISIBLE);
        r1.setVisibility(View.VISIBLE);
        myWebView.loadUrl("");


    }


    public void go(View view) {

        myWebView.loadUrl("https://" + editText.getText().toString());
        View b = findViewById(R.id.r2);
        b.setVisibility(View.INVISIBLE);
        editText.setText("");
        editText.setNextFocusForwardId(R.id.webView);

    }


    public class ImageAdapter extends BaseAdapter {
        private Context mContext;

        public ImageAdapter(Context c) {
            mContext = c;
        }

        public int getCount() {
            return mThumbIds.length;
        }

        public Object getItem(int position) {
            return null;
        }

        public long getItemId(int position) {
            return 0;
        }

        // create a new ImageView for each item referenced by the Adapter
        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView imageView;
            if (convertView == null) {
                // if it's not recycled, initialize some attributes
                imageView = new ImageView(mContext);
                imageView.setLayoutParams(new GridView.LayoutParams(85, 85));
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                imageView.setPadding(8, 8, 8, 8);
            } else {
                imageView = (ImageView) convertView;
            }

            imageView.setImageResource(mThumbIds[position]);
            return imageView;
        }

        // references to our images
        private Integer[] mThumbIds = {
                R.drawable.fb, R.drawable.wiki,
                R.drawable.liks, R.drawable.gt1,
                R.drawable.qura,R.drawable.geeks,
                R.drawable.radio,
        };
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CameraDevice.TEMPLATE_STILL_CAPTURE && requestCode == Activity.RESULT_OK) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");

        }
    }
}
