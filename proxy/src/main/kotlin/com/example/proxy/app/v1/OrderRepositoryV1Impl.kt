package hello.proxy.app.v1

import java.lang.Thread.sleep

class OrderRepositoryV1Impl : OrderRepositoryV1 {
    override fun save(itemId: String) {
        if (itemId == "ex") {
            throw IllegalStateException("예외 발생!")
        }
        sleep(1000)
    }

    private fun sleep(millis: Long) {
        try {
            Thread.sleep(millis)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
    }
}

