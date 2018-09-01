package eleven.lc.gigi.dao;

import eleven.lc.gigi.entity.Danmu;

import java.util.List;

public interface DanmuDao {

    List<Danmu> selectVideoDanmu(Long id);

    int insertNewDanmu(Danmu danmu);
}
