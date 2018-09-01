package eleven.lc.gigi.myutil;

import eleven.lc.gigi.entity.Video;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Component
public class MyFileUtil {


    public String createFileName(String tar,String basePath){
        String[] res=tar.split("\\.");
        String filename=basePath + UUID.randomUUID().toString()+'.'+res[res.length-1];
        return filename;
    }
    @Async
    public void FileSave(String path, MultipartFile multipartFile) throws IOException {
        File file = new File(path);
        multipartFile.transferTo(file);
    }
}
