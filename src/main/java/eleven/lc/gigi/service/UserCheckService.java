package eleven.lc.gigi.service;

import eleven.lc.gigi.entity.UserCheck;

public interface UserCheckService {
    UserCheck getById(String id);

    Boolean addUserCheck(UserCheck userCheck);
}
