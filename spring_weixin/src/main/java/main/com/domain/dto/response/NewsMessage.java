package main.com.domain.dto.response;

import java.util.List;

/**
 * Created by Administrator on 2016/7/30 0030.
 * 响应 图文消息
 */
public class NewsMessage extends BaseMessage {
    //消息个数，限制为10条以内
    private int ArticleCount;
    //多条图文消息，默认第一个item为大图
    private List<Article> Articles;

    public int getArticleCount() {
        return ArticleCount;
    }

    public void setArticleCount(int articleCount) {
        ArticleCount = articleCount;
    }

    public List<Article> getArticles() {
        return Articles;
    }

    public void setArticles(List<Article> articles) {
        Articles = articles;
    }
}
