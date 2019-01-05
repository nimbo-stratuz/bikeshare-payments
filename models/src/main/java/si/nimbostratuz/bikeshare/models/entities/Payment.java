package si.nimbostratuz.bikeshare.models.entities;


import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity(name = "payment")
@Data
@NamedQueries(value = {
        @NamedQuery(
                name = "Payment.getAll",
                query = "SELECT p FROM payment p"
        )
})
public class Payment {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;

        // Payment sent from userID
        @Column(name = "from_user_id", nullable = false)
        private Integer fromUserId;

        // Payment sent to userID.
        @Column(name = "to_user_id", nullable = false)
        private Integer toUserId;

        @Column(nullable = false)
        @Temporal(TemporalType.TIMESTAMP)
        private Date date;

        @Column(name = "ride_id", nullable = false)
        private Integer rideId;

        //  Payment amount
        @Column(nullable = false, precision = 10, scale = 2)
        private BigDecimal amount;
}
