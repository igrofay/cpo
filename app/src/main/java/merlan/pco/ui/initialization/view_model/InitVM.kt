package merlan.pco.ui.initialization.view_model

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import merlan.pco.ui.common.view_model.AppVM
import merlan.pco.ui.initialization.model.InitEvent
import merlan.pco.ui.initialization.model.InitSideEffect
import merlan.pco.ui.initialization.model.InitState
import org.kodein.di.DI
import org.kodein.di.DIAware
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.container
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce

class InitVM(override val di: DI) : AppVM<InitState,InitSideEffect, InitEvent>(), DIAware {

    override val container: Container<InitState, InitSideEffect> =
        viewModelScope.container(InitState()){
            delay(2000)
            reduce {
                state.copy(
                    isSplashing = false
                )
            }
        }
    override fun onEvent(event: InitEvent) {
        when(event){
            is InitEvent.InputEmail -> intent {
                reduce {
                    state.copy(email = event.email)
                }
            }
            is InitEvent.InputPassword -> intent{
                reduce {
                    state.copy(password = event.password)
                }
            }
            InitEvent.LogIn -> {

            }
        }
    }
    override fun onError(er: Throwable) {
    }
}