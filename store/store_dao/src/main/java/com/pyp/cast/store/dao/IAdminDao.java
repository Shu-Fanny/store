package com.pyp.cast.store.dao;

import com.pyp.cast.store.domain.PO.Admin;
import org.apache.ibatis.annotations.Select;

public interface IAdminDao {
    /**
     * 根据用户名，查找该用户的详细信息
     * @param adname 用户名
     * @return
     * @throws Exception
     */
    @Select("select * from admin where adname=#{adname}")
    Admin findAdminByUserName(String adname)throws Exception;
}
