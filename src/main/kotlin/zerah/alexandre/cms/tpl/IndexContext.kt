package zerah.alexandre.cms.tpl

import zerah.alexandre.cms.model.Article

data class IndexContext (val list:List<Article>, val session: UserSession?){
}
