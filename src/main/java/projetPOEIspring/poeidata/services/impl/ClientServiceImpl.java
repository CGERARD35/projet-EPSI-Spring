package projetPOEIspring.poeidata.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import projetPOEIspring.poeidata.exceptions.ClientException;
import projetPOEIspring.poeidata.exceptions.NotAllowedToDeleteClientException;
import projetPOEIspring.poeidata.exceptions.UnknownResourceException;
import projetPOEIspring.poeidata.models.Client;
import projetPOEIspring.poeidata.repositories.ClientRepository;
import projetPOEIspring.poeidata.services.ClientService;

import java.util.List;

@Service
@Qualifier("clientService")
public class ClientServiceImpl implements ClientService {

    Logger log = LoggerFactory.getLogger(ClientServiceImpl.class);

    @Autowired
    private ClientRepository clientRepository;


    @Override
    public List<Client> getAll() {
        return this.clientRepository.findAll(Sort.by("nom").ascending());
    }

    @Override
    public Client getById(Integer id) {
        return clientRepository.findById(id).orElseThrow(
                () -> new UnknownResourceException("No client found for the given ID.")
        );
    }

    @Override
    public Client createClient(Client client) {
        log.debug("Attempting to save in DB.");
        if (client.getNom().isEmpty() || client.getPrenom().isEmpty()) {
            throw new ClientException("Lastname and firstname are obligatory");
        } else if (client.getFixe().length() > 15 && client.getPortable().length() > 15){
            throw new ClientException("Phone number and mobile number have to be superior to 15 numbers");
        }
        return this.clientRepository.save(client);
    }

    @Override
    public void deleteClient(Integer id) {
        Client clientToDelete = this.getById(id);
        if (this.canDeleteClient(clientToDelete)) {
            this.clientRepository.delete(clientToDelete);
        } else {
            throw new NotAllowedToDeleteClientException("The given client still has orders.");
        }
    }

    private boolean canDeleteClient(Client client) {
        return (null == client.getOrders() || client.getOrders().isEmpty());
    }

    @Override
    public Client updateClient(Client client) {
        log.debug("Attempting to update cleint{}", client.getId());
        Client existingClient = this.getById(client.getId());
        existingClient.setNom(client.getNom());
        existingClient.setPrenom(client.getPrenom());
        existingClient.setSociete(client.getSociete());
        existingClient.setMail(client.getMail());
        existingClient.setFixe(client.getFixe());
        existingClient.setPortable(client.getPortable());
        existingClient.setStatut(client.getStatut());
        existingClient.setNotes(client.getNotes());
        return this.clientRepository.save(existingClient);
    }

}
