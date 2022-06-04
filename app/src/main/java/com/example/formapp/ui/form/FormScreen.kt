package com.example.formapp.ui.form

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.formapp.R
import com.example.formapp.ui.form.components.*
import com.example.formapp.util.UiText

@Composable
fun FormScreen(
    modifier: Modifier = Modifier,
    context: Context,
    viewModel: FormViewModel = hiltViewModel()
) {
    val state = viewModel.state
    val scaffoldState: ScaffoldState = rememberScaffoldState()
    LaunchedEffect(scaffoldState.snackbarHostState) {
        viewModel.eventFlow.collect { event ->
            when (event) {
                is FormViewModel.UiEvent.ShowSnackBar -> event.message?.let {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = it.asString(context)
                    )
                }
            }
        }
    }
    Scaffold(
        modifier = modifier.fillMaxWidth(),
        scaffoldState = scaffoldState
    ) {
        Column(
            modifier = Modifier
                .padding(start = 8.dp, end = 8.dp, top = 8.dp)
                .verticalScroll(rememberScrollState())
        ) {
            HeadLine()
            Body(
                context = context,
                onEvent = { viewModel.onEvent(it) },
                firstName = state.firstName,
                firstNameError = state.firstNameError,
                lastName = state.lastName,
                lastNameError = state.lastNameError,
                phone = state.phone,
                phoneError = state.phoneError,
                gender = state.gender,
                genderError = state.genderError,
                email = state.email,
                emailError = state.emailError,
                password = state.password,
                passwordError = state.passwordError,
                showPassword = state.showPassword,
                confirmPassword = state.confirmPassword,
                confirmPasswordError = state.confirmPasswordError,
                showConfirmPassword = state.showConfirmPassword
            )

        }
    }
}

@Composable
private fun HeadLine() {
    Text(
        text = stringResource(id = R.string.headline),
        style = MaterialTheme.typography.h3
    )
}

@Composable
private fun Body(
    context: Context,
    onEvent: (FormEvent) -> Unit,
    firstName: String,
    firstNameError: UiText?,
    lastName: String,
    lastNameError: UiText?,
    phone: String,
    phoneError: UiText?,
    gender: String?,
    genderError: UiText?,
    email: String,
    emailError: UiText?,
    password: String,
    passwordError: UiText?,
    showPassword: Boolean,
    confirmPassword: String,
    confirmPasswordError: UiText?,
    showConfirmPassword: Boolean
) {
    Column(Modifier.fillMaxSize()) {
        FirstName(
            context = context,
            text = firstName,
            textError = firstNameError,
            onTextChange = { onEvent(FormEvent.EnteredFirstName(it)) }
        )
        LastName(
            context = context,
            text = lastName,
            textError = lastNameError,
            onTextChange = { onEvent(FormEvent.EnteredLastName(it)) }
        )
        Phone(
            context = context,
            text = phone,
            textError = phoneError,
            onTextChange = { onEvent(FormEvent.EnteredPhone(it)) }
        )
        Spacer(modifier = Modifier.height(8.dp))
        GenderChoice(
            context = context,
            options = listOf(stringResource(R.string.male), stringResource(R.string.female)),
            selectedOption = gender,
            onOptionSelected = { onEvent(FormEvent.ChoseGender(it)) },
            textError = genderError
        )
        Email(
            context = context,
            text = email,
            textError = emailError,
            onTextChange = { onEvent(FormEvent.EnteredEmail(it)) }
        )
        Password(
            context = context,
            text = password,
            textError = passwordError,
            onTextChange = { onEvent(FormEvent.EnteredPassword(it)) },
            showPassword = showPassword,
            onChangeVisibility = { onEvent(FormEvent.ChangePasswordVisibility(it)) }
        )
        // Confirm Password
        Password(
            context = context,
            text = confirmPassword,
            label = stringResource(R.string.confirm_password),
            textError = confirmPasswordError,
            onTextChange = { onEvent(FormEvent.EnteredConfirmPassword(it)) },
            showPassword = showConfirmPassword,
            onChangeVisibility = { onEvent(FormEvent.ChangeConfirmPasswordVisibility(it)) }
        )
        Spacer(modifier = Modifier.height(12.dp))
        Submit(onSubmit = { onEvent(FormEvent.InsertUser) })
    }
}

@Composable
private fun Submit(
    modifier: Modifier = Modifier,
    onSubmit: () -> Unit
) {
    Button(
        modifier = modifier.fillMaxWidth(),
        onClick = { onSubmit() }
    ) {
        Text(
            text = stringResource(R.string.create_user),
            style = MaterialTheme.typography.body2
        )
    }
}