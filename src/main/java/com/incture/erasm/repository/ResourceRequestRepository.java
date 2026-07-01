package com.incture.erasm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.incture.erasm.entity.ResourceRequest;

public interface ResourceRequestRepository extends JpaRepository<ResourceRequest, Long> {

}