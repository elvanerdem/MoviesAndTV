package com.elvanerdem.moviesandtv.utils

import androidx.navigation.NavController

/**
 * Created by elvanerdem on 28.02.2021.
 */
fun NavController.popBackStackAllInstances(destination: Int): Boolean {
    var popped: Boolean
    while (true) {
        popped = popBackStack(destination, true)
        if (!popped) {
            break
        }
    }
    return popped
}