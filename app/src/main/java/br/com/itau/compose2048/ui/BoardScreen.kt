package br.com.itau.compose2048.ui

import androidx.compose.foundation.Text
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.itau.compose2048.business.Direction
import br.com.itau.compose2048.business.Game2048
import br.com.itau.compose2048.theme.ProjectColors
import br.com.itau.compose2048.theme.shapes

//@Preview
@Composable
fun Board2048() {
    val game = remember { Game2048() }
    val boardState = remember { mutableStateOf(game.board) }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
    ) {

        //TopStatsPanel()

        //Spacer(modifier = Modifier.padding(top = 10.dp))

        Board(boardState.value)

        Row {
            ActionButton("Left") {
                game.shift(Direction.LEFT)
                boardState.value = game.board.copyOf()
            }
            ActionButton("Right") {
                game.shift(Direction.RIGHT)
                boardState.value = game.board.copyOf()
            }
        }
        Row {
            ActionButton("Top") {
                game.shift(Direction.TOP)
                boardState.value = game.board.copyOf()
            }
            ActionButton("Bottom") {
                game.shift(Direction.BOTTOM)
                boardState.value = game.board.copyOf()
            }
        }
    }
}

@Composable
fun Board(board: Array<IntArray>) {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.clip(shapes.large)
            .background(color = ProjectColors.surface)
            .padding(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        repeat(Game2048.BOARD_SIZE) { row ->
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier) {
                repeat(Game2048.BOARD_SIZE) { col ->
                    Cell(board[row][col], modifier = Modifier.weight(1.0f))
                }
            }
        }
    }
}

@Composable
fun Cell(num: Int, modifier: Modifier) {
    Box(
        modifier = modifier.background(ProjectColors.piece).aspectRatio(1.0f),
        contentAlignment = Alignment.Center
    ) {
        if (num > 0) {
            Text(
                text = "$num",
                style = TextStyle(
                    color = Color(0xFF6F665E),
                    fontSize = 48.sp,
                    fontWeight = FontWeight.Black
                )
            )
        }
    }
}

@Composable
fun ActionButton(text: String, action: () -> Unit) {
    Button(
        onClick = { action.invoke() },
        modifier = Modifier.preferredWidth(180.dp)
            .padding(top = 8.dp, start = 8.dp, end = 8.dp)
    ) {
        Text(text)
    }
}
