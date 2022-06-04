package com.example.formapp.ui.form.components

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.input.*
import androidx.compose.ui.unit.dp
import com.example.formapp.R
import com.example.formapp.util.UiText

const val MAX_LENGTH_PHONE = 9

/**
 * TextField 
 */

@Composable
fun FirstName(
    modifier: Modifier = Modifier,
    context: Context,
    text: String,
    textError: UiText?,
    imeAction: ImeAction = ImeAction.Next,
    onImeAction: () -> Unit = { },
    icon: ImageVector = Icons.Outlined.Person,
    label: String = stringResource(id = R.string.first_name),
    onTextChange: (String) -> Unit
) {
    OutlinedTextField(
        modifier = modifier.fillMaxWidth(),
        value =  text,
        onValueChange = onTextChange,
        label = {
            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                Text(text = label, style = MaterialTheme.typography.body2)
            }
        },
        trailingIcon = { Icon(imageVector = icon, contentDescription = label) },
        keyboardOptions = KeyboardOptions(
            imeAction = imeAction,
            capitalization = KeyboardCapitalization.Sentences
        ),
        keyboardActions = KeyboardActions(onDone = { onImeAction() }),
        textStyle = MaterialTheme.typography.body2,
        isError = textError != null
    )
    textError?.let { error -> TextFieldError(textError = error.asString(context)) }
}

@Composable
fun LastName(
    modifier: Modifier = Modifier,
    context: Context,
    text: String,
    textError: UiText?,
    imeAction: ImeAction = ImeAction.Next,
    onImeAction: () -> Unit = { },
    icon: ImageVector = Icons.Outlined.Person,
    label: String = stringResource(id = R.string.last_name),
    onTextChange: (String) -> Unit
) {
    OutlinedTextField(
        modifier = modifier.fillMaxWidth(),
        value =  text,
        onValueChange = onTextChange,
        label = {
            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                Text(text = label, style = MaterialTheme.typography.body2)
            }
        },
        trailingIcon = { Icon(imageVector = icon, contentDescription = label) },
        keyboardOptions = KeyboardOptions(
            imeAction = imeAction,
            capitalization = KeyboardCapitalization.Sentences
        ),
        keyboardActions = KeyboardActions(onDone = { onImeAction() }),
        textStyle = MaterialTheme.typography.body2,
        isError = textError != null
    )
    textError?.let { error -> TextFieldError(textError = error.asString(context)) }
}

@Composable
fun Phone(
    modifier: Modifier = Modifier,
    context: Context,
    text: String,
    textError: UiText?,
    icon: ImageVector = Icons.Outlined.Phone,
    label: String = stringResource(id = R.string.phone),
    onTextChange: (String) -> Unit
) {
    OutlinedTextField(
        modifier = modifier.fillMaxWidth(),
        value =  text,
        onValueChange = { if (it.length <= MAX_LENGTH_PHONE) onTextChange(it) },
        label = {
            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                Text(text = label, style = MaterialTheme.typography.body2)
            }
        },
        trailingIcon = { Icon(imageVector = icon, contentDescription = label) },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
        textStyle = MaterialTheme.typography.body2,
        isError = textError != null
    )
    textError?.let { error -> TextFieldError(textError = error.asString(context)) }
}

@Composable
fun Email(
    modifier: Modifier = Modifier,
    context: Context,
    text: String,
    textError: UiText?,
    imeAction: ImeAction = ImeAction.Next,
    onImeAction: () -> Unit = { },
    icon: ImageVector = Icons.Outlined.Email,
    label: String = stringResource(id = R.string.email),
    onTextChange: (String) -> Unit
) {
    OutlinedTextField(
        modifier = modifier.fillMaxWidth(),
        value =  text,
        onValueChange = onTextChange,
        label = {
            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                Text(text = label, style = MaterialTheme.typography.body2)
            }
        },
        trailingIcon = { Icon(imageVector = icon, contentDescription = label) },
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = imeAction,
            keyboardType = KeyboardType.Email
        ),
        keyboardActions = KeyboardActions(onDone = { onImeAction() }),
        textStyle = MaterialTheme.typography.body2,
        isError = textError != null
    )
    textError?.let { error -> TextFieldError(textError = error.asString(context)) }
}

@Composable
fun Password(
    modifier: Modifier = Modifier,
    context: Context,
    text: String,
    textError: UiText?,
    imeAction: ImeAction = ImeAction.Next,
    onImeAction: () -> Unit = { },
    label: String = stringResource(id = R.string.password),
    onTextChange: (String) -> Unit,
    showPassword: Boolean,
    onChangeVisibility: (Boolean) -> Unit
) {
    OutlinedTextField(
        modifier = modifier.fillMaxWidth(),
        value =  text,
        onValueChange = onTextChange,
        label = {
            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                Text(text = label, style = MaterialTheme.typography.body2)
            }
        },
        trailingIcon = {
            if (showPassword) {
                IconButton(onClick = { onChangeVisibility(false) }) {
                    Icon(imageVector = Icons.Filled.Visibility, contentDescription = stringResource(
                        id = R.string.hide_password
                    ))
                }
            } else {
                IconButton(onClick = { onChangeVisibility(true) }) {
                    Icon(imageVector = Icons.Filled.VisibilityOff, contentDescription = stringResource(
                        id = R.string.show_password
                    ))
                }
            }
        },
        visualTransformation = if (showPassword) {
            VisualTransformation.None
        } else {
            PasswordVisualTransformation()
        },
        keyboardOptions = KeyboardOptions.Default.copy(imeAction = imeAction),
        keyboardActions = KeyboardActions(onDone = { onImeAction() }),
        textStyle = MaterialTheme.typography.body2,
        isError = textError != null
    )
    textError?.let { error -> TextFieldError(textError = error.asString(context)) }
}

/**
 * RadioButton : Single Choice
 */

@Composable
fun GenderChoice(
    modifier: Modifier = Modifier,
    context: Context,
    options: List<String>?,
    selectedOption: String?,
    onOptionSelected: (String) -> Unit,
    textError: UiText?
) {
    Column(modifier = modifier
        .padding(start = 5.dp)
        .fillMaxWidth()) {
        Row(modifier = Modifier.selectableGroup()) {
            Text(
                text = stringResource(id = R.string.gender),
                style = MaterialTheme.typography.body2
            )
            Spacer(Modifier.width(3.dp))
            options?.forEach { text ->
                Row(
                    modifier = Modifier
                        .selectable(
                            selected = (text == selectedOption),
                            onClick = { onOptionSelected(text) },
                            role = Role.RadioButton
                        )
                        .padding(horizontal = 8.dp)
                ) {
                    RadioButton(
                        selected = (text == selectedOption),
                        onClick = null
                    )
                    Text(
                        text = text,
                        style = MaterialTheme.typography.body2,
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }
            }
        }
        textError?.let { error -> TextFieldError(textError = error.asString(context)) }
    }
}

@Composable
fun TextFieldError(
    modifier: Modifier = Modifier,
    textError: String
) {
    Row(modifier = modifier.fillMaxWidth()) {
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = textError,
            modifier = Modifier.fillMaxWidth(),
            style = LocalTextStyle.current.copy(color = MaterialTheme.colors.error)
        )
    }
}