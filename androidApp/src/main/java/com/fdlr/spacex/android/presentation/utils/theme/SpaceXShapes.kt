package com.fdlr.spacex.android.presentation.utils.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Shapes
import com.fdlr.spacex.android.presentation.utils.Dimens

val SpaceXShapes = Shapes(
    small = RoundedCornerShape(Dimens.CornerRadius.small),
    medium = RoundedCornerShape(Dimens.CornerRadius.medium),
    large = RoundedCornerShape(Dimens.CornerRadius.xlarge)
)