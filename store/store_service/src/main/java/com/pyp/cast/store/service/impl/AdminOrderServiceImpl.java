package com.pyp.cast.store.service.impl;

import com.pyp.cast.store.dao.IAdminOrderDao;
import com.pyp.cast.store.domain.PO.Order;
import com.pyp.cast.store.domain.PO.OrderItem;
import com.pyp.cast.store.domain.PO.Product;
import com.pyp.cast.store.domain.PO.User;
import com.pyp.cast.store.service.IAdminOrderService;
import com.pyp.cast.store.utils.MailUtils;
import com.pyp.cast.store.utils.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AdminOrderServiceImpl implements IAdminOrderService {
    @Autowired
    private IAdminOrderDao adminOrderDao;

    /**
     * 管理员查询所有订单并进行分页
     * @param curNum 当前页
     * @param statu 订单状态
     * @return
     * @throws Exception
     */
    @Override
    public PageModel findAllOrdersWithPage(int curNum,String statu) throws Exception {
        //查找的订单状态为statu的总记录条数
        int totalRecords=adminOrderDao.findTotalRecords(statu);
        PageModel pm=new PageModel(curNum,totalRecords,12);
        //查找订单状态为statu的所有订单信息
        List<Order> orders=adminOrderDao.findProductsByCidWithPage(statu,pm.getStartIndex(),pm.getPageSize());
        //将该订单的订单项封装进去
        for (Order order:orders) {
            //查找该订单下所有的订单项
            order.setList(adminOrderDao.findOrderItemsByOid(order.getOid()));
        }
        pm.setList(orders);
        return pm;
    }

    /**
     * 根据订单查询他的订单项
     * @param oid
     * @return
     * @throws Exception
     */
    @Override
    public List<OrderItem> findOrderItemsByOid(String oid) throws Exception {
        return adminOrderDao.findOrderItemsByOid(oid);
    }

    /**
     * 管理员发货，订单状态我为发货状态
     * 更改订单状态  statu=0：购物车内容  statu=1：订单已支付  statu=2：卖家已发货  statu=3：买家确认收货
     * @param oid 订单id
     * @return
     * @throws Exception
     */
    @Override
    public void updateOrderStatuByOid(String oid,String statu) throws Exception {
        Integer s = Integer.valueOf(statu);
        if (statu.equals("2")){
            //根据订单id，查找该订单的收货人邮箱
            String email = adminOrderDao.findEmailByOid(oid);
            //发送邮件给客户签收
            MailUtils.sendMail(email,oid);
            //更新订单状态为发货状态
        }
        //根据订单id，修改他的订单状态
        adminOrderDao.updateOrderStatuByOid(s,oid);
    }
}
