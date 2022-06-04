package com.example.formapp.ui.form

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.formapp.data.Result
import com.example.formapp.domain.model.User
import com.example.formapp.domain.use_cases.*
import com.example.formapp.util.UiText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FormViewModel @Inject constructor(
    private val validateFirstNameUseCase: ValidateFirstNameUseCase,
    private val validateLastNameUseCase: ValidateLastNameUseCase,
    private val validatePhoneUseCase: ValidatePhoneUseCase,
    private val validateGenderUseCase: ValidateGenderUseCase,
    private val validatePasswordUseCase: ValidatePasswordUseCase,
    private val validateEmailUseCase: ValidateEmailUseCase,
    private val validateConfirmPasswordUseCase: ValidateConfirmPasswordUseCase,
    private val insertUserUseCase: InsertUserUseCase
): ViewModel() {

    var state by mutableStateOf(FormState())
        private set

    private val eventChannel = Channel<UiEvent>()
    val eventFlow = eventChannel.receiveAsFlow()

    fun onEvent(event: FormEvent) {
        when (event) {
            is FormEvent.EnteredFirstName -> state = state.copy(firstName = event.value)
            is FormEvent.EnteredLastName -> state = state.copy(lastName = event.value)
            is FormEvent.EnteredPhone -> state = state.copy(phone = event.value)
            is FormEvent.ChoseGender -> state = state.copy(gender = event.value)
            is FormEvent.EnteredEmail -> state = state.copy(email = event.value)
            is FormEvent.EnteredPassword -> state = state.copy(password = event.value)
            is FormEvent.EnteredConfirmPassword -> state = state.copy(confirmPassword = event.value)
            is FormEvent.ChangePasswordVisibility -> state = state.copy(showPassword = event.value)
            is FormEvent.ChangeConfirmPasswordVisibility -> state = state.copy(showConfirmPassword = event.value)
            FormEvent.InsertUser -> insertUser()
        }
    }

    private fun insertUser() {
        val firstNameResult = validateFirstNameUseCase(state.firstName)
        val lastNameResult = validateLastNameUseCase(state.lastName)
        val phoneResult = validatePhoneUseCase(state.phone)
        val genderResult = validateGenderUseCase(state.gender)
        val emailResult = validateEmailUseCase(state.email)
        val passwordResult = validatePasswordUseCase(state.password)
        val confirmPasswordResult = validateConfirmPasswordUseCase(
            password = state.password,
            confirmPassword = state.confirmPassword
        )
        val hasError = listOf(
            firstNameResult,
            lastNameResult,
            phoneResult,
            genderResult,
            emailResult,
            passwordResult,
            confirmPasswordResult
        ).any { it is Result.Error }

        if (!hasError) {
            viewModelScope.launch {
                insertUserUseCase(
                    User(
                        firstName = state.firstName,
                        lastName = state.lastName,
                        phone = state.phone,
                        gender = state.gender,
                        email = state.email,
                        password = state.password
                    )
                ).also { result ->
                    when (result) {
                        is Result.Error -> {
                            eventChannel.send(
                                UiEvent.ShowSnackBar(
                                    message = result.message
                                )
                            )
                        }
                        is Result.Success -> {
                            eventChannel.send(
                                UiEvent.ShowSnackBar(
                                    message = result.message
                                )
                            )
                        }
                    }
                }
            }
        }
        state = state.copy(
            firstNameError = firstNameResult.message,
            lastNameError = lastNameResult.message,
            phoneError = phoneResult.message,
            genderError = genderResult.message,
            emailError = emailResult.message,
            passwordError = passwordResult.message,
            confirmPasswordError = confirmPasswordResult.message
        )
    }

    sealed class UiEvent {
        data class ShowSnackBar(val message: UiText?): UiEvent()
    }
}