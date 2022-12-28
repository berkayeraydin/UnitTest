package com.twounit.unittest.dto;

import lombok.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of = {"id"}) // 2 dto nesnesi birbirine ne zaman eşittir.
// 2 tane kişi dto nesnesinin eşit olmasını sağlayan durum id lerinin eşit olmasıdır.
public class PersonDto {

    private Long id;

    @NotNull
    private String name;

    private String lastName;

    private List<String> addresses;
}