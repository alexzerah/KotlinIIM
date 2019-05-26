package zerah.alexandre.cms.presenter

import zerah.alexandre.cms.model.Article

interface ArticleListPresenter {

    fun start()

    interface View {
        fun displayArticleList(list:List<Article>)
    }

}
