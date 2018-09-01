package eleven.lc.gigi.web;


import eleven.lc.gigi.entity.Comment;
import eleven.lc.gigi.entity.User;
import eleven.lc.gigi.entity.UserCheck;
import eleven.lc.gigi.entity.Video;
import eleven.lc.gigi.myutil.*;
import eleven.lc.gigi.service.CommentService;
import eleven.lc.gigi.service.UserCheckService;
import eleven.lc.gigi.service.UserService;
import eleven.lc.gigi.service.VideoService;
import freemarker.template.TemplateException;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.ProgressListener;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.imageio.ImageIO;
import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.security.PrivateKey;
import java.util.*;
import java.util.List;

@RequestMapping(value = "/user")
//@RestController
@Controller
public class UserAction {

    @Autowired
    private UserService userService;

    @Autowired
    private UserCheckService userCheckService;

    @Autowired
    private AuthenticationManager myAuthenticationManager;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String doLogin(RedirectAttributes model, @SessionAttribute String checkcode, @RequestParam(defaultValue = "") String verificationCode, @RequestParam(defaultValue = "") String username, @RequestParam(defaultValue = "") String password, @SessionAttribute PrivateKey privateKey, HttpServletRequest request) throws Exception {

        if (checkcode.equals(verificationCode)) {

            String newSource = RSAUtil.decryptString(privateKey, password);

            newSource = CryptUtils.GetMD5Code(newSource);

            User user = userService.getUserByUsername(username);

            if (!newSource.equals(user.getPassword())) {
                model.addFlashAttribute("loginError", "密码错误");
            } else {
                UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username, newSource);

                Authentication authentication = myAuthenticationManager.authenticate(authRequest); //调用loadUserByUsername
                SecurityContextHolder.getContext().setAuthentication(authentication);

//                HttpSession session = request.getSession();
//                session.setAttribute("SPRING_SECURITY_CONTEXT", SecurityContextHolder.getContext()); // 这个非常重要，否则验证后将无法登陆

                return "redirect:/home";


            }
        } else {
            model.addFlashAttribute("loginError", "验证码错误");
        }
        model.addFlashAttribute("loginUsername", username);
        return "redirect:/login";
    }

    @RequestMapping(value = "/emailLogin", method = RequestMethod.POST)
    public String emailLogin(Model model, @SessionAttribute String loginCode, @RequestParam String email, @RequestParam String checkCode) {
        if (loginCode.equals(checkCode)) {
            User user = userService.getUserByEmail(email);
            if (user == null) {
                model.addAttribute("loginError", "未注册的邮箱");
            } else {
                UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
                Authentication authentication = myAuthenticationManager.authenticate(authRequest); //调用loadUserByUsername
                SecurityContextHolder.getContext().setAuthentication(authentication);

                return "redirect:/home";
            }
        } else {
            model.addAttribute("loginError", "验证失败");
        }
        return "login";

    }

    @Autowired
    MailUtil mailUtil;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(Model model, @ModelAttribute UserCheck userCheck, @SessionAttribute PrivateKey privateKey) throws Exception {


        String newSource = RSAUtil.decryptString(privateKey, userCheck.getPassword());
        String reNewSource = RSAUtil.decryptString(privateKey, userCheck.getRepassword());


        if (newSource.equals(reNewSource)) {

            userCheck.setPassword(CryptUtils.GetMD5Code(newSource));
            String id = UUID.randomUUID().toString();
            userCheck.setId(id);

            if (userCheckService.addUserCheck(userCheck)) {
                mailUtil.SendHrefMail(userCheck.getEmail(), id);
                model.addAttribute("success", true);
                model.addAttribute("email", userCheck.getEmail());
            } else {
                model.addAttribute("resError", "用户名重复");
            }
        } else {
            model.addAttribute("resError", "密码不一致");
        }
        model.addAttribute("lastUsername", userCheck.getUsername());
        model.addAttribute("lastEmail", userCheck.getEmail());
        model.addAttribute("lastNickname", userCheck.getNickname());
        return "register";
    }

    @RequestMapping(value = {"/info", "/info/{username}"})
    public String message(Model model, @PathVariable(required = false) String username) {
        User user = null;

        if (username == null) {
            user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        } else {
            user = userService.getUserByUsername(username);
        }

        model.addAttribute("nowUser", user);

        return "message";

    }

    @Value("${eleven.lc.gigi.basePath}")
    String basePath;

    //    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/uploadHead", method = RequestMethod.POST)
    @ResponseBody
    public void uploadHead(@RequestParam String data) throws IOException {
        if (data == null) {
            return;
        }
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        String[] imageData = data.split(",");
        if (imageData[0].contains("data:image")) {
            String filename = "/data/head/" + user.getNickname() + ".jpg";

            Base64.Decoder decoder = Base64.getDecoder();
            byte[] b = decoder.decode(imageData[1]);
            ByteArrayInputStream in = new ByteArrayInputStream(b);
            BufferedImage image = ImageIO.read(in);
            Image after = image.getScaledInstance(140, 140, Image.SCALE_DEFAULT);
            BufferedImage inputbig = new BufferedImage(140, 140, BufferedImage.TYPE_INT_BGR);
            inputbig.getGraphics().drawImage(after, 0, 0, 140, 140, null);
            ImageIO.write(inputbig, "JPEG", new File(basePath + filename));

            filename += "?" + UUID.randomUUID();
            user.setAvatar(filename);

            userService.changeAvatar(user);

        }
    }/**/

    @RequestMapping(value = "/changeInfo", method = RequestMethod.POST)
    @ResponseBody
    public String changeInfo(@ModelAttribute User user) {
        User nowUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        user.setId(nowUser.getId());
        if (userService.changeInfo(user)) {

            if (user.getNickname() != null && !user.getNickname().equals("")) nowUser.setNickname(user.getNickname());
            if (user.getEmail() != null && !user.getEmail().equals("")) nowUser.setEmail(user.getEmail());
            if (user.getSex() != null && !user.getSex().equals("")) nowUser.setSex(user.getSex());
            if (user.getIntroduction() != null && !user.getIntroduction().equals(""))
                nowUser.setIntroduction(user.getIntroduction());

            return "ok";
        }
        return "fail";
    }

    @RequestMapping(value = "/doCollect", method = RequestMethod.POST)
    @ResponseBody
    public String doCollect(@RequestParam String tar) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (userService.plusCollect(user.getId(), Long.parseLong(tar))) {
            Video temp = new Video();
            temp.setId(Long.parseLong(tar));
            user.getCollects().add(temp);
            return "ok";
        } else return "fail";
    }

    @RequestMapping(value = "/cancelCollect", method = RequestMethod.POST)
    @ResponseBody
    public String cancelCollect(@RequestParam String tar) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (userService.subCollect(user.getId(), Long.parseLong(tar))) {
            Video temp = new Video();
            temp.setId(Long.parseLong(tar));
            user.getCollects().remove(temp);
            return "ok";
        } else return "fail";
    }

    @Autowired
    CommentService commentService;

    @RequestMapping(value = "/publishComment", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> publishComment(@ModelAttribute Comment comment) {
        Map<String, Object> res = new HashMap<>();
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        comment.setUserId(user.getId());

        if (commentService.addComment(comment)) {
            res.put("flag", "ok");
            res.put("username",user.getUsername());
            res.put("nickname",user.getNickname());
            res.put("avatar",user.getAvatar());
        } else {
            res.put("flag", "fail");
        }

        return res;
    }

    @RequestMapping(value = "/cancelFollow", method = RequestMethod.POST)
    @ResponseBody
    public String cancelFollow(@RequestParam Long followId){
        User user=(User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(userService.removeFollow(user.getId(),followId)){
            User temp=userService.getUserById(followId);
            user.getFollows().remove(temp);
            return "ok";
        }
        return "fail";
    }

    @RequestMapping(value = "/doFollow", method = RequestMethod.POST)
    @ResponseBody
    public String doFollow(@RequestParam Long followId){
        User user=(User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(userService.addFollow(user.getId(),followId)){
            User temp=userService.getUserById(followId);
            user.getFollows().add(temp);
            return "ok";
        }
        return "fail";
    }

//    @Value("eleven.lc.gigi.basePath")
//    private final String basepath;

    @Autowired
    MyFileUtil myFileUtil;

    @Autowired
    VideoService videoService;

    @RequestMapping(value = "/upload",method = RequestMethod.POST)
    public String upload(@RequestParam("title") String title,
                         @RequestParam("title") String type,
                         @RequestParam("title") String tags,
                         @RequestParam("title") String introduction,
                         @RequestParam("preview") MultipartFile preview,
                         @RequestParam("content") MultipartFile content)
            throws Exception {
        User user=(User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Video video=new Video();
        video.setPublisher(user);
        video.setTitle(title);video.setType(type);
        video.setTags(tags);video.setIntroduction(introduction);

        String temp=myFileUtil.createFileName(preview.getOriginalFilename(),basePath+"/data/preview/");
        video.setPreview(temp);
        myFileUtil.FileSave(temp,preview);
        //---
        temp=myFileUtil.createFileName(content.getOriginalFilename(),basePath+"/data/video/");
        video.setContent(temp);
        myFileUtil.FileSave(temp,content);
        //---
        videoService.addVideo(video);

        return "forward:/user/info";
    }
//    @RequestMapping(value = "/upload/status",method = RequestMethod.POST)
//    @ResponseBody
//    public String uploadStatus(@SessionAttribute("status") String status) {
//        return status;
//    }

    @RequestMapping(value = "/toVIP",method = RequestMethod.GET)
    public String toVIP(){
        User user=(User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(userService.upToVIP(user.getId())){
            byte temp=1;
            user.setVip(temp);
        }

        return "forward:/user/info";
    }
}
