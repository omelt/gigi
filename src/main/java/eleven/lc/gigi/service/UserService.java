package eleven.lc.gigi.service;

import eleven.lc.gigi.entity.User;
import eleven.lc.gigi.entity.UserCheck;

public interface UserService {

    User getUserById(Long id);

    User getUserByEmail(String email);

    User getUserByUsername(String name);

    Boolean addUser(UserCheck user);

    Boolean changeAvatar(User user);

    Boolean changeInfo(User user);

    Boolean plusCollect(Long userId,Long videoId);

    Boolean subCollect(Long userId,Long videoId);

    Boolean addFollow(Long userId,Long followId);

    Boolean removeFollow(Long userId,Long followId);

    Boolean upToVIP(Long id);

}
