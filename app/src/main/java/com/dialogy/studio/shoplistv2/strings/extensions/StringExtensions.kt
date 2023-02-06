package com.dialogy.studio.shoplistv2.strings.extensions

import android.util.Patterns
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


fun String.isValidPassword(): Boolean {
    return containUpperCase() && containLowerCase() && containDigit() && containSymbol()
}
fun CharSequence.isValidEmail(): Boolean {
    return Patterns.EMAIL_ADDRESS.matcher(this).matches() && isNullOrBlank().not()
}