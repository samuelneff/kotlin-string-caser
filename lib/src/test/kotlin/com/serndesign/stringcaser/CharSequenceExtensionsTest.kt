package com.serndesign.stringcaser

import com.google.common.truth.Truth.assertWithMessage
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

private val testExpressions = listOf(
    "someTestInput",
    "SomeTestInput",
    "some-test-input",
    "SomeTestInput",
    "SOME-TEST-INPUT",
    "SOME_TEST_INPUT",
    "some-test-input",
    "some_test_input",
    "Some Test Input",
    "Some-Test-Input",
    "SOME TEST INPUT",
    "Some-Test Input",
    "SOME Test INPUT",
    "some   test   input",
    "some_test-input",
    "Some TEST-Input"
)

class CharSequenceExtensionsTest {

    private fun assertCase(input: String, expected: String, actual: String) {
        assertWithMessage("Unexpected result for input: $input")
            .that(actual)
            .isEqualTo(expected)
    }

    @ParameterizedTest
    @MethodSource("testParametersForCamel")
    fun testCamelCase(input: String, expected: String) =
        assertCase(input, expected, input.toCamelCase())

    @ParameterizedTest
    @MethodSource("testParametersForKebab")
    fun testKebabCase(input: String, expected: String) =
        assertCase(input, expected, input.toKebabCase())

    @ParameterizedTest
    @MethodSource("testParametersForPascal")
    fun testPascalCase(input: String, expected: String) =
        assertCase(input, expected, input.toPascalCase())

    @ParameterizedTest
    @MethodSource("testParametersForScreamingKebab")
    fun testScreamingKebabCase(input: String, expected: String) =
        assertCase(input, expected, input.toScreamingKebabCase())

    @ParameterizedTest
    @MethodSource("testParametersForScreamingSnake")
    fun testScreamingSnakeCase(input: String, expected: String) =
        assertCase(input, expected, input.toScreamingSnakeCase())

    @ParameterizedTest
    @MethodSource("testParametersForSentence")
    fun testSentenceCase(input: String, expected: String) =
        assertCase(input, expected, input.toSentenceCase())

    @ParameterizedTest
    @MethodSource("testParametersForSlug")
    fun testSlugCase(input: String, expected: String) =
        assertCase(input, expected, input.toSlugCase())

    @ParameterizedTest
    @MethodSource("testParametersForSnake")
    fun testSnakeCase(input: String, expected: String) =
        assertCase(input, expected, input.toSnakeCase())

    @ParameterizedTest
    @MethodSource("testParametersForTitle")
    fun testTitleCase(input: String, expected: String) =
        assertCase(input, expected, input.toTitleCase())

    @ParameterizedTest
    @MethodSource("testParametersForTrain")
    fun testTrainCase(input: String, expected: String) =
        assertCase(input, expected, input.toTrainCase())

    @ParameterizedTest
    @MethodSource("testParametersForUpperCamel")
    fun testUpperCamelCase(input: String, expected: String) =
        assertCase(input, expected, input.toUpperCamelCase())

    companion object {

        @JvmStatic
        fun testParametersForCamel() =
            testExpressions.map {
                Arguments.of(it, "someTestInput")
            }

        @JvmStatic
        fun testParametersForKebab() =
            testExpressions.map { Arguments.of(it, "some-test-input") }

        @JvmStatic
        fun testParametersForPascal() =
            testExpressions.map { Arguments.of(it, "SomeTestInput") }

        @JvmStatic
        fun testParametersForScreamingKebab() =
            testExpressions.map { Arguments.of(it, "SOME-TEST-INPUT") }

        @JvmStatic
        fun testParametersForScreamingSnake() =
            testExpressions.map { Arguments.of(it, "SOME_TEST_INPUT") }

        @JvmStatic
        fun testParametersForSentence() =
            testExpressions.map { Arguments.of(it, "Some test input") }

        @JvmStatic
        fun testParametersForSlug() =
            testExpressions.map { Arguments.of(it, "some-test-input") }

        @JvmStatic
        fun testParametersForSnake() =
            testExpressions.map { Arguments.of(it, "some_test_input") }

        @JvmStatic
        fun testParametersForTitle() =
            testExpressions.map { Arguments.of(it, "Some Test Input") }

        @JvmStatic
        fun testParametersForTrain() =
            testExpressions.map { Arguments.of(it, "Some-Test-Input") }

        @JvmStatic
        fun testParametersForUpperCamel() =
            testExpressions.map { Arguments.of(it, "SomeTestInput") }
    }
}
