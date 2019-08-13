package com.spring.mapper;

import com.spring.domain.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 *访问数据库的接口
 */
public interface UserMapper {

    //推荐使用#{}取值，不要用${}存在注入的风险
    @Insert("INSERT INTO user(name,phone,create_time,age) VALUE(#{name},#{phone},#{createTime},#{age})")
    @Options(useGeneratedKeys = true,keyProperty = "id",keyColumn = "id")   //keyProperty Java的属性，keyColumn表示数据库的字段
    int insert(User user);

    /**
     * 查找全部
     * @return
     */
    @Select("SELECT * FROM user")
    @Results({
            @Result(column = "create_time",property = "createTime")         //映射不同的名称
    })
    List<User> getAll();

    /**
     * 根据id找对象
     * @param id
     * @return
     */
    @Select("SELECT * FROM user Where id=#{id}")
    @Results({
            @Result(column = "create_time",property = "createTime")
    })
    User findById(Long id);

    /**
     * 更新对象
     * @param user
     */
    @Update("UPDATE user Set name=#{name} Where id=#{id}")
    void update(User user);

    /**
     * 删除对象
     * @param userId
     */
    @Delete("DELETE FROM user Where id=#{userId}")
    void delete(Long userId);
}
