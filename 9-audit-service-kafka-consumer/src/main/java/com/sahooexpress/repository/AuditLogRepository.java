package com.sahooexpress.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.sahooexpress.collections.AuditLog;

public interface AuditLogRepository extends MongoRepository<AuditLog,String> {

}
