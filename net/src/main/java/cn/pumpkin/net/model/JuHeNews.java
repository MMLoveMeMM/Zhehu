package cn.pumpkin.net.model;

import java.util.List;

/**
 * @author: zhibao.Liu
 * @version:
 * @date: 2018/12/11 14:04
 * @des:
 * @see {@link }
 */

public class JuHeNews extends BaseResult{

    private ResultBean result;

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * stat : 1
         * data : [{"uniquekey":"6f9cc3cf2ac80b33480e5e39dccedda7","title":"赚回转会费！今晨阿利松欧冠关键一扑为利物浦带来至少1130万欧！","date":"2018-12-12 11:32","category":"头条","author_name":"球叮足球","url":"http://mini.eastday.com/mobile/181212113215672.html","thumbnail_pic_s":"http://00imgmini.eastday.com/mobile/20181212/20181212113215_7c84725a70e9e1aa098275b74ae8d93a_3_mwpm_03200403.jpg","thumbnail_pic_s02":"http://00imgmini.eastday.com/mobile/20181212/20181212113215_7c84725a70e9e1aa098275b74ae8d93a_4_mwpm_03200403.jpg","thumbnail_pic_s03":"http://00imgmini.eastday.com/mobile/20181212/20181212113215_7c84725a70e9e1aa098275b74ae8d93a_7_mwpm_03200403.jpg"}]
         */

        private String stat;
        private List<DataBean> data;

        public String getStat() {
            return stat;
        }

        public void setStat(String stat) {
            this.stat = stat;
        }

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public static class DataBean {
            /**
             * uniquekey : 6f9cc3cf2ac80b33480e5e39dccedda7
             * title : 赚回转会费！今晨阿利松欧冠关键一扑为利物浦带来至少1130万欧！
             * date : 2018-12-12 11:32
             * category : 头条
             * author_name : 球叮足球
             * url : http://mini.eastday.com/mobile/181212113215672.html
             * thumbnail_pic_s : http://00imgmini.eastday.com/mobile/20181212/20181212113215_7c84725a70e9e1aa098275b74ae8d93a_3_mwpm_03200403.jpg
             * thumbnail_pic_s02 : http://00imgmini.eastday.com/mobile/20181212/20181212113215_7c84725a70e9e1aa098275b74ae8d93a_4_mwpm_03200403.jpg
             * thumbnail_pic_s03 : http://00imgmini.eastday.com/mobile/20181212/20181212113215_7c84725a70e9e1aa098275b74ae8d93a_7_mwpm_03200403.jpg
             */

            private String uniquekey;
            private String title;
            private String date;
            private String category;
            private String author_name;
            private String url;
            private String thumbnail_pic_s;
            private String thumbnail_pic_s02;
            private String thumbnail_pic_s03;

            public String getUniquekey() {
                return uniquekey;
            }

            public void setUniquekey(String uniquekey) {
                this.uniquekey = uniquekey;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public String getCategory() {
                return category;
            }

            public void setCategory(String category) {
                this.category = category;
            }

            public String getAuthor_name() {
                return author_name;
            }

            public void setAuthor_name(String author_name) {
                this.author_name = author_name;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getThumbnail_pic_s() {
                return thumbnail_pic_s;
            }

            public void setThumbnail_pic_s(String thumbnail_pic_s) {
                this.thumbnail_pic_s = thumbnail_pic_s;
            }

            public String getThumbnail_pic_s02() {
                return thumbnail_pic_s02;
            }

            public void setThumbnail_pic_s02(String thumbnail_pic_s02) {
                this.thumbnail_pic_s02 = thumbnail_pic_s02;
            }

            public String getThumbnail_pic_s03() {
                return thumbnail_pic_s03;
            }

            public void setThumbnail_pic_s03(String thumbnail_pic_s03) {
                this.thumbnail_pic_s03 = thumbnail_pic_s03;
            }
        }
    }
}
