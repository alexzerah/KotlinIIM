package zerah.alexandre.cms.tpl

data class SampleSession(val name: String, val value: Int, val isAdmin: Boolean)
abstract class Session {
    abstract val name: String
    abstract val isAdmin: Boolean
}
data class AdminSession(override val name: String, override val isAdmin: Boolean): Session()
data class UserSession(override val name: String, override val isAdmin: Boolean): Session()
