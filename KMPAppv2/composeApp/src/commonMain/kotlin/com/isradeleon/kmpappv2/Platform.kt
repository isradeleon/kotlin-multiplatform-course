package com.isradeleon.kmpappv2

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform