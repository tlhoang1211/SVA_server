package com.example.server.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Calendar;
import java.util.UUID;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Credential {
    @Id
    private String tokenKey;
    private long createdAtMLS;
    private long expiredMLS;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "accountId", nullable = false, referencedColumnName = "id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Account account;

    @Column(insertable = false, updatable = false)
    private int accountId;

    public Credential(Account account) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 7);
        this.tokenKey = UUID.randomUUID().toString();
        this.createdAtMLS = Calendar.getInstance().getTimeInMillis();
        this.expiredMLS = calendar.getTimeInMillis();
        this.account = account;
    }

    public boolean isExpired() {
        return this.expiredMLS < Calendar.getInstance().getTimeInMillis();
    }
}