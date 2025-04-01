package com.example.myapplication2.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.selection.triStateToggleable
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TriStateCheckbox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.state.ToggleableState
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

// https://composables.com/material3/checkbox
// https://composables.com/material3/tristatecheckbox
@Preview
@Composable
fun CheckboxSample() {
    val checkedState = remember { mutableStateOf(true) }
    Checkbox(checked = checkedState.value, onCheckedChange = { checkedState.value = it })
}

@Preview
@Composable
fun SimpleCheckboxExample() {
    var isChecked by remember { mutableStateOf(false) }
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(16.dp)
    ) {
        Checkbox(
            checked = isChecked,
            onCheckedChange = { isChecked = it }
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = if (isChecked) "체크됨" else "체크 안됨")
    }
}


@Preview
@Composable
fun CheckboxWithTextSample() {
    val (checkedState, onStateChange) = remember { mutableStateOf(true) }
    Row(
        Modifier.fillMaxWidth()
            .height(56.dp)
            .toggleable(
                value = checkedState,
                onValueChange = { onStateChange(!checkedState) },
                role = Role.Checkbox
            )
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = checkedState,
            onCheckedChange = null // null recommended for accessibility with screenreaders
        )
        Text(
            text = "Option selection",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(start = 16.dp)
        )
    }
}

@Preview
@Composable
fun TriStateCheckboxSample() {
    Column {
        // define dependent checkboxes states
        val (state, onStateChange) = remember { mutableStateOf(true) }
        val (state2, onStateChange2) = remember { mutableStateOf(true) }

        // TriStateCheckbox state reflects state of dependent checkboxes
        val parentState =
            remember(state, state2) {
                if (state && state2) ToggleableState.On
                else if (!state && !state2) ToggleableState.Off else ToggleableState.Indeterminate
            }
        // click on TriStateCheckbox can set state for dependent checkboxes
        val onParentClick = {
            val s = parentState != ToggleableState.On
            onStateChange(s)
            onStateChange2(s)
        }

        // The sample below composes just basic checkboxes which are not fully accessible on their
        // own. See the CheckboxWithTextSample as a way to ensure your checkboxes are fully
        // accessible.
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier =
            Modifier.triStateToggleable(
                state = parentState,
                onClick = onParentClick,
                role = Role.Checkbox
            )
        ) {
            TriStateCheckbox(
                state = parentState,
                onClick = null,
            )
            Text("Receive Emails")
        }
        Spacer(Modifier.size(25.dp))
        Column(Modifier.padding(24.dp, 0.dp, 0.dp, 0.dp)) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier =
                Modifier.toggleable(
                    value = state,
                    onValueChange = onStateChange,
                    role = Role.Checkbox
                )
            ) {
                Checkbox(state, null)
                Text("Daily")
            }
            Spacer(Modifier.size(25.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier =
                Modifier.toggleable(
                    value = state2,
                    onValueChange = onStateChange2,
                    role = Role.Checkbox
                )
            ) {
                Checkbox(state2, null)
                Text("Weekly")
            }
        }
    }
}