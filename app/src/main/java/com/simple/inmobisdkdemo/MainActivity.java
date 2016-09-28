package com.simple.inmobisdkdemo;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.inmobi.ads.InMobiAdRequestStatus;
import com.inmobi.ads.InMobiNative;
import com.inmobi.sdk.InMobiSdk;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null)
                        .show();
            }
        });
//
        InMobiSdk.init(getApplicationContext(), "847f118063224df2b5362e52257180df");
        // native ad
        InMobiNative nativeAd = new InMobiNative(1464410326949L, new InMobiNative.NativeAdListener() {
            @Override
            public void onAdLoadSucceeded(InMobiNative inMobiNative) {
                Toast.makeText(MainActivity.this, "onAdLoadSucceeded : " + inMobiNative.toString(), Toast
                        .LENGTH_SHORT).show();
                parseAdContent(inMobiNative);
            }

            private void parseAdContent(InMobiNative ad) {
                // perform action here when ad request succeeds.
                if (ad.getAdContent() != null) {
                    String pubContent = ad.getAdContent().toString();
                    JSONObject jObject = null;
                    try {
                        jObject = new JSONObject(pubContent);
                        String parseTitle = jObject.optString("title");
                        String parseContent = jObject.optString("description");
                        JSONObject iconObj = jObject.optJSONObject("icon");
                        String iconUrl = iconObj.optString("url");
                        String landingPage = jObject.optString("landingURL");
                        Log.e("", "### inmobi ad : title = " + parseTitle + ", content : " + parseContent);
                        //                      InMobiNative.unbind(rootView, ad);
                        //                      InMobiNative.bind(rootView);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onAdLoadFailed(InMobiNative inMobiNative, InMobiAdRequestStatus inMobiAdRequestStatus) {
                Toast.makeText(MainActivity.this, "onAdLoadFailed : " + inMobiNative.toString(), Toast.LENGTH_SHORT)
                        .show();
            }

            @Override
            public void onAdDismissed(InMobiNative inMobiNative) {

            }

            @Override
            public void onAdDisplayed(InMobiNative inMobiNative) {
                Toast.makeText(MainActivity.this, "onAdDisplayed : " + inMobiNative.toString(), Toast.LENGTH_SHORT)
                        .show();
            }

            @Override
            public void onUserLeftApplication(InMobiNative inMobiNative) {

            }
        });
        // 加载广告
        nativeAd.load();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
