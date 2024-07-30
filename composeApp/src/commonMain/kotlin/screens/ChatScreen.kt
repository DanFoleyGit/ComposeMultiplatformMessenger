package screens

import ChatViewModel
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import model.Chat

@Composable
fun ChatScreen(viewModel: ChatViewModel) {

    val state = viewModel.chatScreenUiState.collectAsState()
    val listState = rememberLazyListState()

    Box(modifier = Modifier
        .fillMaxSize()
        .padding(bottom = 60.dp),
        contentAlignment = Alignment.BottomCenter
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                state = listState,
                reverseLayout = false
            ) {
                items(state.value.chats.size) { index ->
                    ChatBubble(chat = state.value.chats[index])
                }
            }


            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.Bottom,
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    TextField(
                        value = state.value.userMessage,
                        onValueChange = { viewModel.updateUserMessage(it) },
                        maxLines = 5,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
                Column(
                    modifier = Modifier
                        .width(56.dp),
                    verticalArrangement = Arrangement.Center
                ) {
                    CircularAddButton(
                        listState = listState,
                        scrollToIndexValue = state.value.chats.size - 1,
                        onClick = {
                            viewModel.sendMessage()
                        }
                    )
                }
            }
        }
    }

}

@Composable
fun ChatBubble(chat: Chat){

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = if (chat.isMine) Arrangement.End else Arrangement.Start
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth(0.7f)
                .background(
                    MaterialTheme.colorScheme.surface,
                    shape = RoundedCornerShape(16.dp)
                )
                .border(1.dp, Color.Black, shape = RoundedCornerShape(16.dp))
                .padding(8.dp),
            contentAlignment = if (chat.isMine) Alignment.CenterEnd else Alignment.CenterEnd


        ) {

            Column {
                Text(
                    text = chat.name,
                    style = MaterialTheme.typography.labelMedium,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    chat.message,
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Light
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    Text(
                        chat.time,
                        style = MaterialTheme.typography.bodySmall,
                        fontWeight = FontWeight.Light
                    )
                }
            }
        }
    }
}

@Composable
fun CircularAddButton(listState: LazyListState, onClick: () -> Unit, scrollToIndexValue: Int) {
    val scope = rememberCoroutineScope()

    Box(
        modifier = Modifier
            .size(56.dp)
            .background(MaterialTheme.colorScheme.background, CircleShape)
            .clickable {
                onClick()
                scope.launch {
                    try {
                        listState.animateScrollToItem(scrollToIndexValue)
                    } catch (e: Exception) {
                        listState.animateScrollToItem(scrollToIndexValue + 1)
                    }
                }
           },
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = Icons.AutoMirrored.Filled.Send,
            contentDescription = "Add",
            tint = MaterialTheme.colorScheme.onBackground,modifier = Modifier.size(24.dp)
        )
    }
}