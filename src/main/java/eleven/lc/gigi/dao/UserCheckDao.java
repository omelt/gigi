package eleven.lc.gigi.dao;

import eleven.lc.gigi.entity.UserCheck;

public interface UserCheckDao {
    UserCheck selectById(String id);

    UserCheck selectByUsername(String username);

    int insertUserCheck(UserCheck userCheck);

    int deleteUserCheck(String id);
}
