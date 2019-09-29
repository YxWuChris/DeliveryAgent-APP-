package jobproject.smsf.money.com.deliveryagent;

import android.annotation.TargetApi;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.LocationSource;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.LatLng;
import com.zhy.m.permission.MPermissions;
import com.zhy.m.permission.PermissionDenied;
import com.zhy.m.permission.PermissionGrant;

import java.util.Date;
/**
Create by zy18483, this refering for Amap official development document
 */

public class MainActivity extends AppCompatActivity implements LocationSource, AMapLocationListener   {

    private MapView mMapView;
    private AMap mAMap;
    public static final int REQUEST_CODES = 1000;
    public static AMapLocationClient mLocationClient = null;
    public static AMapLocationClientOption mLocationOption = null;
    private boolean isFirstLocation = true;
    private OnLocationChangedListener mListener;
    private Button start_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            initPermission();
        }
        start_btn = findViewById(R.id.start_btn);
        start_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,StartActivity.class);
                startActivity(intent);
            }
        });
        mMapView = findViewById(R.id.map);
        if (mAMap == null) {
            mAMap = mMapView.getMap();
        }
        mAMap.getUiSettings().setZoomControlsEnabled(false);
        mAMap.getUiSettings().setCompassEnabled(true);
        mAMap.setLocationSource(this);
        mAMap.getUiSettings().setMyLocationButtonEnabled(true);
        mAMap.setMyLocationEnabled(true);
        mAMap.setMyLocationType(AMap.LOCATION_TYPE_LOCATE);
        mMapView.onCreate(savedInstanceState);

        initGaoDeMap();
    }



    /**
     * Initial Amap
     */
    public void initGaoDeMap() {
        mLocationClient = new AMapLocationClient(getApplicationContext());
        mLocationClient.setLocationListener(this);
        mLocationOption = new AMapLocationClientOption();
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        mLocationOption.setInterval(3000);
        mLocationOption.setNeedAddress(true);
        mLocationOption.setWifiActiveScan(true);
        mLocationOption.setMockEnable(false);
        mLocationOption.setHttpTimeOut(50000);
        mLocationOption.setLocationCacheEnable(false);
        mLocationOption.setOnceLocation(false);
        mLocationClient.setLocationOption(mLocationOption);
        mLocationClient.startLocation();
    }

    private void initPermission() {
        MPermissions.requestPermissions(this, REQUEST_CODES,
                android.Manifest.permission.ACCESS_COARSE_LOCATION,
                android.Manifest.permission.ACCESS_FINE_LOCATION
        );
    }


    @PermissionGrant(REQUEST_CODES)
    public void grant() {

    }

    @PermissionDenied(REQUEST_CODES)
    public void denied() {
        Toast.makeText(this, "It may failed if deny GPS Permission", Toast.LENGTH_LONG).show();
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        MPermissions.onRequestPermissionsResult(this, requestCode, permissions, grantResults);
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();

    }


    @Override
    protected void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mMapView.onSaveInstanceState(outState);
    }

    @TargetApi(Build.VERSION_CODES.N)
    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        if (aMapLocation != null) {
            if (aMapLocation.getErrorCode() == 0) {
                aMapLocation.getLocationType();
                aMapLocation.getLatitude();
                aMapLocation.getLongitude();
                aMapLocation.getAccuracy();
                aMapLocation.getAddress();
                aMapLocation.getCountry();
                aMapLocation.getProvince();
                aMapLocation.getCity();
                aMapLocation.getDistrict();
                aMapLocation.getStreet();
                aMapLocation.getStreetNum();
                aMapLocation.getCityCode();
                aMapLocation.getAdCode();
                aMapLocation.getAoiName();
                aMapLocation.getBuildingId();
                aMapLocation.getFloor();
                aMapLocation.getGpsAccuracyStatus();
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date = new Date(aMapLocation.getTime());
                df.format(date);
                if (isFirstLocation) {
                    mAMap.moveCamera(CameraUpdateFactory.zoomTo(17));
                    mAMap.moveCamera(CameraUpdateFactory.changeLatLng(new LatLng(aMapLocation.getLatitude(), aMapLocation.getLongitude())));
                    mListener.onLocationChanged(aMapLocation);
                    isFirstLocation = false;
                }
            } else {

                Log.e("HLQ_Struggle", "location Error, ErrCode:"
                        + aMapLocation.getErrorCode() + ", errInfo:"
                        + aMapLocation.getErrorInfo());
            }
        }
    }

    @Override
    public void activate(OnLocationChangedListener onLocationChangedListener) {
        mListener = onLocationChangedListener;
    }

    @Override
    public void deactivate() {
        mListener = null;
    }
}
