package com.carbon.refactor.support.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.carbon.refactor.support.application.service.ChannelServiceImpl;
import com.carbon.refactor.support.application.service.DocumentServiceImpl;
import com.carbon.refactor.support.application.service.PriceListServiceImpl;
import com.carbon.refactor.support.application.service.PriceProductServiceImpl;
import com.carbon.refactor.support.domain.repository.ChannelRepository;
import com.carbon.refactor.support.domain.repository.DocumentRepository;
import com.carbon.refactor.support.domain.repository.PriceListRepository;
import com.carbon.refactor.support.domain.repository.PriceProductRepository;
import com.carbon.refactor.support.domain.service.ChannelService;
import com.carbon.refactor.support.domain.service.DocumentService;
import com.carbon.refactor.support.domain.service.PriceListService;
import com.carbon.refactor.support.domain.service.PriceProductService;

/**
 * Configuration class for support-related beans.
 */
@Configuration
public class SupportConfig {
    
    /**
     * Creates a ChannelService bean.
     * 
     * @param channelRepository the channel repository
     * @return the channel service
     */
    @Bean
    public ChannelService channelService(ChannelRepository channelRepository) {
        return new ChannelServiceImpl(channelRepository);
    }
    
    /**
     * Creates a PriceProductService bean.
     * 
     * @param priceProductRepository the price product repository
     * @return the price product service
     */
    @Bean
    public PriceProductService priceProductService(PriceProductRepository priceProductRepository) {
        return new PriceProductServiceImpl(priceProductRepository);
    }
    
    /**
     * Creates a PriceListService bean.
     * 
     * @param priceListRepository the price list repository
     * @return the price list service
     */
    @Bean
    public PriceListService priceListService(PriceListRepository priceListRepository) {
        return new PriceListServiceImpl(priceListRepository);
    }
    
    /**
     * Creates a DocumentService bean.
     * 
     * @param documentRepository the document repository
     * @return the document service
     */
    @Bean
    public DocumentService documentService(DocumentRepository documentRepository) {
        return new DocumentServiceImpl(documentRepository);
    }
}
