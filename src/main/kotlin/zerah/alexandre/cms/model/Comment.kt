package zerah.alexandre.cms.model

data class Comment(
    val id: Int,
    val text: String,
    val idArticle: Int,
    val datecreation: String,
    val username: String
)
