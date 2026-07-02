package com.incture.erasm.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.incture.erasm.dto.request.SkillRequestDto;
import com.incture.erasm.dto.response.SkillResponseDto;
import com.incture.erasm.service.SkillService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/skills")
public class SkillController {

    private final SkillService skillService;

    public SkillController(SkillService skillService) {
        this.skillService = skillService;
    }

    @PostMapping
    public ResponseEntity<SkillResponseDto> createSkill(
            @Valid @RequestBody SkillRequestDto requestDto) {

        return new ResponseEntity<>(
                skillService.createSkill(requestDto),
                HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SkillResponseDto> getSkillById(
            @PathVariable Long id) {

        return ResponseEntity.ok(skillService.getSkillById(id));
    }

    @GetMapping
    public ResponseEntity<List<SkillResponseDto>> getAllSkills() {

        return ResponseEntity.ok(skillService.getAllSkills());
    }

    @PutMapping("/{id}")
    public ResponseEntity<SkillResponseDto> updateSkill(
            @PathVariable Long id,
            @Valid @RequestBody SkillRequestDto requestDto) {

        return ResponseEntity.ok(
                skillService.updateSkill(id, requestDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSkill(
            @PathVariable Long id) {

        skillService.deleteSkill(id);

        return ResponseEntity.ok("Skill deleted successfully.");
    }
}