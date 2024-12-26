package org.example.fileservice.domain.dto.requests;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.LinkedHashMap;

@Data
@Schema(description = "Запрос на создание нового документа")
@Builder
@AllArgsConstructor
public class CreateFileRequest {

    @Schema(description = "Имя шаблона")
    @NotBlank(message = "Имя шаблона не может быть пустыми")
    @NotNull(message = "Имя шаблона не может быть null")
    String templateName;

    @Schema(description = "Поля, которые необходимо заменить и значения для подстановки")
    @NotEmpty(message = "Поля не могут быть пустыми")
    @NotNull(message = "Поля не могут быть null")
    LinkedHashMap<String, String> replacements = new LinkedHashMap<>();
}
