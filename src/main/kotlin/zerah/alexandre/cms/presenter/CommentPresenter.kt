package zerah.alexandre.cms.presenter

import zerah.alexandre.cms.model.Article
import zerah.alexandre.cms.model.Comment


interface CommentPresenter {

    fun createComment()

    fun removeComment(id: Int, qParam: String?)

    interface View {
        fun removeComment(commentId: Int)
    }
}
