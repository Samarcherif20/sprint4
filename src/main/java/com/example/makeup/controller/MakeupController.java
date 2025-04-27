package com.example.makeup.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.makeup.dto.MakeupDTO;
import com.example.makeup.entities.BrandLine;
import com.example.makeup.entities.Makeup;
import com.example.makeup.service.MakeupService;

import jakarta.validation.Valid;

@Controller
public class MakeupController {

    @Autowired
    MakeupService makeupService;
    // Display all Makeups
    @RequestMapping("/ListeMakeup")
    public String listeMakeups(ModelMap modelMap,
                               @RequestParam(name = "page", defaultValue = "0") int page,
                               @RequestParam(name = "size", defaultValue = "2") int size) {
        Page<Makeup> makeupsPage = makeupService.getAllMakeupParPage(page, size);
        modelMap.addAttribute("makeups", makeupsPage);
        modelMap.addAttribute("pages", new int[makeupsPage.getTotalPages()]);
        modelMap.addAttribute("currentPage", page);
        return "listeMakeup";
    }


    // Show form to create a new Makeup
    @RequestMapping("/showCreate")
    public String showCreate(ModelMap modelMap) {
    	
		List<BrandLine> lignes = makeupService.getAllBrandLines();
    	    modelMap.addAttribute("makeup", new Makeup());
    	    modelMap.addAttribute("mode", "new");
    	    modelMap.addAttribute("brandLines", lignes);
    	    return "formMakeup";
    }

    // Save the Makeup
    @RequestMapping("/saveMakeup")
    public String saveMakeup(@Valid MakeupDTO makeup, BindingResult bindingResult,
                             @RequestParam(name = "page", defaultValue = "0") int page,
                             @RequestParam(name = "size", defaultValue = "2") int size) {

        int currentPage;
        boolean isNew = false;

        // En cas d'erreur de validation, on revient au formulaire
        if (bindingResult.hasErrors()) return "formMakeup";

        // Vérifie si c'est un ajout ou une modification
        if (makeup.getIdMakeup() == null) {
            isNew = true;
        }

        // Sauvegarde
        makeupService.saveMakeup(makeup);

        // Si c'est un ajout, on redirige vers la dernière page
        if (isNew) {
            Page<Makeup> makeups = makeupService.getAllMakeupParPage(page, size);
            currentPage = makeups.getTotalPages() - 1;
        } else {
            currentPage = page;
        }

        // Redirection vers la liste paginée
        return "redirect:/ListeMakeup?page=" + currentPage + "&size=" + size;
    }



    // Delete a specific Makeup
    @RequestMapping("/supprimerMakeup")
    public String supprimerMakeup(@RequestParam("id") Long id,
                                   ModelMap modelMap,
                                   @RequestParam(name = "page", defaultValue = "1") int page,
                                   @RequestParam(name = "size", defaultValue = "2") int size) {
        makeupService.deleteMakeupById(id);
        Page<Makeup> makeupsPage = makeupService.getAllMakeupParPage(page, size);
        modelMap.addAttribute("makeups", makeupsPage);
        modelMap.addAttribute("pages", new int[makeupsPage.getTotalPages()]);
        modelMap.addAttribute("currentPage", page);
        modelMap.addAttribute("size", size);
        return "listeMakeup";  // Remplacez par le nom de votre vue
    }


    // Edit a specific Makeup
    @RequestMapping("/modifierMakeup")
    public String editerMakeup(@RequestParam("id") Long id, ModelMap modelMap,
    		@RequestParam(name = "page", defaultValue = "1") int page,
            @RequestParam(name = "size", defaultValue = "2") int size) {
    	 MakeupDTO m = makeupService.getMakeup(id);
    	    List<BrandLine> lignes = makeupService.getAllBrandLines();
    	    modelMap.addAttribute("makeup", m);
    	    modelMap.addAttribute("mode", "edit");
    	    modelMap.addAttribute("brandLines", lignes);
    	    modelMap.addAttribute("page", page);
    	    modelMap.addAttribute("size", size);


    	    return "formMakeup";}


    // Update an existing Makeup
    @RequestMapping("/updateMakeup")
    public String updateMakeup(@ModelAttribute("makeup") MakeupDTO makeup, ModelMap modelMap) {
        makeupService.updateMakeup(makeup);
        List<MakeupDTO> makeups = makeupService.getAllMakeup();  
        modelMap.addAttribute("makeups", makeups);
        return "listeMakeups";
    }
    @GetMapping(value = "/")
    public String welcome() {
     return "index";
    }
}
