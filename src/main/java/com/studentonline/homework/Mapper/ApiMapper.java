package com.studentonline.homework.Mapper;

import com.studentonline.homework.entities.Box;
import com.studentonline.homework.entities.User;
import org.apache.ibatis.annotations.*;
import org.springframework.context.annotation.Bean;

import java.util.List;

/**
 * @author 19892
 * ibatis 里包含四个注解：@Insert、@Delete、@Select、@Update，代表增删改查
 */
@Mapper
public interface ApiMapper {

    /**
     * 注册用户
     * @param userId
     * @param psw
     * @return
     */
    @Insert("INSERT INTO `userInformation` (`userId`,`psw`) VALUES (#{userId},#{psw})")
    int register(@Param("userId")String userId,@Param("psw")String psw);


    /**
     * 登陆验证
     * @return
     * @param userId
     * 用limit优化sql语句 加快搜索效率
     */
    @Select("SELECT * FROM `userInformation` WHERE userId = #{userId} Limit 1" )
    User login(@Param("userId")String userId);

    /**
     * 获得当前用户的箱子
     * @param userId
     * @return
     */
    @Select("SELECT * FROM `box` WHERE userId=#{userId}")
    List<Box> getBoxList(@Param("userId")String userId);

    /**
     * 获得其他用户的箱子
     * @param userId
     * @return
     */
    @Select("SELECT * FROM `box` WHERE userId <>#{userId} AND `ifMatch`='no' ORDER BY RAND() LIMIT 1 ")
    Box getBox(@Param("userId")String userId);

    /**
     * 插入问题
     * @return
     * @param question
     * @param userId
     */
    @Insert("INSERT INTO `box`  (`question`,userId) VALUES (#{question},#{userId})")
    int question(@Param("question")String question,@Param("userId")String userId);

    /**
     * 插入回答
     * @param answer
     * @param id 箱子的id
     * @return
     */
    @Update("Update `box` SET `answer`=#{answer},`ifMatch`='yes' WHERE `id`=#{id}")
    int answer(@Param("answer")String answer,String id);
}
