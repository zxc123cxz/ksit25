package com.kaishengit.tms.mapper;

import com.kaishengit.tms.entity.Tickets;
import com.kaishengit.tms.entity.TicketsExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface TicketsMapper {
    long countByExample(TicketsExample example);

    int deleteByExample(TicketsExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Tickets record);

    int insertSelective(Tickets record);

    List<Tickets> selectByExample(TicketsExample example);

    Tickets selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Tickets record, @Param("example") TicketsExample example);

    int updateByExample(@Param("record") Tickets record, @Param("example") TicketsExample example);

    int updateByPrimaryKeySelective(Tickets record);

    int updateByPrimaryKey(Tickets record);

    void batchInsert(@Param("tacketList") List<Tickets> ticketsList);


    List<Tickets> findByBeginNumAndEndNum(@Param("beginNum") String biginTicketNum,
                                          @Param("endNum") String endTicketNum);

    Map<String,Long> countByState();
}