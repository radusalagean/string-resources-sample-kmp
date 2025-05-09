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
    val languageSectionTitle = UIText { res(Res.string.section_title_language) }
    val languageOptions = listOf(
        LanguageOption(
            uiText = UIText { res(Res.string.language_english) },
            languageCode = "en"
        ),
        LanguageOption(
            uiText = UIText { res(Res.string.language_romanian) },
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
    val examplesSectionTitle = UIText { res(Res.string.section_title_examples) }
    val exampleEntries = listOf(
        ExampleEntryModel(
            label = "raw",
            value = UIText {
                raw("Radu")
            }
        ),
        ExampleEntryModel(
            label = "res",
            value = UIText {
                res(Res.string.greeting) {
                    arg("Radu")
                }
            }
        ),
        ExampleEntryModel(
            label = "pluralRes",
            value = UIText {
                pluralRes(Res.plurals.products, 30)
            }
        ),
        ExampleEntryModel(
            label = "res - annotated",
            value = UIText {
                res(Res.string.shopping_cart_status) {
                    arg(
                        UIText {
                            pluralRes(Res.plurals.products, 30)
                        }
                    )
                    arg(
                        UIText {
                            res(Res.string.shopping_cart_status_insert_shopping_cart) {
                                +SpanStyle(color = Color.Red)
                            }
                        }
                    )
                }
            }
        ),
        ExampleEntryModel(
            label = "pluralRes - annotated",
            value = UIText {
                pluralRes(Res.plurals.products, 30) {
                    arg(30.toString()) {
                        +SpanStyle(color = CustomGreen)
                    }
                    +SpanStyle(fontWeight = FontWeight.Bold)
                }
            }
        ),
        ExampleEntryModel(
            label = "compound - example 1",
            value = UIText {
                res(Res.string.greeting) {
                    arg("Radu")
                }
                raw(" ")
                res(Res.string.shopping_cart_status) {
                    arg(
                        UIText {
                            pluralRes(Res.plurals.products, 30) {
                                arg(30.toString()) {
                                    +SpanStyle(color = CustomGreen)
                                }
                                +SpanStyle(fontWeight = FontWeight.Bold)
                            }
                        }
                    )
                    arg(
                        UIText {
                            res(Res.string.shopping_cart_status_insert_shopping_cart) {
                                +SpanStyle(color = Color.Red)
                            }
                        }
                    )
                }
            }
        ),
        ExampleEntryModel(
            label = "compound - example 2",
            value = UIText {
                res(Res.string.greeting) {
                    arg("Radu")
                }
                raw(" ")
                res(Res.string.shopping_cart_status) {
                    arg(
                        UIText {
                            pluralRes(Res.plurals.products, 30) {
                                arg(30.toString()) {
                                    +SpanStyle(color = CustomGreen)
                                }
                                +SpanStyle(fontWeight = FontWeight.Bold)
                            }
                        }
                    )
                    arg(
                        UIText {
                            res(Res.string.shopping_cart_status_insert_shopping_cart)
                        }
                    ) {
                        +SpanStyle(color = Color.Red)
                    }
                }
                raw(" ")
                res(Res.string.proceed_to_checkout) {
                    +LinkAnnotation.Url(
                        url = "https://example.com",
                        styles = TextLinkStyles(
                            style = SpanStyle(
                                color = Color.Blue,
                                textDecoration = TextDecoration.Underline
                            )
                        )
                    )
                }
            }
        ),
        ExampleEntryModel(
            label = "compound - example 3",
            value = UIText {
                res(Res.string.greeting) {
                    arg("Radu")
                }
                res(Res.string.shopping_cart_status) {
                    +ParagraphStyle()
                    arg(
                        UIText {
                            pluralRes(Res.plurals.products, 30) {
                                +SpanStyle(fontWeight = FontWeight.Bold)
                                arg(30.toString()) {
                                    +SpanStyle(color = CustomGreen)
                                }
                            }
                        }
                    )
                    arg(
                        UIText {
                            res(Res.string.shopping_cart_status_insert_shopping_cart)
                        }
                    ) {
                        +SpanStyle(color = Color.Red)
                    }
                }
                res(Res.string.proceed_to_checkout) {
                    +LinkAnnotation.Url(
                        url = "https://example.com",
                        styles = TextLinkStyles(
                            style = SpanStyle(
                                color = Color.Blue,
                                textDecoration = TextDecoration.Underline
                            )
                        )
                    )
                }
            }
        ),
        ExampleEntryModel(
            label = "terms of service & privacy policy",
            value = UIText {
                val linkStyle = TextLinkStyles(
                    SpanStyle(color = Color.Blue, textDecoration = TextDecoration.Underline)
                )
                res(Res.string.legal_footer_example) {
                    arg(
                        UIText {
                            res(Res.string.legal_footer_example_insert_terms_of_service)
                        }
                    ) {
                        +LinkAnnotation.Url(
                            url = "https://radusalagean.com/example-terms-of-service/",
                            styles = linkStyle
                        )
                    }
                    arg(
                        UIText {
                            res(Res.string.legal_footer_example_insert_privacy_policy)
                        }
                    ) {
                        +LinkAnnotation.Url(
                            url = "https://radusalagean.com/example-privacy-policy/",
                            styles = linkStyle
                        )
                    }
                }
            }
        )
    )
}