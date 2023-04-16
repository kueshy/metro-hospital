package com.metro.newborns.dto;

import com.metro.newborns.entities.NewBorn;
import com.metro.newborns.enums.NewBornGender;
import com.metro.newborns.enums.MartialStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.ReadOnlyProperty;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NewBornDto {

    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private Long unique_id;

    @NotEmpty
    private String child_name;

    @NotEmpty
    private String child_place_of_birth;

    @NotEmpty
    private String child_registration_number;

    @NotEmpty
    private LocalDate child_date_of_birth;

    @NotEmpty
    private NewBornGender child_gender;

    @NotEmpty
    private Double child_weight;

    @NotEmpty
    private String mothers_name;

    @NotEmpty
    private Long mothers_age;

    @NotEmpty
    private String mothers_occupation;

    @NotEmpty
    private MartialStatus mothers_martial_status;


}
