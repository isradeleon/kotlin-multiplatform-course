package com.isradeleon.kmpappv2.common.utils

/**
 * Text formatting & decimal precision is handled
 * differently between Android & iOS.
 *
 * So we must define an expected implementation function for both:
 */
expect fun formatFiatCurrency(amount: Double, showDecimal: Boolean = true): String

expect fun formatCoinUnit(amount: Double, symbol: String): String

expect fun formatPercentage(amount: Double): String