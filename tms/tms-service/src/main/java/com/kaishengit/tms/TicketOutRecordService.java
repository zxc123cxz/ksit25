package com.kaishengit.tms;

import com.github.pagehelper.PageInfo;
import com.kaishengit.tms.entity.TicketOutRecord;
import com.kaishengit.tms.exception.ServiceException;


import java.util.Map;

public interface TicketOutRecordService {
    PageInfo<TicketOutRecord> findAllTicketOutRecord(Integer pageNo);

    void save(TicketOutRecord ticketOutRecord) throws ServiceException;

    void delete(Integer id) throws  ServiceException;


    PageInfo<TicketOutRecord> findPageTicketOutRecors(Map<String, Object> map, Integer pageNo);

    TicketOutRecord findAllTicketOutRecordById(Integer id);

    void update(Integer id, String payType);
}
