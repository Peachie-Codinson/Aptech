package org.example.aptechproject.repo;

import org.example.aptechproject.model.Cart;
import org.example.aptechproject.model.CartId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, CartId> {
}
