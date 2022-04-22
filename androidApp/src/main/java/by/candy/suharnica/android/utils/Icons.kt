package by.candy.suharnica.android.utils

import androidx.annotation.DrawableRes
import by.candy.suharnica.MR
import by.candy.suharnica.android.R
import dev.icerock.moko.resources.StringResource


sealed class Icons(
    val description: StringResource, @DrawableRes val image: Int
) {

    object Search : Icons(
        MR.strings.search,
        R.drawable.ic_search
    )

    object Sort : Icons(
        MR.strings.sort,
        R.drawable.ic_sort
    )

    object Profile : Icons(
        MR.strings.profile,
        R.drawable.ic_profile
    )

    object Catalog : Icons(
        MR.strings.catalog,
        R.drawable.ic_catalog
    )

    object Basket : Icons(
        MR.strings.basket,
        R.drawable.ic_basket
    )

    object Edit : Icons(
        MR.strings.edit,
        R.drawable.ic_edit
    )

    object Smile : Icons(
        MR.strings.smile,
        R.drawable.ic_smile
    )

    object Plus : Icons(
        MR.strings.plus,
        R.drawable.ic_plus
    )

}

