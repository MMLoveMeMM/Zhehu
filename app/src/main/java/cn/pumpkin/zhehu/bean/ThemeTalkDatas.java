package cn.pumpkin.zhehu.bean;

import java.util.List;

/**
 * @author: zhibao.Liu
 * @version:
 * @date: 2018/12/4 15:36
 * @des:
 * @see {@link }
 */

public class ThemeTalkDatas {
    private String themeTitle;
    private List<TalkDatas> talkDatas;

    public String getThemeTitle() {
        return themeTitle;
    }

    public void setThemeTitle(String themeTitle) {
        this.themeTitle = themeTitle;
    }

    public List<TalkDatas> getTalkDatas() {
        return talkDatas;
    }

    public void setTalkDatas(List<TalkDatas> talkDatas) {
        this.talkDatas = talkDatas;
    }

    public void addTalkDatas(TalkDatas talkDatas){
        this.talkDatas.add(talkDatas);
    }

    public void removeAllTalkDatas(){
        this.talkDatas.clear();
    }
}
