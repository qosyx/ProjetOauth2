package com.github.arocketman.services;

import com.github.arocketman.entities.Oauth_client_details;
import com.github.arocketman.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Service
public class ClientService  implements ClientDetailsService{
    @Autowired
    private ClientRepository clientRepository;

    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {

    Oauth_client_details oauth_client_details =  clientRepository.getOne(clientId);

        BaseClientDetails details = new BaseClientDetails();
        details.setClientId(oauth_client_details.getClient_id());
        details.setAuthorizedGrantTypes(Arrays.asList("authorization_code") );
        details.setScope(Arrays.asList("read, trust"));
        //details.setRegisteredRedirectUri(Collections.singleton("http://anywhere.com"));
        details.setResourceIds(Arrays.asList("oauth2-resource"));
        Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority("ROLE_CLIENT"));
        details.setAuthorities(authorities);

        return details;
    }
}

