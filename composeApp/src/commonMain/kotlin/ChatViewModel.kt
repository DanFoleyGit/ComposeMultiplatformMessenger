import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import model.Chat
import screens.ChatScreenUiState
import usecases.GetChatsUseCase

class ChatViewModel(
    private val getChatsUseCase: GetChatsUseCase
) : ViewModel() {


    private var _chatScreenUiState = MutableStateFlow(ChatScreenUiState())
    var chatScreenUiState: StateFlow<ChatScreenUiState> = _chatScreenUiState.asStateFlow()

    var chats by mutableStateOf<List<Chat>>(emptyList())

    fun updateUserMessage(message: String) {
        _chatScreenUiState.update { it.copy(userMessage = message) }
    }

    init {
        getChats()
    }

    private fun getChats() {
        _chatScreenUiState.update {
            it.copy(
                chats = getChatsUseCase.getChats(10).sortedBy { it.id }
            )
        }
    }

    fun sendMessage() {
        _chatScreenUiState.update { it.copy(
            chats = _chatScreenUiState.value.chats + Chat(
                "Me",
                getLocalTime(),
                _chatScreenUiState.value.userMessage,
                true,
                null),
            userMessage = ""
            )
        }
    }


    private fun getLocalTime(): String {
        val time = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())

        return "${time.hour}:${time.minute}"
    }


}