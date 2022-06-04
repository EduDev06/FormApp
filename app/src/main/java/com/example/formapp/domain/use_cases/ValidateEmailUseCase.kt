package com.example.formapp.domain.use_cases

import com.example.formapp.R
import com.example.formapp.data.Result
import com.example.formapp.util.UiText
import java.util.regex.Pattern
import javax.inject.Inject

private const val EMAIL_VALIDATION_REGEX = "^(.+)@(.+)\$"

class ValidateEmailUseCase @Inject constructor() {

    operator fun invoke(email: String): Result<Unit> {
        if (email.isBlank()) {
            return Result.Error(
                message = UiText.StringResource(R.string.blank_email_error)
            )
        }
        if (!Pattern.matches(EMAIL_VALIDATION_REGEX, email)) {
            return Result.Error(
                message = UiText.StringResource(R.string.invalid_email)
            )
        }
        return Result.Success()
    }
}