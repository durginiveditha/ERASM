package com.incture.erasm.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.incture.erasm.dto.request.RoleRequestDto;
import com.incture.erasm.dto.response.RoleResponseDto;
import com.incture.erasm.entity.Role;
import com.incture.erasm.mapper.RoleMapper;
import com.incture.erasm.repository.RoleRepository;

import com.incture.erasm.exception.ResourceNotFoundException;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    // Create Role
    public RoleResponseDto createRole(RoleRequestDto requestDto) {

        Role role = RoleMapper.requestDtoToEntity(requestDto);

        Role savedRole = roleRepository.save(role);

        return RoleMapper.entityToResponseDto(savedRole);
    }

    // Get Role By Id
    public RoleResponseDto getRoleById(Long roleId) {

        Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        return RoleMapper.entityToResponseDto(role);
    }

    // Get All Roles
    public List<RoleResponseDto> getAllRoles() {

        return roleRepository.findAll()
                .stream()
                .map(RoleMapper::entityToResponseDto)
                .collect(Collectors.toList());
    }

    // Update Role
    public RoleResponseDto updateRole(Long roleId,
                                      RoleRequestDto requestDto) {

        Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        RoleMapper.updateEntityFromRequestDto(requestDto, role);

        Role updatedRole = roleRepository.save(role);

        return RoleMapper.entityToResponseDto(updatedRole);
    }

    // Delete Role
    public void deleteRole(Long roleId) {

        Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));;

        roleRepository.delete(role);
    }
}