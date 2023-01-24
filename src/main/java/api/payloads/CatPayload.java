package api.payloads;

public class CatPayload {
    // siempre tiene que haber un payload donde solo habra getter y setter y constructor para acceder a ello  y que no hya conflicto
    private Long id;
    private String name;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

}