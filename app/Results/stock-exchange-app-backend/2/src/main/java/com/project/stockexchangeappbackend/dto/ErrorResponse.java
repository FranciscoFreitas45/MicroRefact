package com.project.stockexchangeappbackend.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@ApiModel(description = "Error reponse.")
public class ErrorResponse {

    @ApiModelProperty(notes = "Http status code.")
    private int status;

    @ApiModelProperty(notes = "Message.")
    private String message;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @ApiModelProperty(notes = "List of errors.")
    private Object errors;

}