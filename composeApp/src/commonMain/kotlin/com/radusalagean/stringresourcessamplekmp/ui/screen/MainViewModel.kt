package com.radusalagean.stringresourcessamplekmp.ui.screen

import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLinkStyles
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.lifecycle.ViewModel
import com.radusalagean.stringresourcessamplekmp.ui.component.ExampleEntryModel
import com.radusalagean.stringresourcessamplekmp.ui.component.LanguageOption
import com.radusalagean.stringresourcessamplekmp.ui.theme.CustomGreen
import com.radusalagean.stringresourcessamplekmp.uitext.UIText
import com.radusalagean.stringresourcessamplekmp.uitext.buildUIText
import com.radusalagean.stringresourcessamplekmp.uitext.uiTextAnnotationList
import com.radusalagean.stringresourcessamplekmp.util.LanguageManager
import stringresourcessamplekmp.composeapp.generated.resources.Res
import stringresourcessamplekmp.composeapp.generated.resources.greeting
import stringresourcessamplekmp.composeapp.generated.resources.language_english
import stringresourcessamplekmp.composeapp.generated.resources.language_romanian
import stringresourcessamplekmp.composeapp.generated.resources.legal_footer_example
import stringresourcessamplekmp.composeapp.generated.resources.legal_footer_example_insert_privacy_policy
import stringresourcessamplekmp.composeapp.generated.resources.legal_footer_example_insert_terms_of_service
import stringresourcessamplekmp.composeapp.generated.resources.proceed_to_checkout
import stringresourcessamplekmp.composeapp.generated.resources.products
import stringresourcessamplekmp.composeapp.generated.resources.section_title_examples
import stringresourcessamplekmp.composeapp.generated.resources.section_title_language
import stringresourcessamplekmp.composeapp.generated.resources.shopping_cart_status
import stringresourcessamplekmp.composeapp.generated.resources.shopping_cart_status_insert_shopping_cart

class MainViewModel(
    private val languageManager: LanguageManager
) : ViewModel() {

    // Section: Language
    val languageSectionTitle = UIText.Res(Res.string.section_title_language)
    val languageOptions = listOf(
        LanguageOption(
            uiText = UIText.Res(Res.string.language_english),
            languageCode = "en"
        ),
        LanguageOption(
            uiText = UIText.Res(Res.string.language_romanian),
            languageCode = "ro"
        )
    )
    var selectedLanguageCode by mutableStateOf("")
    val selectedLanguageIndex: Int by derivedStateOf {
        languageOptions.indexOfFirst { it.languageCode == selectedLanguageCode }
    }

    fun syncSelectedLanguage() {
        selectedLanguageCode = languageManager.getCurrentLanguageCode()
    }

    fun onLanguageSelected(code: String) {
        languageManager.onLanguageSelected(code)
    }

    // Section: Examples
    val examplesSectionTitle = UIText.Res(Res.string.section_title_examples)
    val exampleEntries = listOf(
        ExampleEntryModel(
            label = UIText.Raw("UIText.Raw"),
            value = UIText.Raw("Radu")
        ),
        ExampleEntryModel(
            label = UIText.Raw("UIText.Res"),
            value = UIText.Res(Res.string.greeting, "Radu")
        ),
        ExampleEntryModel(
            label = UIText.Raw("UIText.PluralRes"),
            value = UIText.PluralRes(Res.plurals.products, 30)
        ),
        ExampleEntryModel(
            label = UIText.Raw("UIText.ResAnnotated"),
            value = UIText.ResAnnotated(
                Res.string.shopping_cart_status,
                UIText.PluralRes(
                    Res.plurals.products,
                    30
                ) to null,
                UIText.Res(
                    Res.string.shopping_cart_status_insert_shopping_cart
                ) to SpanStyle(color = Color.Red).uiTextAnnotationList()
            )
        ),
        ExampleEntryModel(
            label = UIText.Raw("UIText.PluralResAnnotated"),
            value = UIText.PluralResAnnotated(
                Res.plurals.products,
                quantity = 30,
                30 to SpanStyle(
                    color = CustomGreen
                ).uiTextAnnotationList(),
                baseAnnotations = SpanStyle(
                    fontWeight = FontWeight.Bold
                ).uiTextAnnotationList()
            )
        ),
        ExampleEntryModel(
            label = UIText.Raw("UIText.Compound"),
            value = UIText.Compound(
                UIText.Res(Res.string.greeting, "Radu"),
                UIText.Raw(" "),
                UIText.ResAnnotated(
                    Res.string.shopping_cart_status,
                    UIText.PluralResAnnotated(
                        Res.plurals.products,
                        quantity = 30,
                        30 to SpanStyle(
                            color = CustomGreen
                        ).uiTextAnnotationList(),
                        baseAnnotations = SpanStyle(
                            fontWeight = FontWeight.Bold,
                        ).uiTextAnnotationList()
                    ) to null,
                    UIText.Res(
                        Res.string.shopping_cart_status_insert_shopping_cart
                    ) to SpanStyle(color = Color.Red).uiTextAnnotationList()
                )
            )
        ),
        ExampleEntryModel(
            label = UIText.Raw("DSL Builder - Example 1"),
            value = buildUIText {
                res(Res.string.greeting) {
                    arg("Radu")
                }
                raw(" ")
                resAnnotated(Res.string.shopping_cart_status) {
                    arg(
                        buildUIText {
                            pluralResAnnotated(
                                Res.plurals.products,
                                quantity = 30,
                                baseSpanStyle = SpanStyle(fontWeight = FontWeight.Bold)
                            ) {
                                arg(
                                    30.toString(),
                                    SpanStyle(color = CustomGreen)
                                )
                            }
                        }
                    )
                    arg(
                        buildUIText {
                            res(Res.string.shopping_cart_status_insert_shopping_cart)
                        },
                        SpanStyle(color = Color.Red)
                    )
                }
                raw(" ")
                resAnnotated(
                    stringResource = Res.string.proceed_to_checkout,
                    baseLinkAnnotation = LinkAnnotation.Url(
                        url = "https://example.com",
                        styles = TextLinkStyles(
                            style = SpanStyle(
                                color = Color.Blue,
                                textDecoration = TextDecoration.Underline
                            )
                        )
                    )
                )
            }
        ),
        ExampleEntryModel(
            label = UIText.Raw("DSL Builder - Example 2"),
            value = buildUIText {
                res(Res.string.greeting) {
                    arg("Radu")
                }
                resAnnotated(Res.string.shopping_cart_status, {
                    paragraph(ParagraphStyle())
                }) {
                    arg(
                        buildUIText {
                            pluralResAnnotated(
                                Res.plurals.products,
                                quantity = 30,
                                baseSpanStyle = SpanStyle(fontWeight = FontWeight.Bold)
                            ) {
                                arg(
                                    30.toString(),
                                    SpanStyle(color = CustomGreen)
                                )
                            }
                        }
                    )
                    arg(
                        buildUIText {
                            res(Res.string.shopping_cart_status_insert_shopping_cart)
                        },
                        SpanStyle(color = Color.Red)
                    )
                }
                resAnnotated(
                    stringResource = Res.string.proceed_to_checkout,
                    baseLinkAnnotation = LinkAnnotation.Url(
                        url = "https://example.com",
                        styles = TextLinkStyles(
                            style = SpanStyle(
                                color = Color.Blue,
                                textDecoration = TextDecoration.Underline
                            )
                        )
                    )
                )
            }
        ),
        ExampleEntryModel(
            label = UIText.Raw("DSL Builder - Example 3"),
            value = buildUIText {
                val linkStyle = TextLinkStyles(
                    SpanStyle(color = Color.Blue, textDecoration = TextDecoration.Underline)
                )
                resAnnotated(Res.string.legal_footer_example) {
                    arg(
                        value = UIText.Res(Res.string.legal_footer_example_insert_terms_of_service),
                        linkAnnotation = LinkAnnotation.Url(
                            url = "https://radusalagean.com/example-terms-of-service/",
                            styles = linkStyle
                        )
                    )
                    arg(
                        value = UIText.Res(Res.string.legal_footer_example_insert_privacy_policy),
                        linkAnnotation = LinkAnnotation.Url(
                            url = "https://radusalagean.com/example-privacy-policy/",
                            styles = linkStyle
                        )
                    )
                }
            }
        )
    )
}