package merlan.pco.ui.common.view_model

interface EventBase<T> {
    fun onEvent(event: T)
}