package si.nimbostratuz.bikeshare.services.beans;

import lombok.extern.java.Log;
import si.nimbostratuz.bikeshare.models.entities.User;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.TypedQuery;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.NotFoundException;
import java.util.List;


@Log
@ApplicationScoped
public class UserBean extends  EntityBean<User> {
    public List<User> getAll() {

        TypedQuery<User> query = em.createNamedQuery("User.getAll", User.class);

        return query.getResultList();
    }

    public User get(Integer UserId) {

        User User = em.find(User.class, UserId);

        if (User == null) {
            throw new NotFoundException();
        }

        return User;
    }

    @Override
    public User create(User User) {
        try {
            beginTx();
            em.persist(User);
            commitTx();
        } catch (Exception e) {
            rollbackTx();
            log.throwing(User.class.getName(), "create", e);
            throw new BadRequestException();
        }
        return User;
    }

    @Override
    public User update(Integer id, User User) {

        User originalUser = this.get(id);
        boolean commit = true;

        try {
            if (em.getTransaction().isActive()){
                // If a transaction allready exists
                commit = false;
            } else {
                beginTx();
            }

            User.setId(originalUser.getId());
            User = em.merge(User);
            if (commit)
                commitTx();
        } catch (Exception e) {
            rollbackTx();
            log.throwing(UserBean.class.getName(), "update", e);
            throw new BadRequestException("User update failed.");
        }

        return User;
    }

    @Override
    public void delete(Integer id) {

        User User = this.get(id);

        try {
            beginTx();
            em.remove(User);
            commitTx();
        } catch (Exception e) {
            rollbackTx();
            log.throwing(UserBean.class.getName(), "delete", e);
        }
    }
}
