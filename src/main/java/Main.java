import com.hibirnate.entity.Cart;
import com.hibirnate.entity.Items;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.HashSet;
import java.util.Set;


public class Main {
    public static void main(String[] args) {
        Cart cart1 = new Cart();
        Cart cart2 = new Cart();

        cart1.setUsername("Alex");
        cart2.setUsername("Emma");

        Items item1 = new Items(cart1, "Iphone x");
        Items item2 = new Items(cart2, "MacBook Air");

        Set<Items> itemsSet = new HashSet<Items>();

        itemsSet.add(item1);
        cart1.setItems(itemsSet);

        itemsSet.add(item2);
        cart1.setItems(itemsSet);

        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction tx = null;
        try {
            // Get Session
            sessionFactory = HibernateAnnotationUtil.getSessionFactory();
            session = sessionFactory.getCurrentSession();
            System.out.println("Session created");
            // start transaction
            tx = session.beginTransaction();
            // Save the Model object
            session.save(cart1);
            session.save(cart2);
            session.save(item1);
            session.save(item2);
            // Commit transaction
            tx.commit();
            System.out.println("Cart1 ID=" + cart1.getId() + "Name " + cart1.getUsername());
            System.out.println("Cart2 ID=" + cart2.getId() + "Name " + cart2.getUsername());
            System.out.println("item1 ID=" + item1.getId() + ", Foreign Key Cart ID=" + item1.getCart().getId());
            System.out.println("item2 ID=" + item2.getId() + ", Foreign Key Cart ID=" + item1.getCart().getId());

        } catch (Exception e) {
            System.out.println("Exception occured. " + e.getMessage());
            e.printStackTrace();
        } finally {
            assert sessionFactory != null;
            sessionFactory.close();
        }

    }
}
