import org.junit.Test

import org.junit.Assert.*

class ChatServiceTest {
    val max = User(111,"Max","Petrov")
    val lesha = User(111,"Alex","Maslov")
    val olya = User(111,"Olya","Pyshkina")
    val ann = User(111,"Anna","Karenina")


    @Test
    fun sendMessage() {
        val sizeChat = ChatService.sendMessage(max,olya,"Hello")
        assertNotEquals(1, sizeChat)
    }

    @Test
    fun sendMessage_uncorrected() {
            val sizeChat = ChatService.sendMessage(max,ann,"Hello")
            assertNotEquals(1, sizeChat)
    }

    @Test
    fun deleteChat() {
        ChatService.sendMessage(max,ann,"Hello")
        val sizeChat = ChatService.deleteChat(max)
        assertNotEquals(0, sizeChat)
    }

    @Test
    fun deleteChat_uncorrected() {
        ChatService.sendMessage(max,ann,"Hello")
        val sizeChat = ChatService.deleteChat(lesha)
        assertNotEquals(0, sizeChat)
    }

     @Test
    fun readMessage() {
         ChatService.sendMessage(max,ann,"Hello")
         val readChat:Boolean = ChatService.readMessage(1)
         assertNotEquals(readChat, true)
    }

    @Test
    fun readMessage_uncorrected() {
        ChatService.sendMessage(max,ann,"Hello")
        val readChat:Boolean = ChatService.readMessage(0)
        assertNotEquals(readChat, false)
    }


}