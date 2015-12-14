package com.crash;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.amazon.geo.mapsv2.AmazonMap;
import com.amazon.geo.mapsv2.MapFragment;
import com.amazon.geo.mapsv2.OnMapReadyCallback;
import com.amazon.geo.mapsv2.model.TileOverlayOptions;
import com.amazon.geo.mapsv2.model.UrlTileProvider;

import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends Activity
{
    private Integer refreshCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onResume()
    {
        super.onResume();

        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.mapfragment);

        mapFragment.getMapAsync(new OnMapReadyCallback()
        {
            @Override
            public void onMapReady(final AmazonMap map)
            {
                map.getUiSettings().setAllGesturesEnabled(false);

                findViewById(R.id.crash1).setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        crash1(map);
                    }
                });

                findViewById(R.id.crash2).setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        findViewById(R.id.crash1).setVisibility(View.INVISIBLE);
                        findViewById(R.id.crash2).setVisibility(View.INVISIBLE);
                        findViewById(R.id.counter).setVisibility(View.VISIBLE);

                        crash2(map);
                    }
                });
            }
        });
    }

    // Remove TileOverlay while loading tiles. Instant crash.
    private void crash1(final AmazonMap map)
    {
        addOverlay(map);

        new Handler(Looper.getMainLooper()).postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                removeOverlay(map);
            }
        }, 500);
    }

    // Memory leak (dieing background processes). Crash after 10-15 refreshes.
    private void crash2(final AmazonMap map)
    {
        Log.e("App", "Start crash2()");

        final Handler mainHandler = new Handler(Looper.getMainLooper());
        Runnable myRunnable = new Runnable()
        {
            @Override
            public void run()
            {
                removeOverlay(map);
                addOverlay(map);

                incCounter();
                mainHandler.postDelayed(this, 3000);
            }
        };
        mainHandler.post(myRunnable);
    }

    private void addOverlay(AmazonMap map)
    {
        map.addTileOverlay(new TileOverlayOptions().tileProvider(new UrlTileProvider(256, 256)
        {
            @Override
            public URL getTileUrl(int x, int y, int z)
            {
                try
                {
                    return new URL(String.format("http://a.tile.openstreetmap.org/%d/%d/%d.png", z, x, y));
                }
                catch (MalformedURLException e)
                {
                    e.printStackTrace();
                }

                return null;
            }
        }));
    }

    private void removeOverlay(AmazonMap map)
    {
        map.clear();
    }

    private void incCounter()
    {
        Log.e("App", "Refresh " + (++refreshCount).toString());
        ((TextView) findViewById(R.id.counter)).setText((refreshCount).toString());
    }
}