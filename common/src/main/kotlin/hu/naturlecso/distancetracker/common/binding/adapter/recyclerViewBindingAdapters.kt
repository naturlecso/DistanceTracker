package hu.naturlecso.distancetracker.common.binding.adapter

import android.graphics.drawable.Drawable
import android.widget.LinearLayout
import androidx.databinding.BindingAdapter
import androidx.databinding.BindingMethod
import androidx.databinding.BindingMethods
import androidx.recyclerview.widget.RecyclerView
import hu.naturlecso.distancetracker.common.binding.Command
import hu.naturlecso.distancetracker.common.presentation.BindableRecyclerViewAdapter
import hu.naturlecso.distancetracker.common.presentation.DividerItemDecoration
import hu.naturlecso.distancetracker.common.presentation.ItemActionProvider

@BindingMethods(
    BindingMethod(
        type = RecyclerView::class,
        attribute = "hasFixedSize",
        method = "setHasFixedSize")
)
class RecyclerViewBindingMethods

@Suppress("Unchecked_cast")
@BindingAdapter("items")
fun <T> RecyclerView.bindItems(items: List<T>?) {
    adapter ?: return
    items ?: return

    if (adapter is BindableRecyclerViewAdapter<*>) {
        (adapter as BindableRecyclerViewAdapter<T>).swapItems(items)
    }
}

@Suppress("Unchecked_cast")
@BindingAdapter("itemClicked")
fun <T> RecyclerView.bindItemClickedCommand(commandWithParam: ((T) -> Command)?) {
    if (adapter != null && adapter is ItemActionProvider<*>) {
        val actionProvider = adapter as ItemActionProvider<T>

        actionProvider.setOnItemClickedListener(commandWithParam?.run {
            object : ItemActionProvider.OnItemClickedListener<T> {
                override fun onItemClicked(item: T) {
                    invoke(item).execute()
                }
            }
        })
    }
}

@BindingAdapter(value = ["divider", "dividerOrientation", "showLastDivider"], requireAll = false)
fun RecyclerView.bindDivider(divider: Drawable?, orientation: Int?, showLast: Boolean?) {
    divider ?: return

    addItemDecoration(
        DividerItemDecoration(
        divider = divider,
        orientation = orientation ?: LinearLayout.VERTICAL,
        showLastItem = showLast ?: false)
    )
}
