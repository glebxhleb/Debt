import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.copper.debt.common.onTextChanged
import com.copper.debt.model.Debtor
import kotlinx.android.synthetic.main.item_debtor_sum.view.*


class DebtorHolder(
    itemView: View
) : RecyclerView.ViewHolder(itemView) {

    fun bind(debtor: Debtor) = with(itemView) {

        debtorName.text = debtor.name
        if (debtor.sum > 0) debtorSum.setText(debtor.sum.toString())

        debtorSum.onTextChanged {

        }
    }
}