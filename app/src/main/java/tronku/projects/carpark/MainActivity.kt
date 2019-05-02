package tronku.projects.carpark

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.eftimoff.viewpagertransformers.ForegroundToBackgroundTransformer
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    private lateinit var lotList: ArrayList<LotModel>
    private lateinit var adapter: ViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lotList = ArrayList()
        getLotDetails()

        help_icon.setOnClickListener {
            startActivity(Intent(this, HelpActivity::class.java))
        }

        refresh.setOnClickListener {
            getLotDetails()
        }

        car_viewpager.setPageTransformer(true, ForegroundToBackgroundTransformer())
    }

    private fun getLotDetails() {
        layer.visibility = View.VISIBLE
        loader.visibility = View.VISIBLE

        val gson = Gson()
        val baseUrl = "https://twowaits.herokuapp.com/slotstatus"
        val request = JsonArrayRequest(
            Request.Method.GET, baseUrl, null,
            Response.Listener { response ->
                val listTypeToken = object : TypeToken<ArrayList<LotModel>>() {}.type
                Log.d("Response", response.toString())
                lotList = gson.fromJson(response.toString(), listTypeToken)
                Log.d("LotModel", lotList.toString())
            },
            Response.ErrorListener { error ->
                Log.e("Error: ", error.message)
            })

        val volleyRequestQueue = Volley.newRequestQueue(this)
        volleyRequestQueue.add(request)
        volleyRequestQueue.addRequestFinishedListener<JSONObject> {
            adapter = ViewPagerAdapter(supportFragmentManager, lotList)
            car_viewpager.adapter = adapter
            layer.visibility = View.INVISIBLE
            loader.visibility = View.INVISIBLE
        }
    }
}
