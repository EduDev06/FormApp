package com.example.formapp.domain.use_cases

import com.example.formapp.R
import com.example.formapp.data.Result
import com.example.formapp.util.UiText
import javax.inject.Inject

const val MIN_PASSWORD_LENGTH = 8

class ValidatePasswordUseCase @Inject constructor() {

    operator fun invoke(password: String): Result<Unit> {
        if (password.length < MIN_PASSWORD_LENGTH) {
            return Result.Error(
                message = UiText.StringResource(
                    R.string.length_password_error,
                    MIN_PASSWORD_LENGTH
                )
            )
        }
        val containsLetterAndDigits = password.any { it.isDigit() } && password.any { it.isLetter() }
        if (!containsLetterAndDigits) {
            return Result.Error(
                message = UiText.StringResource(R.string.letter_digit_password_error)
            )
        }
        return Result.Success()
    }
}