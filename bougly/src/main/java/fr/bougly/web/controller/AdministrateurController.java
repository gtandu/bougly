package fr.bougly.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import fr.bougly.exception.MailErrorException;
import fr.bougly.model.CompteUtilisateur;
import fr.bougly.model.enumeration.RoleCompteEnum;
import fr.bougly.model.security.OnRegistrationCompleteEvent;
import fr.bougly.service.CompteService;
import fr.bougly.service.helper.MapperBeanUtil;
import fr.bougly.web.dtos.CompteDto;


@Controller
@RequestMapping(value="/administrateur")
public class AdministrateurController {
	
	private static final String URL_CONTROLLEUR_ADMIN = "/administrateur";
	public static final String URL_GESTION_COMPTE_PAGE = "/gestionCompte.html";
	public static final String URL_CREER_COMPTE = "/creerCompte.html";
	public static final String URL_SUPPRIMER_COMPTE = "/supprimerCompte.html";
	public static final String URL_EDITER_COMPTE = "/editerCompte.html";
	
	@Autowired
	CompteService compteService;
	
	@Autowired
	ApplicationEventPublisher eventPublisher;
	
	@RequestMapping(value=URL_GESTION_COMPTE_PAGE, method=RequestMethod.GET)
	public ModelAndView showPageGestionCompte(@RequestParam(defaultValue="1",required=true) Integer pageNumber)
	{
		Page<CompteUtilisateur> listeComptesPage = compteService.listAllByPage(pageNumber);
		List<CompteUtilisateur> listeComptes = listeComptesPage.getContent();
		ArrayList<CompteDto> listeComptesBean = MapperBeanUtil.convertListCompteToListCompteBean(listeComptes);
		int current = listeComptesPage.getNumber() + 1;
	    int begin = Math.max(1, current - 5);
	    int end = Math.min(begin + 10, listeComptesPage.getTotalPages());
		
		ModelAndView model = new ModelAndView("gestionCompte");
		
		model.addObject("listeComptes", listeComptesBean);
	    model.addObject("beginIndex", begin);
	    model.addObject("endIndex", end);
	    model.addObject("currentIndex", current);
		return model;
		
	}
	
	@RequestMapping(value=URL_CREER_COMPTE, method=RequestMethod.GET)
	public ModelAndView showPageCreerCompte()
	{
		
		ModelAndView model = new ModelAndView("creerCompte");
		model.addObject("compte", new CompteDto());
		model.addObject("allRoles",RoleCompteEnum.listesAllRoles());
		return model;
		
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value=URL_CREER_COMPTE, method=RequestMethod.POST)
	public String creerCompteFromData(@ModelAttribute(value="compte") CompteDto compteDto, HttpServletRequest request) throws Exception
	{

		
		CompteUtilisateur compteSave = compteService.saveNewUserAccount(compteDto);
		
		try {
	        String appUrl = request.getContextPath();
	        eventPublisher.publishEvent(new OnRegistrationCompleteEvent
	          (compteSave, request.getLocale(), appUrl));
	    } catch (Exception me) {
	    	throw new MailErrorException("Erreur OnRegistrationCompleteEvent ");
	       
	    }
		
		return "redirect:"+URL_CONTROLLEUR_ADMIN+URL_GESTION_COMPTE_PAGE;
		
	}
	
	@RequestMapping(value=URL_SUPPRIMER_COMPTE, method=RequestMethod.POST)
	@ResponseBody
	public void supprimerCompte(@RequestParam(required=true) String mail)
	{
		compteService.deleteCompteByMail(mail);
		
	}
	
	//TODO A refactorer classe Controlleur parente
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		StringTrimmerEditor stringtrimmer = new StringTrimmerEditor(true);
		binder.registerCustomEditor(String.class, stringtrimmer);
	}
	
	@RequestMapping(value=URL_EDITER_COMPTE, method=RequestMethod.POST)
	@ResponseBody
	public String editerCompte(CompteDto compteBean)
	{
			compteService.editerCompteWithCompteBean(compteBean);
			return "OK";
	}

}
