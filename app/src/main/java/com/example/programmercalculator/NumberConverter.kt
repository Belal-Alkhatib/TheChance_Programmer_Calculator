package com.example.programmercalculator

class NumberConverter {
    companion object {

        fun binaryToDecimal(binary: String): Int {
            return Integer.parseInt(binary, 2)
        }
        fun binaryToOctal(binary: String): String {
            val decimal = binaryToDecimal(binary)
            return decimalToOctal(decimal)
        }
        fun binaryToHexadecimal(binary: String): String {
            val decimal = binaryToDecimal(binary)
            return decimalToHexadecimal(decimal)
        }

        fun fromBinary(binary: String): ConversionResult{
            val toOct = binaryToOctal(binary)
            val toDecimal = binaryToDecimal(binary).toString()
            val toHex = binaryToHexadecimal(binary)
            return ConversionResult(toOct, toDecimal, toHex)
        }


        fun octalToDecimal(octal: String): Int {
            return Integer.parseInt(octal, 8)
        }
        fun octalToBinary(octal: String): String {
            val decimal = octalToDecimal(octal)
            return decimalToBinary(decimal)
        }
        fun octalToHexadecimal(octal: String): String {
            val decimal = octalToDecimal(octal)
            return decimalToHexadecimal(decimal)
        }
        fun fromOctal(octal: String): ConversionResult{
            val toBIN = octalToBinary(octal)
            val toDecimal = octalToDecimal(octal).toString()
            val toHex = octalToHexadecimal(octal)
            return ConversionResult(toBIN, toDecimal, toHex)
        }


        fun decimalToBinary(decimal: Int): String {
            return Integer.toBinaryString(decimal)
        }
        fun decimalToOctal(decimal: Int): String {
            return Integer.toOctalString(decimal)
        }
        fun decimalToHexadecimal(decimal: Int): String {
            return Integer.toHexString(decimal)
        }
        fun fromDecimal(decimal: String): ConversionResult{
            val toBIN = decimalToBinary(decimal.toInt())
            val toOct = decimalToOctal(decimal.toInt())
            val toHex = decimalToHexadecimal(decimal.toInt())
            return ConversionResult(toBIN, toOct, toHex)
        }


        fun hexadecimalToDecimal(hexadecimal: String): Int {
            return Integer.parseInt(hexadecimal, 16)
//            return if (hexadecimal.matches("[0-9A-Fa-f]+".toRegex())) {
//                Integer.parseInt(hexadecimal, 16)
//            } else {
//                0 // or some other default value or error handling code
//            }
        }
        fun hexadecimalToBinary(hexadecimal: String): String {
            val decimal = hexadecimalToDecimal(hexadecimal)
            return decimalToBinary(decimal)
        }
        fun hexadecimalToOctal(hexadecimal: String): String {
            val decimal = hexadecimalToDecimal(hexadecimal)
            return decimalToOctal(decimal)
        }
        fun fromHexadecimal(hexadecimalmal: String): ConversionResult{
            val toBIN = hexadecimalToBinary(hexadecimalmal)
            val toOct = hexadecimalToOctal(hexadecimalmal)
            val toDec = hexadecimalToDecimal(hexadecimalmal).toString()
            return ConversionResult(toBIN, toOct, toDec)
        }

    }
}

fun main() {
    fun hexadecimalToDecimal(hexadecimal: String): Int {
        return Integer.parseInt(hexadecimal, 16)
    }
    fun hexadecimalToBinary(hexadecimal: String): String {
        val decimal = hexadecimalToDecimal(hexadecimal)
        return NumberConverter.decimalToBinary(decimal)
    }
    fun hexadecimalToOctal(hexadecimal: String): String {
        val decimal = hexadecimalToDecimal(hexadecimal)
        return NumberConverter.decimalToOctal(decimal)
    }
    fun fromHexadecimal(hexadecimalmal: String): ConversionResult{
        val toBIN = hexadecimalToBinary(hexadecimalmal)
        val toOct = hexadecimalToOctal(hexadecimalmal)
        val toDec = hexadecimalToDecimal(hexadecimalmal).toString()
        return ConversionResult(toBIN, toOct, toDec)
    }

    println(fromHexadecimal("A7A7"))
}