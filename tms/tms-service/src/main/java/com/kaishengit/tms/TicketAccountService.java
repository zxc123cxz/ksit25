package com.kaishengit.tms;


import com.github.pagehelper.PageInfo;
import com.kaishengit.tms.entity.StoreAccount;
import com.kaishengit.tms.entity.TicketStore;
import com.kaishengit.tms.exception.ServiceException;



import java.util.List;
import java.util.Map;

public interface TicketAccountService {

    void save(TicketStore ticketStore);

    TicketStore FindTicketStoreById(Integer id);

    void update(TicketStore ticketStore);

    void DeleteFindById (Integer id)throws ServiceException;

    List<TicketStore> findAllTicketStore();

    PageInfo<TicketStore> findPageNoTicketStore(Integer pageNo, Map<String, Object> storeMap);


    StoreAccount FindStoreAccount(Integer id);

    TicketStore findByIdLock(Integer id) throws ServiceException;

    void findByIdRocover(Integer id);

    Map<String,Long> findCountTicket();

    TicketStore findById(Integer id);

    StoreAccount findByMobiles(String userMobile);


    Map<String,Long> findcountTicketStore(Integer id);
}
