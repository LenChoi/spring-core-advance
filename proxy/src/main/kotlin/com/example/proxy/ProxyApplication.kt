package com.example.proxy

import hello.proxy.config.AppV1Config
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Import

@Import(AppV1Config::class)
@SpringBootApplication
class ProxyApplication

fun main(args: Array<String>) {
    runApplication<ProxyApplication>(*args)
}
