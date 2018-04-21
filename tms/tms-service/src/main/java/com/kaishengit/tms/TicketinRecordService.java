package com.kaishengit.tms;
import com.github.pagehelper.PageInfo;
import com.kaishengit.tms.entity.TicketInRecord;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TicketinRecordService {

    void save(TicketInRecord ticketInRecord);

    List<TicketInRecord> findAllTicketInrecord();

    PageInfo<TicketInRecord> findPageTicketInRecord(Integer pageNo);

    TicketInRecord findTicketInRecird(Integer id);

    void update(TicketInRecord ticketInRecord);

    void Delete(Integer id);
}
