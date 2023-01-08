package com.example.healthyway.ui.map;

import android.annotation.SuppressLint;
import android.content.Context;
import android.location.LocationManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.webkit.WebView;

import org.osmdroid.config.Configuration;

import androidx.appcompat.app.AppCompatActivity;


import com.example.healthyway.R;

import org.osmdroid.api.IMapController;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;
import org.osmdroid.views.overlay.mylocation.GpsMyLocationProvider;
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay;

public class MapActivity extends AppCompatActivity {
    private LocationManager locationManager;
    private Marker marker;
    private WebView webView;
    @SuppressLint("SetJavaScriptEnabled")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        String dieticiansName;
        String dieticiansDescription;
        String formUrl;
        String dieticiansImage;

        super.onCreate(savedInstanceState);
        Context ctx = getApplicationContext();
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Configuration.getInstance().load(ctx, PreferenceManager.getDefaultSharedPreferences(ctx));
                setContentView(R.layout.activity_maps);

        MapView map = (MapView) findViewById(R.id.map);
        map.setTileSource(TileSourceFactory.MAPNIK);
        map.setMultiTouchControls(true);
        map.setClickable(true);
        IMapController mapController = map.getController();
        // Add a location overlay to the map
        MyLocationNewOverlay locationOverlay = new MyLocationNewOverlay(new GpsMyLocationProvider(this), map);
        locationOverlay.enableMyLocation();
        locationOverlay.runOnFirstFix(new Runnable() {
                    @Override
                    public void run() {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                mapController.setCenter(locationOverlay.getMyLocation());
                                mapController.animateTo(locationOverlay.getMyLocation());
                                mapController.setZoom(16.0);
                            }
                        });
                    }
                });
        map.getOverlays().add(locationOverlay);

        // Add a marker to the map
        marker = new Marker(map);
        marker.setPosition(new GeoPoint(46.742710, 23.654710));
        marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
        dieticiansName = "Demian Iarina";
        dieticiansDescription = "Meal & Exercise Dietician";
        formUrl = "https://www.facebook.com/iarina.demian.5/";
        dieticiansImage = "iari.jpg";
        displayBanner(dieticiansName,dieticiansDescription,formUrl,dieticiansImage);
        map.getOverlays().add(marker);

        //Second marker
        marker = new Marker(map);
        marker.setPosition(new GeoPoint(46.778340, 23.596550));
        marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
        dieticiansName = "Burzo Bianca";
        dieticiansDescription = "Nutrition Expert";
        formUrl = "https://css-tricks.com/";
        dieticiansImage = "bibi.jpg";
        displayBanner(dieticiansName,dieticiansDescription,formUrl, dieticiansImage);
        map.getOverlays().add(marker);

     }

    public void onResume(){
        super.onResume();
        Configuration.getInstance().load(this, PreferenceManager.getDefaultSharedPreferences(this));
    }

    public void displayBanner(String dieticiansName, String dieticiansDescription, String formUrl, String dieticiansImage) {
        String bannerHTML = "<div class='banner'>" +
                "<p>"+dieticiansName+"</p>" +
                "<p>"+dieticiansDescription+"</p>" +
                "<img src=\""+dieticiansImage+"\" alt=\"\" height=\"50\" width=\"50\"/>"+
                "<button onclick='submitForm()'>Submit</button>" +
                "</div>" +
                "<script>" +
                "function submitForm() {" +
                "  window.location.href = '"+formUrl+"';" +
                "}" +
                "</script>";
        // When the marker is clicked, show the banner
        marker.setOnMarkerClickListener(new Marker.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker, MapView map) {
                // Load the banner HTML into the webview
                WebView webView = findViewById(R.id.web_view);
                webView.getSettings().setJavaScriptEnabled(true);
                webView.loadDataWithBaseURL(null, bannerHTML, "text/html", "utf-8", null);

                // Make the webview visible
                webView.setVisibility(View.VISIBLE);
                return true;
            }
        });
    }
}