package jobproject.smsf.money.com.deliveryagent;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdate;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.geocoder.GeocodeResult;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.amap.api.services.geocoder.RegeocodeQuery;
import com.amap.api.services.geocoder.RegeocodeResult;

import jobproject.smsf.money.com.deliveryagent.gen.MoneyBean;
import jobproject.smsf.money.com.deliveryagent.greendao.gen.MoneyBeanDao;

/**
 * Created by zy18483 on 2019/5/23.
 */

public class StartActivity extends AppCompatActivity {

    private MapView mMapView;
    private AMap mAMap;
    private CameraUpdate cameraUpdate;
    private StyleDialog mStyleDialog;
    private MoneyBeanDao mMoneyBeanDao;
    EditText editText;
    Spinner spinner;
    private String type;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        mMapView = findViewById(R.id.map);
        mMoneyBeanDao = GreenDaoHelper.getInstance().getDaoSession().getMoneyBeanDao();
        if (mAMap == null) {
            mAMap = mMapView.getMap();
        }
        getadress(29.807880, 121.573677);
        getadress(29.813466, 121.571360);
        getadress(29.820987, 121.573162);
        mMapView.onCreate(savedInstanceState);
    }

    public void getadress(final double va1, final double va2) {
        GeocodeSearch geocodeSearch = new GeocodeSearch(this);

        RegeocodeQuery regeocodeQuery = new RegeocodeQuery(new LatLonPoint(va1, va2), 200, GeocodeSearch.AMAP);

        //设置查询结果监听
        geocodeSearch.setOnGeocodeSearchListener(new GeocodeSearch.OnGeocodeSearchListener() {

            @Override
            public void onRegeocodeSearched(RegeocodeResult regeocodeResult, int i) {
                String s = regeocodeResult.getRegeocodeAddress().getFormatAddress();
                makepoint(s, va1, va2);
            }

            @Override
            public void onGeocodeSearched(GeocodeResult geocodeResult, int i) {
            }
        });

        geocodeSearch.getFromLocationAsyn(regeocodeQuery);
    }


    public void makepoint(String s, double va1, double va2) {
        LatLng latLng = new LatLng(va1, va2);
        Log.e("adress", s);


        Marker maker = mAMap.addMarker(new MarkerOptions().position(latLng).title("location:").snippet(s));
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(new LatLng(34, 115)).title("title").snippet("content");
        markerOptions.icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory
                .decodeResource(getResources(), R.mipmap.ic_launcher)));
        mAMap.addMarker(markerOptions);

        cameraUpdate = CameraUpdateFactory.newCameraPosition(new CameraPosition(latLng, 15, 0, 30));
        mAMap.moveCamera(cameraUpdate);



        mAMap.setOnMarkerClickListener(new AMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                mStyleDialog = new StyleDialog(StartActivity.this);
                mStyleDialog.onSetTextTile("Please input price");
                editText = mStyleDialog.findViewById(R.id.content_text);
                spinner = mStyleDialog.findViewById(R.id.spinner);
                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        type = adapterView.getItemAtPosition(i).toString();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });
                mStyleDialog.onSetCanleceBtn(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mStyleDialog.dismiss();
                    }
                });
                mStyleDialog.onSetyes1Btn(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String money = editText.getText().toString();
                        if (TextUtils.isEmpty(money)) {
                            Toast.makeText(StartActivity.this, "Input the amount of price", Toast.LENGTH_LONG).show();
                            return;
                        }
                        MoneyBean moneyBean = new MoneyBean();
                        moneyBean.setDate(String.valueOf(System.currentTimeMillis()));
                        moneyBean.setMoney(money);
                        moneyBean.setType(type);
                        long id = mMoneyBeanDao.insert(moneyBean);
                        mStyleDialog.dismiss();
                        Intent intent = new Intent(StartActivity.this, MoneyActivity.class);
                        intent.putExtra("id", id);
                        startActivity(intent);
                    }
                });
                mStyleDialog.show();
                return false;
            }
        });
        mAMap.setOnInfoWindowClickListener(new AMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {


            }
        });
    }


}
