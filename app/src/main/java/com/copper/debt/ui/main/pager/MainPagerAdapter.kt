
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

@Suppress("DEPRECATION")
class MainPagerAdapter(manager: FragmentManager) : FragmentPagerAdapter(manager) {

  private val pages = mutableListOf<Fragment>()

  fun setPages(data: List<Fragment>) {
    pages.clear()
    pages.addAll(data)
    notifyDataSetChanged()
  }

  override fun getItem(position: Int): Fragment = pages[position]
  override fun getCount() = pages.size
}