package com.grain.utils.validateCode;

import com.grain.utils.cache.CachePara;
import com.grain.utils.cache.CacheService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ValidateCodeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        // ������Ӧ�����͸�ʽΪͼƬ��ʽ
        response.setContentType("image/jpeg");
        //��ֹͼ�񻺴档
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        ValidateCode vCode = new ValidateCode(120, 40, 4, 100);
        new CacheService().setSession2Cache(request, CachePara.CACHE_PARA_VALIDATE_CODE, vCode.getCode());
        vCode.write(response.getOutputStream());
    }


}