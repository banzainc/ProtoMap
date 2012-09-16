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
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.widget.Toast;

public class MapActivity extends Activity implements OnBallonListener{

    MapController mMapController;
    OverlayManager mOverlayManager;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        setTitle(R.string.app_name);
        
        MapView mapView = (MapView) findViewById(R.id.map);
        mapView.showBuiltInScreenButtons(true);
        mMapController = mapView.getMapController();
        mMapController.setJamsVisible(true);
        mMapController.setPositionNoAnimationTo(new GeoPoint(59.917484,30.357646));
        mOverlayManager = mMapController.getOverlayManager();
        mOverlayManager.getMyLocation().setEnabled(false);
        
        Overlay overlay = new Overlay(mMapController);

        OverlayItem CarStation = new OverlayItem(new GeoPoint(59.917484,30.357646), BitmapFactory.decodeResource(getResources() , R.drawable.point));
        BalloonItem balloonCarStation = new BalloonItem(CarStation.getGeoPoint(), BitmapFactory.decodeResource(getResources() , R.drawable.carstation));
        balloonCarStation.setText("<b>Автосалон Эксклюзив</b>");

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
    
    public boolean onCreateOptionsMenu(Menu menu) {
        // TODO Auto-generated method stub
        menu.add("Задать фильтр");
        return super.onCreateOptionsMenu(menu);
      }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
      // TODO Auto-generated method stub
      showDialog(1);
      return super.onOptionsItemSelected(item);
    }
    
    protected Dialog onCreateDialog(int id) {
    	String data[] = { "стоимость", "рейтинг", "удаленность" };
    	boolean chkd[] = { false, false, false, false };
    	
    	AlertDialog.Builder adb = new AlertDialog.Builder(this);
    	adb.setTitle("фильтровать по:");
    	adb.setMultiChoiceItems(data,chkd, null);
    	adb.setPositiveButton("OK", null);
      
      return adb.create();
      }
}
