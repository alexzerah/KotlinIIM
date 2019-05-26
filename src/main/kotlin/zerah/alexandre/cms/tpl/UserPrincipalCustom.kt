package zerah.alexandre.cms.tpl

import io.ktor.auth.Principal

data class UserPrincipalCustom(val name: String, val isAdmin: Boolean) : Principal
