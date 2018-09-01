package eleven.lc.gigi.service.Impl;

import eleven.lc.gigi.dao.VideoDao;
import eleven.lc.gigi.entity.Video;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class VideoServiceImpl implements eleven.lc.gigi.service.VideoService {

    @Autowired
    VideoDao videoDao;

    @Override
    public List<Video> listALL() {
        return videoDao.selectAll();
    }

    @Override
    public List<Video> listByDate() {
        return videoDao.selectOrderByDate();
    }

    @Override
    public List<Video> listByGoods() {
        return videoDao.selectOrderByGoods();
    }

    @Override
    public List<Video> listByTimes() {
        return videoDao.selectOrderByTimes();
    }

    @Override
    public List<Video> listByRandom() {
        return videoDao.selectRandom();
    }

    @Override
    public List<Video> listByCollectNum() {
        return videoDao.selectOrderByCollectNum();
    }

    @Override
    public Video getById(Long id) {
        return videoDao.selectById(id);
    }

    @Override
    public Boolean countTimes(Long id) {
        if (videoDao.updateTimes(id)>0) return true;
        return false;
    }

//    @Override
//    public Boolean plusCollectNum(int id) {
//        if(videoDao.updatePlusCollectNum(id)>0) return true;
//        return false;
//    }
//
//    @Override
//    public Boolean subCollectNum(int id) {
//        if(videoDao.updateSubCollectNum(id)>0) return true;
//        return false;
//    }


    @Override
    public List<Video> findLikeTag(String data) {
        return videoDao.selectLikeTag(data);
    }

    @Override
    public List<Video> findLikeTitle(String data) {
        return videoDao.selectLikeTitle(data);
    }

    @Override
    public List<Video> findLikeType(String data) {
        return videoDao.selectLikeType(data);
    }

    @Override
    public Boolean addVideo(Video video) {
        if(videoDao.insertNewVideo(video)>0){
            return true;
        }
        return false;
    }
}

