package zerah.alexandre.cms.model

data class User(
    val id: Int,
    val email: String,
    val username: String,
    val password: String,
    val isAdmin: Boolean
)
