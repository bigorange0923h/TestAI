package org.bigorange.testai.mvc.model.dto;

import lombok.Data;
import org.bigorange.testai.constants.LanguageType;

@Data
public class RunTestDTO {
    private String url;
    private LanguageType languageType;
}
