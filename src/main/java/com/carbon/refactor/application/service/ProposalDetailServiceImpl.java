package com.carbon.refactor.application.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.carbon.refactor.domain.entity.ProposalDetail;
import com.carbon.refactor.domain.exception.BusinessException;
import com.carbon.refactor.domain.exception.EntityNotFoundException;
import com.carbon.refactor.domain.exception.ForeignKeyConstraintViolationException;
import com.carbon.refactor.domain.repository.ProposalDetailRepository;
import com.carbon.refactor.domain.service.ProposalDetailService;
import com.carbon.refactor.domain.service.ProposalService;
import com.carbon.refactor.support.domain.entity.Channel;
import com.carbon.refactor.support.domain.service.ChannelService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProposalDetailServiceImpl implements ProposalDetailService {
    
    private final ProposalDetailRepository proposalDetailRepository;
    private final ProposalService proposalService;
    private final ChannelService channelService;
    
    @Override
    @Transactional(readOnly = true)
    public List<ProposalDetail> findAll() {
        return proposalDetailRepository.findAll();
    }
    
    @Override
    @Transactional(readOnly = true)
    public ProposalDetail findById(Integer id) {
        return proposalDetailRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("ProposalDetail", id));
    }
    
    @Override
    @Transactional(readOnly = true)
    public ProposalDetail findByProposalId(Integer proposalId) {
        return proposalDetailRepository.findByProposalId(proposalId)
                .orElseThrow(() -> new EntityNotFoundException("ProposalDetail for Proposal", proposalId));
    }
    
    @Override
    @Transactional
    public ProposalDetail create(ProposalDetail proposalDetail) {
        validateForeignKeys(proposalDetail);
        
        // Check if proposal already has a detail
        if (proposalDetailRepository.existsByProposalId(proposalDetail.getProposal().getId())) {
            throw new IllegalArgumentException("Proposal already has a detail record");
        }
        
        // Validate channel and partner relationship
        validateChannelPartnerRelationship(proposalDetail);
        
        return proposalDetailRepository.save(proposalDetail);
    }
    
    @Override
    @Transactional
    public ProposalDetail update(Integer id, ProposalDetail proposalDetail) {
        ProposalDetail existingProposalDetail = findById(id);
        validateForeignKeys(proposalDetail);
        
        // Check if proposal is being changed and if the new one already has a detail
        if (proposalDetail.getProposal() != null && 
                !proposalDetail.getProposal().getId().equals(existingProposalDetail.getProposal().getId()) && 
                proposalDetailRepository.existsByProposalId(proposalDetail.getProposal().getId())) {
            throw new IllegalArgumentException("New proposal already has a detail record");
        }
        
        // Validate channel and partner relationship
        validateChannelPartnerRelationship(proposalDetail);
        
        proposalDetail.setId(id);
        
        return proposalDetailRepository.save(proposalDetail);
    }
    
    @Override
    @Transactional
    public void delete(Integer id) {
        if (!proposalDetailRepository.existsById(id)) {
            throw new EntityNotFoundException("ProposalDetail", id);
        }
        
        proposalDetailRepository.deleteById(id);
    }
    
    @Override
    @Transactional(readOnly = true)
    public boolean existsById(Integer id) {
        return proposalDetailRepository.existsById(id);
    }
    
    @Override
    @Transactional(readOnly = true)
    public boolean existsByProposalId(Integer proposalId) {
        return proposalDetailRepository.existsByProposalId(proposalId);
    }
    
    /**
     * Validates that if the Channel has hasPartner=true, then partnerId must be provided.
     * 
     * @param proposalDetail The ProposalDetail to validate
     * @throws BusinessException if validation fails
     */
    private void validateChannelPartnerRelationship(ProposalDetail proposalDetail) {
        if (proposalDetail.getChannelId() != null) {
            Optional<Channel> channelOpt = channelService.findById(proposalDetail.getChannelId());
            if (channelOpt.isPresent()) {
                Channel channel = channelOpt.get();
                if (channel.isHasPartner() && proposalDetail.getPartnerId() == null) {
                    throw new BusinessException("Partner ID (ptnId) is required when the Channel has partners (hasPartner=true)");
                }
            }
        }
    }
    
    private void validateForeignKeys(ProposalDetail proposalDetail) {
        // Validate proposal exists
        if (proposalDetail.getProposal() != null) {
            Integer proposalId = proposalDetail.getProposal().getId();
            if (!proposalService.existsById(proposalId)) {
                throw new ForeignKeyConstraintViolationException("Proposal", "pps_id", proposalId);
            }
        } else {
            throw new IllegalArgumentException("Proposal is required");
        }
        
        // Validate seller exists
        if (proposalDetail.getSellerId() != null) {
            if (proposalDetail.getSellerId() <= 0) {
                throw new ForeignKeyConstraintViolationException("Seller", "sel_id", proposalDetail.getSellerId());
            }
        }
        
        // Validate internal sale seller exists
        if (proposalDetail.getInternSaleSellerId() != null) {
            if (proposalDetail.getInternSaleSellerId() <= 0) {
                throw new ForeignKeyConstraintViolationException("Seller", "intern_sale_sel_id", proposalDetail.getInternSaleSellerId());
            }
        }
        
        // Validate channel exists
        if (proposalDetail.getChannelId() != null) {
            if (proposalDetail.getChannelId() <= 0) {
                throw new ForeignKeyConstraintViolationException("Channel", "chn_id", proposalDetail.getChannelId());
            }
        }
        
        // Validate partner exists
        if (proposalDetail.getPartnerId() != null) {
            if (proposalDetail.getPartnerId() <= 0) {
                throw new ForeignKeyConstraintViolationException("Partner", "ptn_id", proposalDetail.getPartnerId());
            }
        }
        
        // Validate user exists
        if (proposalDetail.getUserId() != null) {
            if (proposalDetail.getUserId() <= 0) {
                throw new ForeignKeyConstraintViolationException("User", "usr_id", proposalDetail.getUserId());
            }
        }
        
        // Validate intern sale additive exists
        if (proposalDetail.getInternSaleAdditive() != null) {
            if (proposalDetail.getInternSaleAdditive() <= 0) {
                throw new ForeignKeyConstraintViolationException("Seller", "intern_sale_additive", proposalDetail.getInternSaleAdditive());
            }
        }
        
        // Validate seller additive exists
        if (proposalDetail.getSellerAdditive() != null) {
            if (proposalDetail.getSellerAdditive() <= 0) {
                throw new ForeignKeyConstraintViolationException("Seller", "seller_additive", proposalDetail.getSellerAdditive());
            }
        }
    }
}
