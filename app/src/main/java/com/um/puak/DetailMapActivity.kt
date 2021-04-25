package com.um.puak

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.*
import com.um.puak.R
import java.util.*

class DetailMapActivity : AppCompatActivity(), OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    var mMapView: MapView? = null

    private var mGoogleMap: GoogleMap? = null
    private var mGeocoder: Geocoder? = null
    private var mMarker: Marker? = null
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private var location: Location? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        getLocation()

        mGeocoder = Geocoder(this, Locale.getDefault())

        mMapView = findViewById<View>(R.id.mapView) as MapView
        mMapView!!.onCreate(savedInstanceState)
        mMapView!!.getMapAsync(this)

        try {
            MapsInitializer.initialize(applicationContext)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    @SuppressLint("MissingPermission")
    override fun onMapReady(googleMap: GoogleMap?) {
        mGoogleMap = googleMap

        if (mGoogleMap != null) {
            mGoogleMap!!.uiSettings.isMyLocationButtonEnabled = true
            mGoogleMap!!.isMyLocationEnabled = true
            mGoogleMap!!.setOnMarkerClickListener(this)

        } else {
            Toast.makeText(this, "Google Map is Null", Toast.LENGTH_SHORT).show()
        }
    }


    override fun onMarkerClick(marker: Marker?): Boolean {
        updateMapsUi(marker!!.position.latitude ?: 0.0, marker.position.longitude ?: 0.0)
        return false
    }

    private fun updateMapsUi(latitude: Double, longitude: Double) {
        val currentLatLng = LatLng(latitude, longitude)
        val currentPlace = mGeocoder!!.getFromLocation(latitude, longitude, 1)

        mGoogleMap!!.moveCamera(CameraUpdateFactory.newLatLng(currentLatLng))
        mGoogleMap!!.animateCamera(
            CameraUpdateFactory.newLatLngZoom(
                currentLatLng, 18.0f
            )
        )

    }

    private fun getLocation() {
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            fusedLocationClient.lastLocation
                .addOnSuccessListener { location: Location? ->
                    this.location = location
//                    getLatestData(location?.latitude ?: 0.0, location?.longitude ?: 0.0)
                }
        } else {

            // No explanation needed; request the permission
            ActivityCompat.requestPermissions(
                this,
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ),
                1
            )
        }
    }

    fun addMarker(position: LatLng, title: String): Marker {
        mMarker = mGoogleMap!!.addMarker(
            MarkerOptions()
                .position(position)
                .title(title)
                .icon(
                    BitmapDescriptorFactory.defaultMarker(
                        BitmapDescriptorFactory.HUE_RED
                    )
                )
        )

        return mMarker!!
    }


    override fun onResume() {
        mMapView!!.onResume()
        super.onResume()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>,
                                            grantResults: IntArray) {
        when (requestCode) {
            1 -> {
                if (grantResults.isNotEmpty() && grantResults[0] ==
                    PackageManager.PERMISSION_GRANTED) {
                    if ((ContextCompat.checkSelfPermission(this,
                            Manifest.permission.ACCESS_FINE_LOCATION) ===
                                PackageManager.PERMISSION_GRANTED)) {
                        Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show()
                }
                return
            }
        }
    }
}