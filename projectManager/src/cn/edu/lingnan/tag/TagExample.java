package cn.edu.lingnan.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

/**
 * @author 杨炜帆
 * @description 自定义标签
 */
public class TagExample extends TagSupport {

    @Override
    public int doStartTag() throws JspException {
        try {
            pageContext.getOut().print("Hello Tag");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return SKIP_BODY;
    }

    @Override
    public int doEndTag() throws JspException {
        return EVAL_PAGE;
    }
}
