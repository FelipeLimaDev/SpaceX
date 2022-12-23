package com.fdlr.domain.model.model

sealed class UIComponentType{

    object Dialog: UIComponentType()

    object Snackbar: UIComponentType()

    object Toast: UIComponentType()

    object None: UIComponentType()
}