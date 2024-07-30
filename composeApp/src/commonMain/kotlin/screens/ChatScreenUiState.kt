package screens

import androidx.compose.foundation.lazy.LazyListState
import model.Chat

data class ChatScreenUiState(
    var chats: List<Chat> = emptyList<Chat>(),
    var userMessage: String = ""
)