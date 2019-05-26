package zerah.alexandre.cms.db.queries


class SQLQuery(sql: String) {

    var query = ""

    init {
        when (sql) {
            "getComments" -> query = getComments()
        }
    }


    private fun getComments(): String {
        return "SELECT b.id, b.text, b.idArticle, b.datecreation, b.username FROM articles as a INNER JOIN comments as b ON a.id = b.idArticle AND a.id=?"
    }

}
