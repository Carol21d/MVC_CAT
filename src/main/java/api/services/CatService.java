package api.services;

import java.io.BufferedReader;
import java.util.List;

import com.google.gson.Gson;

import api.contracts.InterfaceService;
import api.models.Cat;
import api.payloads.CatPayload;

public class CatService implements InterfaceService {
    
    private Cat cat;
    private Gson gson;//declramos para usarlo
    // declaramos el constructor y lo instaciamos
    public CatService() {
        this.cat = new Cat();
        this.gson = new Gson();
    }

    public List<Object> index() {
        return cat.index();
    }

    @Override
    public Object store(BufferedReader body) {

        try {
            CatPayload catPayload = gson.fromJson(body, CatPayload.class);
            CatPayload catAdded = cat.save(catPayload);
            return catAdded;
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }

    }

    

}
