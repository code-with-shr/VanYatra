package in.sp.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import in.sp.main.entity.Ride;
import in.sp.main.entity.User;
import in.sp.main.service.RideService;
import in.sp.main.service.UserService;
import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {

    @Autowired
    private RideService rideService;

    @Autowired
    private UserService userService;

    // ================= LOGIN =================

    @GetMapping("/login")
    public String loginPage(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }

    @PostMapping("/login")
    public String loginUser(@ModelAttribute User user, Model model) {

        User loggedUser =
                userService.loginUser(user.getEmail(), user.getPassword());

        if (loggedUser != null) {
            return "redirect:/dashboard";
        } else {
            model.addAttribute("error", "Invalid Email or Password");
            return "login";
        }
    }

    // ================= Register PAGES =================

//    @GetMapping("/register")
//    public String registerPage() {
//        return "register";
//    }
//
//

    @GetMapping("/register")
    public String registerPage(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user, Model model) {

        boolean status = userService.registerUser(user);

        if (status) {
            model.addAttribute("success", "Registration successful! Please login.");
            return "login";
        } else {
            model.addAttribute("error", "Email already exists!");
            return "register";
        }
    }
    // ================= HOME PAGES =================



    @GetMapping("/home")
    public String home() {
        return "home";
    }

    // ================= Dashboard PAGES =================


    @GetMapping("/dashboard")
    public String dashboard() {
        return "dashboard";
    }
    // ================= Book_Ride PAGES =================

    @GetMapping("/book-ride")
    public String bookRide(Model model) {
        model.addAttribute("ride", new Ride());
        return "book-ride";
    }
    @PostMapping("/save-ride")
    public String saveRide(@ModelAttribute Ride ride,
                           RedirectAttributes redirectAttributes) {

        rideService.saveRide(ride);

        redirectAttributes.addFlashAttribute(
            "successMsg", "✅ Your ride has been booked successfully!"
        );

        return "redirect:/book-ride";
    }


    // ================= My booking PAGES =================


//  @GetMapping("/my-rides")
//  public String myRides() {
//      return "my-rides";
//  }


    @GetMapping("/my-booking")
    public String myBookingPage(Model model) {
        model.addAttribute("rides", rideService.getAllRides());
        return "my-booking";  
    }

    
    @GetMapping("/about")
    public String aboutPage() {
        return "about";
    }

    // ================= contact  PAGES =================
    @GetMapping("/contact")
    public String contactPage() {
        return "contact";
    }

    @PostMapping("/contact")
    public String submitContact(Model model) {
        model.addAttribute("msg", "Message sent successfully!");
        return "contact";
    }
    // ================= Logout  PAGES =================
 
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();   // session clear
        return "redirect:/login";
    }

  // ================= My drivers  PAGES =================




    @GetMapping("/drivers")
    public String drivers() {
        return "drivers";
    }

    @GetMapping("/profile")
    public String profilePage() {
        return "profile";
    }
}
