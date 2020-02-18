package com.pyp.cast.store.service.impl;

import com.pyp.cast.store.dao.IAdminOrderDao;
import com.pyp.cast.store.dao.IAdminUserDao;
import com.pyp.cast.store.domain.PO.Browse;
import com.pyp.cast.store.domain.PO.Product;
import com.pyp.cast.store.domain.PO.User;
import com.pyp.cast.store.service.IAdminUserService;
import com.pyp.cast.store.utils.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AdminUserServiceImpl implements IAdminUserService {
    @Autowired
    private IAdminUserDao adminUserDao;

    /**
     * 查找当前页用户的详细信息
     * @param curNum
     * @return
     * @throws Exception
     */
    @Override
    public PageModel findAllProductWithpage(int curNum) throws Exception {
        //查询总共用户数量
        int totalRecords=adminUserDao.findTotalRecords();
        PageModel pm=new PageModel(curNum,totalRecords,12);
        //查找当前页用户的详细信息
        List<User> users=adminUserDao.findProductsByCidWithPage(pm.getStartIndex(),pm.getPageSize());
        pm.setList(users);
        pm.setUrl("/product/findAllProduct.do");
        return pm;
    }

    /**
     * 查找用户的浏览记录
     * @param uid 用户id
     * @param cur 当前页
     * @return
     * @throws Exception
     */
    @Override
    public PageModel findBrowseProductWithPage(String uid, int cur) throws Exception {
        //查找该用户浏览总条目
        int totalRecords=adminUserDao.findBrowseTotalRecords(uid);
        PageModel pm=new PageModel(cur,totalRecords,12);
        //查找用户在当前页的总浏览条数
        List<Browse> browses=adminUserDao.findBrowseProductWithPage(uid,pm.getStartIndex(),pm.getPageSize());
        pm.setList(browses);
        return pm;
    }
}
