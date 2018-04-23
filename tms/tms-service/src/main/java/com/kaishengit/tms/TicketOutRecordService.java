package com.kaishengit.tms;

import com.github.pagehelper.PageInfo;
import com.kaishengit.tms.entity.TicketOutRecord;
import com.kaishengit.tms.exception.ServiceException;

public interface TicketOutRecordService {
    PageInfo<TicketOutRecord> findAllTicketOutRecord(Integer pageNo);

    void save(TicketOutRecord ticketOutRecord) throws ServiceException;

    void delete(Integer id) throws  ServiceException;
}
