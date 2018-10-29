package si.nimbostratuz.bikeshare.services.beans;

import lombok.extern.java.Log;
import si.nimbostratuz.bikeshare.models.entities.Payment;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.TypedQuery;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.NotFoundException;
import java.time.Instant;
import java.util.Date;
import java.util.List;


@Log
@ApplicationScoped
public class PaymentBean extends EntityBean<Payment> {

    public List<Payment> getAll() {

        TypedQuery<Payment> query = em.createNamedQuery("Payment.getAll", Payment.class);

        return query.getResultList();
    }

    public Payment get(Integer paymentId) {

        Payment payment = em.find(Payment.class, paymentId);

        if (payment == null) {
            throw new NotFoundException();
        }

        return payment;
    }

    @Override
    public Payment create(Payment payment) {
        try {
            beginTx();

            // TODO - subtract and add amount to specific User' accounts?

            payment.setDate(Date.from(Instant.now()));

            em.persist(payment);

            commitTx();
        } catch (Exception e) {
            rollbackTx();
            log.throwing(Payment.class.getName(), "create", e);
            throw new BadRequestException();
        }
        return payment;
    }

    @Override
    public Payment update(Integer id, Payment payment) {

        Payment originalPayment = this.get(id);

        try {
            beginTx();
            payment.setId(originalPayment.getId());
            payment = em.merge(payment);
            commitTx();
        } catch (Exception e) {
            rollbackTx();
            log.throwing(PaymentBean.class.getName(), "update", e);
        }

        return payment;
    }

    @Override
    public void delete(Integer id) {

        Payment payment = this.get(id);

        try {
            beginTx();
            em.remove(payment);
            commitTx();
        } catch (Exception e) {
            rollbackTx();
            log.throwing(PaymentBean.class.getName(), "delete", e);
        }
    }

}
