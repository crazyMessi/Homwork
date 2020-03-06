package com.studentonline.homework.controller;

import com.studentonline.homework.entities.OutputInformation;
import com.studentonline.homework.entities.User;
import com.studentonline.homework.service.ApiService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 19892
 @RestController说明这是一个restful风格的方法
 */

@RestController
public class ApiController {



    /**
     * @Autowired 自动绑定使得apiService不再是null
     */
    @Autowired
    private ApiService apiService;

    @PostMapping(value = "/register")
    public OutputInformation register(@RequestParam(value = "userId") String id, @RequestParam("psw")String psw){
        OutputInformation o;
        o=OutputInformation.of(apiService.register(id, psw));
        return o;
    }


    /**
     * @RequestMapping 请求映射
     * @return
     */
    @PostMapping(value = "/login")
    public OutputInformation login(@RequestParam("userId") String userId, @RequestParam("psw") String psw,HttpServletRequest request)throws Exception{
        int code=apiService.login(userId,psw);
        OutputInformation o=new OutputInformation();
        if (code==0){
            //用户名存入该用户的session 中
            request.getSession().setAttribute("userId",userId);
        }else {
            o=OutputInformation.of(code);
        }
        return o;
    }

    @PostMapping("/question")
    public OutputInformation question(HttpServletRequest request,@RequestParam("question")String question){
        OutputInformation o=new OutputInformation();
        if (request.getSession().getAttribute("userId")!=null)
            o.setCode(apiService.question((String) request.getSession().getAttribute("userId"),question));
        else o=OutputInformation.of(-1);
        return o;
    }


    @PostMapping(value = "/getBox")
    public OutputInformation getBox(HttpServletRequest request){
        OutputInformation o=new OutputInformation();
        if (request.getSession().getAttribute("userId")!=null)
            o.setData(apiService.getBox((String)request.getSession().getAttribute("userId")));
        else o=OutputInformation.of(-1);
        return o;
    }

    @PostMapping(value = "/answer")
    public OutputInformation answer(@Param("id") String id,@Param("answer") String answer){
        OutputInformation o;
        o=OutputInformation.of(apiService.answer(answer,id));
        return o;
    }

    @PostMapping(value = "/getBoxList")
    public OutputInformation getBoxList(HttpServletRequest request,@Param("userId")String userId){
        OutputInformation o=new OutputInformation();
        try {
            userId=(String)request.getSession().getAttribute("userId");
            o.setData(apiService.getBoxList(userId));
        }catch (Exception e){
            e.printStackTrace();
            o=OutputInformation.of(-1);
        }
        return o;
    }


}
