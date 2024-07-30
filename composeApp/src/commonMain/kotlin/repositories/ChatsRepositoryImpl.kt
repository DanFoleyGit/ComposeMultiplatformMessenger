package repositories

import model.Chat

class ChatsRepositoryImpl() : ChatsRepository {
    val tempChats = listOf(
        Chat("Name", "Time", "Message jdhfdksjfd f dsfj fj h ejfhd jkf dfj dfh djfh dsjkf dsjk dsgdskj hsdf hdsjkfhdskjf dsjkd sd ghsdj g", false,1),
        Chat("Name", "Time", "Message this is chat 2", false, 2),
        Chat("Name", "Time", "Message this is chat 3", false, 3),
        Chat("Name", "Time", "Message this is chat 4", false, 4),
        Chat("Name", "Time", "Message this is chat 5", false, 5),
        Chat("Name", "Time", "Message this is chat 6", false, 6),
        Chat("Name", "Time", "Message this is chat 7", false, 7),
        Chat("Name", "Time", "Message this is chat 8", false, 8),
        Chat("Name", "Time", "Message this is chat 9", false, 9),
        Chat("Name", "Time", "Message this is chat 10", false, 10),
        Chat("Name", "Time", "Message this is chat 11", false, 11),
        Chat("Name", "Time", "Message this is chat 12", false, 12),
        Chat("Name", "Time", "Message this is chat 13", false, 13),
        Chat("Name", "Time", "Message this is chat 14", false, 14),
        Chat("Name", "Time", "Message this is chat 15", false, 15),
    )


    override fun getLast10Chats(): List<Chat> {
        return tempChats
    }

    override fun retrieveLastNumberOfChats(numberOfChats: Int): List<Chat> {
        return tempChats.takeLast(numberOfChats)
    }

}
