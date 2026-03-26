package com.isradeleon.kmpapp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform