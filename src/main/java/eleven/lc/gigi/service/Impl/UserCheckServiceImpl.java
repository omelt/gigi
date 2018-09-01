package eleven.lc.gigi.service.Impl;

import eleven.lc.gigi.dao.UserCheckDao;
import eleven.lc.gigi.dao.UserDao;
import eleven.lc.gigi.entity.UserCheck;
import eleven.lc.gigi.service.UserCheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class UserCheckServiceImpl implements UserCheckService {

    @Autowired
    UserCheckDao userCheckDao;

    @Autowired
    UserDao userDao;

    @Override
    public UserCheck getById(String id) {
        return userCheckDao.selectById(id);
    }

    @Override
    @Transactional
    public Boolean addUserCheck(UserCheck userCheck) {
        if (userDao.selectByUsername(userCheck.getUsername()) == null) {
            if (userCheckDao.selectByUsername(userCheck.getUsername()) == null && userCheckDao.insertUserCheck(userCheck) > 0)
                return true;
            return false;
        }
        return false;
    }
}
