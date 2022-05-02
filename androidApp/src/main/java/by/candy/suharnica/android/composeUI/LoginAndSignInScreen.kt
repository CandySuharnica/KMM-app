package by.candy.suharnica.android.composeUI

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.ExposedDropdownMenuDefaults.outlinedTextFieldColors
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import by.candy.suharnica.MR
import by.candy.suharnica.android.MainViewModel
import by.candy.suharnica.android.utils.Colors
import by.candy.suharnica.android.utils.Icons


@Composable
fun LogInAndSignUpScreen(viewModel: MainViewModel) {
    val signUpState = remember { mutableStateOf(false) }
    Box(modifier = Modifier.fillMaxSize()) {
        Icon(
            modifier = Modifier
                .padding(40.dp)
                .align(Alignment.TopCenter),
            painter = painterResource(id = Icons.Label.image),
            contentDescription = stringResource(id = Icons.Label.description.resourceId)
        )
        if (!signUpState.value)
            login(
                modifier = Modifier
                    .align(Center)
                    .fillMaxHeight(),
                viewModel,
                signUpState
            )
        else
            signUp(
                modifier = Modifier
                    .align(Center)
                    .fillMaxHeight(),
                viewModel,
                signUpState
            )

    }

}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun login(modifier: Modifier, viewModel: MainViewModel, state: MutableState<Boolean>) {
    val userName = remember { mutableStateOf("") }
    val userPassword = remember { mutableStateOf("") }
    Box(modifier = modifier) {

        Column(modifier = Modifier.align(Center)) {
            Text(
                modifier = Modifier.padding(bottom = 8.dp),
                text = stringResource(id = MR.strings.login.resourceId),
                fontWeight = FontWeight.Bold
            )

            OutlinedTextField(
                value = userName.value,
                onValueChange = { userName.value = it },
                label = { Text(text = stringResource(id = MR.strings.user_name.resourceId)) },
                colors = outlinedTextFieldColors(
                    focusedBorderColor = Colors.RedButton.color,
                    unfocusedBorderColor = Color.Black,
                    focusedLabelColor = Color.Black
                )
            )

            OutlinedTextField(
                modifier = Modifier.padding(top = 12.dp),
                value = userPassword.value,
                onValueChange = { userPassword.value = it },
                label = { Text(text = stringResource(id = MR.strings.password.resourceId)) },
                colors = outlinedTextFieldColors(
                    focusedBorderColor = Colors.RedButton.color,
                    unfocusedBorderColor = Color.Black,
                    focusedLabelColor = Color.Black
                )
            )
        }

        Card(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .padding(start = 12.dp, end = 12.dp, bottom = 100.dp),
            backgroundColor = Colors.RedButton.color,
            border = BorderStroke(2.dp, color = Color.Black),
            shape = RoundedCornerShape(8.dp),
            onClick = { viewModel.login(userName.value, userPassword.value) }
        ) {
            Text(
                modifier = Modifier
                    .padding(8.dp),
                text = stringResource(id = MR.strings.log_in.resourceId),
                textAlign = TextAlign.Center,
                fontSize = 22.sp,
                fontWeight = FontWeight.Medium
            )
        }
        Text(
            modifier = Modifier
                .padding(bottom = 70.dp)
                .align(Alignment.BottomCenter)
                .clickable { state.value = true },
            text = stringResource(id = MR.strings.new_to_syharnica.resourceId),
            fontWeight = FontWeight.SemiBold,
            fontSize = 16.sp,
            textAlign = TextAlign.Center
        )
    }
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun signUp(modifier: Modifier, viewModel: MainViewModel, state: MutableState<Boolean>) {
    val userName = remember { mutableStateOf("") }
    val userPassword = remember { mutableStateOf("") }
    Box(modifier = modifier) {

        Column(modifier = Modifier.align(Center)) {
            Text(
                modifier = Modifier.padding(bottom = 8.dp),
                text = stringResource(id = MR.strings.signup.resourceId),
                fontWeight = FontWeight.Bold
            )

            OutlinedTextField(
                value = userName.value,
                onValueChange = { userName.value = it },
                label = { Text(text = stringResource(id = MR.strings.user_name.resourceId)) },
                colors = outlinedTextFieldColors(
                    focusedBorderColor = Colors.RedButton.color,
                    unfocusedBorderColor = Color.Black,
                    focusedLabelColor = Color.Black
                )
            )

            OutlinedTextField(
                modifier = Modifier.padding(top = 12.dp),
                value = userPassword.value,
                onValueChange = { userPassword.value = it },
                label = { Text(text = stringResource(id = MR.strings.password.resourceId)) },
                colors = outlinedTextFieldColors(
                    focusedBorderColor = Colors.RedButton.color,
                    unfocusedBorderColor = Color.Black,
                    focusedLabelColor = Color.Black
                )
            )
        }

        Card(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .padding(start = 12.dp, end = 12.dp, bottom = 100.dp),
            backgroundColor = Colors.RedButton.color,
            border = BorderStroke(2.dp, color = Color.Black),
            shape = RoundedCornerShape(8.dp),
            onClick = { viewModel.register(userName.value, userPassword.value) }
        ) {
            Text(
                modifier = Modifier
                    .padding(8.dp),
                text = stringResource(id = MR.strings.sign_up.resourceId),
                textAlign = TextAlign.Center,
                fontSize = 22.sp,
                fontWeight = FontWeight.Medium
            )
        }
        Text(
            modifier = Modifier
                .padding(bottom = 70.dp)
                .align(Alignment.BottomCenter)
                .clickable { state.value = false },
            text = stringResource(id = MR.strings.already_in_syharnica.resourceId),
            fontWeight = FontWeight.SemiBold,
            fontSize = 16.sp,
            textAlign = TextAlign.Center
        )
    }
}
