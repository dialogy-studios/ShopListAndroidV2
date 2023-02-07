package com.dialogy.studio.shoplistv2.strings.extensions

import android.util.Patterns
import com.dialogy.studio.shoplistv2.constants.PhoneNumberSupport
import java.util.regex.Matcher
import java.util.regex.Pattern

fun String.containUpperCase(): Boolean {
    val uppercase = Pattern.compile("[A-Z]")
    return uppercase.matcher(this).find()
}

fun String.containLowerCase(): Boolean {
    val lowercase = Pattern.compile("[a-z]")
    return lowercase.matcher(this).find()
}

fun String.containDigit(): Boolean {
    val digit = Pattern.compile("\\d")
    return digit.matcher(this).find()
}

fun String.containSymbol(): Boolean {
    val symbol = Pattern.compile("[!\"#$%&'()*+,\\-./:;<>=?@\\[\\]{}\\\\^_`~]")
    return symbol.matcher(this).find()
}

fun String.sanitizePhoneNumber(): String {
    var sanitizedPhoneNumber = ""
    val pattern: Pattern = Pattern.compile("\\d+")
    val matcher: Matcher = pattern.matcher(this)
    while (matcher.find()) {
        sanitizedPhoneNumber += matcher.group(0)
    }
    return sanitizedPhoneNumber.trim()
}

fun String.formatToPhoneNumber(): String {
    return "+${sanitizePhoneNumber()}"
}

fun String.isValidPhoneNumber(): Boolean {
    val sanitizedPhoneNumber = sanitizePhoneNumber()
    return sanitizedPhoneNumber.length == PhoneNumberSupport.PT_BR.length
}
fun String.isValidPassword(): Boolean {
    return containUpperCase() && containLowerCase() && containDigit() && containSymbol()
}
fun CharSequence.isValidEmail(): Boolean {
    return Patterns.EMAIL_ADDRESS.matcher(this).matches() && isNullOrBlank().not()
}