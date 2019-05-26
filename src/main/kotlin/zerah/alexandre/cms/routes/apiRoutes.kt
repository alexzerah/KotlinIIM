package zerah.alexandre.cms.routes

import com.google.gson.Gson
import io.ktor.application.call
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.response.respond
import io.ktor.response.respondText
import io.ktor.routing.Route
import io.ktor.routing.get
import kotlinx.coroutines.launch
import zerah.alexandre.cms.AppComponent
import zerah.alexandre.cms.presenter.ArticleListPresenter
import zerah.alexandre.cms.presenter.ArticlePresenter
import zerah.alexandre.cms.model.Article
import zerah.alexandre.cms.model.Comment

fun Route.apiRoutes(appComponent: AppComponent) {
    get("articles") {
        appComponent.getArticleListPresenter(object : ArticleListPresenter.View {
            override fun displayArticleList(list: List<Article>) {
                launch {
                    val json = Gson().toJson(list)
                    call.respondText(json, ContentType.Application.Json)
                }
            }
        }).start()

    }

    get("article/{id}") {
        val id = call.parameters["id"]!!.toIntOrNull()
        if (id == null)
            call.respond(HttpStatusCode.NotFound)
        else
            appComponent.getArticlePresenter(object : ArticlePresenter.View {
                override fun removeArticle(articleId: Int) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }

                override fun displayArticle(
                    article: Article?,
                    comments: List<Comment>?
                ) {
                    launch {
                        call.respondText(Gson().toJson(article), ContentType.Application.Json)
                    }
                }

                override fun displayArticleNotFound() {
                    launch {
                        call.respond(HttpStatusCode.NotFound)
                    }
                }
            }).start(id, call.parameters["action"])
    }
}
