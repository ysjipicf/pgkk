package com.pgkk.data.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by tanxueze on 2017/12/26.
 */

public class News implements Serializable {

    private String reason;
    private ResultBean result;
    private int error_code;


    @Override
    public String toString() {
        return "News{" +
                "reason='" + reason + '\'' +
                ", result=" + result +
                ", error_code=" + error_code +
                '}';
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public static class ResultBean implements Serializable {


        @Override
        public String toString() {
            return "ResultBean{" +
                    "stat='" + stat + '\'' +
                    ", data=" + data +
                    '}';
        }

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

        public static class DataBean implements Serializable {


            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;

                DataBean dataBean = (DataBean) o;

                if (uniquekey != null ? !uniquekey.equals(dataBean.uniquekey) : dataBean.uniquekey != null)
                    return false;
                if (title != null ? !title.equals(dataBean.title) : dataBean.title != null)
                    return false;
                if (date != null ? !date.equals(dataBean.date) : dataBean.date != null)
                    return false;
                if (category != null ? !category.equals(dataBean.category) : dataBean.category != null)
                    return false;
                if (author_name != null ? !author_name.equals(dataBean.author_name) : dataBean.author_name != null)
                    return false;
                if (url != null ? !url.equals(dataBean.url) : dataBean.url != null) return false;
                if (thumbnail_pic_s != null ? !thumbnail_pic_s.equals(dataBean.thumbnail_pic_s) : dataBean.thumbnail_pic_s != null)
                    return false;
                if (thumbnail_pic_s02 != null ? !thumbnail_pic_s02.equals(dataBean.thumbnail_pic_s02) : dataBean.thumbnail_pic_s02 != null)
                    return false;
                return thumbnail_pic_s03 != null ? thumbnail_pic_s03.equals(dataBean.thumbnail_pic_s03) : dataBean.thumbnail_pic_s03 == null;
            }


            @Override
            public int hashCode() {
                int result = uniquekey != null ? uniquekey.hashCode() : 0;
                result = 31 * result + (title != null ? title.hashCode() : 0);
                result = 31 * result + (date != null ? date.hashCode() : 0);
                result = 31 * result + (category != null ? category.hashCode() : 0);
                result = 31 * result + (author_name != null ? author_name.hashCode() : 0);
                result = 31 * result + (url != null ? url.hashCode() : 0);
                result = 31 * result + (thumbnail_pic_s != null ? thumbnail_pic_s.hashCode() : 0);
                result = 31 * result + (thumbnail_pic_s02 != null ? thumbnail_pic_s02.hashCode() : 0);
                result = 31 * result + (thumbnail_pic_s03 != null ? thumbnail_pic_s03.hashCode() : 0);
                return result;
            }

            @Override
            public String toString() {
                return "DataBean{" +
                        "uniquekey='" + uniquekey + '\'' +
                        ", title='" + title + '\'' +
                        ", date='" + date + '\'' +
                        ", category='" + category + '\'' +
                        ", author_name='" + author_name + '\'' +
                        ", url='" + url + '\'' +
                        ", thumbnail_pic_s='" + thumbnail_pic_s + '\'' +
                        ", thumbnail_pic_s02='" + thumbnail_pic_s02 + '\'' +
                        ", thumbnail_pic_s03='" + thumbnail_pic_s03 + '\'' +
                        '}';
            }


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
