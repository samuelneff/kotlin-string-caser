@file:JvmName("StringCaser")

package com.serndesign.stringcaser

private val nonSlugCharacterExp = "[^A-Za-z0-9 _-]".toRegex()

private fun echo(c: Char) = c

private fun CharSequence.changeCase(
    firstOfExpression: (Char) -> Char = ::echo,
    firstOfWord: (Char) -> Char = ::echo,
    middleOfWord: (Char) -> Char = ::echo,
    separator: String = ""
): String {
    var startingExpression = true
    var startingNewWord = true
    var previousWasSeparator = false
    var previousWasLower = false

    val result = StringBuilder((length * 1.5).toInt())

    forEach {
        if (it == ' ' || it == '_' || it == '-') {
            if (previousWasSeparator) {
                return@forEach
            }
            previousWasSeparator = true
            startingNewWord = true
            previousWasLower = false
            result.append(separator)
            return@forEach
        }

        previousWasSeparator = false

        if (previousWasLower && it.isUpperCase()) {
            result.append(separator)
            startingNewWord = true
        }

        val newChar = if (startingExpression) {
            startingExpression = false
            startingNewWord = false
            firstOfExpression(it)
        } else if (startingNewWord) {
            startingNewWord = false
            firstOfWord(it)
        } else {
            middleOfWord(it)
        }

        previousWasLower = it.isLowerCase()

        result.append(newChar)
    }

    return result.toString()
}

fun CharSequence.toCamelCase() =
    changeCase(
        firstOfExpression = Char::lowercaseChar,
        firstOfWord = Char::uppercaseChar,
        middleOfWord = Char::lowercaseChar
    )

fun CharSequence.toKebabCase() =
    changeCase(
        firstOfExpression = Char::lowercaseChar,
        firstOfWord = Char::lowercaseChar,
        middleOfWord = Char::lowercaseChar,
        separator = "-"
    )

fun CharSequence.toPascalCase() =
    changeCase(
        firstOfExpression = Char::uppercaseChar,
        firstOfWord = Char::uppercaseChar,
        middleOfWord = Char::lowercaseChar
    )

fun CharSequence.toScreamingKebabCase() =
    changeCase(
        firstOfExpression = Char::uppercaseChar,
        firstOfWord = Char::uppercaseChar,
        middleOfWord = Char::uppercaseChar,
        separator = "-"
    )

fun CharSequence.toScreamingSnakeCase() =
    changeCase(
        firstOfExpression = Char::uppercaseChar,
        firstOfWord = Char::uppercaseChar,
        middleOfWord = Char::uppercaseChar,
        separator = "_"
    )

fun CharSequence.toSentenceCase() =
    changeCase(
        firstOfExpression = Char::uppercaseChar,
        firstOfWord = Char::lowercaseChar,
        middleOfWord = Char::lowercaseChar,
        separator = " "
    )

fun CharSequence.toSlugCase() =

    // We should convert accented characters to non-accented ones first,
    // which this commented code should do,
    // but it's seemingly not doing anything.
    // Asked: https://stackoverflow.com/questions/75467396/normalizer-not-removing-accents
    // Normalizer.normalize(this, Normalizer.Form.NFD)
    // .replace("[\\p{InCombiningDiacriticalMarks}]+", "")

    replace(nonSlugCharacterExp, "")
        .changeCase(
            firstOfExpression = Char::lowercaseChar,
            firstOfWord = Char::lowercaseChar,
            middleOfWord = Char::lowercaseChar,
            separator = "-"
        )

fun CharSequence.toSnakeCase() =
    changeCase(
        firstOfExpression = Char::lowercaseChar,
        firstOfWord = Char::lowercaseChar,
        middleOfWord = Char::lowercaseChar,
        separator = "_"
    )

fun CharSequence.toTitleCase() =
    changeCase(
        firstOfExpression = Char::uppercaseChar,
        firstOfWord = Char::uppercaseChar,
        middleOfWord = Char::lowercaseChar,
        separator = " "
    )

fun CharSequence.toTrainCase() =
    changeCase(
        firstOfExpression = Char::uppercaseChar,
        firstOfWord = Char::uppercaseChar,
        middleOfWord = Char::lowercaseChar,
        separator = "-"
    )

fun CharSequence.toUpperCamelCase() =
    toPascalCase()
