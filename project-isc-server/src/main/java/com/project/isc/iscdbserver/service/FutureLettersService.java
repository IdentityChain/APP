package com.project.isc.iscdbserver.service;

import com.project.isc.iscdbserver.entity.FutureLetters;
import com.project.isc.iscdbserver.repository.FutureLettersRepository;
import com.project.isc.iscdbserver.statusType.ISCConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class FutureLettersService {

    @Autowired
    private FutureLettersRepository futureLettersRepository;

    /**
     * 保存信件到数据库
     * @param userid
     * @param flMessage
     * @return
     */
    public String sendMessage(String userid,String flMessage,String flemail,Date sendTime,String flIsPublic,String flEncryption){
        /**
         * 	private String userid;
         *
         * 	// 用户内容
         * 	private String flMessage;
         *
         * 	// 寄送到的邮箱
         * 	private String flemail;
         *
         * 	//寄送时间
         * 	private Date sendTime;
         *
         * 	// 状态-是否公开
         * 	private String flIsPublic;
         *
         * 	// 是否加密或者加密方式
         * 	private String flEncryption;
         */
        //保存到数据库
        FutureLetters futureLetters = new FutureLetters();
        //保存用户填写信息
        futureLetters.setUserId(userid);
        futureLetters.setFlMessage(flMessage);
        futureLetters.setFlemail(flemail);
        futureLetters.setSendTime(sendTime);
        futureLetters.setFlIsPublic(flIsPublic);
        futureLetters.setFlEncryption(flEncryption);
        //公共的信息
        futureLetters.setFlStatus(ISCConstant.ISC_FL_STATUS_NEW);
        futureLetters.setCreateTime(new Date());

        futureLettersRepository.save(futureLetters);
//        //保存到区块链
//        getBlockChainID(futureLetters.getFlId());
        return futureLetters.getFlId();
    }

    /**
     *     // 信件ID
     *     @Id
     *     @GeneratedValue(generator = "jpa-uuid")
     *     @Column(length = 64)
     *     private String flId;
     *
     *     // 用户id
     *     @Column
     *     private String userId;
     *
     *     // 用户内容
     *     @Column
     *     private String flMessage;
     *
     *     //创建时间
     *     @Column
     *     @Temporal(TemporalType.TIMESTAMP)
     *     private Date createTime;
     *
     *     //写入时间
     *     @Column
     *     @Temporal(TemporalType.TIMESTAMP)
     *     private Date writeTime;
     *
     *     // 寄送到的邮箱
     *     @Column
     *     private String flemail;
     *
     *     //寄送时间
     *     @Column
     *     @Temporal(TemporalType.TIMESTAMP)
     *     private Date sendTime;
     *
     *     // 状态-是否已写入区块链
     *     @Column
     *     private String flStatus;
     *
     *     // 状态-是否公开
     *     @Column
     *     private String flIsPublic;
     *
     *     // 写入区块链ID
     *     @Column
     *     private String blockChain;
     *
     *     // 是否加密或者加密方式
     *     @Column
     *     private String flEncryption;
     * 保存到区块链
     * @param flId
     * @return
     */
    public String getBlockChainID(String flId){
        //保存到区块链
        return null;
    }

    /**
     * 更新邮件信息
     * @param futureLetters
     * @return
     */
    public FutureLetters updateFutureLetters(FutureLetters futureLetters){
        futureLettersRepository.save(futureLetters);
        return futureLetters;
    }

    /**
     *
     * @param flId
     * @return
     */
    public FutureLetters getFutureLetters(String flId){
        FutureLetters futureLetters = futureLettersRepository.getOne(flId);
        return futureLetters;
    }

    /**
     *
     * @return
     */
    public long getFutureLettersCount(){
        return futureLettersRepository.count();
    }

    /**
     * 得到最新的10条公开信
     */
    public List<FutureLetters> getFutureLetterTop10Public(){
        Pageable pageable = new PageRequest(0, 10, Sort.Direction.DESC, "createTime");
        List<FutureLetters> lettersList = futureLettersRepository.findByFlIsPublicOrderByCreateTimeTop10(ISCConstant.ISC_FL_IS_PUBLIC_TRUE,pageable);
        return lettersList;
    }

    /**
     * 分页查询公开信箱
     * @param page
     * @param pagesize
     * @return
     */
    public List<FutureLetters> getFutureLetterList(int page,int pagesize){
        Pageable pageable = new PageRequest(page, pagesize, Sort.Direction.DESC, "createTime");
        List<FutureLetters> lettersList = futureLettersRepository.findByFlIsPublicOrderByCreateTimeTop10(ISCConstant.ISC_FL_IS_PUBLIC_TRUE,pageable);
        return lettersList;
    }
}
