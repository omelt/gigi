package eleven.lc.gigi.web.error;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;


/**
 * 捕获异常
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ModelAndView defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
        ErrorInfo<String> r = new ErrorInfo<>();
        ModelAndView modelAndView=new ModelAndView("error/500");
        r.setMessage(e.getMessage());
        r.setData(e.getLocalizedMessage());

        r.setCode(500);
//        r.setData("Some Data");
        r.setUrl(req.getRequestURL().toString());

        modelAndView.addObject("error",r);
        return modelAndView;

    }
}
