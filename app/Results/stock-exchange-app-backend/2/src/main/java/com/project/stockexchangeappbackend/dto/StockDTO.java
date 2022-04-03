package com.project.stockexchangeappbackend.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@Builder
@AllArgsConstructor
@Data
@ApiModel(description = "Stock object stored in database.")
public class StockDTO {

    @ApiModelProperty(notes = "The stock's id.")
    private Long id;

    @ApiModelProperty(notes = "The stock's name.")
    private String name;

    @ApiModelProperty(notes = "The stock's abbreviation.")
    private String abbreviation;

    @ApiModelProperty(notes = "The stock's average current price per unit.")
    private BigDecimal currentPrice;

    @ApiModelProperty(notes = "The stock's amount of units.")
    private Integer amount;

    @ApiModelProperty(notes = "The stock's price change ratio.")
    private Double priceChangeRatio;

    @ApiModelProperty(notes = "The stock's tag.")
    private String tag;

}
