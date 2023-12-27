package merlan.pco.ui.initialization.model

sealed class InitSideEffect {
    data object UserIsAuthorized : InitSideEffect()
}