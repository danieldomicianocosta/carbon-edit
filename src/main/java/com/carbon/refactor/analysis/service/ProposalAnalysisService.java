package com.carbon.refactor.analysis.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.carbon.refactor.analysis.dto.ProposalCountByStatusDTO;
import com.carbon.refactor.analysis.dto.ProposalsByStatusResponseDTO;

public interface ProposalAnalysisService {
    
    /**
     * Find all proposals by status.
     *
     * @param statusId The status ID to filter by
     * @return A list of proposals with the given status
     */
    List<ProposalsByStatusResponseDTO> findProposalsByStatus(Integer statusId);
    
    /**
     * Find all proposals by status with pagination.
     *
     * @param statusId The status ID to filter by
     * @param pageable The pagination information
     * @return A page of proposals with the given status
     */
    Page<ProposalsByStatusResponseDTO> findProposalsByStatus(Integer statusId, Pageable pageable);
    
    /**
     * Count proposals grouped by status.
     *
     * @return A list of DTOs containing the status ID, name, description, and count
     */
    List<ProposalCountByStatusDTO> countProposalsByStatus();
}
