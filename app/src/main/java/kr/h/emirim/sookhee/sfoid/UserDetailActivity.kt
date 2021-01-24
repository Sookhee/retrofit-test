package kr.h.emirim.sookhee.sfoid

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.activity_user_detail.*

class UserDetailActivity : AppCompatActivity(), OnMapReadyCallback {
    lateinit var mMap: GoogleMap
    var latitude: Double = 0.0
    var longitude: Double = 0.0
    var markerTitle = ""
    var markerSnippet = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_detail)

        val intent = getIntent()

        Glide.with(this).load(intent.getStringExtra("image"))
            .centerCrop()
            .circleCrop()
            .into(user_detail_image)
        user_detail_name.text = intent.getStringExtra("name")
        user_detail_age.text = "(${intent.getStringExtra("age")})"
        user_detail_gender.text = intent.getStringExtra("gender")
        user_detail_nat.text = intent.getStringExtra("nat")
        user_detail_email.text = "\uD83D\uDCE7 " + intent.getStringExtra("email")
        user_detail_email.setOnClickListener {
            val myUri = Uri.fromParts("mailto", intent.getStringExtra("email"), null)
            val emailIntent = Intent(Intent.ACTION_SENDTO, myUri)
            startActivity(emailIntent)
        }
        user_detail_phone.text = "\u260E\uFE0F " + intent.getStringExtra("phone")
        user_detail_phone.setOnClickListener {
            val myUri = Uri.fromParts("tel", intent.getStringExtra("phone"), null)
            val dialIntent = Intent(Intent.ACTION_DIAL, myUri)
            startActivity(dialIntent)
        }
        user_detail_cell.text = "\uD83D\uDCF1 " + intent.getStringExtra("cell")
        user_detail_cell.setOnClickListener {
            val myUri = Uri.fromParts("tel", intent.getStringExtra("cell"), null)
            val dialIntent = Intent(Intent.ACTION_DIAL, myUri)
            startActivity(dialIntent)
        }

        latitude = intent.getStringExtra("latitude")?.toDouble() ?: 0.0
        longitude = intent.getStringExtra("longitude")?.toDouble() ?: 0.0
        markerTitle = intent.getStringExtra("city").toString()
        markerSnippet = intent.getStringExtra("country").toString()

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment!!.getMapAsync(this)

    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        val COORDINATES = LatLng(latitude, longitude)
        Log.d("LOCATION", "${latitude}, ${longitude}")
        val markerOptions = MarkerOptions()
        markerOptions.position(COORDINATES)
        markerOptions.title(markerTitle)
        markerOptions.snippet(markerSnippet)
        mMap.addMarker(markerOptions)

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(COORDINATES, 8F))
    }
}