package com.example.proxy.trace.template

import com.example.proxy.trace.TraceStatus
import com.example.proxy.trace.logtrace.LogTrace

abstract class AbstractTemplate<T>(trace: LogTrace) {
    private val trace: LogTrace
    lateinit var status: TraceStatus

    init {
        this.trace = trace
    }

    fun execute(message: String): T {

        return try {
            status = trace.begin(message)

            //로직 호출
            val result = call()
            trace.end(status)
            result
        } catch (e: Exception) {
            trace.exception(status, e)
            throw e
        }
    }

    protected abstract fun call(): T
}