package eleven.lc.gigi.myutil;

import eleven.lc.gigi.web.listener.UploadProgressListener;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUpload;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class CustomMultipartResolver extends CommonsMultipartResolver {

    @Autowired
    private UploadProgressListener uploadProgressListener;

    @Override
    protected MultipartParsingResult parseRequest(HttpServletRequest request) throws MultipartException {
        String encoding = determineEncoding(request);
        FileUpload fileUpload = prepareFileUpload(encoding);
        uploadProgressListener.setSession(request.getSession());//问文件上传进度监听器设置session用于存储上传进度
        fileUpload.setProgressListener(uploadProgressListener);//将文件上传进度监听器加入到 fileUpload 中

        List<FileItem> fileItems = null;
        try {
            fileItems = ((ServletFileUpload) fileUpload).parseRequest(request);
        } catch (FileUploadException e) {
            e.printStackTrace();
        }
        return parseFileItems(fileItems, encoding);


    }

}