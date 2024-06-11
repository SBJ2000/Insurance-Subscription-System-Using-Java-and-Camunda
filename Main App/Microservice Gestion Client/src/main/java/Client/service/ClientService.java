package Client.service;

import Client.model.Client;
import Client.model.ClientBlacklist;
import Client.repository.ClientRepository;
import Client.repository.ClientBlacklistRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    private ClientRepository clientRepository;
    private ClientBlacklistRespository clientblacklistRepository;

    public ClientService(ClientRepository clientRepository, ClientBlacklistRespository clientblacklistRepository) {
        this.clientRepository = clientRepository;
        this.clientblacklistRepository = clientblacklistRepository;
    }

    @Autowired

    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }
    public Client getClientById(Long id) {
        return clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Client not found with id: " + id));
    }
    public Client addClient(Client client) {
        return clientRepository.save(client);
    }
    public Client updateClient(Long id, Client updatedClient) {
        Client existingClient = getClientById(id);

        existingClient.setNom(updatedClient.getNom());
        existingClient.setPrenom(updatedClient.getPrenom());
        existingClient.setCin(updatedClient.getCin());
        existingClient.setRevenuMensuel(updatedClient.getRevenuMensuel());
        existingClient.setTypeEmploi(updatedClient.getTypeEmploi());
        existingClient.setDateNaissance(updatedClient.getDateNaissance());
        existingClient.setClasseBonusMalus(updatedClient.getClasseBonusMalus());
        existingClient.setBesoinAssurance(updatedClient.getBesoinAssurance());

        return clientRepository.save(existingClient);
    }
    public int getClasseBonusMalusByClientId(Long clientID) {
        Optional<Client> optionalClient = clientRepository.findById(clientID);

        if (optionalClient.isPresent()) {
            return optionalClient.get().getClasseBonusMalus();
        } else {
            return 0;
        }
    }
    public void deleteClient(Long id) {
        clientRepository.deleteById(id);
    }

    public Client addToBlacklist(Long clientId) {
        // Vérifiez si le client existe dans la base de données principale
        Client client = getClientById(clientId);

        // Variables temporaires pour stocker les informations du client
        Long id = client.getClientID();
        String nom = client.getNom();
        String prenom = client.getPrenom();
        String cin = client.getCin();
        double revenuMensuel = client.getRevenuMensuel();
        String typeEmploi = client.getTypeEmploi();
        Date dateNaissance = client.getDateNaissance();
        int classeBonusMalus = client.getClasseBonusMalus();
        String besoinAssurance = client.getBesoinAssurance();

        // Copiez les données du client vers la table de la blacklist
        ClientBlacklist clientBlacklist = new ClientBlacklist(id,nom,prenom,cin,revenuMensuel,typeEmploi,dateNaissance,classeBonusMalus,besoinAssurance);

        // Ajoutez le client à la table de la blacklist
        clientblacklistRepository.save(clientBlacklist);

        return client;
    }

}
