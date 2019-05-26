import zerah.alexandre.cms.db.ConnectionPool
import zerah.alexandre.cms.db.MysqlModel

class MySQLModelTests {

    fun simpleTest() {
        val pool = ConnectionPool("jdbc:h2:mem:CMS;MODE=MySQL", "", "")

        pool.useConnection { connection ->
            val sql = """
            DROP TABLE IF EXISTS...

            """
            connection.prepareStatement(sql).use { stmt ->
                stmt.execute()
            }
        }

        val model = MysqlModel(pool)
        val article = model.getArticle(1)

    }
}
