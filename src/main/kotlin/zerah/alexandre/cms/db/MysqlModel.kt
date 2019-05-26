package zerah.alexandre.cms.db

import zerah.alexandre.cms.db.queries.SQLQuery
import zerah.alexandre.cms.model.Article
import zerah.alexandre.cms.model.Comment
import zerah.alexandre.cms.model.Model
import zerah.alexandre.cms.model.User

class MysqlModel(val pool: ConnectionPool) : Model {


    val list = ArrayList<Article>()


    override fun getArticleList(): ArrayList<Article> {
        val list = ArrayList<Article>()
        pool.useConnection { connection ->
            connection.prepareStatement("SELECT * FROM articles").use { stmt ->
                stmt.executeQuery().use { rs ->
                    while (rs.next()) {
                        list.add(
                            Article(
                                rs.getInt("id"),
                                rs.getString("title"),
                                rs.getString("text")
                            )
                        )
                    }
                }
            }
        }
        return list
    }

    override fun getArticle(id: Int): Article? {
        pool.useConnection { connection ->
            connection.prepareStatement("SELECT * FROM articles where id = ?").use { stmt ->
                stmt.setInt(1, id)
                stmt.executeQuery().use { rs ->
                    if (rs.next()) {
                        return Article(
                            rs.getInt("id"),
                            rs.getString("title"),
                            rs.getString("text")
                        )
                    }
                }
            }
        }
        return null
    }


    override fun createArticle(title: String?, text: String?): String {
        pool.useConnection { connection ->
            val stmt = connection.prepareStatement("INSERT INTO `articles` ( `title`, `text`) VALUES (?,?)")
            stmt.setString(1, title)
            stmt.setString(2, text)
            stmt.executeUpdate()
            try {
                val id = stmt.getGeneratedKeys()
                if (id.next())
                    return id.toString()
            } catch (e: Exception) {
                return "-1"

            }
        }
        return "-1"
    }


    override fun getComments(id: Int): List<Comment>? {
        val list = ArrayList<Comment>()
        pool.useConnection { connection ->
            val sql = SQLQuery("getComments")
            connection.prepareStatement(sql.query).use { stmt ->
                stmt.setInt(1, id)
                stmt.executeQuery().use { rs ->
                    while (rs.next()) {
                        list.add(
                            Comment(
                                rs.getInt("id"),
                                rs.getString("text"),
                                rs.getInt("idArticle"),
                                rs.getString("datecreation"),
                                rs.getString("username")
                            )
                        )
                        println(rs.getString("username"))
                    }
                }
            }
        }
        return list
    }

    override fun getComment(id: Int): Comment? {
        pool.useConnection { connection ->
            connection.prepareStatement("SELECT * FROM comments where id = ?").use { stmt ->
                stmt.setInt(1, id)
                stmt.executeQuery().use { rs ->
                    if (rs.next()) {
                        return Comment(
                            rs.getInt("id"),
                            rs.getString("text"),
                            rs.getInt("idArticle"),
                            rs.getString("datecreation"),
                            rs.getString("username")
                        )
                    }
                }
            }
        }
        return null
    }

    override fun createComment(text: String, idArticle: String, datecreation: String, username: String) {
        pool.useConnection { connection ->
            val stmt =
                connection.prepareStatement("INSERT INTO `comments` ( `text`, `idArticle`, `datecreation`, `username`) VALUES (?,?, ?,?)")
            stmt.setString(1, text)
            stmt.setString(2, idArticle)
            stmt.setString(3, datecreation)
            stmt.setString(4, username)

            stmt.executeUpdate()
        }
    }

    override fun register(email: String, username: String, password: String) {
        pool.useConnection { connection ->
            val stmt =
                connection.prepareStatement("INSERT INTO `users` ( `email`,`username`, `password`) VALUES (?,?,?)")
            stmt.setString(1, email)
            stmt.setString(2, username)
            stmt.setString(3, password)
            stmt.executeUpdate()
        }
    }

    override fun getUserByUsername(username: String): User? {
        pool.useConnection { connection ->
            connection.prepareStatement("SELECT * FROM `users` WHERE username = ?").use { stmt ->
                stmt.setString(1, username)
                stmt.executeQuery().use { rs ->
                    if (rs.next()) {
                        return User(
                            rs.getInt("id"),
                            rs.getString("email"),
                            rs.getString("username"),
                            rs.getString("password"),
                            rs.getBoolean("isAdmin")
                        )
                    }
                }
            }
        }
        return null
    }


    override fun removeArticle(id: Int) {
        pool.useConnection { connection ->
            connection.prepareStatement("DELETE FROM `articles` WHERE id = ?").use { stmt ->
                stmt.setInt(1, id)
                stmt.executeUpdate()
            }
        }
    }

    override fun removeComment(id: Int) {
        pool.useConnection { connection ->
            connection.prepareStatement("DELETE FROM `comments` WHERE id = ?").use { stmt ->
                stmt.setInt(1, id)
                stmt.executeUpdate()
            }
        }
    }

    override fun getUsers(): List<User> {
        val list = ArrayList<User>()
        pool.useConnection { connection ->
            connection.prepareStatement("SELECT * FROM users").use { stmt ->
                stmt.executeQuery().use { rs ->
                    while (rs.next()) {
                        list.add(
                            User(
                                rs.getInt("id"),
                                rs.getString("email"),
                                rs.getString("username"),
                                "", rs.getBoolean("isAdmin")
                            )
                        )
                    }
                }
            }
        }
        return list
    }


    override fun upgradeUser(id: Int) {
        pool.useConnection { connection ->
            connection.prepareStatement("UPDATE `users` SET `isAdmin`= ? WHERE id = ?").use { stmt ->
                stmt.setInt(1, 1)
                stmt.setInt(2, id)
                stmt.executeUpdate()
            }
        }
    }

    override fun downgradeUser(id: Int) {
        pool.useConnection { connection ->
            connection.prepareStatement("UPDATE `users` SET `isAdmin`= ? WHERE id = ?").use { stmt ->
                stmt.setInt(1, 0)
                stmt.setInt(2, id)
                stmt.executeUpdate()
            }
        }
    }

    override fun deleteUser(id: Int) {
        pool.useConnection { connection ->
            connection.prepareStatement("DELETE FROM `users` WHERE id = ?").use { stmt ->
                stmt.setInt(1, id)
                stmt.executeUpdate()
            }
        }
    }

}
