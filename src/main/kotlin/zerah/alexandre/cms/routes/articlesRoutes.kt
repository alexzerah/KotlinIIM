package zerah.alexandre.cms.routes

import io.ktor.application.call
import io.ktor.freemarker.FreeMarkerContent
import io.ktor.http.HttpStatusCode
import io.ktor.request.receiveParameters
import io.ktor.response.respond
import io.ktor.response.respondRedirect
import io.ktor.routing.Route
import io.ktor.routing.get
import io.ktor.routing.post
import io.ktor.routing.route
import io.ktor.sessions.get
import io.ktor.sessions.sessions
import kotlinx.coroutines.launch
import zerah.alexandre.cms.AppComponent
import zerah.alexandre.cms.model.Article
import zerah.alexandre.cms.model.Comment
import zerah.alexandre.cms.presenter.ArticleListPresenter
import zerah.alexandre.cms.presenter.ArticlePresenter
import zerah.alexandre.cms.tpl.IndexContext
import zerah.alexandre.cms.tpl.UserSession

fun Route.articlesRoutes(appComponent: AppComponent) {

    get {
        val controller = appComponent.getArticleListPresenter(object : ArticleListPresenter.View {
            override fun displayArticleList(list: List<Article>) {
                val ctx = IndexContext(list, call.sessions.get<UserSession>())
                launch {
                    call.respond(FreeMarkerContent("index.ftl", ctx, "e"))
                }
            }
        })
        controller.start()
    }
    get("{id}") {
        val controller = appComponent.getArticlePresenter(object : ArticlePresenter.View {
            override fun displayArticleNotFound() {
                launch {
                    call.respond(HttpStatusCode.NotFound)
                }
            }

            override fun displayArticle(
                article: Article?,
                comments: List<Comment>?
            ) {
                launch {
                    //val currentSession =
                    val isAdmin =
                        call.sessions.get<UserSession>() != null//(currentSession != null && currentSession.isAdmin)
                    call.respond(
                        FreeMarkerContent(
                            "article.ftl",
                            mapOf(
                                "article" to article,
                                "comments" to comments,
                                "isAdmin" to isAdmin,
                                "ctx" to call.sessions.get<UserSession>()
                            ),
                            "e"
                        )
                    )
                }
            }

            override fun removeArticle(articleId: Int) {
                launch {
                    appComponent.getModel().removeArticle(articleId)
                    call.respondRedirect("/admin")
                }
            }
        })
        val id = call.parameters["id"]!!.toIntOrNull()
        if (id == null)
            call.respond(HttpStatusCode.NotFound)
        else
            controller.start(id, call.parameters["action"])

    }

    route("create") {
        get {
            call.respond(FreeMarkerContent("create.ftl", mapOf("text" to "", "title" to "")))
        }
        post {

            val body = call.receiveParameters()
            val id = appComponent.getModel().createArticle(body["title"], body["text"])
            if (id.equals("-1"))
                call.respondRedirect("/articles/")
            else
                call.respondRedirect("/articles/$id")
        }
    }
}
