package com.prototype.protomap;

import ru.yandex.yandexmapkit.MapController;
import ru.yandex.yandexmapkit.MapView;
import ru.yandex.yandexmapkit.OverlayManager;
import ru.yandex.yandexmapkit.overlay.Overlay;
import ru.yandex.yandexmapkit.overlay.OverlayItem;
import ru.yandex.yandexmapkit.overlay.balloon.BalloonItem;
import ru.yandex.yandexmapkit.overlay.balloon.OnBallonListener;
import ru.yandex.yandexmapkit.utils.GeoPoint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.MotionEvent;

public class MapActivity extends Activity implements OnBallonListener{

    MapController mMapController;
    OverlayManager mOverlayManager;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        setTitle(R.string.app_name);
        
        MapView mapView = (MapView) findViewById(R.id.map);
        mMapController = mapView.getMapController();
        mMapController.setJamsVisible(true);
        mOverlayManager = mMapController.getOverlayManager();
        mOverlayManager.getMyLocation().setEnabled(false);
        
        Overlay overlay = new Overlay(mMapController);

        OverlayItem CarStation = new OverlayItem(new GeoPoint(55.733828,37.59386), BitmapFactory.decodeResource(getResources() , R.drawable.point));
        BalloonItem balloonCarStation = new BalloonItem(CarStation.getGeoPoint(), BitmapFactory.decodeResource(getResources() , R.drawable.carstation));
        balloonCarStation.setText("<b>Автосалон GS-Trade</b>");

        balloonCarStation.setOnBallonListener(this);
        
        CarStation.setBalloonItem(balloonCarStation);
        overlay.addOverlayItem(CarStation);
        
        mOverlayManager.addOverlay(overlay);
    }
    
    public void onBallonClick(MotionEvent arg0, BalloonItem ballonItem) {
        // TODO Auto-generated method stub

    	Intent intent = new Intent(this, OrgActivity.class);
    	startActivity(intent);
    	
    }
}
