import com.nhaarman.mockitokotlin2.*
import org.junit.jupiter.api.Test
import zerah.alexandre.cms.model.Article
import zerah.alexandre.cms.model.Comment
import zerah.alexandre.cms.model.Model
import zerah.alexandre.cms.presenter.ArticleListPresenter
import zerah.alexandre.cms.presenter.ArticlePresenter
import zerah.alexandre.cms.presenter.CommentPresenter
import zerah.alexandre.cms.control.ArticleListPresenterImpl
import zerah.alexandre.cms.control.ArticlePresenterImpl
import zerah.alexandre.cms.control.CommentPresenterImpl
import java.util.*

internal class PresenterTests {
    private val date = Date()

    private val articles = listOf(
        Article(
            1,
            "Lacarnum Inflamare",
            "Enflamme l'objet visé. Hermione l'utilise pour mettre le feu à la cape de Rogue lors du match de quidditch"
        ),
        Article(
            2,
            "Expecto Patronum",
            "Sortilège du patronus, il fait apparaître un animal argenté appelé patronus (l'animal est différent pour chaque sorcier, ou presque). Un patronus représente une énergie positive et est le seul rempart contre les Détraqueurs. Le professeur Lupin l'apprend à Harry dans le 3e film. Dans les livres, la formule est Spero Patronum. Harry l'utilise à maintes reprises comme contre deux Détraqueurs l'ayant attaqué ainsi que son cousin Dudley dans le cinquième film ou encore contre les Détraqueurs dans Harry Potter et les Reliques de la Mort qui ont été libérés par la fin du Patronus de Dolores Ombrage lors du procès."
        )
    )

    private val comments = listOf(
        Comment(0, "blabla", 2, date.toString(), "username")
    )

    @Test
    fun testArticlesListPresenter() {
        val model = mock<Model> {
            on { getArticleList() } doReturn articles
        }
        val view = mock<ArticleListPresenter.View>()
        val presenter = ArticleListPresenterImpl(model, view)
        presenter.start()
        verify(model).getArticleList()
        verify(view).displayArticleList(articles)
        verifyNoMoreInteractions(model, view)
    }


    @Test
    fun testArticlePresenterWithoutComments() {
        val id = 1
        val article = articles.find { x -> x.id == id }
        val comments = comments.filter { x -> x.idArticle == id }
        val model = mock<Model> {
            on { getArticle(id) } doReturn article
            on { getComments(id) } doReturn comments
        }
        val view = mock<ArticlePresenter.View>()
        val presenter = ArticlePresenterImpl(model, view)
        presenter.start(id, null)
        verify(model).getArticle(id)
        verify(model).getComments(id)
        verify(view).displayArticle(article!!, comments)
        verifyNoMoreInteractions(model, view)
    }


    @Test
    fun testArticlePresenterWithComments() {
        val id = 2
        val article = articles.find { x -> x.id == id }
        val comments = comments.filter { x -> x.idArticle == id }
        val model = mock<Model> {
            on { getArticle(id) } doReturn article
            on { getComments(id) } doReturn comments
        }
        val view = mock<ArticlePresenter.View>()
        val presenter = ArticlePresenterImpl(model, view)
        presenter.start(id, null)
        verify(model).getArticle(id)
        verify(model).getComments(id)
        verify(view).displayArticle(article!!, comments)
        verifyNoMoreInteractions(model, view)
    }

    @Test
    fun testArticlePresenterWithCommentRemove() {
        val model = mock<Model> {
            on { getComment(0) } doReturn comments[0]
        }
        val view = mock<CommentPresenter.View>()
        val presenter = CommentPresenterImpl(model, view)
        /*val session = UserSession("", true)
        val mockService : UserSession = mock()
        whenever(mockService).thenReturn(session)*/
        presenter.removeComment(0, "delete")
        verify(view).removeComment(0)
    }

    @Test
    fun testArticlePresenterKO() {
        val id = -1
        val model = mock<Model> {
            on { getArticle(id) } doReturn null
            on { getComments(id) } doReturn listOf()
        }

        val view = mock<ArticlePresenter.View>()

        val presenter = ArticlePresenterImpl(model, view)
        presenter.start(id, null)

        verify(model).getArticle(id)
        verify(view).displayArticleNotFound()
        verifyNoMoreInteractions(model, view)
    }


}

 @Test
 fun testInvalideArticlePresenter() {
     val view = mock<ArticlePresenter.View>()
     val model = mock<Model> {
         on { getArticle(any()) } doReturn null
     }
     val presenter = ArticlePresenterImpl(model, view)
     presenter.start(42)

     verify(model).getArticle(42)
     verify(view).displayArticleNotFound()
     verifyNoMoreInteractions(model, view)
 }


 @Test
 fun testArticleListPresenter() {

     val list = listOf(Article(0, "1", "Z"))

     val view = mock<ArticleListPresenter.View>()
     val model = mock<Model> {
         on { getArticleList() } doReturn list
     }
     val presenter = ArticleListPresenterImpl(model, view)
     presenter.start()

     verify(model).getArticleList()
     verify(view).displayArticleList(list)
     verifyNoMoreInteractions(model, view)



         var displayCalled = false
         val model = object : Model{
         override fun createArticle(title: String?, text: String?): String {
             TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
         }

         override fun getArticle(id: Int): Article? = throw IllegalStateException()

         override fun getArticleList(): List<Article> = listOf(
             Article(1,"in","coucou")
         )
     }



     val view = object : ArticleListPresenter.View {
         override fun displayArticleList(list: List<Article>) {
             displayCalled = true
             inOrder(a,b) {
                 verify(a).doSomething()
                 verify(b).doSomething()
             }
             assertEquals(2, list.size)
             assertEquals("in", list[0].title)
         }

     }


 }
}
