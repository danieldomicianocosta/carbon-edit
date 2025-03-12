package com.carbon.refactor.application.util;

import java.util.List;
import java.util.stream.Collectors;

import com.carbon.refactor.application.dto.response.PossibleNextStatusesResponseDTO;
import com.carbon.refactor.domain.enums.ProposalStatus;

/**
 * Utility class for proposal status operations.
 */
public class ProposalStatusUtil {
    
    /**
     * Maps a list of status IDs to StatusDTO objects with descriptions.
     *
     * @param statusIds The list of status IDs to map
     * @return A list of StatusDTO objects with descriptions
     */
    public static List<PossibleNextStatusesResponseDTO.StatusDTO> mapStatusIdsToStatusDTOs(List<Integer> statusIds) {
        return statusIds.stream()
                .map(statusId -> {
                    ProposalStatus nextStatus = ProposalStatus.findById(statusId);
                    String description = nextStatus != null ? nextStatus.getDescription() : "Unknown";
                    return PossibleNextStatusesResponseDTO.StatusDTO.builder()
                            .id(statusId)
                            .description(description)
                            .build();
                })
                .collect(Collectors.toList());
    }
}
