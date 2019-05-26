package zerah.alexandre.cms.presenter

import zerah.alexandre.cms.model.Article
import zerah.alexandre.cms.model.User

interface UserListPresenter {

    fun start()

    interface View {
        fun displayUserList(list:List<User>)
    }

}
