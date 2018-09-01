package eleven.lc.gigi.service.Impl;

import eleven.lc.gigi.dao.UserCheckDao;
import eleven.lc.gigi.dao.UserDao;
import eleven.lc.gigi.dao.VideoDao;
import eleven.lc.gigi.entity.User;
import eleven.lc.gigi.entity.UserCheck;
import eleven.lc.gigi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserCheckDao userCheckDao;

    @Override
    public User getUserById(Long id) {
        return userDao.selectById(id);
    }

    @Override
    public User getUserByUsername(String name) {
        return userDao.selectByUsername(name);
    }

    @Override
    public Boolean addUser(UserCheck user) {
        if(userDao.insertUser(user)>0) {
            userCheckDao.deleteUserCheck(user.getId());
            return true;
        }
        return false;
    }

    @Override
    public User getUserByEmail(String email) {
        return userDao.selectByEmail(email);
    }

    @Override
    public Boolean changeAvatar(User user) {
        if(userDao.updateAvatar(user)>0)
            return true;
        return false;
    }

    @Override
    public Boolean changeInfo(User user) {
        if (userDao.updateInfo(user)>0)
            return true;
        return false;
    }

    @Autowired VideoDao videoDao;

    @Override
    @Transactional
    public Boolean plusCollect(Long userId, Long videoId) {
        if(userDao.insertCollect(userId,videoId)>0){
            if(videoDao.updatePlusCollectNum(videoId)>0) {
                return true;
            }
        }
        return false;
    }

    @Override
    @Transactional
    public Boolean subCollect(Long userId, Long videoId) {
        if(userDao.deleteCollect(userId,videoId)>0)
            if(videoDao.updateSubCollectNum(videoId)>0) return true;
        return false;
    }

    @Override
    public Boolean addFollow(Long userId, Long followId) {
        if(userDao.insertFollow(userId,followId)>0){
            return true;
        }
        return false;
    }

    @Override
    public Boolean removeFollow(Long userId, Long followId) {
        if(userDao.deleteFollow(userId,followId)>0){
            return true;
        }
        return false;
    }

    @Override
    public Boolean upToVIP(Long id) {
        if(userDao.updateVip(id)>0){
            return true;
        }
        return false;
    }
}
