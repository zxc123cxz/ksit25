package com.kaishengit.tms.serviceimpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kaishengit.tms.TicketOutRecordService;
import com.kaishengit.tms.entity.*;
import com.kaishengit.tms.exception.ServiceException;
import com.kaishengit.tms.mapper.TicketOutRecordMapper;
import com.kaishengit.tms.mapper.TicketStoreMapper;
import com.kaishengit.tms.mapper.TicketsMapper;
import com.kaishengit.tms.util.ShiroUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TicketOutRecordimpl implements TicketOutRecordService {

    @Autowired
    private TicketOutRecordMapper ticketOutRecordMapper;

    private static final Logger logger = LoggerFactory.getLogger(TicketOutRecordimpl.class);

    @Autowired
    private TicketsMapper ticketsMapper;

    @Autowired
    private TicketStoreMapper ticketStoreMapper;

    @Autowired
    private ShiroUtil shiroUtil;

   /*
     * 获取所有年票下发的数据（home首页）
     * @date 2018/4/23
     * @param
     * @return
     */
    @Override
    public PageInfo<TicketOutRecord> findAllTicketOutRecord(Integer pageNo) {
        PageHelper.startPage(pageNo,5);

        TicketOutRecordExample ticketOutRecordExample   =  new TicketOutRecordExample();
        //ticketOutRecordExample.setOrderByClause("id,desc");

        List<TicketOutRecord> ticketOutRecords = ticketOutRecordMapper.selectByExample(ticketOutRecordExample);



        return new PageInfo<>(ticketOutRecords);
    }

    /*
     *  保存年票下发的信息
     * @date 2018/4/23
     * @param
     * @return
     */
    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void save(TicketOutRecord ticketOutRecord) throws ServiceException {

        //先判断是否年票的票号一样（已经有过的票号）
        List<Tickets>  ticketsList =  ticketsMapper.findByBeginNumAndEndNum(ticketOutRecord.getBiginTicketNum(),ticketOutRecord.getEndTicketNum());

        for(Tickets tickets : ticketsList){
            if(!Tickets.TICKET_STATE_IN_STORE.equals(tickets.getTicketState()) && !Tickets.TICKET_STATE_IN_STORE.equals(tickets.getTicketState())){
                    throw new  ServiceException("该票段已经有下发,或已存在该票号，请重新输入");
            }
        }

        //获取下发的售票点对象的售票点地址
        TicketStore ticketStore = ticketStoreMapper.selectByPrimaryKey(ticketOutRecord.getStoreAccountId());

        ticketOutRecord.setStoreAccountName(ticketStore.getStoreName());


        //下票的数量
        int total = ticketsList.size();
        //获得的下票的总金额
       // BigDecimal price = ticketOutRecord.getPrice().multiply(new BigDecimal(total));
        BigDecimal price = ticketOutRecord.getPrice();
        BigDecimal priceNum = price.multiply(new BigDecimal(total));
        ticketOutRecord.setTotalPrice(priceNum);

        ticketOutRecord.setCreateTime(new Date());
        ticketOutRecord.setContent(ticketOutRecord.getBiginTicketNum()+"-"+ticketOutRecord.getEndTicketNum());
        ticketOutRecord.setState(TicketOutRecord.PAID);
        ticketOutRecord.setTotalNum(total);



        //获取当前登陆的对象
        Account account = shiroUtil.getCurrAccount();
        ticketOutRecord.setOutAccountId(account.getId());
        ticketOutRecord.setOutAccountName(account.getAccountName());

        ticketOutRecordMapper.insertSelective(ticketOutRecord);


        //下票成功后 修改年票记录
        for(Tickets tickets : ticketsList){
            tickets.setTicketState(Tickets.TICKET_STATE_OUT_STORE);

            ticketsMapper.updateByPrimaryKey(tickets);

        }

        logger.info("{},下发成功",account);

    }


    /*
     *  删除年票下发的记录
     *
     *  已经支付的不能删除，未支付的可以删除
     * @date 2018/4/23
     * @param
     * @return
     */
    @Override
    public void delete(Integer id) throws ServiceException {
        TicketOutRecord ticketOutRecord = ticketOutRecordMapper.selectByPrimaryKey(id);


        // 删除未支付
        if(TicketOutRecord.NOT_PATD.equals(ticketOutRecord.getState())){
                ticketOutRecordMapper.deleteByPrimaryKey(id);
        }

        Account account = shiroUtil.getCurrAccount();

        logger.info("{},删除成功",account);
    }


}
