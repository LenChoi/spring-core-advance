package com.example.proxy.trace.callback

import com.example.proxy.trace.TraceStatus
import com.example.proxy.trace.logtrace.LogTrace

class TraceTemplate(trace: LogTrace) {
    private val trace: LogTrace
    lateinit var status: TraceStatus

    init {
        this.trace = trace
    }

    fun <T> execute(message: String, callback: TraceCallback<T>): T? {
        return try {
            status = trace.begin(message)

            //로직 호출
            val result = callback.call()
            trace.end(status)
            result
        } catch (e: Exception) {
            trace.exception(status, e)
            throw e
        }
    }
}