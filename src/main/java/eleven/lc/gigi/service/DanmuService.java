package eleven.lc.gigi.service;

import eleven.lc.gigi.entity.Danmu;

import java.util.List;

public interface DanmuService {

    List<Danmu> listDanmuFromVideo(Long id);

    Boolean addDanmuIntoVideo(Danmu danmu);

}
