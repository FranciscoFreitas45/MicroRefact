package org.gliderwiki.util;
 public class WikiProdectStatus {


public String prodectStatus(String prodectStatus){
    String protectStatus = null;
    switch(Integer.parseInt(prodectStatus)) {
        case 0:
            protectStatus = "기본";
            break;
        case 1:
            protectStatus = "준보호";
            break;
        case 2:
            protectStatus = "보호";
            break;
        case 3:
            protectStatus = "신고";
            break;
        case 4:
            protectStatus = "삭제예정";
            break;
        case 5:
            protectStatus = "이의제기";
            break;
        case 6:
            protectStatus = "토론";
            break;
        case 8:
            protectStatus = "삭제복구";
            break;
        case 9:
            protectStatus = "관리자삭제";
            break;
        default:
            protectStatus = "기본";
            break;
    }
    return protectStatus;
}


}