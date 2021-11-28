package com.elvanerdem.moviesandtv.ui.base

import android.os.Bundle
import android.widget.Toast
import androidx.activity.addCallback
import androidx.appcompat.app.AppCompatDialog
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.elvanerdem.moviesandtv.R
import com.elvanerdem.moviesandtv.utils.popBackStackAllInstances


abstract class BaseFragment<VM: BaseViewModel, VB: ViewDataBinding> : Fragment() {

    protected lateinit var viewModel: VM

    private val progressDialog: AppCompatDialog? by lazy {
        AppCompatDialog(context).apply {
            setContentView(R.layout.layout_progressbar_dialog)
            window?.setBackgroundDrawableResource(android.R.color.transparent)
            setCancelable(false)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = provideViewModel()
        bindViewModel()

        viewModel.isLoading.observe(this, {
            if (it) {
                showLoading()
            } else {
                hideLoading()
            }
        })

        viewModel.errorMessage.observe(this, {
            if (it.isNotEmpty()) {
                showErrorMessage(it)
            }
        })

    }

    abstract fun provideViewModel(): VM

    abstract fun bindViewModel()

    open fun showLoading() {
        progressDialog?.show()
    }

    open fun hideLoading() {
        progressDialog?.hide()
    }

    open fun showErrorMessage(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()

        requireActivity().onBackPressedDispatcher.addCallback(this) {
            val navController = findNavController()
            findNavController().popBackStackAllInstances(navController.currentBackStackEntry?.destination?.id!!)
        }
    }
}