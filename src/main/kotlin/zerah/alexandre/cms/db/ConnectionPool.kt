package zerah.alexandre.cms.db

import java.sql.Connection
import java.sql.DriverManager
import java.util.concurrent.ConcurrentLinkedQueue

class ConnectionPool(val url:String, val user:String, val password: String) {

    // Queue synchronized
    private val queue = ConcurrentLinkedQueue<Connection>()

    fun getConnection(): Connection{
        val connection = queue.poll()
        //Elvis - Return connection if not null otherwise create it with DriverManager
        return connection ?: DriverManager.getConnection( url, user, password )
    }

    fun releaseConnection(c:Connection){
        queue.add(c)
    }

    inline fun useConnection(f: (Connection) -> Unit){
        val connection = getConnection()
        try {
            f(connection)
        } finally {
            releaseConnection(connection)
        }
    }
}
