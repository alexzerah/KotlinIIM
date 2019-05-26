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
import io.ktor.sessions.get
import io.ktor.sessions.sessions
import kotlinx.coroutines.launch
import zerah.alexandre.cms.AppComponent
import zerah.alexandre.cms.model.Article
import zerah.alexandre.cms.presenter.ArticleListPresenter
import zerah.alexandre.cms.presenter.CommentPresenter
import zerah.alexandre.cms.tpl.IndexContext
import zerah.alexandre.cms.tpl.UserSession
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

fun Route.commentsRoutes(appComponent: AppComponent) {

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

    post {
        val body = call.receiveParameters()
        val current = LocalDateTime.now()

        val formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)
        val formatted = current.format(formatter)

        appComponent.getModel().createComment(body["text"]!!, body["current"]!!, formatted, body["username"]!!)
        call.respondRedirect("/articles/${body["current"]!!}")
    }


    get("{id}") {
        val controller = appComponent.getCommentPresenter(object : CommentPresenter.View {
            override fun removeComment(commentId: Int) {
                launch {
                    val articleRelated = appComponent.getModel().getComment(commentId)
                    appComponent.getModel().removeComment(commentId)
                    call.respondRedirect("/articles/${articleRelated!!.idArticle}")
                }
            }
        })
        val id = call.parameters["id"]!!.toIntOrNull()
        if (id == null)
            call.respond(HttpStatusCode.NotFound)
        else {
            val session = call.sessions.get<UserSession>()
            if (session != null && session.isAdmin)
                controller.removeComment(id, call.parameters["action"])
            else {
                call.respond(HttpStatusCode.Forbidden)
            }
        }
    }
}
