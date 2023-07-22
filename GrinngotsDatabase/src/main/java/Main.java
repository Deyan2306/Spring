import javax.persistence.Persistence;

public class Main {
    public static void main(String[] args) {

        // Create the entity manager
        Persistence.createEntityManagerFactory("gringotts")
                .createEntityManager();

    }
}
