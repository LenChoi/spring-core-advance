package hello.proxy.config

import hello.proxy.app.v1.*
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
open class AppV1Config {

    @Bean
    open fun orderControllerV1(): OrderControllerV1 {
        return OrderControllerV1Impl(orderServiceV1())
    }

    @Bean
    open fun orderServiceV1() : OrderServiceV1 {
        return OrderServiceV1Impl(orderRepositortV1())
    }

    @Bean
    open fun orderRepositortV1() : OrderRepositoryV1 {
        return OrderRepositoryV1Impl()
    }
}