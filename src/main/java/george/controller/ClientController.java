package george.controller;

import george.dao.ClientDao;
import george.models.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

    @Autowired
    ClientDao clientDao;

    @GetMapping("")
    public List<Client> getClients(@RequestParam(value = "id", required = false) Integer id) {
        if (id == null) {
            return clientDao.getAll();
        } else {
            List<Client> clients = new ArrayList<Client>();
            try {
                Client client = clientDao.getEntityById(id);
                clients.add(client);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return clients;
        }

    }

    @PostMapping("")
    public Client addClient(@RequestParam(value = "id", required = true) int id,
                      @RequestParam(value = "name", required = true) String name,
                      @RequestParam(value = "phoneNumber", required = true) String phoneNumber){
        Client client = new Client();
        client.setId(id);
        client.setName(name);
        client.setPhoneNumber(phoneNumber);
        clientDao.insert(client);
        return client;
    }

    @DeleteMapping("")
    public Client removeClient(@RequestParam(value = "id", required = true) Integer id) {
        try {
            Client client = clientDao.getEntityById(id);
            clientDao.delete(id);
            return client;
        } catch (Exception e) {
            e.printStackTrace();
            return new Client();
        }
    }

    @PatchMapping("")
    public Client updateCar(@RequestParam(value = "id", required = true) int id,
                         @RequestParam(value = "name", required = true) String name,
                         @RequestParam(value = "phoneNumber", required = true) String phoneNumber){
        try {
            Client client = clientDao.getEntityById(id);
            client.setName(name);
            client.setPhoneNumber(phoneNumber);
            clientDao.update(client);
            return client;
        } catch (Exception e) {
            e.printStackTrace();
            return new Client();
        }
    }
}
