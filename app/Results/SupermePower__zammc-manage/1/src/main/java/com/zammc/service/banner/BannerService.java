package com.zammc.service.banner;
 import com.zammc.domain.banner.BannerEntity;
import com.zammc.page.PageBean;
import com.zammc.response.Message;
import javax.servlet.http.HttpServletRequest;
public interface BannerService {


public Message addBanner(BannerEntity bannerEntity,HttpServletRequest request)
;

public void deleteBanner(BannerEntity bannerEntity)
;

public Message editBanner(BannerEntity bannerEntity,HttpServletRequest request)
;

public void queryBannerPage(BannerEntity bannerEntity,PageBean pageBean)
;

public void forbiddenBanner(BannerEntity bannerEntity)
;

public void startUsingBanner(BannerEntity bannerEntity)
;

public BannerEntity queryBannerById(BannerEntity bannerEntity)
;

}