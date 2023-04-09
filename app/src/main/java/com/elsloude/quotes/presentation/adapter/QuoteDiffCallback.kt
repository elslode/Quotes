package com.elsloude.quotes.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.elsloude.quotes.presentation.entity.QuoteUi

object QuoteDiffCallback : DiffUtil.ItemCallback<QuoteUi>() {
    override fun areItemsTheSame(oldItem: QuoteUi, newItem: QuoteUi): Boolean {
        return oldItem.ticker == newItem.ticker
    }

    override fun areContentsTheSame(oldItem: QuoteUi, newItem: QuoteUi): Boolean {
        return oldItem == newItem
    }
}