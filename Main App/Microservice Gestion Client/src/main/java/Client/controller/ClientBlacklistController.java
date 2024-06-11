package Client.controller;

import Client.model.Client;
import Client.model.ClientBlacklist;
import Client.service.ClientBlacklistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/client-blacklist")
public class ClientBlacklistController {

    @Autowired
    private ClientBlacklistService clientBlacklistService;

    @GetMapping
    public List<ClientBlacklist> getAllClients() {
        return clientBlacklistService.getAllClients();
    }
    @GetMapping("/{clientID}")
    public boolean isClientInBlacklist(@PathVariable Long clientID) {
        ClientBlacklist clientBlacklist = clientBlacklistService.getClientById(clientID);
        return clientBlacklist != null; // Si le client est présent, retourne true, sinon false
    }
    @GetMapping("/exists-by-prenom/{prenom}")
    public boolean isClientExistsByPrenom(@PathVariable String prenom) {
        return clientBlacklistService.isClientExistsByPrenom(prenom);
    }

    @Autowired
    private RestTemplate restTemplate;
    @PostMapping("/add-client-to-blacklist/{idClient}")
    public ClientBlacklist addClientToBlacklist(@PathVariable Long idClient) {
        // Make an internal API call to get the client by id
        String clientApiUrl = "http://localhost:8084/Client/" + idClient;
        // You can use RestTemplate or WebClient for making HTTP requests
        ResponseEntity<Client> responseEntity = restTemplate.getForEntity(clientApiUrl, Client.class);

        // Check if the client is retrieved successfully

            // Get the client from the response
            Client client = responseEntity.getBody();

            // Now, add the client to the blacklist
            ClientBlacklist clientBlacklist = new ClientBlacklist();
            // Set properties of clientBlacklist using properties of the retrieved client
            clientBlacklist.setClientID(client.getClientID());
            clientBlacklist.setNom(client.getNom());
            clientBlacklist.setPrenom(client.getPrenom());
            clientBlacklist.setCin(client.getCin());
            clientBlacklist.setBesoinAssurance(client.getBesoinAssurance());
            clientBlacklist.setClasseBonusMalus(client.getClasseBonusMalus());
            clientBlacklist.setDateNaissance(client.getDateNaissance());
            clientBlacklist.setTypeEmploi(client.getTypeEmploi());
            clientBlacklist.setRevenuMensuel(client.getRevenuMensuel());

            // Set other properties accordingly

            // Add the client to the blacklist
            return clientBlacklistService.addClient(clientBlacklist);

    }

    @PutMapping("/{clientID}")
    public ClientBlacklist updateClient(@PathVariable Long clientID, @RequestBody ClientBlacklist client) {
        return clientBlacklistService.updateClient(clientID, client);
    }

    @DeleteMapping("/{clientID}")
    public void deleteClient(@PathVariable Long clientID) {
        clientBlacklistService.deleteClient(clientID);
    }
}