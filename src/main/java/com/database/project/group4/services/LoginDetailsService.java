package com.database.project.group4.services;

import com.database.project.group4.database.GenericDatabase;
import com.database.project.group4.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LoginDetailsService implements UserDetailsService {

    private final GenericDatabase genericDatabase;

    public LoginDetailsService(GenericDatabase genericDatabase) {
        this.genericDatabase = genericDatabase;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
      User user = genericDatabase.findUserAccount(username);
        if(user==null)
        {
            System.out.println("user not found="+username);
            throw new UsernameNotFoundException("user not found="+username);
        }
        String role = genericDatabase.getRolesById(user.getUserid());
        List<GrantedAuthority> grantList=new ArrayList<>();
        if(role != null)
        {

                grantList.add(new SimpleGrantedAuthority(role));

        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getEncryptedPassword(), grantList);

    }
}
