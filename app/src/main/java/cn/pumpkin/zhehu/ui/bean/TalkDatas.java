package cn.pumpkin.zhehu.ui.bean;

/**
 * @author: zhibao.Liu
 * @version:
 * @date: 2019/2/14 16:04
 * @des:
 * @see {@link }
 */

public class TalkDatas {

    private int talkType;
    private String talkTitle;
    private String talkContent;
    private String talkAuthor;
    private int talkPraises;
    private int talkComments;

    public int getTalkType() {
        return talkType;
    }

    public void setTalkType(int talkType) {
        this.talkType = talkType;
    }

    public String getTalkTitle() {
        return talkTitle;
    }

    public void setTalkTitle(String talkTitle) {
        this.talkTitle = talkTitle;
    }

    public String getTalkContent() {
        return talkContent;
    }

    public void setTalkContent(String talkContent) {
        this.talkContent = talkContent;
    }

    public String getTalkAuthor() {
        return talkAuthor;
    }

    public void setTalkAuthor(String talkAuthor) {
        this.talkAuthor = talkAuthor;
    }

    public int getTalkPraises() {
        return talkPraises;
    }

    public void setTalkPraises(int talkPraises) {
        this.talkPraises = talkPraises;
    }

    public int getTalkComments() {
        return talkComments;
    }

    public void setTalkComments(int talkComments) {
        this.talkComments = talkComments;
    }
}