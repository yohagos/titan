package com.titan.transactions;

import com.titan.product.ProductEntity;
import com.titan.user.UserEntity;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.cglib.core.Local;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@Entity
public class TransactionEntity implements Serializable {

    @Id
    @GeneratedValue
    private Long id;
    private Double price;
    private Boolean withTip;
    private Double tip;
    private Boolean withCard;
    private String cardNumber;
    private Boolean paid;
    private LocalDateTime date;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<ProductEntity> products;

    @ManyToOne
    private UserEntity user;

    public TransactionEntity(
            Double price,
            Boolean withTip,
            Double tip,
            Boolean paid,
            UserEntity user,
            LocalDateTime date
    ) {
        this.price = price;
        this.withTip = withTip;
        this.tip = tip;
        this.paid = paid;
        this.user = user;
        this.date = date;
    }

    public TransactionEntity(
            Double price,
            Boolean withTip,
            Double tip,
            Boolean withCard,
            String cardNumber,
            Boolean paid,
            UserEntity user,
            LocalDateTime date
    ) {
        this.price = price;
        this.withTip = withTip;
        this.tip = tip;
        this.withCard = withCard;
        this.cardNumber = cardNumber;
        this.paid = paid;
        this.user = user;
        this.date = date;
    }

    TransactionEntity() {

    }

    public String toString() {
        return String.format(
                "Transaction=[id=%d, price=%.2f, withTip=%s, tip=%.2f, withCard=%s, cardNumber=%s, paid=%s]",
                getId(), getPrice(), getWithTip(), getTip(), getWithCard(), getCardNumber(), getPaid()
        );
    }
}
