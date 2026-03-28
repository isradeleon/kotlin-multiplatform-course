package com.isradeleon.kmpappv2.common

sealed interface FailureDetail: Failure {
    enum class Remote: FailureDetail {
        TIMEOUT,
        TOO_MANY_REQUESTS,
        NO_CONNECTION,
        SERVER_ERROR,
        SERIALIZATION,
        UNKNOWN
    }

    enum class Local: FailureDetail {
        DISK_FULL,
        INSUFFICIENT_FUNDS,
        UNKNOWN
    }
}