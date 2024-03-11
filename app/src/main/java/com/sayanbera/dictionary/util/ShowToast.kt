package com.sayanbera.dictionary.util

import android.content.Context
import android.widget.Toast

fun showToast(
    message: String,
    context: Context,
    toastLength: Int = Toast.LENGTH_SHORT
) {
    Toast.makeText(context, message, toastLength).show()
}