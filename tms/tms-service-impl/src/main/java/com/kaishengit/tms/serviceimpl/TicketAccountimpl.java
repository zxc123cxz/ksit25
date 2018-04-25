package com.kaishengit.tms.serviceimpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kaishengit.tms.TicketAccountService;
import com.kaishengit.tms.entity.*;
import com.kaishengit.tms.exception.ServiceException;
import com.kaishengit.tms.mapper.StoreAccountMapper;
import com.kaishengit.tms.mapper.TicketStoreMapper;
import com.kaishengit.tms.mapper.TicketsMapper;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class TicketAccountimpl implements TicketAccountService {

    @Autowired
    private TicketStoreMapper ticketStoreMapper;

    @Autowired
    private StoreAccountMapper storeAccountMapper;

    @Autowired
    private TicketsMapper ticketsMapper;

    private static final Logger logger = LoggerFactory.getLogger(TicketAccountimpl.class);

    /*
     * 保存售票点相关信息
     *
     * @date 2018/4/19
     * @param
     * @return
     */
    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void save(TicketStore ticketStore) {
        // 保存售票点信息
        if(ticketStore != null ){
            ticketStore.setCreateTime(new Date());
            ticketStoreMapper.insertSelective(ticketStore);
            //保存售票登陆用户
            StoreAccount storeAccount = new StoreAccount();
            storeAccount.setId(ticketStore.getId());
            storeAccount.setCreateTime(new Date());
            storeAccount.setStoreAccount(ticketStore.getStoreTel());

            storeAccount.setStorePassword(ticketStore.getStoreTel().substring(5));
            storeAccount.setStoreState(StoreAccount.NOR_MAL);

            storeAccountMapper.insertSelective(storeAccount);

            ticketStore.setStoreAccountId(storeAccount.getId());
            ticketStoreMapper.updateByPrimaryKeySelective(ticketStore);
            logger.info("{},添加成功",ticketStore);
        }

    }

    /*
     * 根据Id 查出售票点信息
     * @date 2018/4/19
     * @param
     * @return
     */
    @Override
    public TicketStore FindTicketStoreById(Integer id) {
       TicketStore ticketStore =  ticketStoreMapper.selectByPrimaryKey(id);
       return ticketStore;
    }

    /*
     * 修改售票点信息
     * @date 2018/4/19
     * @param
     * @return
     */
    @Override
    public void update(TicketStore ticketStore) {
        if(ticketStore !=null){
            //查询数据库的数据
           StoreAccount storeAccount = storeAccountMapper.selectByPrimaryKey(ticketStore.getStoreAccountId());
            if(storeAccount!=null){
                if(!ticketStore.getStoreTel().equals(storeAccount.getStoreAccount())){
                        //设置新手机号的和密码
                    storeAccount.setStorePassword(ticketStore.getStoreTel().substring(5));
                    storeAccount.setStoreAccount(ticketStore.getStoreTel());
                    storeAccountMapper.updateByPrimaryKeySelective(storeAccount);

                }
            }
            ticketStoreMapper.updateByPrimaryKeySelective(ticketStore);

            logger.info("{},修改售票信息",ticketStore);
        }
    }

    /*
     *根据Id删除售票点信息 和售票人信息
     * @date 2018/4/19
     * @param
     * @return
     */
    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void DeleteFindById(Integer id) throws ServiceException{
        if(id != null){
           //删除售票人的信息
            TicketStore ticketStore = ticketStoreMapper.selectByPrimaryKey(id);
            System.out.println(ticketStore.getStoreAccountId());
            storeAccountMapper.deleteByPrimaryKey(ticketStore.getStoreAccountId());

            //删除售票点信息
            ticketStoreMapper.deleteByPrimaryKey(id);
            logger.info("{}，删除售票点信息成功",ticketStore);
        }



    }


    /*
     * 分页查询 和模糊查询
     * @date 2018/4/20
     * @param
     * @return
     */
    @Override
    public PageInfo<TicketStore> findPageNoTicketStore(Integer pageNo, Map<String, Object> storeMap) {
        //设置每页的数量
        PageHelper.startPage(pageNo,2);

        //获取模糊参数
        String storeName = (String) storeMap.get("storeName");
        String storeManager = (String) storeMap.get("storeManager");
        String storeTel = (String) storeMap.get("storeTel");

        //模糊查询
        TicketStoreExample ticketStoreExample = new TicketStoreExample();
        TicketStoreExample.Criteria criteria = ticketStoreExample.createCriteria();

        if(StringUtils.isNotEmpty(storeName)){
            criteria.andStoreNameLike("%"+storeManager+"%");
        }
        if(StringUtils.isNotEmpty(storeManager)){
            criteria.andStoreManagerLike("%"+storeManager+"%");
        }

        if(StringUtils.isNotEmpty(storeTel)){
            criteria.andStoreTelLike("%"+storeTel+"%");
        }

        //根据id倒序排
        ticketStoreExample.setOrderByClause("id desc");
        List<TicketStore> storeList = ticketStoreMapper.selectByExample(ticketStoreExample);
        return new PageInfo<>(storeList);
    }

    /*
     *根据id  查看法人的详细信息
     * @date 2018/4/20
     * @param
     * @return
     */
    @Override
    public StoreAccount FindStoreAccount(Integer id) {

        return storeAccountMapper.selectByPrimaryKey(id);
    }


    /*
     * 根据id禁用 销售人的账号
     * @date 2018/4/20
     * @param
     * @return
     */
    @Override
    public void findByIdLock(Integer id) throws  ServiceException {
        if(id != null && StringUtils.isNotEmpty("id")){
            //根据id查询数据
            StoreAccount storeAccount = storeAccountMapper.selectByPrimaryKey(id);

            storeAccount.setStoreState(StoreAccount.DISA_BLE);

            storeAccountMapper.updateByPrimaryKeySelective(storeAccount);

            logger.info("{},禁用账号",storeAccount);
        }

    }
    /*
     *  恢复售票账号
     * @date 2018/4/20
     * @param
     * @return
     */
    @Override
    public void findByIdRocover(Integer id) {
        StoreAccount storeAccount = storeAccountMapper.selectByPrimaryKey(id);
        storeAccount.setStoreState(StoreAccount.NOR_MAL);

        storeAccountMapper.updateByPrimaryKeySelective(storeAccount);

        logger.info("{},恢复账号",storeAccount);

    }


    /*
     * 查询盘点统计
     * @date 2018/4/23
     * @param
     * @return
     */
    @Override
    public Map<String, Long> findCountTicket() {

        return ticketsMapper.countByState();
    }


    /*
     *  查询出售票点的和售票人的信息
     * @date 2018/4/19
     * @param
     * @return
     */
    @Override
    public List<TicketStore> findAllTicketStore() {
        TicketStoreExample ticketStoreExample = new TicketStoreExample();

        return ticketStoreMapper.selectByExample(ticketStoreExample);
    }






}
