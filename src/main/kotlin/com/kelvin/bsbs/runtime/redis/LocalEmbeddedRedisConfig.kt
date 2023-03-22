package com.kelvin.bsbs.runtime.redis

import jakarta.annotation.PostConstruct
import jakarta.annotation.PreDestroy
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import redis.embedded.RedisServer
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader

@Configuration
@Profile("local")
class LocalEmbeddedRedisConfig {
    @Value("\${spring.data.redis.port}")
    private val port = 0

    /**z
     * Local Redis Server start
     */
    private var redisServer: RedisServer? = null
    
    @PostConstruct
    @Throws(IOException::class)
    fun redisServer() {
        redisServer = RedisServer(port)
        redisServer!!.start()
    }

    @PreDestroy
    fun stopRedis() {
        if (redisServer != null) {
            redisServer!!.stop()
        }
    }

    /**
     * Embedded Redis가 현재 실행중인지 확인
     */
    @get:Throws(IOException::class)
    private val isRedisRunning: Boolean
        get() = isRunning(executeGrepProcessCommand(port))

    /**
     * 해당 port를 사용중인 프로세스 확인하는 sh 실행
     */
    @Throws(IOException::class)
    private fun executeGrepProcessCommand(port: Int): Process {
        val command = String.format("netstat -nat | grep LISTEN|grep %d", port)
        val shell = arrayOf("/bin/sh", "-c", command)
        return Runtime.getRuntime().exec(shell)
    }

    /**
     * 해당 Process가 현재 실행중인지 확인
     */
    private fun isRunning(process: Process): Boolean {
        var line: String?
        val pidInfo = StringBuilder()
        try {
            BufferedReader(InputStreamReader(process.inputStream)).use { input ->
                while (input.readLine().also {
                        line = it
                    } != null) {
                    pidInfo.append(line)
                }
            }
        } catch (e: Exception) {
            // do nothing
        }
        return pidInfo.isNotEmpty()
    }
}
