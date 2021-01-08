package com.codingbydumbbell.shop

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_bus.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.info
import org.jetbrains.anko.uiThread
import java.net.URL

class BusActivity : AppCompatActivity(), AnkoLogger {
    var buses: List<BusInfo>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bus)
        doAsync {
            val json =
                URL("http://data.tycg.gov.tw/opendata/datalist/datasetMeta/download?id=b3abedf0-aeae-4523-a804-6e807cbad589&rid=bf55b21a-2b7c-4ede-8048-f75420344aed")
                    .readText()
            info { json }
            println(json)
            buses = Gson().fromJson<Bus>(json, object : TypeToken<Bus>() {}.type).datas
            uiThread {
                recycler.layoutManager = LinearLayoutManager(this@BusActivity)
                recycler.setHasFixedSize(true)
                recycler.adapter = BusAdapter()
            }
        }
    }

    inner class BusAdapter : RecyclerView.Adapter<BusHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BusHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.row_bus, parent, false)
            return BusHolder(view)
        }

        override fun getItemCount(): Int {
            return buses?.size ?: 0
        }

        override fun onBindViewHolder(holder: BusHolder, position: Int) {
            val busInfo = buses!!.get(position)
            holder.bindBus(busInfo!!)
        }
    }

    inner class BusHolder(view: View) : RecyclerView.ViewHolder(view) {
        val busId: TextView = view.findViewById(R.id.bus_id)
        val routeId: TextView = view.findViewById(R.id.route_id)
        val speed: TextView = view.findViewById(R.id.speed)
        fun bindBus(busInfo: BusInfo) {
            busId.text = busInfo.BusID
            routeId.text = busInfo.RouteID
            speed.text = busInfo.Speed
        }
    }
}

data class Bus(
    val datas: List<BusInfo>
)

data class BusInfo(
    val Azimuth: String,
    val BusID: String,
    val BusStatus: String,
    val DataTime: String,
    val DutyStatus: String,
    val GoBack: String,
    val Latitude: String,
    val Longitude: String,
    val ProviderID: String,
    val RouteID: String,
    val Speed: String,
    val ledstate: String,
    val sections: String
)