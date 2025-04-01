package com.example.dweek04a.uicomponents

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dweek04a.model.Item
import com.example.dweek04a.model.TodoStatus
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Composable
fun TodoItemInput(
    todoList: MutableList<Item>,
    showCompleted: Boolean,
    modifier: Modifier = Modifier
) {
    var textState by remember { mutableStateOf("") }

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextField(
            value = textState,
            onValueChange = { newText -> textState = newText },
            placeholder = { Text("할 일을 입력하세요", color = Color.Gray) },
            singleLine = true,
            modifier = Modifier
                .weight(1f)
                .onFocusChanged { }
        )
        Spacer(modifier = Modifier.width(5.dp))
        Button(onClick = {
            if (textState.isNotBlank()) {
                val currentTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("MM-dd HH:mm"))
                val newItem = Item(textState, currentTime, TodoStatus.PENDING)
                todoList.add(newItem)

                // ✅ 만약 미완료 항목만 보기가 활성화되어 있다면 즉시 반영
                if (!showCompleted) {
                    todoList.removeAll { it.status == TodoStatus.COMPLETED }
                }

                textState = ""
            }
        }) {
            Text(text = "추가")
        }
    }
}

@Preview
@Composable
private fun TodoItemInputPreview() {
    val todoList = remember { mutableListOf<Item>() }
    var showCompleted by remember { mutableStateOf(true) }

    TodoItemInput(todoList, showCompleted)
}
