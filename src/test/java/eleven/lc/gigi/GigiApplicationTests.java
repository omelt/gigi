package eleven.lc.gigi;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import eleven.lc.gigi.dao.CommentDao;
import eleven.lc.gigi.dao.DanmuDao;
import eleven.lc.gigi.dao.UserDao;
import eleven.lc.gigi.dao.VideoDao;
import eleven.lc.gigi.entity.Comment;
import eleven.lc.gigi.entity.Danmu;
import eleven.lc.gigi.entity.User;
import eleven.lc.gigi.entity.Video;
import eleven.lc.gigi.myutil.MailUtil;
import eleven.lc.gigi.myutil.PageBean;
import freemarker.template.TemplateException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.test.context.junit4.SpringRunner;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.*;

@RunWith(SpringRunner.class)
@EnableScheduling
@SpringBootTest(classes = GigiApplication.class)
public class GigiApplicationTests {

    @Test
    public void contextLoads() {
    }

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Test
    public void redisTest() {
        // 保存字符串
        stringRedisTemplate.opsForValue().set("中文", "中文");
        String temp=stringRedisTemplate.opsForValue().get("中文");
        System.out.println(temp);
        Assert.assertEquals("中文", temp);
    }

    @Autowired UserDao userDao;

    @Autowired VideoDao videoDao;

    @Autowired DanmuDao danmuDao;

    @Autowired CommentDao commentDao;

    @Test
    public void mybatisUserTest(){

        User user=userDao.selectById(new Long(1));

        System.out.println(user.getComments());

        System.out.println(user.getMyVideoes());

        System.out.println(user.getCollects());

        System.out.println(user.getFollowed());

        System.out.println(user.getFollows());
    }

    @Test
    public void mybatisVideoTest(){

        Video video=videoDao.selectById(new Long(1));

        System.out.println(video.getPublisher());

        List<Comment> comments= video.getComments();
        System.out.println(comments.get(1).getTalker());
        System.out.println(comments.get(1).getSource());


        System.out.println(video.getDanmus());

    }

    @Test
    public void mybatisInsertTest(){

        Video video=videoDao.selectById(new Long(1));

        List<Danmu> list=danmuDao.selectVideoDanmu(video.getId());

        System.out.println(list);
    }

    @Autowired
    MailUtil mailUtil;

    @Test
    public void mailTest() {
        try {
            mailUtil.SendHrefMail("mage_of_code@outlook.com","id");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void mybatisTest(){

        User user=userDao.selectById(new Long(1));
        List<Video> list=user.getCollects();

        Video video=videoDao.selectById(new Long(1));



        Assert.assertEquals(4,list.size());

        Video temp=new Video();
        temp.setId(new Long(1));


        list.remove(temp);
        Assert.assertEquals(true,temp.equals(video));
        Assert.assertEquals(3,list.size());

    }
    @Value("${eleven.lc.gigi.pageSize}")
    int pageSize;

    @Test
    public void mybatisPage(){
        Video video = videoDao.selectById(new Long(1));

        Page page = PageHelper.startPage(1, pageSize, true);

        List<Comment> list=video.getComments();

        PageBean<Comment> pageBean=new PageBean(1,pageSize,page.getPages());

        pageBean.setItems(list);
//


        System.out.println(JSON.toJSON(pageBean));
    }

    @Test
    public void mybatisTest02(){
//        List<Comment> list=commentDao.selectVideoComment(1);
//        for (Comment i:list) {
//            if(i.getId()==1){
//                System.out.println(i.getResponse());
//            }
//        }

//        (content,userId,videoId,commentId)
        Comment comment=new Comment();
        comment.setContent("mybatisTest02");
        comment.setUserId(new Long(1));
        comment.setVideoId(new Long(1));
        commentDao.insertNewComment(comment);

//        User user=userDao.selectByUsername("onlytest0");
//        User temp=user;
//        System.out.println(user.getFollows());
    }
}
