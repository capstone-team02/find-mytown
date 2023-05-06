package com.team2.findmytown.dto.request;

import com.team2.findmytown.domain.entity.DistrictEntity;
import com.team2.findmytown.domain.entity.GuEntity;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@Data
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GuDTO {
    private Long guId;

    private String guName;

    private List<DistrictEntity> districtEntities = new ArrayList<>();

    public GuDTO(final GuEntity guEntity){
        this.guId = guEntity.getGuId();
        this.guName = guEntity.getGuName();
        this.districtEntities = guEntity.getDistrictEntities();
    }

    public  static GuEntity toGuEntity(final GuDTO guDTO){
        return GuEntity.builder()
                .guId(guDTO.getGuId())
                .guName(guDTO.getGuName())
                .districtEntities(guDTO.getDistrictEntities())
                .build();
    }
}
