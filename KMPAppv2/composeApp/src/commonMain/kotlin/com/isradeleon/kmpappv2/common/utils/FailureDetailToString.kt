package com.isradeleon.kmpappv2.common.utils

import com.isradeleon.kmpappv2.common.FailureDetail
import kmpappv2.composeapp.generated.resources.Res
import kmpappv2.composeapp.generated.resources.error_disk_full
import kmpappv2.composeapp.generated.resources.error_insufficient_balance
import kmpappv2.composeapp.generated.resources.error_no_internet
import kmpappv2.composeapp.generated.resources.error_request_timeout
import kmpappv2.composeapp.generated.resources.error_serialization
import kmpappv2.composeapp.generated.resources.error_too_many_requests
import kmpappv2.composeapp.generated.resources.error_unknown
import org.jetbrains.compose.resources.StringResource

fun FailureDetail.toStringResource(): StringResource {
    return when (this) {
        FailureDetail.Local.DISK_FULL -> Res.string.error_disk_full
        FailureDetail.Local.UNKNOWN -> Res.string.error_unknown
        FailureDetail.Remote.TIMEOUT -> Res.string.error_request_timeout
        FailureDetail.Remote.TOO_MANY_REQUESTS -> Res.string.error_too_many_requests
        FailureDetail.Remote.NO_CONNECTION -> Res.string.error_no_internet
        FailureDetail.Remote.SERVER_ERROR -> Res.string.error_unknown
        FailureDetail.Remote.SERIALIZATION -> Res.string.error_serialization
        FailureDetail.Remote.UNKNOWN -> Res.string.error_unknown
        FailureDetail.Local.INSUFFICIENT_FUNDS -> Res.string.error_insufficient_balance
    }
}