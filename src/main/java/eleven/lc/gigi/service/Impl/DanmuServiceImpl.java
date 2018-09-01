package eleven.lc.gigi.service.Impl;

import eleven.lc.gigi.dao.DanmuDao;
import eleven.lc.gigi.entity.Danmu;
import eleven.lc.gigi.service.DanmuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DanmuServiceImpl implements DanmuService{

    @Autowired
    DanmuDao danmuDao;

    @Override
    public List<Danmu> listDanmuFromVideo(Long id) {
        return danmuDao.selectVideoDanmu(id);
    }

    @Override
    public Boolean addDanmuIntoVideo(Danmu danmu) {
        if (danmuDao.insertNewDanmu(danmu)>0)
            return true;
        return false;
    }
}
