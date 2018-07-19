package com.maven.packagetest.controller;

import com.maven.packagetest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private UserService userService;

    static int i;
    static int j;

    @RequestMapping( "index" )
    public String getUsers(Model model) {
        List<HashMap<String, Object>> users = userService.getAll();
        model.addAttribute( "users", users );
        model.addAttribute( "i", i++ );
        return "index";
    }

    @RequestMapping( "{id}/getUserById" )
    public String getUserById(Model model, @PathVariable Integer id) {
        HashMap<String, Object> user = userService.getUserById( id );
        model.addAttribute( "user", user );
        model.addAttribute( "i", i++ );
        return "user";
    }


    @RequestMapping( "{id}/updateUserById" )
    public String updateUserById(Model model, @PathVariable Integer id) {
        HashMap<String, Object> map = new HashMap<>(3);
        map.put( "id", id );
        map.put( "username", "user:" + j++ );
        map.put( "password", "admin:" + j );
        userService.updateUserById( map ,id);
        HashMap<String, Object> user = userService.getUserById( id );
        model.addAttribute( "i", i++ );
        model.addAttribute( "user", user );
        return "user";
    }
}
