package zerah.alexandre.cms.model

interface Model {

    /**
     * get all the articles
     */
    fun getArticleList(): List<Article>

    /**
     * Get one article
     */
    fun getArticle(id: Int): Article?

    /**
     * Create an article
     */
    fun createArticle(title: String?, text: String?): String

    /**
     * Get all comments
     */
    fun getComments(id: Int): List<Comment>?

    /**
     * Get on comment
     */
    fun getComment(id: Int): Comment?

    /**
     * create a comment
     */
    fun createComment(text: String, idArticle: String, datecreation: String, username: String)

    /**
     * register an user
     */
    fun register(email: String, username: String, password: String)

    /**
     * Get all users
     */
    fun getUsers(): List<User>


    /**
     * Get a user by its username
     */
    fun getUserByUsername(username: String): User?

    /**
     * Promote an user to admin
     */
    fun upgradeUser(id: Int)

    /**
     * Demote an admin to user
     */
    fun downgradeUser(id: Int)

    /**
     * Remove from db user
     */
    fun deleteUser(id: Int)

    /**
     * Remove an article (only admin)
     */
    fun removeArticle(id: Int): Unit

    /**
     * Remove a comment (only admin)
     */
    fun removeComment(id: Int): Unit

}
