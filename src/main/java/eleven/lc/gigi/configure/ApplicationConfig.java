package eleven.lc.gigi.configure;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 在spring boot中加入相应配置的扫描
 * @author :liuqh
 * @date :2017/12/5 11:06
 */
@Configuration
public class ApplicationConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        /*
        * 说明：增加虚拟路径(经过本人测试：在此处配置的虚拟路径，用springboot内置的tomcat时有效，
        * 用外部的tomcat也有效;所以用到外部的tomcat时不需在tomcat/config下的相应文件配置虚拟路径了,阿里云linux也没问题)
        */
        registry.addResourceHandler("/data/head/**").addResourceLocations("file:/home/lc/Workspace/IdeaProjects/gigi/data/head/");
        registry.addResourceHandler("/data/preview/**").addResourceLocations("file:/home/lc/Workspace/IdeaProjects/gigi/data/preview/");
        registry.addResourceHandler("/data/video/**").addResourceLocations("file:/home/lc/Workspace/IdeaProjects/gigi/data/video/");


        //阿里云(映射路径去除盘符)
        //registry.addResourceHandler("/ueditor/image/**").addResourceLocations("/upload/image/");
        //registry.addResourceHandler("/ueditor/video/**").addResourceLocations("/upload/video/");
        super.addResourceHandlers(registry);
    }

}
