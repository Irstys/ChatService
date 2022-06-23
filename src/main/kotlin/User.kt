data class User(
    val userId:Int,
    val name:String,
    val surname:String
)
{    override fun toString(): String {
    return "$userId, $name $surname"
}
}