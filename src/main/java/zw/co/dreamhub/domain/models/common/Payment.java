package zw.co.dreamhub.domain.models.common;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import zw.co.dreamhub.domain.models.BaseEntity;
import zw.co.dreamhub.domain.models.common.Amount;
import zw.co.dreamhub.domain.models.enums.PaymentStatus;
import zw.co.dreamhub.domain.models.ride.PaymentType;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "payment")
public class Payment extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "amount_id")
    private Amount amount;

    @ManyToOne
    @JoinColumn(name = "type_id")
    private PaymentType type;

    @Column(name = "account", columnDefinition = "TEXT")
    private String account;

    // todo : add migration

    @Column(name = "email")
    @Email
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private PaymentStatus status;

    @Column(name = "upstream_reference", columnDefinition = "TEXT", unique = true)
    private String upstreamReference;

    @Column(columnDefinition = "TEXT")
    private String payload;

    @Column(name = "upstream_response", columnDefinition = "TEXT")
    private String upstreamResponse;

    @Column(name = "upstream_status_response", columnDefinition = "TEXT")
    private String upstreamStatusResponse;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    public Payment(Amount amount, PaymentType type, String account, String email){
        this.amount = amount;
        this.type = type;
        this.account = account;
        this.email = email;
        this.status = PaymentStatus.PENDING_PROCESSING;
    }

    public void updatePayment(Amount amount, PaymentType type, String account, String email){
        this.amount = amount;
        this.type = type;
        this.account = account;
        this.email = email;
    }

}
