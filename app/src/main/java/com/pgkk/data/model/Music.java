package com.pgkk.data.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by tanxueze on 2018/1/11.
 *
 * "http://www.wawa.fm:9090/wawa/api.php/magazine/";
 */

public class Music  implements Serializable{

    private String mid;
    private String mcode;
    private String resourcecode;
    private String mnum;
    private String mname;
    private String mdesc;
    private String mphoto;
    private String status;
    private Object issuedate;
    private String field2;
    private String ophoto;
    private Object create_by;
    private String create_date;
    private Object update_by;
    private String update_date;
    private Object remarks;
    private String del_flag;
    private String hits;
    private String thumbnail_url;
    private String nshow;
    private String publish_time;
    private String create_time;
    private String comment_count;
    private String play_count;
    private String total_count;
    private List<TracksBean> tracks;

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public String getMcode() {
        return mcode;
    }

    public void setMcode(String mcode) {
        this.mcode = mcode;
    }

    public String getResourcecode() {
        return resourcecode;
    }

    public void setResourcecode(String resourcecode) {
        this.resourcecode = resourcecode;
    }

    public String getMnum() {
        return mnum;
    }

    public void setMnum(String mnum) {
        this.mnum = mnum;
    }

    public String getMname() {
        return mname;
    }

    public void setMname(String mname) {
        this.mname = mname;
    }

    public String getMdesc() {
        return mdesc;
    }

    public void setMdesc(String mdesc) {
        this.mdesc = mdesc;
    }

    public String getMphoto() {
        return mphoto;
    }

    public void setMphoto(String mphoto) {
        this.mphoto = mphoto;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getIssuedate() {
        return issuedate;
    }

    public void setIssuedate(Object issuedate) {
        this.issuedate = issuedate;
    }

    public String getField2() {
        return field2;
    }

    public void setField2(String field2) {
        this.field2 = field2;
    }

    public String getOphoto() {
        return ophoto;
    }

    public void setOphoto(String ophoto) {
        this.ophoto = ophoto;
    }

    public Object getCreate_by() {
        return create_by;
    }

    public void setCreate_by(Object create_by) {
        this.create_by = create_by;
    }

    public String getCreate_date() {
        return create_date;
    }

    public void setCreate_date(String create_date) {
        this.create_date = create_date;
    }

    public Object getUpdate_by() {
        return update_by;
    }

    public void setUpdate_by(Object update_by) {
        this.update_by = update_by;
    }

    public String getUpdate_date() {
        return update_date;
    }

    public void setUpdate_date(String update_date) {
        this.update_date = update_date;
    }

    public Object getRemarks() {
        return remarks;
    }

    public void setRemarks(Object remarks) {
        this.remarks = remarks;
    }

    public String getDel_flag() {
        return del_flag;
    }

    public void setDel_flag(String del_flag) {
        this.del_flag = del_flag;
    }

    public String getHits() {
        return hits;
    }

    public void setHits(String hits) {
        this.hits = hits;
    }

    public String getThumbnail_url() {
        return thumbnail_url;
    }

    public void setThumbnail_url(String thumbnail_url) {
        this.thumbnail_url = thumbnail_url;
    }

    public String getNshow() {
        return nshow;
    }

    public void setNshow(String nshow) {
        this.nshow = nshow;
    }

    public String getPublish_time() {
        return publish_time;
    }

    public void setPublish_time(String publish_time) {
        this.publish_time = publish_time;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getComment_count() {
        return comment_count;
    }

    public void setComment_count(String comment_count) {
        this.comment_count = comment_count;
    }

    public String getPlay_count() {
        return play_count;
    }

    public void setPlay_count(String play_count) {
        this.play_count = play_count;
    }

    public String getTotal_count() {
        return total_count;
    }

    public void setTotal_count(String total_count) {
        this.total_count = total_count;
    }

    public List<TracksBean> getTracks() {
        return tracks;
    }

    public void setTracks(List<TracksBean> tracks) {
        this.tracks = tracks;
    }

    public static class TracksBean  implements Serializable{


        private String id;
        private String resourcecode;
        private String songer;
        private String songname;
        private Object songalbum;
        private String filename;
        private String songphoto;
        private String remarks;
        private String del_flag;
        private Object create_by;
        private String create_date;
        private Object update_by;
        private String update_date;
        private String filename192;
        private String filename320;
        private String time;
        private String thumbnail_url;
        private String fsize;
        private Object ggid;
        private Object ypid;
        private String mtype;
        private String uid;
        private Object lyrics;
        private String status;
        private String flag;
        private Object note;
        private String create_time;
        private Object update_time;
        private String publish_time;
        private String vol_id;
        private Object musician_id;
        private Object banner_id;
        private String fee_tag;
        private Object cmcc_mid;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getResourcecode() {
            return resourcecode;
        }

        public void setResourcecode(String resourcecode) {
            this.resourcecode = resourcecode;
        }

        public String getSonger() {
            return songer;
        }

        public void setSonger(String songer) {
            this.songer = songer;
        }

        public String getSongname() {
            return songname;
        }

        public void setSongname(String songname) {
            this.songname = songname;
        }

        public Object getSongalbum() {
            return songalbum;
        }

        public void setSongalbum(Object songalbum) {
            this.songalbum = songalbum;
        }

        public String getFilename() {
            return filename;
        }

        public void setFilename(String filename) {
            this.filename = filename;
        }

        public String getSongphoto() {
            return songphoto;
        }

        public void setSongphoto(String songphoto) {
            this.songphoto = songphoto;
        }

        public String getRemarks() {
            return remarks;
        }

        public void setRemarks(String remarks) {
            this.remarks = remarks;
        }

        public String getDel_flag() {
            return del_flag;
        }

        public void setDel_flag(String del_flag) {
            this.del_flag = del_flag;
        }

        public Object getCreate_by() {
            return create_by;
        }

        public void setCreate_by(Object create_by) {
            this.create_by = create_by;
        }

        public String getCreate_date() {
            return create_date;
        }

        public void setCreate_date(String create_date) {
            this.create_date = create_date;
        }

        public Object getUpdate_by() {
            return update_by;
        }

        public void setUpdate_by(Object update_by) {
            this.update_by = update_by;
        }

        public String getUpdate_date() {
            return update_date;
        }

        public void setUpdate_date(String update_date) {
            this.update_date = update_date;
        }

        public String getFilename192() {
            return filename192;
        }

        public void setFilename192(String filename192) {
            this.filename192 = filename192;
        }

        public String getFilename320() {
            return filename320;
        }

        public void setFilename320(String filename320) {
            this.filename320 = filename320;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getThumbnail_url() {
            return thumbnail_url;
        }

        public void setThumbnail_url(String thumbnail_url) {
            this.thumbnail_url = thumbnail_url;
        }

        public String getFsize() {
            return fsize;
        }

        public void setFsize(String fsize) {
            this.fsize = fsize;
        }

        public Object getGgid() {
            return ggid;
        }

        public void setGgid(Object ggid) {
            this.ggid = ggid;
        }

        public Object getYpid() {
            return ypid;
        }

        public void setYpid(Object ypid) {
            this.ypid = ypid;
        }

        public String getMtype() {
            return mtype;
        }

        public void setMtype(String mtype) {
            this.mtype = mtype;
        }

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public Object getLyrics() {
            return lyrics;
        }

        public void setLyrics(Object lyrics) {
            this.lyrics = lyrics;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getFlag() {
            return flag;
        }

        public void setFlag(String flag) {
            this.flag = flag;
        }

        public Object getNote() {
            return note;
        }

        public void setNote(Object note) {
            this.note = note;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public Object getUpdate_time() {
            return update_time;
        }

        public void setUpdate_time(Object update_time) {
            this.update_time = update_time;
        }

        public String getPublish_time() {
            return publish_time;
        }

        public void setPublish_time(String publish_time) {
            this.publish_time = publish_time;
        }

        public String getVol_id() {
            return vol_id;
        }

        public void setVol_id(String vol_id) {
            this.vol_id = vol_id;
        }

        public Object getMusician_id() {
            return musician_id;
        }

        public void setMusician_id(Object musician_id) {
            this.musician_id = musician_id;
        }

        public Object getBanner_id() {
            return banner_id;
        }

        public void setBanner_id(Object banner_id) {
            this.banner_id = banner_id;
        }

        public String getFee_tag() {
            return fee_tag;
        }

        public void setFee_tag(String fee_tag) {
            this.fee_tag = fee_tag;
        }

        public Object getCmcc_mid() {
            return cmcc_mid;
        }

        public void setCmcc_mid(Object cmcc_mid) {
            this.cmcc_mid = cmcc_mid;
        }
    }
}
