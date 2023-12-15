package com.extranslate.exodus_qfirebase;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.app.Activity;
import android.widget.TextView;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.api.IMapController;
import org.osmdroid.config.Configuration;
import org.osmdroid.views.overlay.Marker;
import org.osmdroid.views.overlay.Polyline;
import android.preference.PreferenceManager;
import androidx.appcompat.app.AppCompatActivity;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;

public class Map extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        Configuration.getInstance().load(getApplicationContext(), PreferenceManager.getDefaultSharedPreferences(getApplicationContext()));

        MapView mapView = findViewById(R.id.mapView);
        mapView.setTileSource(TileSourceFactory.MAPNIK);
        mapView.setBuiltInZoomControls(true);
        mapView.setMultiTouchControls(true);

        double santiagoLatitude = -33.4489;
        double santiagoLongitude = -70.6693;

        double valparaisoLatitude = -33.036;
        double valparaisoLongitude = -71.62963;

        double lapintanaLatitude = -33.58331;
        double lapintanaLongitude = -70.63419;

        GeoPoint santiagoPoint = new GeoPoint(santiagoLatitude, santiagoLongitude);
        GeoPoint valparaisoPoint = new GeoPoint(valparaisoLatitude, valparaisoLongitude);
        GeoPoint lapintanaPoint = new GeoPoint(lapintanaLatitude, lapintanaLongitude);

        Marker santiagoMarker = new Marker(mapView);
        santiagoMarker.setPosition(santiagoPoint);
        santiagoMarker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
        santiagoMarker.setTitle("Santiago, Chile");
        santiagoMarker.setSnippet("Capital de Chile");

        Marker valparaisoMarker = new Marker(mapView);
        valparaisoMarker.setPosition(valparaisoPoint);
        valparaisoMarker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
        valparaisoMarker.setTitle("Valparaíso, Chile");
        valparaisoMarker.setSnippet("Puerto principal de Chile");
        Marker lapintanaMarker = new Marker(mapView);
        valparaisoMarker.setPosition(lapintanaPoint);
        valparaisoMarker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
        valparaisoMarker.setTitle("La pintana, Chile");
        valparaisoMarker.setSnippet("Comunade de Santiago, Chile");

        mapView.getOverlays().add(santiagoMarker);
        mapView.getOverlays().add(valparaisoMarker);
        mapView.getOverlays().add(lapintanaMarker);

        Polyline polyline = new Polyline();
        polyline.addPoint(santiagoPoint);
        polyline.addPoint(valparaisoPoint);
        polyline.addPoint(lapintanaPoint);
        polyline.setColor(0xFF0000FF);
        polyline.setWidth(5);

        mapView.getOverlayManager().add(polyline);

        double distance = Map_Distance.Map_Distance(santiagoPoint, valparaisoPoint);
        TextView distanceTextView = findViewById(R.id.distanceTextView);
        distanceTextView.setText("Distancia entre Santiago y Valparaíso: " +
                String.format("%.2f", distance) + " km");

        IMapController mapController = mapView.getController();
        mapController.setCenter(santiagoPoint);
        mapController.setZoom(14);

        Button GotoActivityBtn = findViewById(R.id.GotoMainActivityBtn);

        GotoActivityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Map.this, Main.class);
                startActivity(intent);
            }
        });
    }
}
