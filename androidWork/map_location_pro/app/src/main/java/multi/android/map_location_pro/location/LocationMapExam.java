package multi.android.map_location_pro.location;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.security.Provider;
import java.util.List;

import multi.android.map_location_pro.R;
//현재 위치 정보를 가져와서 map에 연결해서 출력 - avd, device 둘 다 체크
public class LocationMapExam extends AppCompatActivity implements LocationListener,
                                                        OnMapReadyCallback {
    boolean permission_state;
    LocationManager locationManager;
    List<String> enableProvider_list;
    GoogleMap map;
    MarkerOptions markerOptions;
    LatLng curLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_map_exam);
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        getMap();
        checkPermissions();

    }

    public void getMap() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        SupportMapFragment mapFragment = (SupportMapFragment) fragmentManager.findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    public void checkPermissions() {
        if(ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED |
                ContextCompat.checkSelfPermission(this,
                        Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,
                    new String[] {
                            Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_COARSE_LOCATION},
                    1001);
        } else {
            permission_state = true;
            //권한설정이 완료되면 provider목록을 가져와서 출력
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //                         권한의 성공 설정에 대한 결과가 있다.
        if(requestCode == 1001 && grantResults.length > 0) {
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED
                    & grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                permission_state = true;
            }
        }
    }

    public void getLocation() {
        //사용 가능한 목록
        enableProvider_list = locationManager.getProviders(true);
        for (String provider : enableProvider_list) {
            Log.d("provider", provider);
            Location location = null;
            try {
                location = locationManager.getLastKnownLocation(provider);
                if(location != null) {
                    locationManager.requestLocationUpdates(provider,
                            1000,      //1초마다
                            1,      //m 기준
                            this);
                    curLocation = new LatLng(location.getLatitude(), location.getLongitude());
                }
            } catch (SecurityException e) {
                Log.d("msg", "============" + e.getMessage());
            }
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        if(map != null) {
            getLocation();
            map.getUiSettings().setZoomControlsEnabled(true);
            //현재 나의 위치를 포인트로 표시 - 위치기반서비스에 대한 퍼미션 체크가 완료되어야 표시
            map.setMyLocationEnabled(true);
            map.moveCamera(CameraUpdateFactory.newLatLngZoom(curLocation,15));
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        Log.d("provider", location.getLatitude() + "," +  location.getLongitude());
        String provider = location.getProvider();
        Log.d("provider", provider);
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(location.getLatitude(), location.getLongitude()),15));
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
