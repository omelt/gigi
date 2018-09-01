package eleven.lc.gigi.entity;

import javax.persistence.*;


public class Danmu {
    private Long id;
    private String color;
    private Integer position;
    private Integer size;
    private String text;
    private Integer time;
    private Long videoId;
//    private Video videoByVideoId;


    @Override
    public String toString() {
        StringBuilder res=new StringBuilder();
        res.append("{\"text\":\"");
        res.append(text);
        res.append("\",\"color\":\"");
        res.append(color);
        res.append("\",\"size\":");
        res.append(size);
        res.append(",\"position\":");
        res.append(position);
        res.append(",\"time\":");
        res.append(time);
        res.append("}");
        return res.toString();
    }

    @Id
    @Column(name = "id", nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "color", nullable = false, length = 10)
    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Basic
    @Column(name = "position", nullable = false)
    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    @Basic
    @Column(name = "size", nullable = false)
    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    @Basic
    @Column(name = "text", nullable = false, length = 120)
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Basic
    @Column(name = "time", nullable = false)
    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    @Basic
    @Column(name = "videoId", nullable = true)
    public Long getVideoId() {
        return videoId;
    }

    public void setVideoId(Long videoId) {
        this.videoId = videoId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Danmu danmu = (Danmu) o;

        if (id != null ? !id.equals(danmu.id) : danmu.id != null) return false;
        if (color != null ? !color.equals(danmu.color) : danmu.color != null) return false;
        if (position != null ? !position.equals(danmu.position) : danmu.position != null) return false;
        if (size != null ? !size.equals(danmu.size) : danmu.size != null) return false;
        if (text != null ? !text.equals(danmu.text) : danmu.text != null) return false;
        if (time != null ? !time.equals(danmu.time) : danmu.time != null) return false;
        if (videoId != null ? !videoId.equals(danmu.videoId) : danmu.videoId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (color != null ? color.hashCode() : 0);
        result = 31 * result + (position != null ? position.hashCode() : 0);
        result = 31 * result + (size != null ? size.hashCode() : 0);
        result = 31 * result + (text != null ? text.hashCode() : 0);
        result = 31 * result + (time != null ? time.hashCode() : 0);
        result = 31 * result + (videoId != null ? videoId.hashCode() : 0);
        return result;
    }

//    @ManyToOne
//    @JoinColumn(name = "videoId", referencedColumnName = "id")
//    public Video getVideoByVideoId() {
//        return videoByVideoId;
//    }
//
//    public void setVideoByVideoId(Video videoByVideoId) {
//        this.videoByVideoId = videoByVideoId;
//    }
}
