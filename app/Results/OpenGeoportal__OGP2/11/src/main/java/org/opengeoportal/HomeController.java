package org.opengeoportal;
 import java.util.HashSet;
import java.util.Set;
import org.opengeoportal.config.ogp.OgpConfig;
import org.opengeoportal.config.ogp.OgpConfigRetriever;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
@Controller
public class HomeController {

@Autowired
 private  OgpConfigRetriever ogpConfigRetriever;

 final  Logger logger;


public void addConfig(ModelAndView mav){
    OgpConfig conf = ogpConfigRetriever.getConfig();
    mav.addObject("titlePrimary", conf.getPageTitlePrimary());
    mav.addObject("titleOffset", conf.getPageTitleOffset());
    // <script type="text/javascript" src="resources/javascript/dataTables.scroller.min.js"></script>
    mav.addObject("extraJs", conf.getJsLocalized());
    // <link rel="stylesheet" href="resources/css/google.css" type="text/css" />
    mav.addObject("extraCss", conf.getCssLocalized());
    mav.addObject("searchUrl", conf.getSearchUrl().toString());
    mav.addObject("analyticsId", conf.getAnalyticsId());
    mav.addObject("gmapsAPIKey", conf.getGmapsAPIKey());
    mav.addObject("loginRepository", conf.getLoginConfig().getRepositoryId());
    mav.addObject("loginType", conf.getLoginConfig().getType());
    mav.addObject("loginUrl", conf.getLoginConfig().getUrl());
    mav.addObject("secureDomain", conf.getLoginConfig().getSecureDomain());
}


@RequestMapping(value = { "/index", "/" }, method = RequestMethod.GET)
public ModelAndView getHomePage(Set<String> layerIds,String bbox,Set<String> layers,String minx,String maxx,String miny,String maxy,Boolean isDev){
    // @RequestParam("ogpids") Set<String> layerIds, ..should be optional.  also a param to set dev vs. prod
    // create the model to return
    ModelAndView mav = new ModelAndView("ogp_home");
    mav.addObject("dev", isDev);
    // if ogpids exists, add them to the Model
    if (!layerIds.isEmpty()) {
        mav.addObject("shareIds", getQuotedSet(layerIds));
        mav.addObject("shareBbox", bbox);
    } else if (!layers.isEmpty()) {
        // support old style share just in case
        mav.addObject("shareIds", getQuotedSet(layers));
        mav.addObject("shareBbox", minx + "," + miny + "," + maxx + "," + maxy);
    } else {
        // default values
        mav.addObject("shareIds", layerIds);
        mav.addObject("shareBbox", bbox);
    }
    addConfig(mav);
    // Debugging
    /*Iterator<Entry<String, Object>> iter = mav.getModelMap().entrySet().iterator();
		  while (iter.hasNext()){
			Entry<String, Object> stuff = iter.next();
			logger.info(stuff.getKey());
			logger.info((String) stuff.getValue());
		}*/
    return mav;
}


public Set<String> getQuotedSet(Set<String> uqSet){
    Set<String> quotedSet = new HashSet<String>();
    for (String item : uqSet) {
        quotedSet.add("\"" + item + "\"");
    }
    return quotedSet;
}


}