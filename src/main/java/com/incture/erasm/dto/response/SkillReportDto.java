package com.incture.erasm.dto.response;

public class SkillReportDto {

    private Long skillId;
    private String skillName;

    public SkillReportDto() {
    }

    public SkillReportDto(Long skillId, String skillName) {
        this.skillId = skillId;
        this.skillName = skillName;
    }

    public Long getSkillId() {
        return skillId;
    }

    public void setSkillId(Long skillId) {
        this.skillId = skillId;
    }

    public String getSkillName() {
        return skillName;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }
}