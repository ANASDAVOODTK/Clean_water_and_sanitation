package com.anasandalexy.cleanwaterandsanitation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.graphics.drawable.DrawableCompat;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.mapbox.android.core.permissions.PermissionsListener;
import com.mapbox.android.core.permissions.PermissionsManager;
import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.annotations.Icon;
import com.mapbox.mapboxsdk.annotations.IconFactory;
import com.mapbox.mapboxsdk.annotations.MarkerOptions;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.location.LocationComponent;
import com.mapbox.mapboxsdk.location.LocationComponentActivationOptions;
import com.mapbox.mapboxsdk.location.modes.CameraMode;
import com.mapbox.mapboxsdk.location.modes.RenderMode;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;
import com.mapbox.mapboxsdk.maps.Style;
import com.mapbox.mapboxsdk.plugins.annotation.OnSymbolClickListener;
import com.mapbox.mapboxsdk.plugins.annotation.Symbol;
import com.mapbox.mapboxsdk.plugins.annotation.SymbolManager;
import com.mapbox.mapboxsdk.plugins.annotation.SymbolOptions;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class MapActivity extends AppCompatActivity implements
        OnMapReadyCallback, PermissionsListener {

    private PermissionsManager permissionsManager;
    private MapboxMap mapboxMap;
    private MapView mapView;
    private Symbol symbol;
    private static final String ID_ICON_AIRPORT = "airport";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

// Mapbox access token is configured here. This needs to be called either in your application
// object or in the same activity which contains the mapview.
        Mapbox.getInstance(this, getString(R.string.mapbox_access_token));

// This contains the MapView in XML and needs to be called after the access token is configured.
        setContentView(R.layout.activity_map);

        mapView = findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);
    }

    @Override
    public void onMapReady(@NonNull final MapboxMap mapboxMap) {
        MapActivity.this.mapboxMap = mapboxMap;

        mapboxMap.setStyle(new Style.Builder().fromUri("mapbox://styles/anasdavoodtk/ckraazho5644117qfpvww8b56"),
                new Style.OnStyleLoaded() {
                    @Override
                    public void onStyleLoaded(@NonNull Style style) {
                        enableLocationComponent(style);

                        Bitmap bitmap = getBitmapFromVectorDrawable(MapActivity.this,R.drawable.ic_green_circle);
                        IconFactory iconFactory = IconFactory.getInstance(MapActivity.this);
                        Icon icon = iconFactory.fromBitmap(bitmap);

                        Bitmap bitmap1 = getBitmapFromVectorDrawable(MapActivity.this,R.drawable.ic_orenge_circle);
                        IconFactory iconFactory1 = IconFactory.getInstance(MapActivity.this);
                        Icon icon1 = iconFactory1.fromBitmap(bitmap1);

                        mapboxMap.addMarker(new MarkerOptions()
                                .position(new LatLng(11.200780, 76.066096))
                                .icon(icon)
                                .title("public borewell"));

                        mapboxMap.addMarker(new MarkerOptions()
                                .position(new LatLng(11.190259, 76.063220))
                                .icon(icon)
                                .title("public borewell"));
                        mapboxMap.addMarker(new MarkerOptions()
                                .position(new LatLng(11.219055, 76.043326))
                                .icon(icon)
                                .title("public borewell"));

                        mapboxMap.addMarker(new MarkerOptions()
                                .position(new LatLng(11.185279, 76.056542))
                                .icon(icon1)
                                .title("public borewell"));

                        mapboxMap.addMarker(new MarkerOptions()
                                .position(new LatLng(11.182557, 76.042940))
                                .icon(icon)
                                .title("public borewell"));

                       mapboxMap.addMarker(new MarkerOptions()
                               .position(new LatLng(11.199759, 76.054971))
                                .icon(icon)
                                .title("public borewell11"));

                        mapboxMap.addMarker(new MarkerOptions()
                                .position(new LatLng(11.193988, 76.044776))
                                .icon(icon1)
                                .title("public borewell"));

                        mapboxMap.addMarker(new MarkerOptions()
                                .position(new LatLng(11.212044, 76.063768))
                                .icon(icon)
                                .title("Public Tank"));


                        mapboxMap.addMarker(new MarkerOptions()
                                .position(new LatLng(9.477696628478197, 76.3439326408599))
                                .icon(icon)
                                .title("KWA Water Tank, Pazhaveedu"));


                        mapboxMap.addMarker(new MarkerOptions()
                                .position(new LatLng(9.475555003096781, 76.33720644574807))
                                .icon(icon)
                                .title("Over Head Tank"));

                        mapboxMap.addOnMapClickListener(new MapboxMap.OnMapClickListener() {
                            @Override
                            public boolean onMapClick(@NonNull @NotNull LatLng point) {

                                Intent intent = new Intent(MapActivity.this , WaterQuality.class);
                                startActivity(intent);
                                return false;
                            }
                        });




                        SymbolManager symbolManager = new SymbolManager(mapView, mapboxMap, style);


                        symbolManager.setIconAllowOverlap(true);
                        symbolManager.setTextAllowOverlap(true);

                        SymbolOptions symbolOptions = new SymbolOptions()
                                .withLatLng(new LatLng(11.199759, 76.054971))
                                .withIconImage("green-circle")
                                .withIconSize(1.3f);

                        symbol = symbolManager.create(symbolOptions);

                        symbolManager.addClickListener(new OnSymbolClickListener() {
                            @Override
                            public boolean onAnnotationClick(Symbol symbol) {

                                return false;
                            }
                        });

                    }
                });


    }

    public static Bitmap getBitmapFromVectorDrawable(Context context, int drawableId) {
        Drawable drawable =  AppCompatResources.getDrawable(context, drawableId);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            drawable = (DrawableCompat.wrap(drawable)).mutate();
        }

        Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(),
                drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);

        return bitmap;
    }

    @SuppressWarnings( {"MissingPermission"})
    private void enableLocationComponent(@NonNull Style loadedMapStyle) {
// Check if permissions are enabled and if not request
        if (PermissionsManager.areLocationPermissionsGranted(this)) {

// Get an instance of the component
            LocationComponent locationComponent = mapboxMap.getLocationComponent();

// Activate with options
            locationComponent.activateLocationComponent(
                    LocationComponentActivationOptions.builder(this, loadedMapStyle).build());

// Enable to make component visible
            locationComponent.setLocationComponentEnabled(true);

// Set the component's camera mode
            locationComponent.setCameraMode(CameraMode.TRACKING);

// Set the component's render mode
            locationComponent.setRenderMode(RenderMode.COMPASS);
        } else {
            permissionsManager = new PermissionsManager(this);
            permissionsManager.requestLocationPermissions(this);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        permissionsManager.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onExplanationNeeded(List<String> permissionsToExplain) {
      //  Toast.makeText(this, R.string.user_location_permission_explanation, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onPermissionResult(boolean granted) {
        if (granted) {
            mapboxMap.getStyle(new Style.OnStyleLoaded() {
                @Override
                public void onStyleLoaded(@NonNull Style style) {
                    enableLocationComponent(style);
                }
            });
        } else {
            //Toast.makeText(this, R.string.user_location_permission_not_granted, Toast.LENGTH_LONG).show();
            finish();
        }
    }

    @Override
    @SuppressWarnings( {"MissingPermission"})
    protected void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mapView.onStop();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }


}