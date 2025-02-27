package use_case.Search;

import entity.Article;
import entity.ArticleFactory;
import entity.KeywordArticleFactory;

import java.util.ArrayList;
import java.util.List;

public class SearchInteractor implements SearchInputBoundary{
    final SearchOutputBoundary searchPresenter;

    public SearchInteractor(SearchOutputBoundary searchOutputBoundary) {
        this.searchPresenter = searchOutputBoundary;
    }

    public void execute(SearchInputData searchInputData) throws InterruptedException{
        ArticleFactory articleFactory = new KeywordArticleFactory(searchInputData.getCountryName());

        // n = -1 indicates "get all articles"
        List<Article> articles = articleFactory.createArticles(-1);
        List<List<String>> output = new ArrayList<>();
        for (Article a: articles) {
            List<String> x = new ArrayList<>();
            x.add(a.getTitle());
            x.add(a.getImageUrl());
            x.add(a.getDescription());
            x.add(a.getUrl());
            x.add(a.getPublishedAt());
            x.add(a.getAuthor());
            output.add(x);
        }
        SearchOutputData searchOutputData = new SearchOutputData(output);
        searchPresenter.prepareView(searchOutputData);
    }
}
