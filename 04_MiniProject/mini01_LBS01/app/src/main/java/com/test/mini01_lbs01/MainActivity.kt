package com.test.mini01_lbs01

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.test.mini01_lbs01.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    val HTTP_URL = "https://maps.googleapis.com/maps/api/place/nearbysearch/output?parameters"

    var permissionCheck = false
    lateinit var gMap: GoogleMap
    var location: Location? = null
    var myMarker:Marker? = null
    var check = false

    lateinit var activityMainBinding: ActivityMainBinding
    var myLocationListener: LocationListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()

        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        activityMainBinding.run{
            toolbar.run{
                title = "LBSProject"
                inflateMenu(R.menu.current_place_menu)
                setOnMenuItemClickListener {
                    when(it?.itemId){
                        // 현재 위치 메뉴
                        R.id.get_now_position -> {
                            // 현재 위치를 측정하고 지도를 갱신한다.
                            check = true
                            getMyLocation()
                            check = false
                        }
                        R.id.selectPlace -> {
                            getPlace()
                        }
                    }
                    false
                }
            }
        }
        val locationManager = getSystemService(LOCATION_SERVICE) as LocationManager
        val supportMapFragment = supportFragmentManager.findFragmentById(R.id.map_fragment) as SupportMapFragment
        supportMapFragment?.getMapAsync{
            gMap = it
            it.uiSettings.isZoomControlsEnabled = true

            // 권한을 확인한다.
            val a1 = ActivityCompat.checkSelfPermission(this@MainActivity, Manifest.permission.ACCESS_FINE_LOCATION)

            if(a1 == PackageManager.PERMISSION_GRANTED ){
                location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
            }
            if(location != null){
                setMyLocation(location!!)
            }
            getMyLocation()
        }
    }

    private fun getPlace(){
        
    }

    private fun setMyLocation(location: Location){
        // 위치 측정을 중단한다.
        if(myLocationListener != null){
            val locationManager = getSystemService(LOCATION_SERVICE) as LocationManager
            locationManager.removeUpdates(myLocationListener!!)
            myLocationListener = null
        }

        // 위도와 경도를 관리하는 객체를 생성한다.
        val latLng = LatLng(location.latitude, location.longitude)

        // 지도를 이용시키기 위한 객체를 생성한다.
        //val cameraUpdate = CameraUpdateFactory.newLatLng(latLng)
        val cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, gMap.cameraPosition.zoom)
        // 지도를 이동한다.
        // mainGoogleMap.moveCamera(cameraUpdate)
        gMap.animateCamera(cameraUpdate)

        // 현재 위치에 마커를 표시한다.
        val markerOptions = MarkerOptions()
        markerOptions.position(latLng)

        // 마커 이미지를 변경한다.
//        val markerBitmap = BitmapDescriptorFactory.fromResource(android.R.drawable.ic_menu_mylocation)
//        markerOptions.icon(markerBitmap)

        // 기존에 표시한 마커를 제거한다.
        if(myMarker != null){
            myMarker?.remove()
            myMarker = null
        }

        myMarker = gMap.addMarker(markerOptions)
    }

    private fun getMyLocation(){
        val a1 = ActivityCompat.checkSelfPermission(this@MainActivity, Manifest.permission.ACCESS_FINE_LOCATION)
        if(a1 == PackageManager.PERMISSION_GRANTED){
            val locationManager = getSystemService(LOCATION_SERVICE) as LocationManager

            myLocationListener = object: LocationListener{
                override fun onLocationChanged(location: Location) {
                    setMyLocation(location)
                }

            }
            if(locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) == true){

                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, gMap.cameraPosition.zoom, myLocationListener!!)
            }
        } else {
            getLocationPermission()
        }
    }

    private fun getLocationPermission() {

        if (ContextCompat.checkSelfPermission(this.applicationContext,
                Manifest.permission.ACCESS_FINE_LOCATION)
            == PackageManager.PERMISSION_GRANTED) {
            permissionCheck = true
        } else {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                10)
        }

    }

}