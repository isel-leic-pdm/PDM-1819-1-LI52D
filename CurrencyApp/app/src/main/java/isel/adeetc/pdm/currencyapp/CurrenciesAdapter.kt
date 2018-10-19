package isel.adeetc.pdm.currencyapp

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import isel.adeetc.pdm.currencyapp.network.Quote

class CurrencyViewHolder(view: ViewGroup) : RecyclerView.ViewHolder(view) {

    private val currecyNameView: TextView = view.findViewById(android.R.id.text1)
    private val currecyValueView: TextView = view.findViewById(android.R.id.text2)

    fun bindTo(quote: Quote?) {
        currecyNameView.text = quote?.currency
        currecyValueView.text = quote?.quote.toString()
    }
}

class CurrenciesAdapter(val viewModel: CurrenciesViewModel) : RecyclerView.Adapter<CurrencyViewHolder>() {

    override fun getItemCount(): Int = viewModel.currencies.value?.quotes?.size ?: 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyViewHolder =
        CurrencyViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(android.R.layout.simple_list_item_2, parent, false) as ViewGroup
        )

    override fun onBindViewHolder(holder: CurrencyViewHolder, position: Int) {
        holder.bindTo(viewModel.currencies.value?.quotes?.get(position))
    }
}