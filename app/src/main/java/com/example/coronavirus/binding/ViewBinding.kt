package com.example.coronavirus.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.coronavirus.data.CoronaCountryStats
import com.example.coronavirus.ui.country.CoronaAdapter
import com.example.coronavirus.ui.country.CountryAdapter


@BindingAdapter(value = ["app:coronaStatsList"])
fun setCoronaStatsList(rv: RecyclerView, items: List<CoronaCountryStats>?) {

    if (rv.adapter as? CoronaAdapter == null) {
        rv.adapter = CoronaAdapter()
    }
    (rv.adapter as CoronaAdapter).submitList(items)
}

@BindingAdapter(value = ["app:countryList"])
fun setWorldList(rcv: RecyclerView, items: List<String>?) {

    if (rcv.adapter as? CountryAdapter == null) {
        rcv.adapter = CountryAdapter()
    }
    (rcv.adapter as CountryAdapter).submitList(items)
    //(rcv.adapter as CountryAdapter).notifyDataSetChanged()
}

@BindingAdapter("flagImage")
fun loadFlag(view: ImageView, imageUrl: String?) {
    Glide.with(view.context)
        .load(imageUrl).apply(RequestOptions())
        .into(view)

    //.circleCrop()
}

//@BindingAdapter(value = ["app:parameterText", "app:parameterValue"])
//fun setWorldDetailParameters(cl: ConstraintLayout, parameterText: String, parameterValue: String) {
//
//    val binding: ItemEachDetailBinding = DataBindingUtil.inflate(
//        LayoutInflater.from(cl.context),
//        R.layout.item_each_detail, null, false
//    )
//    cl.addView(binding.root)
//    binding.parameterText = parameterText
//    binding.parameterValue = parameterValue
//    binding.executePendingBindings()
//}