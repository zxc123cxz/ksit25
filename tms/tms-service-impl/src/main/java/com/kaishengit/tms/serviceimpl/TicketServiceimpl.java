package com.kaishengit.tms.serviceimpl;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kaishengit.tms.TicketinRecordService;
import com.kaishengit.tms.entity.*;
import com.kaishengit.tms.mapper.TicketInRecordMapper;
import com.kaishengit.tms.mapper.TicketsMapper;
import com.kaishengit.tms.TicketinRecordService;
import com.kaishengit.tms.util.ShiroUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TicketServiceimpl implements TicketinRecordService {



     @Autowired
     private ShiroUtil shiroUtil;

     @Autowired
     private TicketInRecordMapper ticketInRecordMapper;

     @Autowired
     private TicketsMapper ticketsMapper;


     private static final Logger logger= LoggerFactory.getLogger(TicketServiceimpl.class);

   /*
     *  保存年票入库和年票下发的信息
     * @date 2018/4/20
     * @param
     * @return
     */
    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void save(TicketInRecord ticketInRecord) {
        //保存年票入库

        if(ticketInRecord != null){

            ticketInRecord.setCreateTime(new Date());

            Account account = shiroUtil.getCurrAccount();
            ticketInRecord.setAccountName(account.getAccountName());
            ticketInRecord.setAccountId(account.getId());

            //总票数 = 截至票数 - 起始票数 +1
            BigInteger start = new BigInteger(ticketInRecord.getBeginTicketNum());
            BigInteger end = new BigInteger(ticketInRecord.getEndTicketNum());

            BigInteger total = end.subtract(start).add(new BigInteger(String.valueOf(1)));
            ticketInRecord.setTotalNum(total.intValue());
            //内容
            ticketInRecord.setContent(ticketInRecord.getBeginTicketNum()+"-"+ticketInRecord.getEndTicketNum());

            ticketInRecordMapper.insertSelective(ticketInRecord);

            logger.info("{},新增年票入库,{},入库人",ticketInRecord,account);

            //保存年票记录
            //添加年票记录  多次添加
            List<Tickets> ticketsList = new ArrayList<>();
            for(int i= 0; i<total.intValue(); i++){
                Tickets tickets = new Tickets();
                tickets.setTicketInTime(new Date());
                tickets.setCreateTime(new Date());
                tickets.setTicketNum(start.add(new BigInteger(String.valueOf(i))).toString());
                tickets.setTicketState(Tickets.TICKET_STATE_IN_STORE);

                ticketsList.add(tickets);
            }
            //批量增加
            ticketsMapper.batchInsert(ticketsList);

            logger.info("{}，年票入库记录",ticketsList);


        }




    }

    /*
     *   查询年票入库的信息
     * @date 2018/4/21
     * @param
     * @return
     */
    @Override
    public List<TicketInRecord> findAllTicketInrecord() {
        TicketInRecordExample ticketInRecordExample = new TicketInRecordExample();

        return ticketInRecordMapper.selectByExample(ticketInRecordExample);
    }




    /*
     *  查询年票入库的信息
     * @date 2018/4/21
     * @param
     * @return
     */
    @Override
    public PageInfo<TicketInRecord> findPageTicketInRecord(Integer pageNo) {
        PageHelper.startPage(pageNo,5);
        TicketInRecordExample ticketInRecordExample = new TicketInRecordExample();
        List<TicketInRecord> ticketInRecords = ticketInRecordMapper.selectByExample(ticketInRecordExample);
        return new PageInfo<>(ticketInRecords);
    }

    /*
     *  根据id查询出 TicketInRecord（年票入库） 的信息
     * @date 2018/4/21
     * @param
     * @return
     */
    @Override
    public TicketInRecord findTicketInRecird(Integer id) {


        return  ticketInRecordMapper.selectByPrimaryKey(id);
    }

    /*
     *  修改年票入库
     * @date 2018/4/21
     * @param
     * @return
     */
    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void update(TicketInRecord ticketInRecord) {

        if(ticketInRecord != null){

            TicketInRecord ticks  = ticketInRecordMapper.selectByPrimaryKey(ticketInRecord.getId());
            System.out.println("ticks"+ticks);
            //获取原来的数据
            BigInteger start = new BigInteger(ticks.getBeginTicketNum());
            //BigInteger end = new BigInteger(ticks.getEndTicketNum());

            int total = ticks.getTotalNum();

            //删除原来的
            for(int i=0; i<total; i++){
                TicketsExample ticketsExample = new TicketsExample();
                ticketsExample.createCriteria().andTicketNumEqualTo(start.add(new BigInteger(String.valueOf(i))).toString());
                ticketsMapper.deleteByExample(ticketsExample);
            }
            ticketInRecordMapper.deleteByPrimaryKey(ticketInRecord.getId());



            //新增
            BigInteger starts = new BigInteger(ticketInRecord.getBeginTicketNum());
            BigInteger ends = new BigInteger(ticketInRecord.getEndTicketNum());

            BigInteger totalNum = ends.subtract(starts).add(new BigInteger(String.valueOf(1)));
            ticketInRecord.setTotalNum(totalNum.intValue());
            Account account = shiroUtil.getCurrAccount();
            ticketInRecord.setCreateTime(new Date());
            ticketInRecord.setAccountId(account.getId());
            ticketInRecord.setAccountName(account.getAccountName());
            ticketInRecord.setContent(ticketInRecord.getBeginTicketNum()+"-"+ticketInRecord.getEndTicketNum());
            ticketInRecordMapper.insertSelective(ticketInRecord);

            List<Tickets> ticketInRecords = new ArrayList<>();
            for(int i=0; i<totalNum.intValue(); i++){
                Tickets ticketss = new Tickets();
                ticketss.setTicketInTime(new Date());
                ticketss.setCreateTime(new Date());
                ticketss.setTicketNum(start.add(new BigInteger(String.valueOf(i))).toString());
                ticketss.setTicketState(Tickets.TICKET_STATE_IN_STORE);

                ticketInRecords.add(ticketss);
            }
            ticketsMapper.batchInsert(ticketInRecords);
            logger.info("{}，修改年票入库",account);
        }

    }


    /*
     *  删除年票入库记录
     * @date 2018/4/21
     * @param
     * @return
     */
    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void Delete(Integer id) {

        //删除年票记录Tickets //根据票号删除
        TicketInRecord ticketInRecord = ticketInRecordMapper.selectByPrimaryKey(id);
        int total = ticketInRecord.getTotalNum();

        BigInteger start = new BigInteger(ticketInRecord.getBeginTicketNum());
        for(int i=0; i<total; i++){
            TicketsExample ticketsExample = new TicketsExample();
            ticketsExample.createCriteria().andTicketNumEqualTo(start.add(new BigInteger(String.valueOf(i))).toString());
            ticketsMapper.deleteByExample(ticketsExample);
        }

        //删除年票入库记录 TicketInRecord
        ticketInRecordMapper.deleteByPrimaryKey(id);

        logger.info("{}, 删除年票记录",ticketInRecord.getAccountName());

    }


}
