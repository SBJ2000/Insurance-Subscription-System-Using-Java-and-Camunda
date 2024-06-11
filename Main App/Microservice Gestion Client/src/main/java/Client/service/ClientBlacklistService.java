package Client.service;
import Client.model.ClientBlacklist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import Client.repository.ClientBlacklistRespository;

import java.util.List;
import java.util.Optional;

@Service
public class ClientBlacklistService {

    @Autowired
    private ClientBlacklistRespository clientBlacklistRepository;

    public List<ClientBlacklist> getAllClients() {
        return clientBlacklistRepository.findAll();
    }

    public ClientBlacklist getClientById(Long clientID) {
        Optional<ClientBlacklist> optionalClient = clientBlacklistRepository.findById(clientID);
        return optionalClient.orElse(null);
    }

    public ClientBlacklist addClient(ClientBlacklist client) {
        return clientBlacklistRepository.save(client);
    }
    public boolean isClientExistsByPrenom(String prenom) {
        // Récupérer tous les clients de la base de données
        List<ClientBlacklist> allClients = clientBlacklistRepository.findAll();

        // Parcourir la liste pour vérifier si un client avec le prénom donné existe
        for (ClientBlacklist client : allClients) {
            if (prenom.equals(client.getPrenom())) {
                // Le client avec le prénom donné a été trouvé
                return true;
            }
        }

        // Aucun client avec le prénom donné n'a été trouvé
        return false;
    }

    public ClientBlacklist updateClient(Long clientID, ClientBlacklist updatedClient) {
        Optional<ClientBlacklist> optionalClient = clientBlacklistRepository.findById(clientID);

        if (optionalClient.isPresent()) {
            ClientBlacklist existingClient = optionalClient.get();
            existingClient.setNom(updatedClient.getNom());
            existingClient.setPrenom(updatedClient.getPrenom());
            existingClient.setCin(updatedClient.getCin());
            existingClient.setRevenuMensuel(updatedClient.getRevenuMensuel());
            existingClient.setTypeEmploi(updatedClient.getTypeEmploi());
            existingClient.setDateNaissance(updatedClient.getDateNaissance());
            existingClient.setClasseBonusMalus(updatedClient.getClasseBonusMalus());
            existingClient.setBesoinAssurance(updatedClient.getBesoinAssurance());
            return clientBlacklistRepository.save(existingClient);
        } else {
            return null;
        }
    }

    public void deleteClient(Long clientID) {
        clientBlacklistRepository.deleteById(clientID);
    }
}
