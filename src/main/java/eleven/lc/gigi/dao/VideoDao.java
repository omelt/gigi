package eleven.lc.gigi.dao;

import eleven.lc.gigi.entity.Video;

import java.util.List;

public interface VideoDao {

    List<Video> selectAll();

    List<Video> selectOrderByDate();

    List<Video> selectRandom();

    List<Video> selectOrderByTimes();

    List<Video> selectOrderByGoods();

    List<Video> selectOrderByCollectNum();

    List<Video> selectFromCollect(Long id);

    Video selectById(Long id);

    Video selectByIdNotDetail(Long id);

    List<Video> selectUserVideo(Long id);

    int insertNewVideo(Video video);

    int updateTimes(Long id);

    int updatePlusCollectNum(Long id);

    int updateSubCollectNum(Long id);

    int updatePlusCommentNum(Long id);

    int updateSubCommentNum(Long id);

    List<Video> selectLikeTag(String data);

    List<Video> selectLikeTitle(String data);

    List<Video> selectLikeType(String data);
}
