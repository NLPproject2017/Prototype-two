
package Model;

/**
 *
 * @author Henrietta
 */
public class EntityObject {

    private String entity;
    private String entityType;

    public EntityObject() {

    }

    public void setEntity(String entity) {
        this.entity = entity;
    }

    public void setEntityType(String entityType) {
        this.entityType = entityType;
    }

    public String getEntityType() {
        return entityType;
    }

    public String getEntity() {
        return entity;
    }
}
