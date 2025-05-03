package com.radusalagean.stringresourcessamplekmp.uitext

import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import org.jetbrains.compose.resources.PluralStringResource
import org.jetbrains.compose.resources.StringResource

@DslMarker
annotation class UITextDslMarker

fun buildUIText(block: UITextBuilder.() -> Unit): UIText = UITextBuilder().apply(block).build()

@UITextDslMarker
class UITextBuilder {
    private val components = mutableListOf<UIText>()

    fun raw(text: CharSequence) {
        components += UIText.Raw(text)
    }

    fun res(
        stringResource: StringResource,
        argsBlock: ArgsBuilder.() -> Unit = { }
    ) {
        val args = ArgsBuilder().apply(argsBlock).build()
        components += UIText.Res(stringResource, *args.toTypedArray())
    }

    fun pluralRes(
        pluralStringResource: PluralStringResource,
        quantity: Int,
        argsBlock: ArgsBuilder.() -> Unit = {
            arg(quantity.toString())
        }
    ) {
        val args = ArgsBuilder().apply(argsBlock).build()
        components += UIText.PluralRes(pluralStringResource, quantity, *args.toTypedArray())
    }

    //region resAnnotated

    fun resAnnotated(
        stringResource: StringResource,
        baseAnnotations: List<UITextAnnotation>? = null,
        argsBlock: AnnotatedArgsBuilder.() -> Unit = { }
    ) {
        val annotatedArgs = AnnotatedArgsBuilder().apply(argsBlock).build()
        components += UIText.ResAnnotated(stringResource, annotatedArgs, baseAnnotations)
    }

    fun resAnnotated(
        stringResource: StringResource,
        argsBlock: AnnotatedArgsBuilder.() -> Unit = { }
    ) = resAnnotated(
        stringResource = stringResource,
        baseAnnotations = null,
        argsBlock = argsBlock
    )

    fun resAnnotated(
        stringResource: StringResource,
        baseAnnotation: UITextAnnotation,
        argsBlock: AnnotatedArgsBuilder.() -> Unit = { }
    ) = resAnnotated(
        stringResource = stringResource,
        baseAnnotations = listOf(baseAnnotation),
        argsBlock = argsBlock
    )

    fun resAnnotated(
        stringResource: StringResource,
        baseSpanStyle: SpanStyle,
        argsBlock: AnnotatedArgsBuilder.() -> Unit = { }
    ) = resAnnotated(
        stringResource = stringResource,
        baseAnnotation = baseSpanStyle.uiTextAnnotation(),
        argsBlock = argsBlock
    )

    fun resAnnotated(
        stringResource: StringResource,
        baseParagraphStyle: ParagraphStyle,
        argsBlock: AnnotatedArgsBuilder.() -> Unit = { }
    ) = resAnnotated(
        stringResource = stringResource,
        baseAnnotation = baseParagraphStyle.uiTextAnnotation(),
        argsBlock = argsBlock
    )

    fun resAnnotated(
        stringResource: StringResource,
        baseLinkAnnotation: LinkAnnotation,
        argsBlock: AnnotatedArgsBuilder.() -> Unit = { }
    ) = resAnnotated(
        stringResource = stringResource,
        baseAnnotation = baseLinkAnnotation.uiTextAnnotation(),
        argsBlock = argsBlock
    )

    fun resAnnotated(
        stringResource: StringResource,
        baseAnnotationsBlock: AnnotationsBuilder.() -> Unit,
        argsBlock: AnnotatedArgsBuilder.() -> Unit = { }
    ) {
        val baseAnnotations = AnnotationsBuilder().apply(baseAnnotationsBlock).build()
        val annotatedArgs = AnnotatedArgsBuilder().apply(argsBlock).build()
        components += UIText.ResAnnotated(stringResource, annotatedArgs, baseAnnotations)
    }

    //endregion

    //region pluralResAnnotated

    fun pluralResAnnotated(
        pluralStringResource: PluralStringResource,
        quantity: Int,
        baseAnnotations: List<UITextAnnotation>? = null,
        argsBlock: AnnotatedArgsBuilder.() -> Unit = {
            arg(quantity.toString())
        }
    ) {
        val annotatedArgs = AnnotatedArgsBuilder().apply(argsBlock).build()
        components += UIText.PluralResAnnotated(pluralStringResource, quantity, annotatedArgs, baseAnnotations)
    }

    fun pluralResAnnotated(
        pluralStringResource: PluralStringResource,
        quantity: Int,
        argsBlock: AnnotatedArgsBuilder.() -> Unit = {
            arg(quantity.toString())
        }
    ) = pluralResAnnotated(
        pluralStringResource = pluralStringResource,
        quantity = quantity,
        baseAnnotations = null,
        argsBlock = argsBlock
    )

    fun pluralResAnnotated(
        pluralStringResource: PluralStringResource,
        quantity: Int,
        baseAnnotation: UITextAnnotation,
        argsBlock: AnnotatedArgsBuilder.() -> Unit = {
            arg(quantity.toString())
        }
    ) = pluralResAnnotated(
        pluralStringResource = pluralStringResource,
        quantity = quantity,
        baseAnnotations = listOf(baseAnnotation),
        argsBlock = argsBlock
    )

    fun pluralResAnnotated(
        pluralStringResource: PluralStringResource,
        quantity: Int,
        baseSpanStyle: SpanStyle,
        argsBlock: AnnotatedArgsBuilder.() -> Unit = {
            arg(quantity.toString())
        }
    ) = pluralResAnnotated(
        pluralStringResource = pluralStringResource,
        quantity = quantity,
        baseAnnotation = baseSpanStyle.uiTextAnnotation(),
        argsBlock = argsBlock
    )

    fun pluralResAnnotated(
        pluralStringResource: PluralStringResource,
        quantity: Int,
        baseParagraphStyle: ParagraphStyle,
        argsBlock: AnnotatedArgsBuilder.() -> Unit = {
            arg(quantity.toString())
        }
    ) = pluralResAnnotated(
        pluralStringResource = pluralStringResource,
        quantity = quantity,
        baseAnnotation = baseParagraphStyle.uiTextAnnotation(),
        argsBlock = argsBlock
    )

    fun pluralResAnnotated(
        pluralStringResource: PluralStringResource,
        quantity: Int,
        baseLinkAnnotation: LinkAnnotation,
        argsBlock: AnnotatedArgsBuilder.() -> Unit = {
            arg(quantity.toString())
        }
    ) = pluralResAnnotated(
        pluralStringResource = pluralStringResource,
        quantity = quantity,
        baseAnnotation = baseLinkAnnotation.uiTextAnnotation(),
        argsBlock = argsBlock
    )

    fun pluralResAnnotated(
        pluralStringResource: PluralStringResource,
        quantity: Int,
        baseAnnotationsBlock: AnnotationsBuilder.() -> Unit,
        argsBlock: AnnotatedArgsBuilder.() -> Unit = {
            arg(quantity.toString())
        }
    ) {
        val baseAnnotations = AnnotationsBuilder().apply(baseAnnotationsBlock).build()
        val annotatedArgs = AnnotatedArgsBuilder().apply(argsBlock).build()
        components += UIText.PluralResAnnotated(pluralStringResource, quantity, annotatedArgs, baseAnnotations)
    }

    //endregion

    internal fun build(): UIText = when (components.size) {
        0 -> UIText.Raw("")
        1 -> components[0]
        else -> UIText.Compound(components)
    }
}

@UITextDslMarker
class ArgsBuilder {
    private val args = mutableListOf<Any>()

    fun arg(arg: CharSequence) {
        args += arg
    }

    fun arg(arg: UIText) {
        args += arg
    }

    fun build(): List<Any> = args
}

@UITextDslMarker
class AnnotatedArgsBuilder {

    private val args = mutableListOf<Pair<Any, List<UITextAnnotation>?>>()

    //region CharSequence

    fun arg(value: CharSequence, annotations: List<UITextAnnotation>? = null) {
        args += value to annotations
    }

    fun arg(value: CharSequence) =
        arg(value, null)

    fun arg(value: CharSequence, annotation: UITextAnnotation) =
        arg(value, listOf(annotation))

    fun arg(value: CharSequence, spanStyle: SpanStyle) =
        arg(value, spanStyle.uiTextAnnotation())

    fun arg(value: CharSequence, paragraphStyle: ParagraphStyle) =
        arg(value, paragraphStyle.uiTextAnnotation())

    fun arg(value: CharSequence, linkAnnotation: LinkAnnotation) =
        arg(value, linkAnnotation.uiTextAnnotation())

    fun arg(value: CharSequence, annotationsBlock: AnnotationsBuilder.() -> Unit) {
        val annotations = AnnotationsBuilder().apply(annotationsBlock).build()
        args += value to annotations
    }

    //endregion

    //region UIText

    fun arg(value: UIText, annotations: List<UITextAnnotation>? = null) {
        args += value to annotations
    }

    fun arg(value: UIText) =
        arg(value, null)

    fun arg(value: UIText, annotation: UITextAnnotation) =
        arg(value, listOf(annotation))

    fun arg(value: UIText, spanStyle: SpanStyle) =
        arg(value, spanStyle.uiTextAnnotation())

    fun arg(value: UIText, paragraphStyle: ParagraphStyle) =
        arg(value, paragraphStyle.uiTextAnnotation())

    fun arg(value: UIText, linkAnnotation: LinkAnnotation) =
        arg(value, linkAnnotation.uiTextAnnotation())

    fun arg(value: UIText, annotationsBlock: AnnotationsBuilder.() -> Unit) {
        val annotations = AnnotationsBuilder().apply(annotationsBlock).build()
        args += value to annotations
    }

    //endregion

    fun build(): List<Pair<Any, List<UITextAnnotation>?>> = args
}

@UITextDslMarker
class AnnotationsBuilder {
    private val annotations = mutableListOf<UITextAnnotation>()

    fun span(style: SpanStyle) = annotations.add(UITextAnnotation.Span(style))
    fun paragraph(style: ParagraphStyle) = annotations.add(UITextAnnotation.Paragraph(style))
    fun link(annotation: LinkAnnotation) = annotations.add(UITextAnnotation.Link(annotation))

    fun build(): List<UITextAnnotation> = annotations
}