object ChatService {
    private var  chatMap : MutableMap<Set<Int>, MutableList<Message>> = mutableMapOf()
    private var  lastId=0

    fun sendMessage(sender:User,recipient:User,text:String):Int{
            val userSet = setOf(sender.userId,recipient.userId)
            val newMessage:Message=Message(idSender=sender.userId,idRecipient=recipient.userId,text=text,idMessage = lastId++)
        if (!chatMap.containsKey(userSet)) {
            chatMap[userSet] = mutableListOf(newMessage)
        } else {
            chatMap.forEach { (k, v) ->
                if (k.contains(newMessage.idSender) && k.contains(newMessage.idRecipient)) {
                    chatMap[k] = v.plusElement(newMessage) as MutableList<Message>
                }
            }
        }
        println(newMessage.toString())
        return chatMap.size
    }

    fun deleteChat(talker: User):Int {
        chatMap.filter { (k, _) -> k.contains(talker.userId)}
        return chatMap.size
    }

    fun getUnreadChats(talker: User): MutableList<List<Message>> {
        val unreadChatList = mutableListOf<List<Message>>()
        chatMap.forEach { (k, v) ->
            if (k.contains(talker.userId)) {
                val newList = v.filter { it.idRecipient == talker.userId && !it.isReading }
                unreadChatList.plusAssign(newList)
            }
        }
        val iterator = unreadChatList.iterator()
        iterator.forEach {
            if (it.isEmpty()) {
                iterator.remove()
            }
        }
        return unreadChatList
    }

    fun readMessage(idMessage:Int): Boolean{
        chatMap.forEach { (_: Set<Int>, v: MutableList<Message>) ->
            v.forEach { message: Message ->
                if (message.idMessage == idMessage) {
                    val readMessage = message.copy(isReading = true)
                    v[v.indexOf(message)] = readMessage
                    return true
                }
            }
        }
        println("Don't find message with ID $idMessage")
        return false
    }

    fun getChatList(): MutableMap<Int, MutableList<Message>> {
        val chatList = mutableMapOf<Int, MutableList<Message>>()
        var count = 1
        chatMap.forEach { (_, v) ->
            chatList[count] = v
            count += 1
        }
        return chatList
    }

}
