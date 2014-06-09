package org.mongeez;

import com.mongodb.Mongo;
import com.mongodb.MongoClientURI;
import org.mongeez.dao.MongeezDao;
import org.mongeez.dao.RawMongeezDao;

import java.net.UnknownHostException;

/**
 * Created by tash on 6/9/14.
 */
public class RawChangeSetExecutor extends ChangeSetExecutor {

    public RawChangeSetExecutor(Mongo mongo, String dbName) {
        super(mongo, dbName);
    }

    public RawChangeSetExecutor(Mongo mongo, String dbName, MongoAuth auth) {
        super(mongo, dbName, auth);
    }

    public RawChangeSetExecutor(MongoClientURI mongo, String dbName, MongoAuth auth) {
        try{
            dao = new RawMongeezDao(mongo, dbName, auth);
        }catch (UnknownHostException ex){
            throw new RuntimeException(ex);
        }
    }
}
