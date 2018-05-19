package br.gov.rodacultural.rodacultural.models;

import java.io.Serializable;

/**
 * Created by Giovanne on 18/05/2018.
 */

public class FeedItem implements Serializable{
    private int id;
    private boolean isMEI;
    private String title;
    private String subTitle;
    private String content;
    private String contentImageUrl;
    private String titleImageUrl;

    public FeedItem() {
    }

    /**
     *
     * @param id
     * @param title
     * @param subTitle
     * @param content
     * @param contentImageUrl
     * @param titleImageUrl
     */
    public FeedItem(int id, String title, String subTitle, String content, String contentImageUrl, String titleImageUrl, boolean isMEI) {
        this.id = id;
        this.title = title;
        this.subTitle = subTitle;
        this.content = content;
        this.contentImageUrl = contentImageUrl;
        this.titleImageUrl = titleImageUrl;
        this.isMEI = isMEI;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public String getContent() {
        return content;
    }

    public String getContentImageUrl() {
        return contentImageUrl;
    }

    public String getTitleImageUrl() {
        return titleImageUrl;
    }

    public boolean isMEI() {
        return isMEI;
    }
}
