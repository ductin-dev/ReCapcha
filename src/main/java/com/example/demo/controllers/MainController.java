package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.logging.Logger;

@Controller
@RequestMapping("/")
public class MainController {

    public final String SCR_KEY="<RECAPCHA_SCR_KEY>";

    @RequestMapping(value = {"/", "/index"})
    public String home() {
        return "home";
    }

    @RequestMapping(value = "/download", method = RequestMethod.GET)
    public ModelAndView uploadPhotoForm(HttpServletRequest request,HttpServletResponse response) {
        ModelAndView mav=new ModelAndView("success");
        try{
            String name=request.getParameter("name");
            if (!isCaptchaValid(SCR_KEY, request.getParameter("g-recaptcha-response"))) throw new Exception("Wrong Capcha");
            mav.addObject("name",name);

        }catch (Exception e){
            e.printStackTrace();
            mav=new ModelAndView("failure");
            mav.addObject("log",e.getMessage());
            return mav;
        }
        return mav;
    }
    public synchronized boolean isCaptchaValid(String secretKey, String response) {
        try {
            String url = "https://www.google.com/recaptcha/api/siteverify",
                    params = "secret=" + secretKey + "&response=" + response;

            HttpURLConnection http = (HttpURLConnection) new URL(url).openConnection();
            http.setDoOutput(true);
            http.setRequestMethod("POST");
            http.setRequestProperty("Content-Type",
                    "application/x-www-form-urlencoded; charset=UTF-8");
            OutputStream out = http.getOutputStream();
            out.write(params.getBytes("UTF-8"));
            out.flush();
            out.close();

            InputStream res = http.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(res, "UTF-8"));

            StringBuilder sb = new StringBuilder();
            int cp;
            while ((cp = rd.read()) != -1) {
                sb.append((char) cp);
            }
            JSONObject json = new JSONObject(sb.toString());
            res.close();

            return json.getBoolean("success");
        } catch (Exception e) {
            //e.printStackTrace();
        }
        return false;
    }
}
