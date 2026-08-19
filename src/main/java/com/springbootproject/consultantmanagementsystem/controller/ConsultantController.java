package com.springbootproject.consultantmanagementsystem.controller;

import org.springframework.ui.Model;
import com.springbootproject.consultantmanagementsystem.entity.Consultant;
import com.springbootproject.consultantmanagementsystem.service.ConsultantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;


@Controller
@RequestMapping("/consultants")
public class ConsultantController {
    private final ConsultantService consultantService;

    @Autowired
    public ConsultantController(ConsultantService consultantService) {
        this.consultantService = consultantService;
    }

    // GET
    // /consultants
    // list page, search, pagination, sorting
    @GetMapping
    public String listConsultants(
            @RequestParam(value = "keyword", required = false)
            String keyword,
            @RequestParam(value = "page", defaultValue = "0")
            int page,
            @RequestParam(value = "size", defaultValue = "8")
            int size,
            @RequestParam(value = "SortField", defaultValue = "id")
            String sortField,
            @RequestParam(value = "sortDir", defaultValue = "asc")
            String sortDir,
            Model model
    ){
        Sort sort = sortDir.equalsIgnoreCase("desc")
                ? Sort.by(sortField).descending()
                : Sort.by(sortField).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Consultant> resultPage = consultantService.search(keyword, pageable);

        model.addAttribute("consultantPage", resultPage);
        model.addAttribute("consultants", resultPage.getContent());
        model.addAttribute("keyword", keyword == null ? "" : keyword);
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", resultPage.getTotalPages());

        return "consultants/list";
    }

    // GET
    // /consultants/add
    // show form -- "add consultant"
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("consultant", new Consultant());
        model.addAttribute("formTitle", "Add Consultant");
        return "consultants/form";
    }

    // GET
    // /consultants/edit/{id}
    // show form -- edit consultant
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Consultant consultant = consultantService.findById(id).orElseThrow(() -> new
                IllegalArgumentException("Invalid consultant id: " + id));
        model.addAttribute("consultant", consultant);
        model.addAttribute("formTitle", "Update Consultant");
        return "consultants/form";
    }

    // POST
    // /consultants/save
    // Handle create and update
    // To Update existing row if it's set, if consultant.id is null -> set a new row
    @PostMapping("/save")
    public String saveConsultant(@Valid @ModelAttribute("consultant") Consultant consultant,
                                 BindingResult result,
                                 Model model) {
        if(result.hasErrors()) {
            model.addAttribute("formTitle", consultant.getId() == null ? "Add Consultant" : "Update Consultant");
            return "consultants/form";
        }
        consultantService.save(consultant);
        return "redirect:/consultants?saved=true";

    }

    // GET
    // /consultants/delete/{id}/confirm
    // Delete confirmation screen
    @GetMapping("/delete/{id}/confirm")
    public String confirmDelete(@PathVariable Long id, Model model) {
        Consultant consultant = consultantService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid consultant id : " + id));
        model.addAttribute("consultant", consultant);
        return "consultants/delete-confirm";
    }

    // POST
    // /consultants/delete/{id}
    // Delete the row
    @PostMapping("/delete/{id}")
    public String deleteConsultant(@PathVariable Long id) {
        consultantService.deleteById(id);
        return "redirect:/consultants?deleted=true";
    }
}
