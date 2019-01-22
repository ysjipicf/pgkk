package com.pgkk.data.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by tanxueze on 2018/1/10.
 */

public class Video  implements Serializable{

    /**

     * count : 15
     * total : 0
     * nextPageUrl : https://baobab.kaiyanapp.com/api/v4/tabs/selected?date=1515373200000&num=2&page=2
     * adExist : false
     * date : 1515546000000
     * nextPublishTime : 1515632400000
     * dialog : null
     * topIssue : null
     * refreshCount : 0
     * lastStartId : 0
     */

    private int count;
    private int total;
    private String nextPageUrl;
    private boolean adExist;
    private long date;
    private long nextPublishTime;
    private Object dialog;
    private Object topIssue;
    private int refreshCount;
    private int lastStartId;
    private List<ItemListBean> itemList;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getNextPageUrl() {
        return nextPageUrl;
    }

    public void setNextPageUrl(String nextPageUrl) {
        this.nextPageUrl = nextPageUrl;
    }

    public boolean isAdExist() {
        return adExist;
    }

    public void setAdExist(boolean adExist) {
        this.adExist = adExist;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public long getNextPublishTime() {
        return nextPublishTime;
    }

    public void setNextPublishTime(long nextPublishTime) {
        this.nextPublishTime = nextPublishTime;
    }

    public Object getDialog() {
        return dialog;
    }

    public void setDialog(Object dialog) {
        this.dialog = dialog;
    }

    public Object getTopIssue() {
        return topIssue;
    }

    public void setTopIssue(Object topIssue) {
        this.topIssue = topIssue;
    }

    public int getRefreshCount() {
        return refreshCount;
    }

    public void setRefreshCount(int refreshCount) {
        this.refreshCount = refreshCount;
    }

    public int getLastStartId() {
        return lastStartId;
    }

    public void setLastStartId(int lastStartId) {
        this.lastStartId = lastStartId;
    }

    public List<ItemListBean> getItemList() {
        return itemList;
    }

    public void setItemList(List<ItemListBean> itemList) {
        this.itemList = itemList;
    }

    public static class ItemListBean  implements  Serializable{

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            ItemListBean that = (ItemListBean) o;

            if (id != that.id) return false;
            if (adIndex != that.adIndex) return false;
            if (type != null ? !type.equals(that.type) : that.type != null) return false;
            if (data != null ? !data.equals(that.data) : that.data != null) return false;
            return tag != null ? tag.equals(that.tag) : that.tag == null;
        }

        @Override
        public int hashCode() {
            int result = type != null ? type.hashCode() : 0;
            result = 31 * result + (data != null ? data.hashCode() : 0);
            result = 31 * result + (tag != null ? tag.hashCode() : 0);
            result = 31 * result + id;
            result = 31 * result + adIndex;
            return result;
        }

        /**

         * tag : 0
         * id : 0
         * adIndex : -1
         */

        private String type;
        private DataBean data;
        private String tag;
        private int id;
        private int adIndex;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public DataBean getData() {
            return data;
        }

        public void setData(DataBean data) {
            this.data = data;
        }

        public String getTag() {
            return tag;
        }

        public void setTag(String tag) {
            this.tag = tag;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getAdIndex() {
            return adIndex;
        }

        public void setAdIndex(int adIndex) {
            this.adIndex = adIndex;
        }

        public static class DataBean  implements Serializable{

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;

                DataBean dataBean = (DataBean) o;

                if (id != dataBean.id) return false;
                if (duration != dataBean.duration) return false;
                if (releaseTime != dataBean.releaseTime) return false;
                if (idx != dataBean.idx) return false;
                if (date != dataBean.date) return false;
                if (collected != dataBean.collected) return false;
                if (played != dataBean.played) return false;
                if (dataType != null ? !dataType.equals(dataBean.dataType) : dataBean.dataType != null)
                    return false;
                if (title != null ? !title.equals(dataBean.title) : dataBean.title != null)
                    return false;
                if (slogan != null ? !slogan.equals(dataBean.slogan) : dataBean.slogan != null)
                    return false;
                if (description != null ? !description.equals(dataBean.description) : dataBean.description != null)
                    return false;
                if (provider != null ? !provider.equals(dataBean.provider) : dataBean.provider != null)
                    return false;
                if (category != null ? !category.equals(dataBean.category) : dataBean.category != null)
                    return false;
                if (author != null ? !author.equals(dataBean.author) : dataBean.author != null)
                    return false;
                if (cover != null ? !cover.equals(dataBean.cover) : dataBean.cover != null)
                    return false;
                if (playUrl != null ? !playUrl.equals(dataBean.playUrl) : dataBean.playUrl != null)
                    return false;
                if (thumbPlayUrl != null ? !thumbPlayUrl.equals(dataBean.thumbPlayUrl) : dataBean.thumbPlayUrl != null)
                    return false;
                if (webUrl != null ? !webUrl.equals(dataBean.webUrl) : dataBean.webUrl != null)
                    return false;
                if (library != null ? !library.equals(dataBean.library) : dataBean.library != null)
                    return false;
                if (consumption != null ? !consumption.equals(dataBean.consumption) : dataBean.consumption != null)
                    return false;
                if (campaign != null ? !campaign.equals(dataBean.campaign) : dataBean.campaign != null)
                    return false;
                if (waterMarks != null ? !waterMarks.equals(dataBean.waterMarks) : dataBean.waterMarks != null)
                    return false;
                if (adTrack != null ? !adTrack.equals(dataBean.adTrack) : dataBean.adTrack != null)
                    return false;
                if (type != null ? !type.equals(dataBean.type) : dataBean.type != null)
                    return false;
                if (titlePgc != null ? !titlePgc.equals(dataBean.titlePgc) : dataBean.titlePgc != null)
                    return false;
                if (descriptionPgc != null ? !descriptionPgc.equals(dataBean.descriptionPgc) : dataBean.descriptionPgc != null)
                    return false;
                if (remark != null ? !remark.equals(dataBean.remark) : dataBean.remark != null)
                    return false;
                if (shareAdTrack != null ? !shareAdTrack.equals(dataBean.shareAdTrack) : dataBean.shareAdTrack != null)
                    return false;
                if (favoriteAdTrack != null ? !favoriteAdTrack.equals(dataBean.favoriteAdTrack) : dataBean.favoriteAdTrack != null)
                    return false;
                if (webAdTrack != null ? !webAdTrack.equals(dataBean.webAdTrack) : dataBean.webAdTrack != null)
                    return false;
                if (promotion != null ? !promotion.equals(dataBean.promotion) : dataBean.promotion != null)
                    return false;
                if (label != null ? !label.equals(dataBean.label) : dataBean.label != null)
                    return false;
                if (descriptionEditor != null ? !descriptionEditor.equals(dataBean.descriptionEditor) : dataBean.descriptionEditor != null)
                    return false;
                if (lastViewTime != null ? !lastViewTime.equals(dataBean.lastViewTime) : dataBean.lastViewTime != null)
                    return false;
                if (playlists != null ? !playlists.equals(dataBean.playlists) : dataBean.playlists != null)
                    return false;
                if (src != null ? !src.equals(dataBean.src) : dataBean.src != null) return false;
                if (playInfo != null ? !playInfo.equals(dataBean.playInfo) : dataBean.playInfo != null)
                    return false;
                if (tags != null ? !tags.equals(dataBean.tags) : dataBean.tags != null)
                    return false;
                if (labelList != null ? !labelList.equals(dataBean.labelList) : dataBean.labelList != null)
                    return false;
                return subtitles != null ? subtitles.equals(dataBean.subtitles) : dataBean.subtitles == null;
            }

            @Override
            public int hashCode() {
                int result = dataType != null ? dataType.hashCode() : 0;
                result = 31 * result + id;
                result = 31 * result + (title != null ? title.hashCode() : 0);
                result = 31 * result + (slogan != null ? slogan.hashCode() : 0);
                result = 31 * result + (description != null ? description.hashCode() : 0);
                result = 31 * result + (provider != null ? provider.hashCode() : 0);
                result = 31 * result + (category != null ? category.hashCode() : 0);
                result = 31 * result + (author != null ? author.hashCode() : 0);
                result = 31 * result + (cover != null ? cover.hashCode() : 0);
                result = 31 * result + (playUrl != null ? playUrl.hashCode() : 0);
                result = 31 * result + (thumbPlayUrl != null ? thumbPlayUrl.hashCode() : 0);
                result = 31 * result + duration;
                result = 31 * result + (webUrl != null ? webUrl.hashCode() : 0);
                result = 31 * result + (int) (releaseTime ^ (releaseTime >>> 32));
                result = 31 * result + (library != null ? library.hashCode() : 0);
                result = 31 * result + (consumption != null ? consumption.hashCode() : 0);
                result = 31 * result + (campaign != null ? campaign.hashCode() : 0);
                result = 31 * result + (waterMarks != null ? waterMarks.hashCode() : 0);
                result = 31 * result + (adTrack != null ? adTrack.hashCode() : 0);
                result = 31 * result + (type != null ? type.hashCode() : 0);
                result = 31 * result + (titlePgc != null ? titlePgc.hashCode() : 0);
                result = 31 * result + (descriptionPgc != null ? descriptionPgc.hashCode() : 0);
                result = 31 * result + (remark != null ? remark.hashCode() : 0);
                result = 31 * result + idx;
                result = 31 * result + (shareAdTrack != null ? shareAdTrack.hashCode() : 0);
                result = 31 * result + (favoriteAdTrack != null ? favoriteAdTrack.hashCode() : 0);
                result = 31 * result + (webAdTrack != null ? webAdTrack.hashCode() : 0);
                result = 31 * result + (int) (date ^ (date >>> 32));
                result = 31 * result + (promotion != null ? promotion.hashCode() : 0);
                result = 31 * result + (label != null ? label.hashCode() : 0);
                result = 31 * result + (descriptionEditor != null ? descriptionEditor.hashCode() : 0);
                result = 31 * result + (collected ? 1 : 0);
                result = 31 * result + (played ? 1 : 0);
                result = 31 * result + (lastViewTime != null ? lastViewTime.hashCode() : 0);
                result = 31 * result + (playlists != null ? playlists.hashCode() : 0);
                result = 31 * result + (src != null ? src.hashCode() : 0);
                result = 31 * result + (playInfo != null ? playInfo.hashCode() : 0);
                result = 31 * result + (tags != null ? tags.hashCode() : 0);
                result = 31 * result + (labelList != null ? labelList.hashCode() : 0);
                result = 31 * result + (subtitles != null ? subtitles.hashCode() : 0);
                return result;
            }

            /**

             */

            private String dataType;
            private int id;
            private String title;
            private String slogan;
            private String description;
            private ProviderBean provider;
            private String category;
            private AuthorBean author;
            private CoverBean cover;
            private String playUrl;
            private Object thumbPlayUrl;
            private int duration;
            private WebUrlBean webUrl;
            private long releaseTime;
            private String library;
            private ConsumptionBean consumption;
            private Object campaign;
            private Object waterMarks;
            private Object adTrack;
            private String type;
            private Object titlePgc;
            private Object descriptionPgc;
            private Object remark;
            private int idx;
            private Object shareAdTrack;
            private Object favoriteAdTrack;
            private Object webAdTrack;
            private long date;
            private Object promotion;
            private Object label;
            private String descriptionEditor;
            private boolean collected;
            private boolean played;
            private Object lastViewTime;
            private Object playlists;
            private Object src;
            private List<PlayInfoBean> playInfo;
            private List<TagsBean> tags;
            private List<?> labelList;
            private List<?> subtitles;

            public String getDataType() {
                return dataType;
            }

            public void setDataType(String dataType) {
                this.dataType = dataType;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getSlogan() {
                return slogan;
            }

            public void setSlogan(String slogan) {
                this.slogan = slogan;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public ProviderBean getProvider() {
                return provider;
            }

            public void setProvider(ProviderBean provider) {
                this.provider = provider;
            }

            public String getCategory() {
                return category;
            }

            public void setCategory(String category) {
                this.category = category;
            }

            public AuthorBean getAuthor() {
                return author;
            }

            public void setAuthor(AuthorBean author) {
                this.author = author;
            }

            public CoverBean getCover() {
                return cover;
            }

            public void setCover(CoverBean cover) {
                this.cover = cover;
            }

            public String getPlayUrl() {
                return playUrl;
            }

            public void setPlayUrl(String playUrl) {
                this.playUrl = playUrl;
            }

            public Object getThumbPlayUrl() {
                return thumbPlayUrl;
            }

            public void setThumbPlayUrl(Object thumbPlayUrl) {
                this.thumbPlayUrl = thumbPlayUrl;
            }

            public int getDuration() {
                return duration;
            }

            public void setDuration(int duration) {
                this.duration = duration;
            }

            public WebUrlBean getWebUrl() {
                return webUrl;
            }

            public void setWebUrl(WebUrlBean webUrl) {
                this.webUrl = webUrl;
            }

            public long getReleaseTime() {
                return releaseTime;
            }

            public void setReleaseTime(long releaseTime) {
                this.releaseTime = releaseTime;
            }

            public String getLibrary() {
                return library;
            }

            public void setLibrary(String library) {
                this.library = library;
            }

            public ConsumptionBean getConsumption() {
                return consumption;
            }

            public void setConsumption(ConsumptionBean consumption) {
                this.consumption = consumption;
            }

            public Object getCampaign() {
                return campaign;
            }

            public void setCampaign(Object campaign) {
                this.campaign = campaign;
            }

            public Object getWaterMarks() {
                return waterMarks;
            }

            public void setWaterMarks(Object waterMarks) {
                this.waterMarks = waterMarks;
            }

            public Object getAdTrack() {
                return adTrack;
            }

            public void setAdTrack(Object adTrack) {
                this.adTrack = adTrack;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public Object getTitlePgc() {
                return titlePgc;
            }

            public void setTitlePgc(Object titlePgc) {
                this.titlePgc = titlePgc;
            }

            public Object getDescriptionPgc() {
                return descriptionPgc;
            }

            public void setDescriptionPgc(Object descriptionPgc) {
                this.descriptionPgc = descriptionPgc;
            }

            public Object getRemark() {
                return remark;
            }

            public void setRemark(Object remark) {
                this.remark = remark;
            }

            public int getIdx() {
                return idx;
            }

            public void setIdx(int idx) {
                this.idx = idx;
            }

            public Object getShareAdTrack() {
                return shareAdTrack;
            }

            public void setShareAdTrack(Object shareAdTrack) {
                this.shareAdTrack = shareAdTrack;
            }

            public Object getFavoriteAdTrack() {
                return favoriteAdTrack;
            }

            public void setFavoriteAdTrack(Object favoriteAdTrack) {
                this.favoriteAdTrack = favoriteAdTrack;
            }

            public Object getWebAdTrack() {
                return webAdTrack;
            }

            public void setWebAdTrack(Object webAdTrack) {
                this.webAdTrack = webAdTrack;
            }

            public long getDate() {
                return date;
            }

            public void setDate(long date) {
                this.date = date;
            }

            public Object getPromotion() {
                return promotion;
            }

            public void setPromotion(Object promotion) {
                this.promotion = promotion;
            }

            public Object getLabel() {
                return label;
            }

            public void setLabel(Object label) {
                this.label = label;
            }

            public String getDescriptionEditor() {
                return descriptionEditor;
            }

            public void setDescriptionEditor(String descriptionEditor) {
                this.descriptionEditor = descriptionEditor;
            }

            public boolean isCollected() {
                return collected;
            }

            public void setCollected(boolean collected) {
                this.collected = collected;
            }

            public boolean isPlayed() {
                return played;
            }

            public void setPlayed(boolean played) {
                this.played = played;
            }

            public Object getLastViewTime() {
                return lastViewTime;
            }

            public void setLastViewTime(Object lastViewTime) {
                this.lastViewTime = lastViewTime;
            }

            public Object getPlaylists() {
                return playlists;
            }

            public void setPlaylists(Object playlists) {
                this.playlists = playlists;
            }

            public Object getSrc() {
                return src;
            }

            public void setSrc(Object src) {
                this.src = src;
            }

            public List<PlayInfoBean> getPlayInfo() {
                return playInfo;
            }

            public void setPlayInfo(List<PlayInfoBean> playInfo) {
                this.playInfo = playInfo;
            }

            public List<TagsBean> getTags() {
                return tags;
            }

            public void setTags(List<TagsBean> tags) {
                this.tags = tags;
            }

            public List<?> getLabelList() {
                return labelList;
            }

            public void setLabelList(List<?> labelList) {
                this.labelList = labelList;
            }

            public List<?> getSubtitles() {
                return subtitles;
            }

            public void setSubtitles(List<?> subtitles) {
                this.subtitles = subtitles;
            }

            public static class ProviderBean implements Serializable {
                /**
                 * name : YouTube
                 * alias : youtube
                 * icon : http://img.kaiyanapp.com/fa20228bc5b921e837156923a58713f6.png
                 */

                private String name;
                private String alias;
                private String icon;

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getAlias() {
                    return alias;
                }

                public void setAlias(String alias) {
                    this.alias = alias;
                }

                public String getIcon() {
                    return icon;
                }

                public void setIcon(String icon) {
                    this.icon = icon;
                }
            }

            public static class AuthorBean implements Serializable {
                /**
                 * id : 2162
                 * icon : http://img.kaiyanapp.com/98beab66d3885a139b54f21e91817c4f.jpeg
                 * name : 开眼广告精选
                 * description : 为广告人的精彩创意点赞
                 * link :
                 * latestReleaseTime : 1515546000000
                 * videoNum : 835
                 * adTrack : null
                 * follow : {"itemType":"author","itemId":2162,"followed":false}
                 * shield : {"itemType":"author","itemId":2162,"shielded":false}
                 * approvedNotReadyVideoCount : 0
                 * ifPgc : true
                 */

                private int id;
                private String icon;
                private String name;
                private String description;
                private String link;
                private long latestReleaseTime;
                private int videoNum;
                private Object adTrack;
                private FollowBean follow;
                private ShieldBean shield;
                private int approvedNotReadyVideoCount;
                private boolean ifPgc;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getIcon() {
                    return icon;
                }

                public void setIcon(String icon) {
                    this.icon = icon;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getDescription() {
                    return description;
                }

                public void setDescription(String description) {
                    this.description = description;
                }

                public String getLink() {
                    return link;
                }

                public void setLink(String link) {
                    this.link = link;
                }

                public long getLatestReleaseTime() {
                    return latestReleaseTime;
                }

                public void setLatestReleaseTime(long latestReleaseTime) {
                    this.latestReleaseTime = latestReleaseTime;
                }

                public int getVideoNum() {
                    return videoNum;
                }

                public void setVideoNum(int videoNum) {
                    this.videoNum = videoNum;
                }

                public Object getAdTrack() {
                    return adTrack;
                }

                public void setAdTrack(Object adTrack) {
                    this.adTrack = adTrack;
                }

                public FollowBean getFollow() {
                    return follow;
                }

                public void setFollow(FollowBean follow) {
                    this.follow = follow;
                }

                public ShieldBean getShield() {
                    return shield;
                }

                public void setShield(ShieldBean shield) {
                    this.shield = shield;
                }

                public int getApprovedNotReadyVideoCount() {
                    return approvedNotReadyVideoCount;
                }

                public void setApprovedNotReadyVideoCount(int approvedNotReadyVideoCount) {
                    this.approvedNotReadyVideoCount = approvedNotReadyVideoCount;
                }

                public boolean isIfPgc() {
                    return ifPgc;
                }

                public void setIfPgc(boolean ifPgc) {
                    this.ifPgc = ifPgc;
                }

                public static class FollowBean implements Serializable{
                    /**
                     * itemType : author
                     * itemId : 2162
                     * followed : false
                     */

                    private String itemType;
                    private int itemId;
                    private boolean followed;

                    public String getItemType() {
                        return itemType;
                    }

                    public void setItemType(String itemType) {
                        this.itemType = itemType;
                    }

                    public int getItemId() {
                        return itemId;
                    }

                    public void setItemId(int itemId) {
                        this.itemId = itemId;
                    }

                    public boolean isFollowed() {
                        return followed;
                    }

                    public void setFollowed(boolean followed) {
                        this.followed = followed;
                    }
                }

                public static class ShieldBean  implements Serializable{
                    /**
                     * itemType : author
                     * itemId : 2162
                     * shielded : false
                     */

                    private String itemType;
                    private int itemId;
                    private boolean shielded;

                    public String getItemType() {
                        return itemType;
                    }

                    public void setItemType(String itemType) {
                        this.itemType = itemType;
                    }

                    public int getItemId() {
                        return itemId;
                    }

                    public void setItemId(int itemId) {
                        this.itemId = itemId;
                    }

                    public boolean isShielded() {
                        return shielded;
                    }

                    public void setShielded(boolean shielded) {
                        this.shielded = shielded;
                    }
                }
            }

            public static class CoverBean  implements  Serializable{
                /**
                 * feed : http://img.kaiyanapp.com/92de9dada56fa5e22a942695c4b160c5.jpeg?imageMogr2/quality/60/format/jpg
                 * detail : http://img.kaiyanapp.com/92de9dada56fa5e22a942695c4b160c5.jpeg?imageMogr2/quality/60/format/jpg
                 * blurred : http://img.kaiyanapp.com/8937608ee54b8d218f92bd76ffb3aa39.jpeg?imageMogr2/quality/60/format/jpg
                 * sharing : null
                 * homepage : http://img.kaiyanapp.com/92de9dada56fa5e22a942695c4b160c5.jpeg?imageView2/1/w/720/h/560/format/jpg/q/75|watermark/1/image/aHR0cDovL2ltZy5rYWl5YW5hcHAuY29tL2JsYWNrXzMwLnBuZw==/dissolve/100/gravity/Center/dx/0/dy/0|imageslim
                 */

                private String feed;
                private String detail;
                private String blurred;
                private Object sharing;
                private String homepage;

                public String getFeed() {
                    return feed;
                }

                public void setFeed(String feed) {
                    this.feed = feed;
                }

                public String getDetail() {
                    return detail;
                }

                public void setDetail(String detail) {
                    this.detail = detail;
                }

                public String getBlurred() {
                    return blurred;
                }

                public void setBlurred(String blurred) {
                    this.blurred = blurred;
                }

                public Object getSharing() {
                    return sharing;
                }

                public void setSharing(Object sharing) {
                    this.sharing = sharing;
                }

                public String getHomepage() {
                    return homepage;
                }

                public void setHomepage(String homepage) {
                    this.homepage = homepage;
                }
            }

            public static class WebUrlBean  implements Serializable{
                /**
                 * raw : http://www.eyepetizer.net/detail.html?vid=71520
                 * forWeibo : http://www.eyepetizer.net/detail.html?vid=71520
                 */

                private String raw;
                private String forWeibo;

                public String getRaw() {
                    return raw;
                }

                public void setRaw(String raw) {
                    this.raw = raw;
                }

                public String getForWeibo() {
                    return forWeibo;
                }

                public void setForWeibo(String forWeibo) {
                    this.forWeibo = forWeibo;
                }
            }

            public static class ConsumptionBean implements Serializable {
                /**
                 * collectionCount : 503
                 * shareCount : 724
                 * replyCount : 14
                 */

                private int collectionCount;
                private int shareCount;
                private int replyCount;

                public int getCollectionCount() {
                    return collectionCount;
                }

                public void setCollectionCount(int collectionCount) {
                    this.collectionCount = collectionCount;
                }

                public int getShareCount() {
                    return shareCount;
                }

                public void setShareCount(int shareCount) {
                    this.shareCount = shareCount;
                }

                public int getReplyCount() {
                    return replyCount;
                }

                public void setReplyCount(int replyCount) {
                    this.replyCount = replyCount;
                }
            }

            public static class PlayInfoBean implements Serializable{


                private int height;
                private int width;
                private String name;
                private String type;
                private String url;
                private List<UrlListBean> urlList;

                public int getHeight() {
                    return height;
                }

                public void setHeight(int height) {
                    this.height = height;
                }

                public int getWidth() {
                    return width;
                }

                public void setWidth(int width) {
                    this.width = width;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getType() {
                    return type;
                }

                public void setType(String type) {
                    this.type = type;
                }

                public String getUrl() {
                    return url;
                }

                public void setUrl(String url) {
                    this.url = url;
                }

                public List<UrlListBean> getUrlList() {
                    return urlList;
                }

                public void setUrlList(List<UrlListBean> urlList) {
                    this.urlList = urlList;
                }

                public static class UrlListBean implements Serializable{


                    private String name;
                    private String url;
                    private int size;

                    public String getName() {
                        return name;
                    }

                    public void setName(String name) {
                        this.name = name;
                    }

                    public String getUrl() {
                        return url;
                    }

                    public void setUrl(String url) {
                        this.url = url;
                    }

                    public int getSize() {
                        return size;
                    }

                    public void setSize(int size) {
                        this.size = size;
                    }
                }
            }

            public static class TagsBean implements Serializable{


                private int id;
                private String name;
                private String actionUrl;
                private Object adTrack;
                private Object desc;
                private String bgPicture;
                private String headerImage;
                private String tagRecType;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getActionUrl() {
                    return actionUrl;
                }

                public void setActionUrl(String actionUrl) {
                    this.actionUrl = actionUrl;
                }

                public Object getAdTrack() {
                    return adTrack;
                }

                public void setAdTrack(Object adTrack) {
                    this.adTrack = adTrack;
                }

                public Object getDesc() {
                    return desc;
                }

                public void setDesc(Object desc) {
                    this.desc = desc;
                }

                public String getBgPicture() {
                    return bgPicture;
                }

                public void setBgPicture(String bgPicture) {
                    this.bgPicture = bgPicture;
                }

                public String getHeaderImage() {
                    return headerImage;
                }

                public void setHeaderImage(String headerImage) {
                    this.headerImage = headerImage;
                }

                public String getTagRecType() {
                    return tagRecType;
                }

                public void setTagRecType(String tagRecType) {
                    this.tagRecType = tagRecType;
                }
            }
        }
    }
}
