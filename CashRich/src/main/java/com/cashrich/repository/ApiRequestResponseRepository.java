package com.cashrich.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cashrich.entity.ApiRequestResponse;

@Repository
public interface ApiRequestResponseRepository extends JpaRepository<ApiRequestResponse, Long> {
}