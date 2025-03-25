Change History:

Version 1.0.0
- Added 3 Controllers with endpoints: /welcome, /accounts, /balance
- Added Customer, Authority model for DB schema
- Added Customer Repository for DB connection and process

Version 1.0.1
* Added ProjectSecurityConfig for Security Configuration
* 3 Methods for configuration
* - **SecurityFilterChain defaultSecurityFilterChain ( HttpRequest http )** {
    csrf, sessionManagement, authorizeHttpRequests, httpBasic, httpFormLogin, http.build()
}
* - **AuthenticationManager authManager ( AuthenticationConfiguration authConfig )** {
        return authConfig.getAuthenticationManager();
}

* - **PasswordEncoder passwordEncoder()** {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
}

Version 1.0.2
* Added CustomUserDetailsService class to loadUsername from repository(database)
* @Override
  public **UserDetails** **loadUserByUsername(String username)** throws UsernameNotFoundException
* returns User(String username, String credentials, List<GrantedAuthority> authorities)
