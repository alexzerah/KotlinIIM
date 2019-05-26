package zerah.alexandre.cms

import de.mkammerer.argon2.Argon2Factory
import freemarker.cache.ClassTemplateLoader
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.auth.*
import io.ktor.features.AutoHeadResponse
import io.ktor.features.DefaultHeaders
import io.ktor.freemarker.FreeMarker
import io.ktor.freemarker.FreeMarkerContent
import io.ktor.http.content.files
import io.ktor.http.content.resources
import io.ktor.http.content.static
import io.ktor.request.receiveParameters
import io.ktor.response.respond
import io.ktor.response.respondRedirect
import io.ktor.routing.get
import io.ktor.routing.post
import io.ktor.routing.route
import io.ktor.routing.routing
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import io.ktor.sessions.*
import kotlinx.coroutines.launch
import zerah.alexandre.cms.model.Article
import zerah.alexandre.cms.model.User
import zerah.alexandre.cms.presenter.ArticleListPresenter
import zerah.alexandre.cms.presenter.UserListPresenter
import zerah.alexandre.cms.routes.adminRoutes
import zerah.alexandre.cms.routes.apiRoutes
import zerah.alexandre.cms.routes.articlesRoutes
import zerah.alexandre.cms.routes.commentsRoutes
import zerah.alexandre.cms.tpl.IndexContext
import zerah.alexandre.cms.tpl.UserPrincipalCustom
import zerah.alexandre.cms.tpl.UserSession
import java.io.FileInputStream
import java.util.*

class App


fun main() {
    val prop = Properties()
    prop.load(FileInputStream("src/main/kotlin/zerah/alexandre/cms/config.properties"))


    val appComponent = AppComponent(
        prop.getProperty("url"), prop.getProperty("username"), prop.getProperty("password")
    )


    embeddedServer(Netty, prop.getProperty("port").toInt()) {
        install(AutoHeadResponse)
        install(DefaultHeaders) {
            header("X-Engine", "Ktor") // will send this header with each response
        }
        install(FreeMarker) {
            templateLoader = ClassTemplateLoader(App::class.java.classLoader, "templates")
        }
        install(Sessions) {
            cookie<UserSession>("User", SessionStorageMemory())
        }
        install(Authentication) {
            form(name = "form_auth") {
                val argon2 = Argon2Factory.create()
                skipWhen { call -> call.sessions.get<UserSession>() != null }
                userParamName = "name"
                passwordParamName = "password"
                challenge = FormAuthChallenge.Redirect { "/login" }
                validate { credentials ->
                    try {
                        // Hash password
                        val usr = appComponent.getModel().getUserByUsername(credentials.name)
                        // Verify password
                        if (usr == null) {
                            null
                        } else {
                            if (argon2.verify(usr.password, credentials.password)) {
                                UserPrincipalCustom(usr.email, usr.isAdmin)
                            } else {
                                null
                            }
                        }
                    } finally {
                        // Wipe confidential data
                        argon2.wipeArray(credentials.password.toCharArray())
                    }
//                    if (credentials.name == "admin" && credentials.password == "admin")

                }
            }
        }

        routing {
            static("static") {
                resources("static")
                files("images")
                files("css")
                files("js")
            }

            get("/") {
                call.respondRedirect("/articles/")
                // If the session was not set, or is invalid, the returned value is null.
                // val mySession: MySession? = call.sessions.get<MySession>()
            }

            authenticate("form_auth") {
                post("/login") {

                    val principal = call.authentication.principal<UserPrincipalCustom>()
                    if (principal != null) {
                        call.sessions.set(UserSession(principal.name, principal.isAdmin))
                        if (principal.isAdmin) call.respondRedirect("/admin/") else call.respondRedirect("/articles/")
                    } else {
                        call.respondRedirect("/articles/")
                    }
                }
            }


            route("/articles") { articlesRoutes(appComponent) }
            route("/comments") { commentsRoutes(appComponent) }
            route("/admin") { adminRoutes(appComponent) }

            route("/login") {
                get {
                    call.respond(FreeMarkerContent("login.ftl", mapOf("name" to "", "password" to "")))
                }
            }

            route("/logout") {
                get {
                    if (call.sessions.get<UserSession>() != null) {
                        call.sessions.clear<UserSession>()
                        call.respondRedirect("/articles/")
                    }
                }
            }

            route("/register") {
                get {
                    call.respond(FreeMarkerContent("register.ftl", mapOf("username" to "", "password" to "")))

                }
                post {
                    val body = call.receiveParameters()
                    if ("password" !in body || "email" !in body || "username" !in body) {
                        call.respond(FreeMarkerContent("register.ftl", mapOf("username" to "", "password" to "")))
                    } else {
                        val email = body["email"]!!
                        val password = body["password"]
                        val username = body["username"]!!
                        val argon2 = Argon2Factory.create()
                        val hash = argon2.hash(10, 65536, 1, password)
                        appComponent.getModel().register(email, username, hash)
                        call.respond(FreeMarkerContent("login.ftl", mapOf("name" to "", "password" to "")))
                    }
                }
            }



            route("api") { apiRoutes(appComponent) }

        }
    }.start(wait = true)
}
