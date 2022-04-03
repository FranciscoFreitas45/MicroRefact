package com.zis.shop.dto;
 public class ShopDownloadYouZanDto implements ShopDownloadInterfaceDto{

 private  ApiQueryItemsDto apiQueryItemsDto;


public ApiQueryItemsDto getApiQueryItemsDto(){
    return apiQueryItemsDto;
}


public void setApiQueryItemsDto(ApiQueryItemsDto apiQueryItemsDto){
    this.apiQueryItemsDto = apiQueryItemsDto;
}


}