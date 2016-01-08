package com.sebn.gsd.aptgrade.core.database;

import java.lang.reflect.InvocationTargetException;
import org.neo4j.graphdb.Node;

/**
 *
 * @author Ryppo
 */
public class ModelFactory {
    
    private Class modelClass = DefaultModel.class;

    public void setModelClass(Class modelClass) {
        this.modelClass = modelClass;
    }
    
    public ModelFactory(Class _modelClass) {
        modelClass = _modelClass;
    }
    
    public ModelFactory() {
    }
    
    public Class getModelClass() {
        return modelClass;
    }
    
    public String getModelType() {
        return modelClass.getSimpleName();
    }
    
    public Model createModelFromNode(Node node) throws DatabaseException  {
        try {
            return (Model) Class.forName(modelClass.getName()).getConstructor(Node.class).newInstance(node);
        } catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            throw new DatabaseException(ex);
        }
    }
    
    public Model castFromAnonymousModel(Model model) {
        return (Model) modelClass.cast(model);
    }
    
}
