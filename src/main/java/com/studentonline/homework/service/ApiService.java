package com.studentonline.homework.service;

import com.studentonline.homework.Mapper.ApiMapper;
import com.studentonline.homework.entities.Box;
import com.studentonline.homework.entities.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 19892
 *
 */
@Service
public class ApiService {


    /**
     * 逻辑写在service里面
     * @return
     */

    public String test(String name) {
        return "hi,I'm"+name;
    }

    @Autowired
    private ApiMapper apiMapper;

    public int login(String userId, String psw) {
        if (psw.equals(apiMapper.login(userId).getPsw()))
            return 0;
        else return -1;
    }

    public List<Box> getBoxList(String userId){
        return apiMapper.getBoxList(userId);
    }

    public Box getBox(String userId){
        return apiMapper.getBox(userId);
    }

    public int answer(String answer,String id){
        return apiMapper.answer(answer,id);
    }

    public int question(String userId,String question){
        return apiMapper.question(question,userId);
    }

    public int register(String userId, String psw){
        return apiMapper.register(userId,psw);
    }
}
