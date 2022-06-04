package com.example.formapp.domain.use_cases

import com.example.formapp.R
import com.example.formapp.data.Result
import com.example.formapp.util.UiText
import javax.inject.Inject

const val CORRECT_PHONE_LENGTH = 9

class ValidatePhoneUseCase @Inject constructor() {

    operator fun invoke(phone: String): Result<Unit> {
        if (phone.isBlank()) {
            return Result.Error(
                message = UiText.StringResource(R.string.blank_phone_error)
            )
        }
        if (phone.length != CORRECT_PHONE_LENGTH) {
            return Result.Error(
                message = UiText.StringResource(R.string.length_phone_error)
            )
        }
        return Result.Success()
    }
}