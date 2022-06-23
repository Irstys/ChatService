data class Message (
    val idMessage:Int?=null,
    val idSender:Int,
    val idRecipient:Int,
    val isReading:Boolean = false,
    val text:String?=null,
    val date:String?=null
) {
    override fun toString(): String {
        return "Message from $(idRecipient.toString()) by$(idSender.toString()): $text"
    }
}