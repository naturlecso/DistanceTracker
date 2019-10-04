package hu.naturlecso.distancetracker.common.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import hu.naturlecso.distancetracker.common.BR

abstract class BaseFragment<T: ViewModel>: Fragment() {

    abstract val layoutRes: Int

    abstract val viewModel: T

    protected open fun afterViews() {
        // hook method
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding: ViewDataBinding = DataBindingUtil.inflate(
            inflater, layoutRes, container, false)

        binding.lifecycleOwner = this
        binding.setVariable(BR.vm, viewModel)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        afterViews()
    }
}
