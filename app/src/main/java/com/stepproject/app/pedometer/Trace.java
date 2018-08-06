package com.stepproject.app.pedometer;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.stepproject.app.R;

import java.util.List;

public class Trace extends Activity implements OnMapReadyCallback {
    // public BaiduMap mBaiduMap = null;
    // public MapView mMapView = null;
    MapView mapView;
    GoogleMap map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /* Initialize context information with ApplicationContext
         *  noted that this should be set before setContentView() */
//        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.activity_show_map);
        mapView = (com.google.android.gms.maps.MapView) findViewById(R.id.bmapView);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);

        /*mMapView = (MapView) findViewById(R.id.bmapView);
        mBaiduMap = mMapView.getMap();
        mBaiduMap.setMyLocationEnabled(true);
        mBaiduMap.setMyLocationConfigeration(new MyLocationConfiguration(
                        MyLocationConfiguration.LocationMode.FOLLOWING, false, null));*/
    }
/*
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mMapView.onResume();

        List<LatLng> mHistoryTrace =PedometerService.getHistoryTrace();
        MyLocationData mCurrentLocation = PedometerService.getCurrentLocation();
        float zoomLevel = 17; //TODO: remove this constant

        // display the trace
        BitmapDescriptor bitmap = BitmapDescriptorFactory.fromResource(R.drawable.ic_notification);
        if(mHistoryTrace.size() > 2) {
            mBaiduMap.addOverlay(
                    new PolylineOptions()
                            .color(R.color.CornflowerBlue)
                            .points(mHistoryTrace)
                            .visible(true));

            for(LatLng point : mHistoryTrace) {
                mBaiduMap.addOverlay(new MarkerOptions().position(point).icon(bitmap));
            }
        }

        // move and zoom to current position
        if(mCurrentLocation != null) {
            mBaiduMap.setMyLocationData(mCurrentLocation);
            mBaiduMap.animateMapStatus(
                    MapStatusUpdateFactory.newLatLngZoom(
                            new LatLng(
                                    mCurrentLocation.latitude,
                                    mCurrentLocation.longitude),
                            zoomLevel));
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        mMapView.onPause();
    }*/

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.map = googleMap;
        map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

        } else {
            map.setMyLocationEnabled(true);
        }
        map.setTrafficEnabled(true);
        map.setIndoorEnabled(true);
        map.setBuildingsEnabled(true);
        map.getUiSettings().setZoomControlsEnabled(true);
        drawTrace();
    }

    private void drawTrace() {
        List<com.baidu.mapapi.model.LatLng> mHistoryTrace = PedometerService.getHistoryTrace();
        for (int z = 0; z < mHistoryTrace.size() - 1; z++) {
            LatLng src = new LatLng(mHistoryTrace.get(z).latitude, mHistoryTrace.get(z).longitude);
            LatLng dest = new LatLng(mHistoryTrace.get(z + 1).latitude, mHistoryTrace.get(z).longitude);
            ;
            MarkerOptions markerOptions = new MarkerOptions();
            markerOptions.position(new LatLng(mHistoryTrace.get(z).latitude, mHistoryTrace.get(z).longitude));
            //mMap.clear();
            //markerOptions.title("Current Position");
            markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_notification));
            markerOptions.getPosition();
            map.addMarker(markerOptions);
            map.addPolyline(new PolylineOptions()
                    .add(new LatLng(src.latitude, src.longitude),
                            new LatLng(dest.latitude, dest.longitude))
                    .width(5).color(Color.BLUE).geodesic(true));
        }
    }

    @Override
    public void onResume() {
        mapView.onResume();
        super.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }
}
