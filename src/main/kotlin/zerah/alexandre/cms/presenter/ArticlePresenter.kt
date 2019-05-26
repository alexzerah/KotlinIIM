package zerah.alexandre.cms.presenter

import zerah.alexandre.cms.model.Article
import zerah.alexandre.cms.model.Comment

interface ArticlePresenter {
    fun start(id:Int, qParam:String?)

    interface View {
        fun displayArticle(article: Article?, comments: List<Comment>?)
        fun displayArticleNotFound()
        fun removeArticle(articleId: Int)
    }

}
