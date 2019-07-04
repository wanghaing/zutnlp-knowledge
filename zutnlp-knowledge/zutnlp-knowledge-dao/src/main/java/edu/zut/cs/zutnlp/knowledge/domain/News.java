package edu.zut.cs.zutnlp.knowledge.domain;

/**
 * @author lvixn
 * @date 2019/1/4 9:43
 */
public class News {

    private String title;

    private String content;

    private String pubtime;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPubtime() {
        return pubtime;
    }

    public void setPubtime(String pubtime) {
        this.pubtime = pubtime;
    }

    public News() {

    }

    public News(String title, String content, String pubtime) {

        this.title = title;
        this.content = content;
        this.pubtime = pubtime;
    }
}
