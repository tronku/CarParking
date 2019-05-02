package tronku.projects.carpark

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class ViewPagerAdapter(fragmentManager: FragmentManager, private val list: ArrayList<LotModel>): FragmentStatePagerAdapter(fragmentManager) {

    override fun getItem(position: Int): Fragment {
        return LotFrag.getInstance(list[position])
    }

    override fun getCount(): Int {
        return list.size
    }
}