package tronku.projects.carpark


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import kotlinx.android.synthetic.main.fragment_lot.*

class LotFrag : Fragment() {

    companion object {
        fun getInstance(lot: LotModel): Fragment {
            val fragment = LotFrag()
            val bundle = bundleOf(
                "id" to lot.id,
                "status" to lot.status,
                "carNo" to lot.carNo,
                "owner" to lot.owner
            )

            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_lot, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val status = arguments!!["status"] as Int
        val carNo = arguments!!["carNo"] as String
        val owner = arguments!!["owner"] as String
        val id = arguments!!["id"] as Int

        car_no!!.text = carNo
        owner_name!!.text = owner.capitalize()
        slot_id!!.text = id.toString()

        parking_status.setImageResource( when(status) {
            1 -> R.drawable.ic_parking_red
            2 -> R.drawable.ic_parking_yellow
            3 -> R.drawable.ic_parking_green
            else -> R.drawable.ic_parking_green
        })

        if (status == 3) {
            car_no.visibility = View.GONE
            owner_name.visibility = View.GONE
            available.visibility = View.VISIBLE
        }
    }
}
