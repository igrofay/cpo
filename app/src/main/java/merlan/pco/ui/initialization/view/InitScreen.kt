package merlan.pco.ui.initialization.view

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContent
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonColors
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import merlan.pco.R
import merlan.pco.ui.common.view.edit_text.EditText
import merlan.pco.ui.common.view_model.rememberVM
import merlan.pco.ui.initialization.model.InitEvent
import merlan.pco.ui.initialization.model.InitSideEffect
import merlan.pco.ui.initialization.view_model.InitVM
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@Composable
fun InitScreen(
    goToContent: ()-> Unit
) {
    val initVM by rememberVM<InitVM>()
    initVM.collectSideEffect {sideEffect->
        when(sideEffect){
            InitSideEffect.UserIsAuthorized -> goToContent()
        }
    }
    val state by initVM.collectAsState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(WindowInsets.safeContent.asPaddingValues())
    ) {
        Image(
            painter = painterResource(R.drawable.logo),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1.2f),
            contentScale = ContentScale.FillWidth,
            alignment = Alignment.Center
        )
        AnimatedVisibility(
            visible = !state.isSplashing,
            modifier = Modifier
                .weight(0.8f)
                .fillMaxWidth(),
            enter = fadeIn() + expandVertically(expandFrom = Alignment.Top),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                EditText(
                    value = state.email,
                    onValueChange = {
                        initVM.onEvent(InitEvent.InputEmail(it))
                    },
                    modifier = Modifier
                        .fillMaxWidth(0.85f),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Email
                    ),
                    hint = "Почта"
                )
                Spacer(modifier = Modifier.height(14.dp))
                EditText(
                    value = state.password,
                    onValueChange = {
                        initVM.onEvent(InitEvent.InputPassword(it))
                    },
                    modifier = Modifier
                        .fillMaxWidth(0.85f),
                    visualTransformation = PasswordVisualTransformation(),
                    hint = "Пароль"
                )
                Spacer(modifier = Modifier.weight(1f))
                Button(
                    onClick = { /*TODO*/ },
                    shape = CircleShape,
                    modifier = Modifier.fillMaxWidth(0.8f),
                ) {
                    Text(
                        text = "Войти",
                        fontSize = 15.sp,
                        color = Color.White,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                }
                Spacer(modifier = Modifier.weight(0.3f))
            }
        }
    }
}