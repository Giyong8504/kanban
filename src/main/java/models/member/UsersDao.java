package models.member;

import configs.DBConnection;
import controllers.member.UserForm;
import org.apache.ibatis.session.SqlSession;
import org.mindrot.jbcrypt.BCrypt;

public class UsersDao {
    public boolean register(UserForm users) {
        SqlSession sqlSession = DBConnection.getSession();
        String hash = BCrypt.hashpw(users.getUserPw(), BCrypt.gensalt(12)); // 반드시 해시를 사용해서 비밀번호를 암호화.
        users.setUserPw(hash);

        int affectedRow = sqlSession.insert("UserMapper.add", users);

        sqlSession.commit();

        return affectedRow > 0;
    }

    public Users get(String userId) {
        SqlSession sqlSession = DBConnection.getSession();
        UserForm params = new UserForm();
        params.setUserId(userId);

        Users users = sqlSession.selectOne("UsersMapper.info", params);

        return users;
    }

    public boolean exists(String userId) {
        SqlSession sqlSession = DBConnection.getSession();
        UserForm params = new UserForm();
        params.setUserId(userId);
        int cnt = sqlSession.selectOne("UserMapper.exists", params);

        return cnt > 0;
    }
}
