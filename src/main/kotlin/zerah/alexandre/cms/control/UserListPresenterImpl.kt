package zerah.alexandre.cms.control

import zerah.alexandre.cms.presenter.ArticleListPresenter
import zerah.alexandre.cms.model.Model
import zerah.alexandre.cms.presenter.UserListPresenter

class UserListPresenterImpl(val model: Model, val view: UserListPresenter.View):
    UserListPresenter {

    override fun start() {
        val list = model.getUsers()
        view.displayUserList(list)
    }
}
