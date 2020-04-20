package multi.android.map_location_pro.location;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;

import multi.android.map_location_pro.R;

//현재 위치 정보를 가져와서 map에 연결해서 출력 - avd, device 둘 다 체크
public class LocationMapExam2 extends AppCompatActivity implements OnMapReadyCallback {
    LocationManager locationManager;
    GoogleMap map;
    String[] permission_list = {Manifest.permission.ACCESS_FINE_LOCATION,
                                Manifest.permission.ACCESS_COARSE_LOCATION};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_map_exam);
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
            requestPermissions(permission_list,1000);
        }else{
            init();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        for(int result:grantResults){
            if(result== PackageManager.PERMISSION_DENIED){
                return;
            }
        }
        init();
    }

    //맵정보 추출
    public void init() {
        FragmentManager manager = getSupportFragmentManager();
        SupportMapFragment mapFragment = (SupportMapFragment)manager.findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    //지도가 준비되면 자동으로 호출되는 메서드
    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        if(map != null) {
            getMyLocation();
        }
    }

    //location을 추출 - 현재 나의 위치정보를 추출
    public void getMyLocation() {
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        //이전에 이미 측정했었던 값을 가져오고 - 새롭게 측정하는데 시간이 많이 걸릴 수 있으므로
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            for(String permission : permission_list) {
                if(checkSelfPermission(permission) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
            }
        }
        Location gps_loc = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        Location network_loc = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

        if(gps_loc != null) {
            setMyLocation(gps_loc);
        } else if (network_loc != null) {
            setMyLocation(network_loc);
        }
        //현재 측정한 값도 가져오고
        MyLocationListener locationListener = new MyLocationListener();
        //현재 활성화되어 있는 Provider를 체크
        if(locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                                        3000,
                                        1,
                                         locationListener);
        } else if(locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,
                    3000,
                    1,
                    locationListener);
        }
    }

    //location정보를 지도에 세팅하는 메서드
    public void setMyLocation(Location myLocation) {
        Log.d("myloc", "위도:"+myLocation.getLatitude() + ", 경도:" + myLocation.getLongitude());
        LatLng myloc = new LatLng(myLocation.getLatitude(), myLocation.getLongitude());
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(myloc, 15);
        map.moveCamera(cameraUpdate);
        //현재 위치를 포인트로 표시
        map.setMyLocationEnabled(true);
    }

    //현재 위치가 변경되거나 Provider에 변화가 있을 때 반응할 수 있도록 설정
    class MyLocationListener implements LocationListener {

        @Override
        public void onLocationChanged(Location location) {
            //현재 위도 경도가 변경되면 호출되는 메서드
            setMyLocation(location);
            //리스너 연결해제
            locationManager.removeUpdates(this);

        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    }


}
