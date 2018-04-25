package com.kaishengit.tms;
import com.github.pagehelper.PageInfo;
import com.kaishengit.tms.entity.TicketInRecord;
import com.kaishengit.tms.exception.ServiceException;


import java.util.List;


public interface TicketinRecordService {

    void save(TicketInRecord ticketInRecord) throws ServiceException;

    List<TicketInRecord> findAllTicketInrecord();

    PageInfo<TicketInRecord> findPageTicketInRecord(Integer pageNo);

    TicketInRecord findTicketInRecird(Integer id);

    void update(TicketInRecord ticketInRecord);

    void Delete(Integer id);
}
