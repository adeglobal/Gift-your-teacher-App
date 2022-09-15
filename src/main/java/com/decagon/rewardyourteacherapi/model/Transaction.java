package com.decagon.rewardyourteacherapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "transaction")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @OneToOne
    @JoinColumn(name = "sender_id")
    private Users sender;
    @OneToOne
    @JoinColumn(name = "recipient_id")
    private Users recipient;
    private BigDecimal amount;

    public Transaction(Users sender, Users recipient, BigDecimal amount) {
        this.sender = sender;
        this.recipient = recipient;
        this.amount = amount;
    }

    public Transaction(long id, Users sender, Users recipient, BigDecimal amount) {
        this.id = id;
        this.sender = sender;
        this.recipient = recipient;
        this.amount = amount;
    }
}
