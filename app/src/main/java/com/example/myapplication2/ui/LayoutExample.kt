package com.example.myapplication2.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview
@Composable
private fun ColumnLab2Preview() {
    ColumnLab2()
}
@Composable
fun ColumnLab2(modifier: Modifier = Modifier) {
    Column(modifier=Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally,
        ){
        Box(Modifier
            .size(100.dp)
            .background(Color.Green)
            .border(width = 2.dp, Color.Black))
        Box(Modifier
            .size(80.dp)
            .background(Color.Magenta)
            .border(width = 2.dp, Color.Black))
        Box(Modifier
            .size(40.dp)
            .background(Color.Yellow)
            .border(width = 2.dp, Color.Black))
    }
}
@Preview
@Composable
fun ColumnLab() {
    Column{
        Box(Modifier
            .size(100.dp)
            .background(Color.Green)
            .border(width = 2.dp, Color.Black))
        Box(Modifier
            .size(80.dp)
            .background(Color.Magenta)
            .border(width = 2.dp, Color.Black))
        Box(Modifier
            .size(40.dp)
            .background(Color.Yellow)
            .border(width = 2.dp, Color.Black))
    }
}

@Preview
@Composable
fun RowLab() {
    Row {
        Box(Modifier
            .size(100.dp)
            .background(Color.Green)
            .border(width = 2.dp, Color.Black))
        Box(Modifier
            .size(80.dp)
            .background(Color.Magenta)
            .border(width = 2.dp, Color.Black))
        Box(Modifier
            .size(40.dp)
            .background(Color.Yellow)
            .border(width = 2.dp, Color.Black))
    }
}

@Preview
@Composable
fun BoxLab() {
    Box {
        Box(Modifier
            .size(100.dp)
            .background(Color.Green)
            .border(width = 2.dp, Color.Black))
        Box(Modifier
            .size(80.dp)
            .background(Color.Magenta)
            .border(width = 2.dp, Color.Black))
        Box(Modifier
            .size(40.dp)
            .background(Color.Yellow)
            .border(width = 2.dp, Color.Black))
    }
}
