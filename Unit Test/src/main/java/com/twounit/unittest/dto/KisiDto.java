package com.twounit.unittest.dto;

import lombok.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of = {"id"})
public class KisiDto {

    private Long id;

    @NotNull
    private String adi;

    private String soyadi;

    private List<String> adresler;
}