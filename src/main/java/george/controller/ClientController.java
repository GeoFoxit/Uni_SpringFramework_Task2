package george.controller;

import george.repositories.ClientRepository;
import george.models.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

    @Autowired
    ClientRepository clientRepo;

    @GetMapping("")
    public Iterable<Client> getClients(@RequestParam(value = "id", required = false) Integer id) {
        if (id == null) { return clientRepo.findAll(); }
        List<Client> clients = new ArrayList<Client>();
        Optional<Client> optionalClient= clientRepo.findById(id);
        if (optionalClient.isPresent()) { clients.add(optionalClient.get()); }
        return clients;
    }

    @PostMapping("")
    public Client addClient(@RequestParam(value = "name", required = true) String name,
                      @RequestParam(value = "phoneNumber", required = true) String phoneNumber){
        Client client = new Client(name,phoneNumber);
        clientRepo.save(client);
        return client;
    }

    @DeleteMapping("")
    public String removeClient(@RequestParam(value = "id", required = true) Integer id) {
        clientRepo.deleteById(id);
        return "Deleted";
    }

    @PatchMapping("")
    public String updateCar(@RequestParam(value = "id", required = true) int id,
                         @RequestParam(value = "name", required = false) String name,
                         @RequestParam(value = "phoneNumber", required = false) String phoneNumber) {
        Optional<Client> optionalClient= clientRepo.findById(id);
        if (optionalClient.isPresent() && (name != null || phoneNumber != null)) {
            Client client = optionalClient.get();
            if (name != null) { client.setName(name); }
            if (phoneNumber != null) { client.setPhoneNumber(phoneNumber); }
            clientRepo.save(client);
            return "Updated";
        }
        return "Unable to update";
    }
}
