package model

data class Chat(
    val name: String,
    val time: String,
    val message: String,
    val isMine: Boolean,
    val id: Int?
)
