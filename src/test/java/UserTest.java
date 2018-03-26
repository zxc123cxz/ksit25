
import com.kaisheng.Util.SqlSessionFactoryUtil;
import com.kaisheng.entity.Member;
import com.kaisheng.entity.User;
import com.kaisheng.mapper.MemberMapper;
import com.kaisheng.mapper.UserMapper;
import org.apache.ibatis.session.SqlSession;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class UserTest {

    SqlSession sqlsession = SqlSessionFactoryUtil.getSqlSession(true);
    private UserMapper userMapper;
    private MemberMapper memberMapper;
    @Before
    public void init(){
        userMapper = sqlsession.getMapper(UserMapper.class);
        System.out.println(userMapper);
    }


   /* public void testFindById()throws Exception{
        User user =  sqlsession.selectOne("com.kaisheng.mapper.UserMapper.findById",1);
        System.out.println(user);
        sqlsession.close();
    }*/
   /* @Test
   public void findById(){
        User user = userMapper.findById(2);
        System.out.println(user);
        sqlsession.close();
    }*/
   /*  @Test
    public void testFindAll()throws Exception{
        List<User> userList =  sqlsession.selectList("User.findAll");
        for(User user : userList){
            System.out.println(user);
        }
        sqlsession.close();
    }
*/
   @Test
    public void findAll(){
        userMapper = sqlsession.getMapper(UserMapper.class);
        List<User> userList = userMapper.findAll();
        for(User user : userList){
            System.out.println(user);
        }
        sqlsession.close();
    }
    @Test
    public void finfByid(){
       memberMapper = sqlsession.getMapper(MemberMapper.class);
       Member member = memberMapper.findById(2);
       System.out.println(member);
       sqlsession.close();
    }


    /*public void testfind(){
        memberMapper = sqlsession.getMapper(MemberMapper.class);
        Member member = memberMapper.findByid(1);

    }*/
    @Test
    public void TestUpdate(){
    User user = userMapper.findById(2);
    user.setPublishname("他的老家啊2");
    user.setAddress("大东北啊啊2");
    userMapper.update(user);

    }






}



