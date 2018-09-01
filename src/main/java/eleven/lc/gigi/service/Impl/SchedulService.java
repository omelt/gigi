package eleven.lc.gigi.service.Impl;

import eleven.lc.gigi.entity.Video;
import eleven.lc.gigi.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SchedulService {

    private static List<Video> allList=null;

    private static List<Video> headList=null;

    private static List<Video> featuredList=null;

    private static List<Video> hotList=null;

    @Autowired
    VideoService videoService;

    @Scheduled( fixedRate = 60000)
    @Transactional
    @Async
    public void updateList(){
        allList=videoService.listALL();
        headList=videoService.listByCollectNum();
        featuredList=videoService.listByGoods();
        hotList=videoService.listByTimes();
    }

    public static List<Video> getAllList() {
        return allList;
    }

    public static List<Video> getHeadList() {
        return headList;
    }

    public static List<Video> getFeaturedList() {
        return featuredList;
    }

    public static List<Video> getHotList() {
        return hotList;
    }

    public VideoService getVideoService() {
        return videoService;
    }
}
