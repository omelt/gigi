package eleven.lc.gigi.dao;

import eleven.lc.gigi.entity.User;
import eleven.lc.gigi.entity.UserCheck;

import java.util.List;

public interface UserDao {

    List<User> selectAll();

    User selectById(Long id);

    int insertUser(UserCheck user);

    User selectByUsername(String name);

    User selectByEmail(String email);

    int updateAvatar(User user);

    int updateInfo(User user);

    int insertCollect(Long userId,Long videoId);

    int deleteCollect(Long userId,Long videoId);

    int insertFollow(Long userId,Long followId);

    int deleteFollow(Long userId,Long followId);

    int updateVip(Long id);
}
