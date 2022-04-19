package com.example.proxy.trace.logtrace

import com.example.proxy.trace.TraceId
import com.example.proxy.trace.TraceStatus

class FieldLogTrace : LogTrace {
    private var traceIdHolder : TraceId? = null

    override fun begin(message: String): TraceStatus {
        syncTraceId()
        val traceId: TraceId = traceIdHolder!!
        val startTimeMs = System.currentTimeMillis()

        return TraceStatus(traceId, startTimeMs, message)
    }

    override fun end(status: TraceStatus) {
        complete(status, null)
    }

    override fun exception(status: TraceStatus, e: Exception) {
        complete(status, e)
    }

    private fun complete(status: TraceStatus, e: Exception?) {
        val stopTimeMs = System.currentTimeMillis()
        val resultTimeMs: Long = stopTimeMs - status.startTimeMs
        val traceId: TraceId = status.traceId

        releaseTraceId()
    }

    private fun syncTraceId() {
        if (traceIdHolder == null) {
            traceIdHolder = TraceId()
        } else {
            traceIdHolder = traceIdHolder!!.createNextId()
        }
    }

    private fun releaseTraceId() {
        traceIdHolder = if (traceIdHolder!!.isFirstLevel) {
            null //destroy
        } else {
            traceIdHolder!!.createPreviousId()
        }
    }

    companion object {
        private const val START_PREFIX = "-->"
        private const val COMPLETE_PREFIX = "<--"
        private const val EX_PREFIX = "<X-"
        private fun addSpace(prefix: String, level: Int): String {
            val sb = StringBuilder()
            for (i in 0 until level) {
                sb.append(if (i == level - 1) "|$prefix" else "|   ")
            }
            return sb.toString()
        }
    }
}