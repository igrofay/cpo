package merlan.pco.ui.initialization.model

sealed class InitEvent {
    data class InputEmail(val email: String = "") : InitEvent()
    data class InputPassword(val password: String = "") : InitEvent()
    data object LogIn : InitEvent()
}