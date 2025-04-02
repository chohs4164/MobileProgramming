package com.example.dweek04a.uicomponents

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dweek04a.model.TodoItemFactory
import com.example.dweek04a.model.TodoStatus

@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    val todoList = remember { TodoItemFactory.makeTodoList().toMutableStateList() }
    var showCompleted by remember { mutableStateOf(true) }

    // filteredList를 derivedStateOf로 관리하여 항상 최신 상태 유지하기
    val filteredList by remember { derivedStateOf {
        if (showCompleted) todoList
        else todoList.filter { it.status == TodoStatus.PENDING }.toMutableStateList()
    } }

    val scrollState = rememberScrollState()

    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            TodoListTitle()
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("미완료 항목만 보기")
                Spacer(modifier = modifier.width(5.dp))
                TodoFilterSwitch(
                    checked = !showCompleted,
                    onCheckedChange = { showCompleted = !it }
                )
            }
        }
        Text(text = "202315323 조현승")
        Spacer(modifier = Modifier.height(8.dp))
        Box(
            modifier = Modifier
                .padding(15.dp)
                .weight(1f)
                .verticalScroll(scrollState)
        ) {
            TodoList(filteredList, todoList)
        }
        Column(modifier = Modifier.padding(15.dp)) {
            TodoItemInput(todoList, showCompleted)
        }
    }
}


@Preview(widthDp = 360, heightDp = 800)
@Composable
private fun MainScreenPreview() {
    MainScreen()
}
