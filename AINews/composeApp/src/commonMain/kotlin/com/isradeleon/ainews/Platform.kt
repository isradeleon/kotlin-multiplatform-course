package com.isradeleon.ainews

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform