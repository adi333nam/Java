package com.cognizant.authorization.service;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.cognizant.authorization.model.User;

@ExtendWith(MockitoExtension.class)
class AuthUserDetailsServiceTest {

	@Mock
	AuthUserDetailsService authUserDetailsService;
	AuthUserDetails authUser1;
	AuthUserDetails authUser2;
	
	@BeforeEach
	public void init()
	{
		User user1=new User(1, "Abhishek Tiwari", "abhishek", false, true, false, true, "USER");
		User user2=new User(2, "Aditya Namdev", "aditya", false, true, false, true, "USER");
		authUser1=new AuthUserDetails(user1);
		authUser2=new AuthUserDetails(user2);
	}
	
	@Test
	void testLoadUserByName()
	{
		when(authUserDetailsService.loadUserByUsername("Abhishek Tiwari")).thenReturn(authUser1);
		UserDetails actualUserDetails=authUserDetailsService.loadUserByUsername("Abhishek Tiwari");
		assertEquals(authUser1,actualUserDetails);
	}
	
	@Test
	void testLoadUserByNameForUsernameNotFoundException()
	{
		when(authUserDetailsService.loadUserByUsername("Aman Jain")).thenThrow(UsernameNotFoundException.class);
		assertThrows(UsernameNotFoundException.class,()->authUserDetailsService.loadUserByUsername("Aman Jain"));
	}

}
