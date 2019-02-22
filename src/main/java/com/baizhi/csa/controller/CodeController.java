package com.baizhi.csa.controller;

import com.baizhi.csa.util.ValidateImageCodeUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

@RestController
@RequestMapping("/code")
public class CodeController {
    @RequestMapping("getCode")

    public void getCode(HttpServletResponse response, HttpSession session) {
        /*1.绘制码*/
        String code = ValidateImageCodeUtils.getSecurityCode();
        session.setAttribute("code", code);
        BufferedImage image = ValidateImageCodeUtils.createImage(code);
        ServletOutputStream outputStream = null;
        try {
            outputStream = response.getOutputStream();
            ImageIO.write(image, "png", outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                outputStream.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }

    }
}
