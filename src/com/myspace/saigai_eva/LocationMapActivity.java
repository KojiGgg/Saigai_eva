package com.myspace.saigai_eva;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.LocationSource.OnLocationChangedListener;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Point;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

public class LocationMapActivity extends MapActivity implements LocationListener{
	private OnLocationChangedListener mListener;
	private GoogleMap mMap;
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        mMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.mapview)).getMap();
        if( mMap != null){
            // Mapは利用可能 
        }
        // 拡大・縮小ボタンを表示
        mMap.getUiSettings().setZoomControlsEnabled(true); 
        // 通常の地図に変更
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
	    //コンパスの有効化
        mMap.getUiSettings().setCompassEnabled(true);
	    //現在位置を獲得する
        mMap.setMyLocationEnabled(true);
        //現在位置の獲得（パラメータ）
        LocationManager l = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        l.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
	}
	public void onLocationChanged(Location location) {
		if (mListener != null) {
            mListener.onLocationChanged(location);
        }
        mMap.animateCamera(CameraUpdateFactory.newLatLng(new LatLng(location.getLatitude(), location.getLongitude())));
    }

	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}
	
	class MapOverlay extends Overlay {
	    private GeoPoint pointToDraw;
	    int imageNames;

	    // This is the cached Point on the screen that will get refilled on
	    // every draw
	    private Point mScreenPoints;

	    // This is the cached decoded bitmap that will be drawn each time
	    private Bitmap mBitmap;

	    // Cached Paint
	    private Paint mCirclePaint;

	    public MapOverlay() {

	        imageNames = R.drawable.bule_dot;

	        // This only needs to be made here, once. It never needs to change.
	        mCirclePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
	        mCirclePaint.setColor(0x10000000);
	        mCirclePaint.setStyle(Style.FILL_AND_STROKE);

	        // We only need to load this image once and then just keep drawing
	        // it when dirtied.
	        mBitmap = BitmapFactory.decodeResource(getResources(),
	                imageNames);

	        // This Point object will be changed every call to toPixels(), but
	        // the instance can be recycled
	        mScreenPoints = new Point();
	    }
	    

	    @Override
	    public boolean draw(Canvas canvas, MapView mapView, boolean shadow,
	            long when) {
	        super.draw(canvas, mapView, shadow);
	        mScreenPoints = mapView.getProjection().toPixels(pointToDraw,
	                mScreenPoints);

	        int totalCircle = 500;
	        int radius = 200;
	        int centerimagesize = 200;

	        for (int i = 1; i <= totalCircle; i++) {
	            canvas.drawCircle(mScreenPoints.x + 18, mScreenPoints.y + 36, i
	                    * radius, mCirclePaint);
	            canvas.drawBitmap(BitmapFactory.decodeResource(getResources(),
	                    imageNames), ((mScreenPoints.x) + (radius)),
	                    (mScreenPoints.y), null);
	        }

	        canvas.drawBitmap(mBitmap,
	                (mScreenPoints.x - (centerimagesize / 2)),
	                (mScreenPoints.y - (centerimagesize / 2)), null);
	        super.draw(canvas, mapView, shadow);

	        return true;
	    }
	}
}