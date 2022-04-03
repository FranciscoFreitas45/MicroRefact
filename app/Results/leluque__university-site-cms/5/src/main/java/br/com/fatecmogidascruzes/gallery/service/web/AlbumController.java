package br.com.fatecmogidascruzes.gallery.service.web;
 import java.util.Optional;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.devskiller.friendly_id.FriendlyId;
import br.com.fatecmogidascruzes.controller.MVCController;
import br.com.fatecmogidascruzes.dto.FormItemDTO;
import br.com.fatecmogidascruzes.event.service.EventService;
import br.com.fatecmogidascruzes.exception.DoesNotHaveAccessException;
import br.com.fatecmogidascruzes.exception.InexistentOrDisabledEntity;
import br.com.fatecmogidascruzes.exception.web.BadRequestException;
import br.com.fatecmogidascruzes.exception.web.ForbiddenException;
import br.com.fatecmogidascruzes.exception.web.InternalErrorException;
import br.com.fatecmogidascruzes.exception.web.NotFoundException;
import br.com.fatecmogidascruzes.file.File;
import br.com.fatecmogidascruzes.file.FileDTO;
import br.com.fatecmogidascruzes.file.service.web.FileWebService;
import br.com.fatecmogidascruzes.gallery.Album;
import br.com.fatecmogidascruzes.gallery.AlbumService;
import br.com.fatecmogidascruzes.user.service.UserService;
import br.com.fatecmogidascruzes.util.Constants;
import br.com.fatecmogidascruzes.Interface.EventService;
import br.com.fatecmogidascruzes.Interface.FileWebService;
import br.com.fatecmogidascruzes.DTO.Album;
import br.com.fatecmogidascruzes.DTO.FileWebService;
@Controller
@PreAuthorize("hasRole('MULTIMIDIA')")
@RequestMapping("/admin/gallery/albums")
public class AlbumController extends MVCController{

 private  AlbumWebService albumWebService;

 private  AlbumService albumService;

 private  EventService eventService;

 private  FileWebService fileWebService;

@Autowired
public AlbumController(HttpSession httpSession, UserService userService, AlbumWebService albumWebService, AlbumService albumService, EventService eventService, FileWebService fileWebService) {
    super(httpSession, userService);
    this.albumWebService = albumWebService;
    this.albumService = albumService;
    this.eventService = eventService;
    this.fileWebService = fileWebService;
}
@RequestMapping(path = "{albumHash}/photos/{imageHash}", method = RequestMethod.GET)
public String image(UUID albumHash,UUID imageHash,Integer width,Integer height,HttpServletRequest request){
    try {
        Optional<Album> albumOptional = this.albumService.getEnabledByHash(albumHash);
        if (albumOptional.isPresent()) {
            Optional<File> photoOptional = albumOptional.get().getImage(imageHash);
            FileDTO fileDTO = this.fileWebService.getImage(photoOptional.get().getHash(), width, height);
            if (null != fileDTO) {
                return "redirect:" + getBaseURI(request) + Constants.FILES_PATH + fileDTO.getFileName();
            } else {
                throw new NotFoundException("The album photo image has not been found.");
            }
        } else {
            throw new BadRequestException("The album does not exist");
        }
    } catch (InexistentOrDisabledEntity e) {
        e.printStackTrace();
        throw new BadRequestException(e);
    } catch (DoesNotHaveAccessException e) {
        e.printStackTrace();
        throw new ForbiddenException(e);
    } catch (Exception e) {
        e.printStackTrace();
        throw new InternalErrorException(e);
    }
}


@RequestMapping(path = "/new", method = RequestMethod.GET)
public ModelAndView newAlbum(AlbumEditDTO albumEditDTO,RedirectAttributes redirectAttributes){
    ModelAndView modelAndView = new ModelAndView("/gallery/edit");
    modelAndView.addObject("events", FormItemDTO.listFrom(eventService.getEnabled()));
    modelAndView.addObject("album", albumEditDTO);
    modelAndView.addObject("message", redirectAttributes.getFlashAttributes().get("message"));
    return modelAndView;
}


@RequestMapping(path = "/{albumHash}", method = RequestMethod.GET)
public ModelAndView view(UUID albumHash,RedirectAttributes redirectAttributes){
    ModelAndView modelAndView = new ModelAndView("/gallery/edit");
    try {
        Optional<Album> albumOptional = this.albumService.getEnabledByHash(albumHash);
        if (albumOptional.isPresent()) {
            modelAndView.addObject("album", AlbumEditDTO.from(albumOptional.get()));
            modelAndView.addObject("isUpdate", true);
        } else {
            redirectAttributes.addFlashAttribute("message", "error.notFound");
        }
    } catch (ForbiddenException forbiddenException) {
        forbiddenException.printStackTrace();
        throw forbiddenException;
    } catch (Exception exception) {
        exception.printStackTrace();
        redirectAttributes.addFlashAttribute("message", "error");
        return search(redirectAttributes);
    }
    return modelAndView;
}


@RequestMapping
public ModelAndView search(RedirectAttributes redirectAttributes){
    try {
        ModelAndView modelAndView = new ModelAndView("/gallery/albums");
        modelAndView.addObject("albums", albumWebService.getEnabled());
        return modelAndView;
    } catch (Exception e) {
        e.printStackTrace();
        throw new InternalErrorException();
    }
}


@RequestMapping(path = "/{albumHash}/upload", method = RequestMethod.GET)
public ModelAndView upload(UUID albumHash,RedirectAttributes redirectAttributes){
    ModelAndView modelAndView = new ModelAndView("/gallery/upload");
    modelAndView.addObject("album", new AlbumPhotosEditDTO(FriendlyId.toFriendlyId(albumHash)));
    modelAndView.addObject("message", redirectAttributes.getFlashAttributes().get("message"));
    return modelAndView;
}


@RequestMapping(path = "/save", method = RequestMethod.POST)
public ModelAndView save(AlbumEditDTO albumEditDTO,BindingResult binding,RedirectAttributes redirectAttributes){
    if (binding.hasErrors()) {
        redirectAttributes.addFlashAttribute("message", "error");
        return newAlbum(albumEditDTO, redirectAttributes);
    }
    try {
        albumWebService.save(albumEditDTO);
        return upload(FriendlyId.toUuid(albumEditDTO.getHashString()), redirectAttributes);
    } catch (Exception error) {
        error.printStackTrace();
        redirectAttributes.addFlashAttribute("message", "error");
        return search(redirectAttributes);
    }
}


@RequestMapping(path = "/upload", method = RequestMethod.POST)
public ModelAndView processUpload(AlbumPhotosEditDTO albumPhotosEditDTO,BindingResult binding,RedirectAttributes redirectAttributes){
    if (binding.hasErrors()) {
        redirectAttributes.addFlashAttribute("message", "error");
        return upload(FriendlyId.toUuid(albumPhotosEditDTO.getHashString()), redirectAttributes);
    }
    try {
        albumWebService.upload(albumPhotosEditDTO);
        return search(redirectAttributes);
    } catch (Exception error) {
        error.printStackTrace();
        redirectAttributes.addFlashAttribute("message", "error");
        return upload(FriendlyId.toUuid(albumPhotosEditDTO.getHashString()), redirectAttributes);
    }
}


@RequestMapping(path = "/delete", method = RequestMethod.GET)
public ModelAndView delete(UUID hash,RedirectAttributes redirectAttributes){
    try {
        Optional<Album> albumOptional = albumService.getByHash(hash);
        if (albumOptional.isPresent()) {
            albumService.removeLogicallyByHash(hash);
            redirectAttributes.addFlashAttribute("message", "success");
        } else {
            redirectAttributes.addFlashAttribute("message", "error.notFound");
        }
    } catch (Exception error) {
        error.printStackTrace();
        redirectAttributes.addFlashAttribute("message", "error");
    }
    return search(redirectAttributes);
}


}