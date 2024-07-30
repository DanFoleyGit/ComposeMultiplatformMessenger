package usecases

import model.Chat
import repositories.ChatsRepository

class GetChatsUseCase(
    private val repository: ChatsRepository
) {
    fun getChats(numberOfChats: Int): List<Chat> {
        return repository.retrieveLastNumberOfChats(numberOfChats)
    }
}
