package com.web.SM_Barber;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SmController {
	@Autowired
	SmRepository repo;
	
	@GetMapping("/")
	public String GetAll(Model m)
	{
		List<SmModel> list = repo.sp_fetch();
		m.addAttribute("smblist",list);
		return "index";
	}
	
	@GetMapping("/dashboard")
	public String showall(Model m)
	{
		List<SmModel> list = repo.sp_fetch();
		m.addAttribute("smblist",list);
		return "dashboard";
	}
	
	@GetMapping("/edit/{id}")
	public String GetOne(@PathVariable int id , Model m)
	{
		SmModel smb = (SmModel)repo.sp_search(id);
		m.addAttribute("smbobj",smb);
		return "edit";
	}
	
	@GetMapping("/signup")
	public String sp_register1()
	{
		return "signup";
	}
	
	@PostMapping("/signup-post")
	public String sp_register2(@ModelAttribute SmModel smb) {
		if(smb !=null) {
			repo.sp_register(smb.getFname(), smb.getLname(), smb.getEmail(), smb.getPassword(), smb.getPhoneno(), smb.getGender(), smb.getSelecttime(), smb.getDescription());
	        return "redirect:/login";
		}
		return "redirect:/signup";
	}
	
	@GetMapping("/login")
	public String sp_login1()
	{
		return "login";
	}
	
	@PostMapping("/login-post")
	public String LoginPost(@ModelAttribute SmModel smb) {
		SmModel check = repo.sp_login(smb.getEmail(),smb.getPassword());
		if(check != null) {
			return "redirect:/dashboard";
		}
		return "redirect:/login";
	}
	
	@PostMapping("/edit-post")
	public String sp_update(@ModelAttribute SmModel smb) {
		System.out.println(smb.getId());
		if(smb !=null) {
			repo.sp_update(smb.getId(), smb.getFname(), smb.getLname(), smb.getEmail(), smb.getPassword(), smb.getPhoneno(), smb.getGender(), smb.getSelecttime(), smb.getDescription());
			return "redirect:/";
		}
		return "redirect:/edit/"+smb.getId();
	}
	
	@GetMapping("/delete/{id}")
	public String sp_delete(@PathVariable int id)
	{
		repo.sp_delete(id);
		return "redirect:/";
	}
}
