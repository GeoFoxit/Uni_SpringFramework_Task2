package george.dao;

import java.sql.SQLException;
import java.util.List;

public interface Dao<Entity,Key> {
    public abstract List<Entity> getAll();
    public abstract Entity getEntityById(Key id);
    public abstract void update(Entity entity);
    public abstract void delete(Key id);
    public abstract void insert(Entity entity);

}
