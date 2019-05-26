package zerah.alexandre.cms

import zerah.alexandre.cms.db.ConnectionPool
import zerah.alexandre.cms.db.MysqlModel
import zerah.alexandre.cms.model.Model
import zerah.alexandre.cms.presenter.ArticleListPresenter
import zerah.alexandre.cms.presenter.ArticlePresenter
import zerah.alexandre.cms.presenter.CommentPresenter
import zerah.alexandre.cms.control.CommentPresenterImpl
import zerah.alexandre.cms.control.ArticleListPresenterImpl
import zerah.alexandre.cms.control.ArticlePresenterImpl
import zerah.alexandre.cms.control.UserListPresenterImpl
import zerah.alexandre.cms.presenter.UserListPresenter

class AppComponent( mysqlUrl: String, mysqlUser: String,  mysqlPassword:String) {

    private val pool = ConnectionPool(mysqlUrl, mysqlUser, mysqlPassword)
    private val model = MysqlModel(getPool())

    fun getModel(): Model = model

    fun getPool(): ConnectionPool = pool


    fun getArticleListPresenter(view: ArticleListPresenter.View): ArticleListPresenter {
        return ArticleListPresenterImpl(getModel(), view )
    }

    fun getArticlePresenter(view: ArticlePresenter.View): ArticlePresenter {
        return ArticlePresenterImpl(getModel(), view )
    }

    fun getCommentPresenter(view: CommentPresenter.View): CommentPresenter {
        return CommentPresenterImpl(getModel(), view)
    }

    fun getUserListPresenter(view: UserListPresenter.View): UserListPresenter {
        return UserListPresenterImpl(getModel(), view)
    }



}

