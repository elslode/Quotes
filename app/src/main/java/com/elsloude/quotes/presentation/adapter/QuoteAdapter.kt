package com.elsloude.quotes.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.elsloude.quotes.R
import com.elsloude.quotes.common.isNull
import com.elsloude.quotes.databinding.QuoteItemBinding
import com.elsloude.quotes.presentation.entity.QuoteUi

class QuoteAdapter(private val context: Context) :
    ListAdapter<QuoteUi, QuoteViewHolder>(QuoteDiffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuoteViewHolder {
        val binding = QuoteItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return QuoteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: QuoteViewHolder, position: Int) {
        val quote = getItem(position)
        with(holder.binding) {
            with(quote) {

                val traderName = context.resources.getString(R.string.company_trade_name)
                val changePricePercent = context.resources.getString(R.string.price_change)
                val priceQuote = context.resources.getString(R.string.price_quote)

                val defaultBack = R.color.white
                val defaultText = R.color.black

                ticketTv.text = ticker
                traderNameTv.text = String.format(traderName, lastTradeExchange, companyName)
                lastPriceTv.text = String.format(
                    priceQuote,
                    lastTradePrice,
                    percentChangeFromPreviousClose
                )

                quoteChangePriceTv.apply {
                    text = String.format(changePricePercent, priceChangeInPoints)
                    setBackgroundResource(colorChangesBackground ?: defaultBack)
                    setTextColor(context.getColor(changePriceColor ?: defaultText))
                }
            }
        }
    }
}