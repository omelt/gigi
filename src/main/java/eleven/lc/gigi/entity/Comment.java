package eleven.lc.gigi.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

public class Comment {
    private Long id;
    private Integer goods;
    private Integer bads;
    private String content;
    private Timestamp date;

    private Long userId;
    private Long videoId;
    private User talker;
    private Video source;

    private Long commentId;
    private List<Comment> response;

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                '}';
    }

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public List<Comment> getResponse() {
        return response;
    }

    public void setResponse(List<Comment> response) {
        this.response = response;
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
    @Column(name = "goods", nullable = true)
    public Integer getGoods() {
        return goods;
    }

    public void setGoods(Integer goods) {
        this.goods = goods;
    }

    @Basic
    @Column(name = "bads", nullable = true)
    public Integer getBads() {
        return bads;
    }

    public void setBads(Integer bads) {
        this.bads = bads;
    }

    @Basic
    @Column(name = "content", nullable = false, length = 128)
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Basic
    @Column(name = "date", nullable = true)
    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }


    @Basic
    @Column(name = "userId", nullable = true)
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

        Comment comment = (Comment) o;

        if (id != null ? !id.equals(comment.id) : comment.id != null) return false;
        if (goods != null ? !goods.equals(comment.goods) : comment.goods != null) return false;
        if (bads != null ? !bads.equals(comment.bads) : comment.bads != null) return false;
        if (content != null ? !content.equals(comment.content) : comment.content != null) return false;
        if (date != null ? !date.equals(comment.date) : comment.date != null) return false;
        if (userId != null ? !userId.equals(comment.userId) : comment.userId != null) return false;
        if (videoId != null ? !videoId.equals(comment.videoId) : comment.videoId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (goods != null ? goods.hashCode() : 0);
        result = 31 * result + (bads != null ? bads.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (videoId != null ? videoId.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "id")
    public User getTalker() {
        return talker;
    }

    public void setTalker(User talker) {
        this.talker = talker;
    }

    @ManyToOne
    @JoinColumn(name = "videoId", referencedColumnName = "id")
    public Video getSource() {
        return source;
    }

    public void setSource(Video source) {
        this.source = source;
    }
}
