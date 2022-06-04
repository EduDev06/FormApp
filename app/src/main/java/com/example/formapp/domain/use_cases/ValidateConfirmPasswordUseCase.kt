package com.example.formapp.domain.use_cases

import com.example.formapp.R
import com.example.formapp.data.Result
import com.example.formapp.util.UiText
import javax.inject.Inject

class ValidateConfirmPasswordUseCase @Inject constructor() {

    operator fun invoke(password: String, confirmPassword: String): Result<Unit> {
        if (confirmPassword.isBlank()) {
            return Result.Error(
                message = UiText.StringResource(R.string.blank_field_error)
            )
        }
        if (password != confirmPassword) {
            return Result.Error(
                message = UiText.StringResource(R.string.confirm_password_error)
            )
        }
        return Result.Success()
    }
}