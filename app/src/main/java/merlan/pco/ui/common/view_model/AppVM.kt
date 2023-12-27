package merlan.pco.ui.common.view_model

import androidx.lifecycle.ViewModel
import org.orbitmvi.orbit.ContainerHost

abstract class AppVM <State : Any, SideEffect : Any, Event : Any > :
    ContainerHost<State, SideEffect>, EventBase<Event>, ViewModel() {
    protected abstract fun onError(er: Throwable)
}