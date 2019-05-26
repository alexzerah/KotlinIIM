package zerah.alexandre.cms.control

import zerah.alexandre.cms.presenter.ArticleListPresenter
import zerah.alexandre.cms.model.Model

class ArticleListPresenterImpl(val model: Model, val view: ArticleListPresenter.View):
    ArticleListPresenter {

    override fun start() {
        val list = model.getArticleList()
        view.displayArticleList(list)
    }
}
