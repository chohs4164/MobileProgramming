package com.example.dweek04a.uicomponents

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dweek04a.model.Item
import com.example.dweek04a.model.TodoItemFactory
import com.example.dweek04a.model.TodoStatus

@Composable
fun TodoList(
    filteredList: List<Item>,
    todoList: MutableList<Item>,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        filteredList.forEach { item ->
            Card(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
                    .height(56.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .toggleable( // ✅ Row 전체가 클릭 가능하도록 설정
                            value = item.status == TodoStatus.COMPLETED,
                            onValueChange = { checked ->
                                val index = todoList.indexOf(item)
                                if (index != -1) {
                                    todoList[index] = item.copy(
                                        status = if (checked) TodoStatus.COMPLETED else TodoStatus.PENDING
                                    )
                                }
                            },
                            role = androidx.compose.ui.semantics.Role.Checkbox
                        )
                        .padding(horizontal = 16.dp) // ✅ 패딩 추가해서 클릭하기 편하게 설정
                ) {
                    TodoCheckbox(checked = item.status == TodoStatus.COMPLETED) { checked ->
                        val index = todoList.indexOf(item)
                        if (index != -1) {
                            todoList[index] = item.copy(
                                status = if (checked) TodoStatus.COMPLETED else TodoStatus.PENDING
                            )
                        }
                    }
                    TodoItem(item = item)
                }
            }
        }
    }
}ㅎ

@Preview
@Composable
private fun TodoListPreview() {
    val todoList = TodoItemFactory.makeTodoList().toMutableStateList()
    TodoList(
        filteredList = todoList,
        todoList = todoList
    )
}