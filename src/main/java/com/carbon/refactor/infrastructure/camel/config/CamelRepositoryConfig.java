package com.carbon.refactor.infrastructure.camel.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.carbon.refactor.application.mapper.ProposalDetailMapper;
import com.carbon.refactor.application.mapper.ProposalDetailVehicleMapper;
import com.carbon.refactor.application.mapper.ProposalMapper;
import com.carbon.refactor.application.util.ProposalStatusUtil;
import com.carbon.refactor.domain.repository.ProposalCommissionRepository;
import com.carbon.refactor.domain.repository.ProposalDocumentRepository;
import com.carbon.refactor.domain.repository.ProposalFupRepository;
import com.carbon.refactor.domain.service.ProposalStatusService;
import com.carbon.refactor.infrastructure.mapper.ProposalCommissionMapper;
import com.carbon.refactor.infrastructure.mapper.ProposalDocumentMapper;
import com.carbon.refactor.infrastructure.mapper.ProposalFupMapper;

import lombok.RequiredArgsConstructor;

/**
 * Configuration class to register repositories and services as beans for Camel routes.
 * This ensures that the beans are available with the expected names in the Camel registry.
 */
@Configuration
@RequiredArgsConstructor
public class CamelRepositoryConfig {

    private final ProposalCommissionRepository proposalCommissionRepository;
    private final ProposalDocumentRepository proposalDocumentRepository;
    private final ProposalFupRepository proposalFupRepository;
    private final ProposalStatusService proposalStatusService;
    private final ProposalMapper proposalMapper;
    private final ProposalDetailMapper proposalDetailMapper;
    private final ProposalDetailVehicleMapper proposalDetailVehicleMapper;
    private final ProposalCommissionMapper proposalCommissionMapper;
    private final ProposalDocumentMapper proposalDocumentMapper;
    private final ProposalFupMapper proposalFupMapper;

    /**
     * Register the ProposalCommissionRepository as a bean with the name "proposalCommissionRepository".
     * 
     * @return The ProposalCommissionRepository instance
     */
    @Bean(name = "proposalCommissionRepository")
    public ProposalCommissionRepository proposalCommissionRepositoryBean() {
        return proposalCommissionRepository;
    }

    /**
     * Register the ProposalDocumentRepository as a bean with the name "proposalDocumentRepository".
     * 
     * @return The ProposalDocumentRepository instance
     */
    @Bean(name = "proposalDocumentRepository")
    public ProposalDocumentRepository proposalDocumentRepositoryBean() {
        return proposalDocumentRepository;
    }

    /**
     * Register the ProposalFupRepository as a bean with the name "proposalFupRepository".
     * 
     * @return The ProposalFupRepository instance
     */
    @Bean(name = "proposalFupRepository")
    public ProposalFupRepository proposalFupRepositoryBean() {
        return proposalFupRepository;
    }

    /**
     * Register the ProposalStatusService as a bean with the name "proposalStatusService".
     * 
     * @return The ProposalStatusService instance
     */
    @Bean(name = "proposalStatusService")
    public ProposalStatusService proposalStatusServiceBean() {
        return proposalStatusService;
    }

    /**
     * Register the ProposalStatusUtil as a bean with the name "proposalStatusUtil".
     * 
     * @return A new instance of ProposalStatusUtil
     */
    @Bean(name = "proposalStatusUtil")
    public ProposalStatusUtil proposalStatusUtilBean() {
        return new ProposalStatusUtil();
    }
    
    /**
     * Register the ProposalMapper as a bean with the name "proposalMapper".
     * 
     * @return The ProposalMapper instance
     */
    @Bean(name = "proposalMapper")
    public ProposalMapper proposalMapperBean() {
        return proposalMapper;
    }
    
    /**
     * Register the ProposalDetailMapper as a bean with the name "proposalDetailMapper".
     * 
     * @return The ProposalDetailMapper instance
     */
    @Bean(name = "proposalDetailMapper")
    public ProposalDetailMapper proposalDetailMapperBean() {
        return proposalDetailMapper;
    }
    
    /**
     * Register the ProposalDetailVehicleMapper as a bean with the name "proposalDetailVehicleMapper".
     * 
     * @return The ProposalDetailVehicleMapper instance
     */
    @Bean(name = "proposalDetailVehicleMapper")
    public ProposalDetailVehicleMapper proposalDetailVehicleMapperBean() {
        return proposalDetailVehicleMapper;
    }
    
    /**
     * Register the ProposalCommissionMapper as a bean with the name "proposalCommissionMapper".
     * 
     * @return The ProposalCommissionMapper instance
     */
    @Bean(name = "proposalCommissionMapper")
    public ProposalCommissionMapper proposalCommissionMapperBean() {
        return proposalCommissionMapper;
    }
    
    /**
     * Register the ProposalDocumentMapper as a bean with the name "proposalDocumentMapper".
     * 
     * @return The ProposalDocumentMapper instance
     */
    @Bean(name = "proposalDocumentMapper")
    public ProposalDocumentMapper proposalDocumentMapperBean() {
        return proposalDocumentMapper;
    }
    
    /**
     * Register the ProposalFupMapper as a bean with the name "proposalFupMapper".
     * 
     * @return The ProposalFupMapper instance
     */
    @Bean(name = "proposalFupMapper")
    public ProposalFupMapper proposalFupMapperBean() {
        return proposalFupMapper;
    }
}
