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

import java.util.*;

@Service
public class ClientService implements ClientDetailsService {
    @Autowired
    private ClientRepository clientRepository;

    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {

        Oauth_client_details oauth_client_details = clientRepository.getOne(clientId);

        BaseClientDetails details = new BaseClientDetails();
        details.setClientId(oauth_client_details.getClient_id());
        details.setAuthorizedGrantTypes(Arrays.asList(oauth_client_details.getAuthorized_grant_types()));
        details.setScope(Arrays.asList(oauth_client_details.getScope()));
        details.setResourceIds(Arrays.asList(oauth_client_details.getResource_ids()));
        details.setAuthorizedGrantTypes(Arrays.asList(oauth_client_details.getAuthorized_grant_types()));

        return details;
    }
}

