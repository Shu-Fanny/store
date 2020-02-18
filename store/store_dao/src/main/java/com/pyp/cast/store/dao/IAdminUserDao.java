package com.pyp.cast.store.dao;

import com.pyp.cast.store.domain.PO.Browse;
import com.pyp.cast.store.domain.PO.Product;
import com.pyp.cast.store.domain.PO.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface IAdminUserDao {
    /**
     * 查询总共用户数量
     * @return
     * @throws Exception
     */
    @Select("select count(*) from user")
    int findTotalRecords()throws Exception;

    /**
     * 此方法名字不对，应改为findUsersByCidWithPage
     * 查找当前页用户的详细信息
     * @param startIndex 开始查询条数
     * @param pageSize 总查询条数
     * @return
     * @throws Exception
     */
    @Select("select * from `user` limit  #{startIndex},#{pageSize}")
    List<User> findProductsByCidWithPage(@Param("startIndex") int startIndex,@Param("pageSize") int pageSize)throws Exception;

    /**
     * 查找该用户浏览总条目
     * @param uid
     * @return
     */
    @Select("Select count(*) from `browse` where uid=#{uid}")
    int findBrowseTotalRecords(String uid);

    /**
     * 查找用户在当前页的总浏览条数
     * @param uid
     * @param startIndex
     * @param pageSize
     * @return
     */
    @Select("select * from browse where uid=#{uid} limit  #{startIndex},#{pageSize}")
    @Results({
            @Result(property = "uid", column = "uid"),
            @Result(property = "total", column = "total"),
            @Result(property = "product", column = "pid", javaType = Product.class, one = @One(select = "com.pyp.cast.store.dao.IProductDao.findProductByPid")),
    })
    List<Browse> findBrowseProductWithPage(@Param("uid") String uid, @Param("startIndex") int startIndex,@Param("pageSize") int pageSize);
}
