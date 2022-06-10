package projetPOEIspring.poeidata.services;

import projetPOEIspring.poeidata.models.Client;

import java.util.List;

public interface ClientService {

    List<Client> getAll();

    Client getById(Integer id);

    Client createClient(Client client);

    void deleteClient(Integer id);

    Client updateClient(Client client);




}
