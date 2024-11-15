package com.bowoon.common

object Log {
    private val IS_SHOWING = BuildConfig.IS_DEBUGGING_LOGGING

    fun i(tag: String, msg: String) {
        if (IS_SHOWING) android.util.Log.i("bowoon_$tag", getMessageWithLineNumber(msg))
    }

    fun i(msg: String) {
        if (IS_SHOWING) android.util.Log.i(tag(), getMessageWithLineNumber(msg))
    }

    fun v(tag: String, msg: String) {
        if (IS_SHOWING) android.util.Log.v("bowoon_$tag", getMessageWithLineNumber(msg))
    }

    fun v(msg: String) {
        if (IS_SHOWING) android.util.Log.v(tag(), getMessageWithLineNumber(msg))
    }

    fun d(tag: String, msg: String) {
        if (IS_SHOWING) android.util.Log.d("bowoon_$tag", getMessageWithLineNumber(msg))
    }

    fun d(msg: String) {
        if (IS_SHOWING) android.util.Log.d(tag(), getMessageWithLineNumber(msg))
    }

    fun w(tag: String, msg: String) {
        if (IS_SHOWING) android.util.Log.d("bowoon_$tag", getMessageWithLineNumber(msg))
    }

    fun w(msg: String) {
        if (IS_SHOWING) android.util.Log.w(tag(), getMessageWithLineNumber(msg))
    }

    fun e(tag: String, msg: String) {
        if (IS_SHOWING) android.util.Log.d("bowoon_$tag", getMessageWithLineNumber(msg))
    }

    fun e(msg: String) {
        if (IS_SHOWING) android.util.Log.e(tag(), getMessageWithLineNumber(msg))
    }

    fun printStackTrace(tr: Throwable? = null) {
        if (IS_SHOWING) tr?.printStackTrace()
    }

    private fun tag(): String =
        Thread.currentThread().stackTrace.let { trace ->
            var index = 4

            while (index < trace.size && trace[index].fileName.isNullOrEmpty()) {
                index++
            }

            when {
                trace.size > index -> "(${trace[index].fileName}:${trace[index].lineNumber})"
                else -> "LinkNotFound"
            }
        }

    private fun getMessageWithLineNumber(msg: String): String =
        Thread.currentThread().stackTrace.let { trace ->
            var index = 4

            while (index < trace.size && trace[index].fileName.isNullOrEmpty()) {
                index++
            }

            when {
                trace.size > index -> "(${trace[index].fileName}:${trace[index].lineNumber}) $msg"
                else -> msg
            }
        }
}