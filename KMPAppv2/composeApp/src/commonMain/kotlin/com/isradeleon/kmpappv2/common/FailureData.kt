package com.isradeleon.kmpappv2.common

sealed interface FailureData: Failure {
    enum class Remote: FailureData {
        TIMEOUT,
        TOO_MANY_REQUESTS,
        NO_CONNECTION,
        SERVER_ERROR,
        SERIALIZATION,
        UNKNOWN
    }

    enum class Local: FailureData {
        DISK_FULL,
        DATABASE_ERROR,
        UNKNOWN
    }
}