package eleven.lc.gigi.service;

import eleven.lc.gigi.entity.Video;

import java.util.List;

public interface VideoService {

    List<Video> listALL();

    List<Video> listByDate();

    List<Video> listByGoods();

    List<Video> listByTimes();

    List<Video> listByRandom();

    List<Video> listByCollectNum();

    Video getById(Long id);

    Boolean countTimes(Long id);

//    Boolean plusCollectNum(int id);

//    Boolean subCollectNum(int id);

    List<Video> findLikeTag(String data);

    List<Video> findLikeTitle(String data);

    List<Video> findLikeType(String data);

    Boolean addVideo(Video video);

}
