package hu.naturlecso.distancetracker.common.presentation

interface ItemActionProvider<T> {

    fun setOnItemClickedListener(onItemClickedListener: OnItemClickedListener<T>?)

    interface OnItemClickedListener<T> {
        fun onItemClicked(item: T)
    }
}
