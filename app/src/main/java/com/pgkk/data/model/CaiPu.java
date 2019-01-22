package com.pgkk.data.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by tanxueze on 2017/12/14.
 */

public class CaiPu implements Serializable {

    private String resultcode;
    private String reason;
    private ResultBean result;
    private int error_code;

    public String getResultcode() {
        return resultcode;
    }

    public void setResultcode(String resultcode) {
        this.resultcode = resultcode;
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

    public static class ResultBean {
        private String totalNum;
        private String pn;
        private String rn;
        private List<DataBean> data;

        public String getTotalNum() {
            return totalNum;
        }

        public void setTotalNum(String totalNum) {
            this.totalNum = totalNum;
        }

        public String getPn() {
            return pn;
        }

        public void setPn(String pn) {
            this.pn = pn;
        }

        public String getRn() {
            return rn;
        }

        public void setRn(String rn) {
            this.rn = rn;
        }

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public static class DataBean implements Serializable {
            private String id;
            private String title;
            private String tags;
            private String imtro;
            private String ingredients;
            private String burden;
            private List<String> albums;
            private List<StepsBean> steps;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getTags() {
                return tags;
            }

            public void setTags(String tags) {
                this.tags = tags;
            }

            public String getImtro() {
                return imtro;
            }

            public void setImtro(String imtro) {
                this.imtro = imtro;
            }

            public String getIngredients() {
                return ingredients;
            }

            public void setIngredients(String ingredients) {
                this.ingredients = ingredients;
            }

            public String getBurden() {
                return burden;
            }

            public void setBurden(String burden) {
                this.burden = burden;
            }

            public List<String> getAlbums() {
                return albums;
            }

            public void setAlbums(List<String> albums) {
                this.albums = albums;
            }

            public List<StepsBean> getSteps() {
                return steps;
            }

            public void setSteps(List<StepsBean> steps) {
                this.steps = steps;
            }

            public static class StepsBean implements Serializable {
                /**
                 * img : http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/1/18_4d28e6101f9b487f.jpg
                 * step : 1.带皮五花肉冷水下锅加入葱段、姜片花椒7、8粒，黄酒适量煮开
                 */

                private String img;
                private String step;

                public String getImg() {
                    return img;
                }

                public void setImg(String img) {
                    this.img = img;
                }

                public String getStep() {
                    return step;
                }

                public void setStep(String step) {
                    this.step = step;
                }

                @Override
                public String toString() {
                    return "StepsBean{" +
                            "img='" + img + '\'' +
                            ", step='" + step + '\'' +
                            '}';
                }
            }

            @Override
            public String toString() {
                return "DataBean{" +
                        "id='" + id + '\'' +
                        ", title='" + title + '\'' +
                        ", tags='" + tags + '\'' +
                        ", imtro='" + imtro + '\'' +
                        ", ingredients='" + ingredients + '\'' +
                        ", burden='" + burden + '\'' +
                        ", albums=" + albums +
                        ", steps=" + steps +
                        '}';
            }
        }

        @Override
        public String toString() {
            return "ResultBean{" +
                    "totalNum='" + totalNum + '\'' +
                    ", pn='" + pn + '\'' +
                    ", rn='" + rn + '\'' +
                    ", data=" + data +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "CaiPu{" +
                "resultcode='" + resultcode + '\'' +
                ", reason='" + reason + '\'' +
                ", result=" + result +
                ", error_code=" + error_code +
                '}';
    }
}
