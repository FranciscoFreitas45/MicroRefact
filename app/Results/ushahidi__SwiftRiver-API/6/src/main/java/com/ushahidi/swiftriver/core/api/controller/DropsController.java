package com.ushahidi.swiftriver.core.api.controller;
 import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ushahidi.swiftriver.core.api.dto.CreateDropDTO;
import com.ushahidi.swiftriver.core.api.dto.GetDropDTO;
import com.ushahidi.swiftriver.core.api.exception.BadRequestException;
import com.ushahidi.swiftriver.core.api.exception.ErrorField;
import com.ushahidi.swiftriver.core.api.service.DropService;
import com.ushahidi.swiftriver.core.DTO.BadRequestException;
@Controller
@RequestMapping("/v1/drops")
public class DropsController extends AbstractController{

@Autowired
 private  DropService dropService;


@RequestMapping(method = RequestMethod.POST)
@ResponseBody
public List<GetDropDTO> createDrop(List<CreateDropDTO> drops){
    List<ErrorField> errors = new ArrayList<ErrorField>();
    logger.debug("Received {} new drops. Validating...", drops.size());
    // Validate each drop that has been submitted
    for (int i = 0; i < drops.size(); i++) {
        CreateDropDTO drop = drops.get(i);
        // Drop title
        if (drop.getTitle() == null) {
            errors.add(new ErrorField("[" + i + "].title", "missing"));
        }
        // Drop content
        if (drop.getContent() == null) {
            errors.add(new ErrorField("[" + i + "].content", "missing"));
        }
        // Channel name
        if (drop.getChannel() == null) {
            errors.add(new ErrorField("[" + i + "].channel", "missing"));
        }
        // Date published
        if (drop.getDatePublished() == null) {
            errors.add(new ErrorField("[" + i + "].date_published", "missing"));
        }
        // Original ID of the drop
        if (drop.getOriginalId() == null) {
            errors.add(new ErrorField("[" + i + "].original_id", "missing"));
        }
        // Identity of the drop
        if (drop.getIdentity() == null || drop.getIdentity().getOriginId() == null) {
            errors.add(new ErrorField("[" + i + "].identity.origin_id", "missing"));
        }
        // Destination rivers
        if (drop.getRiverIds() == null || drop.getRiverIds().isEmpty()) {
            errors.add(new ErrorField("[" + i + "].rivers", "missing"));
        }
        // Source channels
        if (drop.getChannelIds() == null || drop.getChannelIds().isEmpty()) {
            errors.add(new ErrorField("[" + i + "].channel_ids", "missing"));
        }
    }
    if (!errors.isEmpty()) {
        BadRequestException e = new BadRequestException("Invalid parameter.");
        e.setErrors(errors);
        throw e;
    }
    logger.debug("Validation passed. Creating drops..");
    return dropService.createDrops(drops);
}


}