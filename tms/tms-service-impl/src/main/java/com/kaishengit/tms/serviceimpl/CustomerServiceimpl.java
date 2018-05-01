package com.kaishengit.tms.serviceimpl;

import com.kaishengit.tms.CustomerService;
import com.kaishengit.tms.entity.*;

import com.kaishengit.tms.exception.ServiceException;
import com.kaishengit.tms.mapper.CustomerMapper;
import com.kaishengit.tms.mapper.TicketOrderMapper;
import com.kaishengit.tms.mapper.TicketsMapper;
import com.kaishengit.tms.util.SnowFlake;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


@Service
public class CustomerServiceimpl implements CustomerService {

    private Logger logger = LoggerFactory.getLogger(CustomerServiceimpl.class);

    @Autowired
    private CustomerMapper customerMapper;


    @Autowired
    private TicketsMapper ticketsMapper;

    /*
     *
     * @date 2018/4/28
     * @param
     * @return
     */
    @Autowired
    private TicketOrderMapper ticketOrderMapper;

    /*
     *  客户
     * @date 2018/4/28
     * @param
     * @return
     */
    @Override
    public void save(Customer customer) {
        customer.setCreateTime(new Date());

        //保存Tickets年票记录的客户id
        Tickets tickets = new Tickets();
        tickets.setCustomerId(customer.getTicketId());

        
    }



    /*  保存客户信息（customer）
     *  ticketNum(票号)
     *  customer销售对象的信息
     *  price 销售的金额
     *  ticketStore 售票点对象
     * @date 2018/4/29
     *
     * @param
     * @return
     */
    @Override
    public void saveCustomer(Customer customer, String ticketNum, BigDecimal price, TicketStore ticketStore) throws ServiceException {
        //根据年票查询 是否是已下发 如果不是一下发那就是销售点没有这个票号，则保存失败
        TicketsExample ticketsExample = new TicketsExample();
        ticketsExample.createCriteria().andTicketNumEqualTo(ticketNum);

        List<Tickets> ticketList = ticketsMapper.selectByExample(ticketsExample);
        Tickets ticket = null;
       if(ticketList != null && ticketList.isEmpty()){
           ticket = ticketList.get(0);

           if(Tickets.TICKET_STATE_OUT_STORE.equals(ticket.getTicketState())) {
               if(ticket.getStoreAccountId().equals(ticketStore.getId())) {

                   //判断该用户是否办理过年票
                   CustomerExample customerExample = new CustomerExample();
                   customerExample.createCriteria().andCustomerIdCardEqualTo(customer.getCustomerIdCard());
                   List<Customer> customerList = customerMapper.selectByExample(customerExample);
                   if(customerList != null && !customerList.isEmpty()) {
                       Customer dbCustomer = customerList.get(0);
                       //查询用户当前绑定的年票
                       Tickets customerTicket = ticketsMapper.selectByPrimaryKey(dbCustomer.getTicketId());
                       if(customerTicket != null) {
                           if(Tickets.TICKET_STATE_SALE.equals(customerTicket.getTicketState())) {
                               throw new ServiceException("该用户已购买过年票，不能再次购买");
                           }
                       } else {
                           //用户存在，但未绑定年票
                           customer = dbCustomer;
                       }
                   } else {
                       //2保存客户
                       customer.setTicketId(ticket.getId());
                       customer.setCreateTime(new Date());
                       customerMapper.insertSelective(customer);
                   }


                   //3. 将该年票状态修改为[已销售]
                   ticket.setTicketState(Tickets.TICKET_STATE_SALE);
                   //设置年票有效期
                   ticket.setTicketValidityStart(new Date());

                   DateTime endDate = DateTime.now().plusYears(1);
                   ticket.setTicketVilidityEnd(endDate.toDate());

                   //绑定销售用户
                   ticket.setCustomerId(customer.getId());
                   //修改年票对象
                   ticketsMapper.updateByPrimaryKeySelective(ticket);

                   //4. 创建销售订单
                   TicketOrder ticketOrder = new TicketOrder();
                   ticketOrder.setCreateTime(new Date());
                   ticketOrder.setCustomerId(customer.getId());
                   ticketOrder.setStoreAccountId(ticketStore.getId());
                   ticketOrder.setTicketId(ticket.getId());
                   ticketOrder.setTicketOrderPrice(price);

                   //流水号
                   SnowFlake snowFlake = new SnowFlake(snowFlakeDataCenter,snowFlakeMachineId);
                   ticketOrder.setTicketOrderNum(String.valueOf(snowFlake.nextId()));
                   ticketOrder.setTicketOrderType(TicketOrder.ORDER_TYPE_NEW);

                   ticketOrderMapper.insertSelective(ticketOrder);



               } else {
                   throw new ServiceException("该年票不属于当前售票点，请核查");
               }
           } else {
               throw new ServiceException("该年票状态异常，请核查");
           }






       }else {
           throw new ServiceException("该票号不存在，请核查票号");
       }





    }
}
