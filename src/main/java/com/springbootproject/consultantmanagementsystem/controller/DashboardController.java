package com.springbootproject.consultantmanagementsystem.controller;

import com.springbootproject.consultantmanagementsystem.entity.Consultant;
import com.springbootproject.consultantmanagementsystem.service.ConsultantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import com.springbootproject.consultantmanagementsystem.service.ConsultantService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;


import java.util.List;

@Controller
public class DashboardController {
    private final ConsultantService consultantService;

    @Autowired
    public DashboardController(ConsultantService consultantService) {
        this.consultantService = consultantService;
    }

    // Root URL redirects straight to the dashboard
    @GetMapping("/")
    public String home() {
        return "redirect:/dashboard";
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        long total = consultantService.countAll();
        long active = consultantService.countActive();
        long inactive = consultantService.countInactive();

        // Show 8 most recently added consultants
        // Latest consultants panel
        List<Consultant> latest = consultantService
                .search(null,
                        PageRequest.of(0, 8,
                                Sort.by("id").descending()))
                .getContent();

        model.addAttribute("totalConsultants", total);
        model.addAttribute("activeConsultants", active);
        model.addAttribute("inactiveConsultants", inactive);
        model.addAttribute("latestConsultants", latest);

        return "dashboard";
    }
}
