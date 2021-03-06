package de.techdev.trackr.domain.project.invoice;

import de.techdev.trackr.domain.company.Company;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "identifier"))
public class Invoice {

    public enum InvoiceState {
        OUTSTANDING, PAID, OVERDUE
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Version
    private Integer version;

    @NotEmpty
    private String identifier;

    @NotNull
    @Temporal(TemporalType.DATE)
    private Date creationDate;

    @Min(0)
    private BigDecimal invoiceTotal;

    @ManyToOne
    @JoinColumn(name = "debitor")
    private Company debitor;

    @Temporal(TemporalType.DATE)
    private Date dueDate;

    @NotNull
    @Enumerated(EnumType.STRING)
    private InvoiceState invoiceState;
}
