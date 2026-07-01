package com.incture.erasm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.incture.erasm.entity.AuditLog;

public interface AuditLogRepository extends JpaRepository<AuditLog, Long> {

}