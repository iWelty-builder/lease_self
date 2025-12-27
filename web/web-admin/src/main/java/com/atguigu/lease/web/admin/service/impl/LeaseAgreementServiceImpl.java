package com.atguigu.lease.web.admin.service.impl;

import ch.qos.logback.core.util.AggregationType;
import com.atguigu.lease.model.entity.*;
import com.atguigu.lease.web.admin.mapper.ApartmentInfoMapper;
import com.atguigu.lease.web.admin.mapper.LeaseAgreementMapper;
import com.atguigu.lease.web.admin.service.*;
import com.atguigu.lease.web.admin.vo.agreement.AgreementQueryVo;
import com.atguigu.lease.web.admin.vo.agreement.AgreementVo;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author liubo
 * @description 针对表【lease_agreement(租约信息表)】的数据库操作Service实现
 * @createDate 2023-07-24 15:48:00
 */
@Service
public class LeaseAgreementServiceImpl extends ServiceImpl<LeaseAgreementMapper, LeaseAgreement>
        implements LeaseAgreementService {

    @Autowired
    private ApartmentInfoService apartmentInfoService;
    @Autowired
    private RoomInfoService roomInfoService;
    @Autowired
    private PaymentTypeService paymentTypeService;
    @Autowired
    private LeaseTermService leaseTermService;

    @Autowired
    private LeaseAgreementMapper leaseAgreementMapper;
    @Override
    public AgreementVo getLeaseAgreementInfoById(Long id) {

        AgreementVo agreementVo = new AgreementVo();
        LeaseAgreement agreement = super.getById(id);

        BeanUtils.copyProperties(agreement,agreementVo);
        agreementVo.setApartmentInfo(apartmentInfoService.getById(agreement.getApartmentId()));
        agreementVo.setRoomInfo(roomInfoService.getById(agreement.getRoomId()));
        agreementVo.setPaymentType(paymentTypeService.getById(agreement.getPaymentTypeId()));
        agreementVo.setLeaseTerm(leaseTermService.getById(agreement.getLeaseTermId()));

        return agreementVo;
    }

    @Override
    public IPage<AgreementVo> pageAgreementByQuery(Page<AgreementVo> page, AgreementQueryVo queryVo) {
        return leaseAgreementMapper.pageAgreementByQuery(page,queryVo);
    }
}




