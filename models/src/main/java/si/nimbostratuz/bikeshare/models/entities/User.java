package si.nimbostratuz.bikeshare.models.entities;


import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity(name = "user")
@Data
@NamedQueries(value = {
        @NamedQuery(
                name = "User.getAll",
                query = "SELECT u FROM user u"
        )
})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(nullable = false)
    private char password;

    // If the user is only using the service - false, if he is also renting a bicycle - true
    @Column(name = "is_renting", nullable = false)
    private boolean isRenting;

    @Column(nullable = false)
    private BigDecimal funds;

}
