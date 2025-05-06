package com.radusalagean.stringresourcessamplekmp.uitext

import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import org.jetbrains.compose.resources.PluralStringResource
import org.jetbrains.compose.resources.StringResource

@DslMarker
annotation class UITextDslMarker

fun UIText(block: UITextBuilder.() -> Unit): UIText = UITextBuilder().apply(block).build()

@UITextDslMarker
class UITextBuilder {
    private val components = mutableListOf<UIText>()

    fun raw(text: CharSequence) {
        components += UIText.Raw(text)
    }

    fun res(
        stringResource: StringResource,
        resBuilder: ResBuilder.() -> Unit = { }
    ) {
        val args = ResBuilder().apply(resBuilder).build()
        components += UIText.Res(stringResource = stringResource, args = args.toTypedArray())
    }

    fun pluralRes(
        pluralStringResource: PluralStringResource,
        quantity: Int,
        resBuilder: ResBuilder.() -> Unit = {
            arg(quantity.toString())
        }
    ) {
        val args = ResBuilder().apply(resBuilder).build()
        components += UIText.PluralRes(
            pluralStringResource = pluralStringResource,
            quantity = quantity,
            args = args.toTypedArray()
        )
    }

    fun resAnnotated(
        stringResource: StringResource,
        resAnnotatedBuilder: ResAnnotatedBuilder.() -> Unit = { }
    ) {
        val config = ResAnnotatedBuilder().apply(resAnnotatedBuilder).build()
        components += UIText.ResAnnotated(
            stringResource = stringResource,
            args = config.args,
            baseAnnotations = config.annotations
        )
    }

    fun pluralResAnnotated(
        pluralStringResource: PluralStringResource,
        quantity: Int,
        resAnnotatedBuilder: ResAnnotatedBuilder.() -> Unit = {
            arg(quantity.toString())
        }
    ) {
        val config = ResAnnotatedBuilder().apply(resAnnotatedBuilder).build()
        components += UIText.PluralResAnnotated(
            pluralStringResource = pluralStringResource,
            quantity = quantity,
            args = config.args,
            baseAnnotations = config.annotations
        )
    }

    internal fun build(): UIText = when (components.size) {
        0 -> UIText.Raw("")
        1 -> components[0]
        else -> UIText.Compound(components)
    }
}

@UITextDslMarker
class ResBuilder {
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
class ResAnnotatedBuilder {

    private val annotations = mutableListOf<UITextAnnotation>()
    private val args = mutableListOf<Pair<Any, List<UITextAnnotation>>>()

    fun annotation(annotationsBlock: AnnotationsBuilder.() -> Unit) {
        annotations += AnnotationsBuilder().apply(annotationsBlock).build()
    }

    fun arg(value: CharSequence, annotationsBuilder: AnnotationsBuilder.() -> Unit = { }) {
        val annotations = AnnotationsBuilder().apply(annotationsBuilder).build()
        args += value to annotations
    }

    fun arg(value: UIText, annotationsBuilder: AnnotationsBuilder.() -> Unit = { }) {
        val annotations = AnnotationsBuilder().apply(annotationsBuilder).build()
        args += value to annotations
    }

    fun build(): ResAnnotatedConfig = ResAnnotatedConfig(
        annotations = annotations,
        args = args
    )
}

data class ResAnnotatedConfig(
    val annotations: List<UITextAnnotation>,
    val args: List<Pair<Any, List<UITextAnnotation>>>
)

@UITextDslMarker
class AnnotationsBuilder {
    private val annotations = mutableListOf<UITextAnnotation>()

    operator fun SpanStyle.unaryPlus() {
        annotations.add(UITextAnnotation.Span(this))
    }

    operator fun ParagraphStyle.unaryPlus() {
        annotations.add(UITextAnnotation.Paragraph(this))
    }

    operator fun LinkAnnotation.unaryPlus() {
        annotations.add(UITextAnnotation.Link(this))
    }

    fun build(): List<UITextAnnotation> = annotations
}