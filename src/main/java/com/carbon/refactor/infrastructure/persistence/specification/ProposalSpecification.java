package com.carbon.refactor.infrastructure.persistence.specification;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import com.carbon.refactor.domain.entity.Proposal;
import com.carbon.refactor.domain.entity.ProposalDetail;
import com.carbon.refactor.domain.entity.ProposalDetailVehicle;

import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;

/**
 * Specification class for building dynamic queries for Proposal entities.
 * This class provides methods to create specifications based on various filter criteria.
 */
public class ProposalSpecification {
    
    /**
     * Create a specification for filtering proposals based on multiple criteria.
     *
     * @param statusIds List of status IDs to filter by
     * @param proposalNumber Proposal number to filter by
     * @param updateDateStart Start date for update date range
     * @param updateDateEnd End date for update date range
     * @param channelId Channel ID to filter by
     * @param partnerId Partner ID to filter by
     * @param vehicleId Vehicle ID to filter by
     * @param modelId Model ID to filter by
     * @return A specification for filtering proposals
     */
    public static Specification<Proposal> filterBy(
            List<Integer> statusIds,
            String proposalNumber,
            LocalDateTime updateDateStart,
            LocalDateTime updateDateEnd,
            Integer channelId,
            Integer partnerId,
            Integer vehicleId,
            Integer modelId) {
        
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            
            // Filter by status IDs
            if (statusIds != null && !statusIds.isEmpty()) {
                predicates.add(root.get("statusClaId").in(statusIds));
            }
            
            // Filter by proposal number
            if (proposalNumber != null && !proposalNumber.isEmpty()) {
                predicates.add(criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("proposalNumber")),
                        "%" + proposalNumber.toLowerCase() + "%"));
            }
            
            // Filter by update date range
            if (updateDateStart != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(
                        root.get("lastUpdateDate"), updateDateStart));
            }
            
            if (updateDateEnd != null) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(
                        root.get("lastUpdateDate"), updateDateEnd));
            }
            
            // Filter by channel ID, partner ID (requires join with ProposalDetail)
            if (channelId != null || partnerId != null) {
                // Use a subquery to find proposals that have a detail with the specified channel or partner
                query.distinct(true);
                
                // Join with ProposalDetail using the inverse relationship
                Join<Proposal, ProposalDetail> detailJoin = root.join("proposalDetails", jakarta.persistence.criteria.JoinType.LEFT);
                
                if (channelId != null) {
                    predicates.add(criteriaBuilder.equal(detailJoin.get("channelId"), channelId));
                }
                
                if (partnerId != null) {
                    predicates.add(criteriaBuilder.equal(detailJoin.get("partnerId"), partnerId));
                }
            }
            
            // Filter by vehicle ID, model ID (requires join with ProposalDetail and ProposalDetailVehicle)
            if (vehicleId != null || modelId != null) {
                // Use a subquery to find proposals that have a vehicle with the specified vehicle ID or model ID
                query.distinct(true);
                
                // Join with ProposalDetail using the inverse relationship
                Join<Proposal, ProposalDetail> detailJoin = root.join("proposalDetails", jakarta.persistence.criteria.JoinType.LEFT);
                
                // Join with ProposalDetailVehicle using the inverse relationship
                Join<ProposalDetail, ProposalDetailVehicle> vehicleJoin = detailJoin.join("proposalDetailVehicles", jakarta.persistence.criteria.JoinType.LEFT);
                
                if (vehicleId != null) {
                    predicates.add(criteriaBuilder.equal(vehicleJoin.get("vehicleId"), vehicleId));
                }
                
                if (modelId != null) {
                    predicates.add(criteriaBuilder.equal(vehicleJoin.get("modelId"), modelId));
                }
            }
            
            // Combine all predicates with AND
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
