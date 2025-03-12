package com.carbon.refactor.support.domain.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Domain entity representing the document table.
 */
@Entity
@Table(name = "document")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Document {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "doc_id")
    private Integer id;
    
    @Column(name = "file_name")
    private String fileName;
    
    @Column(name = "content_type")
    private String contentType;
    
    @Column(name = "description")
    private String description;
    
    @Column(name = "file_path")
    private String filePath;
    
    @Column(name = "create_date")
    private LocalDateTime createDate;
    
    @Column(name = "usr_id")
    private Integer userId;
    
    @Column(name = "type_cla_id")
    private Integer typeClaId;
    
    @Column(name = "file_hash")
    private String fileHash;
}
