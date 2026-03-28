package com.isradeleon.kmpappv2.common.utils

/**
 * In the iOS module, we need the Foundation package & NSNumberFormatter
 * for text & decimals formatting:
 */
import platform.Foundation.NSNumber
import platform.Foundation.NSNumberFormatter
import platform.Foundation.NSNumberFormatterDecimalStyle

actual fun formatFiatCurrency(amount: Double, showDecimal: Boolean): String {
    val numberFormatter = NSNumberFormatter()
    numberFormatter.numberStyle = NSNumberFormatterDecimalStyle

    when {
        showDecimal.not() -> {
            // Showing no decimal points
            numberFormatter.minimumFractionDigits = 0.toULong()
            numberFormatter.maximumFractionDigits = 0.toULong()
        }

        amount >= 0.01 -> {
            // Showing only 2 decimal points
            numberFormatter.minimumFractionDigits = 2.toULong()
            numberFormatter.maximumFractionDigits = 2.toULong()
        }

        else -> {
            // Showing 8 decimal points when amount is less than 0.01
            numberFormatter.minimumFractionDigits = 8.toULong()
            numberFormatter.maximumFractionDigits = 8.toULong()
        }
    }

    val formatterAmount = numberFormatter.stringFromNumber(NSNumber(amount))
    return if (formatterAmount != null) "$ $formatterAmount" else ""
}

actual fun formatCoinUnit(amount: Double, symbol: String): String {
    val numberFormatter = NSNumberFormatter()
    numberFormatter.numberStyle = NSNumberFormatterDecimalStyle
    numberFormatter.minimumFractionDigits = 8.toULong()
    numberFormatter.maximumFractionDigits = 8.toULong()

    return numberFormatter.stringFromNumber(NSNumber(amount)) + " $symbol"
}

actual fun formatPercentage(amount: Double): String {
    val numberFormatter = NSNumberFormatter()
    numberFormatter.numberStyle = NSNumberFormatterDecimalStyle
    numberFormatter.minimumFractionDigits = 2.toULong()
    numberFormatter.maximumFractionDigits = 2.toULong()
    val prefix = if (amount >= 0) "+" else ""

    return prefix + numberFormatter.stringFromNumber(NSNumber(amount)) + " %"
}