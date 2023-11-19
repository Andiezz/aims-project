package entity.cart;

import entity.db.AIMSDB;
import entity.media.DVD;
import entity.media.Media;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class CartTest {

    @Test
    void addCartMedia() {
        AIMSDB db = new AIMSDB();
        ArrayList<Media> media = db.media;

        Cart cart = new Cart();
        for (int i = 1; i < 3; i++) {
            CartMedia cartMedia = new CartMedia(media.get(i),cart,3,media.get(i).getPrice());
            cart.addCartMedia(cartMedia);
            assertTrue(cart.getListMedia().contains(cartMedia));
        }
    }
    @Test
    void removeCartMedia() {
        AIMSDB db = new AIMSDB();
        ArrayList<Media> media = db.media;

        Cart cart = new Cart();
        for (int i = 0; i < 3; i++) {
            CartMedia cartMedia = new CartMedia(media.get(i), cart, 2, media.get(i).getPrice());
            cart.addCartMedia(cartMedia);
        }

        CartMedia cartMedia = new CartMedia(media.get(1), cart, 2, media.get(1).getPrice());
        cart.removeCartMedia(cartMedia);
        assertFalse(cart.getListMedia().contains(cartMedia));
    }
    @Test
    void testGetTotalMedia() {
        AIMSDB db = new AIMSDB();
        ArrayList<Media> media = db.media;

        Cart cart = new Cart();
        for (int i = 0; i < 3; i++) {
            CartMedia cartMedia = new CartMedia(media.get(i), cart, 2, media.get(i).getPrice());
            cart.addCartMedia(cartMedia);
        }
        assertEquals(6, cart.getTotalMedia());
    }
    @Test
    void calSubtotal() {
        AIMSDB db = new AIMSDB();
        ArrayList<Media> media = db.media;

        Cart cart = new Cart();
        for (int i = 0; i < 3; i++) {
            CartMedia cartMedia = new CartMedia(media.get(i), cart, 2, media.get(i).getPrice());
            cart.addCartMedia(cartMedia);
        }
        assertEquals(2*450+2*300+2*100, cart.calSubtotal());
    }
    @Test
    void checkMediaInCart() {
        AIMSDB db = new AIMSDB();
        ArrayList<Media> media = db.media;
        Cart cart = new Cart();
        for (int i = 0; i < 3; i++) {
            CartMedia cartMedia = new CartMedia(media.get(i), cart, 2, media.get(i).getPrice());
            cart.addCartMedia(cartMedia);
        }
        CartMedia cartMedia = new CartMedia(media.get(1), cart, 2, media.get(1).getPrice());
        assertEquals(cartMedia.toString(),cart.checkMediaInCart(media.get(1)).toString());
        Media media3 = new DVD(4, "dvd", "dvd", 234, 20, "dvd", "ms", "B", 3232, "ks", "bc", "17/11/2023", "a");
        assertNull(cart.checkMediaInCart(media3));
    }

}