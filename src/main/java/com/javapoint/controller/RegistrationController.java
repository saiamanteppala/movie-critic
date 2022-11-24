/*
 * package com.javapoint.controller;
 * 
 * import org.springframework.stereotype.Controller; import
 * org.springframework.ui.Model; import
 * org.springframework.web.bind.annotation.GetMapping; import
 * org.springframework.web.bind.annotation.ModelAttribute; import
 * org.springframework.web.bind.annotation.PostMapping; import
 * org.springframework.web.bind.annotation.RequestMapping;
 * 
 * import com.javapoint.controller.dto.UserRegistrationDto; import
 * com.javapoint.service.RegistrationService;
 * 
 * @Controller
 * 
 * @RequestMapping("/registration") public class RegistrationController {
 * 
 * private RegistrationService registrationService;
 * 
 * public RegistrationController(RegistrationService registrationService) {
 * super(); this.registrationService = registrationService; }
 * 
 * @ModelAttribute("user") public UserRegistrationDto registrationDto() { return
 * new UserRegistrationDto(); }
 * 
 * @GetMapping public String showRegistrationForm(Model model) {
 * model.addAttribute("user", new UserRegistrationDto()); return "registration";
 * }
 * 
 * @PostMapping public String registerUserAccount(@ModelAttribute("user")
 * UserRegistrationDto userRegistrationDto) {
 * 
 * registrationService.save(userRegistrationDto); return
 * "redirect:/registration?sucess";
 * 
 * } }
 */