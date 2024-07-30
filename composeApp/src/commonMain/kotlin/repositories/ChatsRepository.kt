package repositories

import model.Chat

interface ChatsRepository {
    fun getLast10Chats() : List<Chat>
    fun retrieveLastNumberOfChats(numberOfChats: Int) : List<Chat>
}