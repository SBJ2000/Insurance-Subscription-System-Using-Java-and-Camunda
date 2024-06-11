package Client.controller;

import Client.model.Client;
import Client.model.ClientBlacklist;
import Client.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/Client")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping
    public List<Client> getAllClients() {
        return clientService.getAllClients();
    }

    @GetMapping("/{id}")
    public Client getClientById(@PathVariable Long id) {
        return clientService.getClientById(id);
    }

    @PostMapping("/add-client/{nom}/{prenom}/{cin}/{revenuMensuel}/{typeEmploi}/{classeBonusMalus}/{besoinAssurance}")
    public Client addClient(@PathVariable String nom,
                            @PathVariable String prenom,
                            @PathVariable String cin,
                            @PathVariable double revenuMensuel,
                            @PathVariable String typeEmploi,
                            @PathVariable int classeBonusMalus,
                            @PathVariable String besoinAssurance

                            ) {
                // Use the current system date
                Date dateNaissance = new Date();
                // Create a new Client object with the provided information
                Client client = new Client(nom, prenom, cin, revenuMensuel, typeEmploi, dateNaissance, classeBonusMalus, besoinAssurance);

        // Call the service method to add the client
        return clientService.addClient(client);
    }
    @GetMapping("prenom/{id}")
    public ResponseEntity<String> getClientPrenomById(@PathVariable Long id) {
        // Call the service to get the client by ID
        Client client = clientService.getClientById(id);

        // Check if the client is found
        if (client != null) {
            // Return the first name in the response body
            return new ResponseEntity<>(client.getPrenom(), HttpStatus.OK);
        } else {
            // If client not found, return 404 Not Found
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public Client updateClient(@PathVariable Long id, @RequestBody Client client) {
        return clientService.updateClient(id, client);
    }

    @DeleteMapping("/{id}")
    public void deleteClient(@PathVariable Long id) {
        clientService.deleteClient(id);
    }

    @GetMapping("besoin-assurance/{id}")
    public String getBesoinAssuranceById(@PathVariable Long id) {
        Client client = clientService.getClientById(id);
        return client.getBesoinAssurance();
    }
    @PostMapping("/add-to-blacklist/{clientId}")
    public Client addToBlacklist(@PathVariable Long clientId) {
        return clientService.addToBlacklist(clientId);
    }
    @GetMapping("/{id}/revenu-mensuel")
    public Double getRevenuMensuelById(@PathVariable Long id) {
        Client client = clientService.getClientById(id);
            return client.getRevenuMensuel();
    }
    @GetMapping("classe-bonus-malus/{clientID}")
    public int getClasseBonusMalusByClientId(@PathVariable Long clientID) {
        return clientService.getClasseBonusMalusByClientId(clientID);
    }
}
