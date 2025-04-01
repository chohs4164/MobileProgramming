package com.example.dweek04a.uicomponents

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
                Row(verticalAlignment = Alignment.CenterVertically) {
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
}


@Preview
@Composable
private fun TodoListPreview() {
    val todoList = TodoItemFactory.makeTodoList().toMutableStateList()
    TodoList(
        filteredList = todoList,
        todoList = todoList
    )
}